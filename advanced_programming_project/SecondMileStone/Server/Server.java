package SecondMileStone.Server;

import SecondMileStone.Game.Brick;
import SecondMileStone.Game.Phases;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Server {
    private int uniqueId;
    private final ArrayList<ClientThread> clientThreads = new ArrayList<>();
    private final ArrayList<Phases> phasesList = new ArrayList<>();
    private ArrayList<String> lobbyList = new ArrayList<>(),
            waitingList = new ArrayList<>(),
            inGameList = new ArrayList<>();



    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/LoggedInList.txt"))){
            writer.flush();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }

        Server server = new Server();
        server.start();
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(4444)){
            while (true) {
                Socket client = server.accept();
                ClientThread thread = new ClientThread(client);
                clientThreads.add(thread);
                thread.start();
            }
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }



    public void addToRegisteredFile(String username, String password) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/AccountList.txt", true))) {
            fileWriter.write(username + "\n" + password + "\n\n");
            fileWriter.flush();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }

    public boolean foundInRegisteredFile(String username, String password) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/AccountList.txt"))){
            String name, next;

            do {
                name = fileReader.readLine();
            } while (name != null && !Objects.equals(name, username));
            if (Objects.equals(name, username) && !Objects.equals(next = fileReader.readLine(), "")) {
                if (password == null)
                    return true;
                else if (Objects.equals(next, password))
                    return true;
            }
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
        return false;
    }

    public void addToLoginFile(String username) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/LoggedInList.txt", true))) {
            writer.write(username + "\n");
            writer.flush();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }

    public boolean foundInLoginFile(String username) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/LoggedInList.txt"))){
            String read = fileReader.readLine();

            while (read!= null && !read.equals(username))
                read = fileReader.readLine();

            return read != null;
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
        return false;
    }

    public void removeFromLoginFile(String username) {
        ArrayList<String> lines = new ArrayList<>();
        String read;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/LoggedInList.txt"))) {
            while ((read = reader.readLine()) != null)
                lines.add(read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/LoggedInList.txt"))) {
            for (String line : lines)
                if (!line.equals(username))
                    writer.write(line + "\n");
            writer.flush();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }



    private void sentToEveryone(String text) {
        for (ClientThread clientThread : clientThreads)
            clientThread.write(text);
    }

    private void sentTo(String username, String text) {
        for (ClientThread clientThread : clientThreads) {
            if (clientThread.getUsername().equals(username))
                clientThread.write(text);
        }
    }



    public class ClientThread extends Thread{
        final Socket client;
        BufferedReader reader;
        PrintWriter writer;
        String username, enemy;
        int gameId;
        Phases phase;

        public ClientThread(Socket socket) {
            this.client = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            write("LOGIN_BUILD");

            String fromClient;
            try {
                while (true) {
                    fromClient = reader.readLine();
                    System.out.println(fromClient);
                    if (fromClient.startsWith("LOGIN")) login(fromClient.replace("LOGIN_", ""));
                    else if (fromClient.startsWith("MENU")) menu(fromClient.replace("MENU_", ""));
                    else if (fromClient.startsWith("GAME")) game(Integer.parseInt(fromClient.replace("GAME_", "")));
                    else if (fromClient.startsWith("MOVE")) {
                        inGameList.remove(username);
                        waitingList.remove(username);
                        lobbyList.add(username);
                        sentToEveryone(fromClient.replace("MOVE_", ""));
                    }
                    else if (fromClient.startsWith("QUIT")) sentTo(enemy, "GAME_END_FALSE_QUIT");
                    else if (fromClient.startsWith("LOGOUT")) {
                        sentToEveryone("LOGOUT_" + fromClient);
                        removeFromLoginFile(fromClient.replace("LOGOUT_", ""));
                    }
                }
            }
            catch (IOException e) {
                try {this.close();}
                catch (IOException ex) {ex.printStackTrace();}
                clientThreads.remove(this);
                e.printStackTrace();
            }
        }



        private void login(String fromClient) {
            String register, username, password;
            String[] split;

            fromClient = fromClient.replace("FIRST_", "");
            split = fromClient.split("USERNAME_");
            register = split[0];
            split = split[1].split("PASSWORD_");
            username = split[0];
            password = split[1];

            if (Objects.equals(register, "true")) {
                if (!foundInRegisteredFile(username, null)) {
                    addToRegisteredFile(username, password);
                    letIn(username);
                }
                else write("LOGIN_AGAIN_REGISTER");
            }
            else {
                if (foundInRegisteredFile(username, password) && !foundInLoginFile(username))
                    letIn(username);
                else write("LOGIN_AGAIN_LOGIN");
            }
        }

        private void letIn(String username) {
            this.username = username;
            lobbyList.add(this.username);
            if (clientThreads.size() > 0)
                for (String lobby : lobbyList)
                    if (lobby != null)
                        write("MENU_LIST_LOBBY_ADD_" + lobby);
                for (String waiting : waitingList)
                    if (waiting != null)
                        write("MENU_LIST_WAITING_ADD_" + waiting);
                for (String inGame : inGameList)
                    if (inGame != null)
                        write("MENU_LIST_INGAME_ADD_" + inGame);
            addToLoginFile(this.username);
            write("LOGIN_SUCCESSFUL");
            write("MENU_BUILD");
        }



        private void menu(String fromClient) {
            if (fromClient.startsWith("OPEN")) togglesOpen();
            else if (fromClient.startsWith("JOIN")) toggledJoin(fromClient.replace("JOIN_", ""));
        }

        private void togglesOpen() {
            lobbyList.remove(username);
            waitingList.add(username);
            sentToEveryone("MENU_LIST_LOBBY_REMOVE_" + username);
            sentToEveryone("MENU_LIST_WAITING_ADD_" + username);
            write("GAME_BUILD");
            write("GAME_WAIT");
        }

        private void toggledJoin(String fromServer) {
            enemy = fromServer;
            final int startColor = new Random().nextInt(2)== 0 ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
            this.gameId = ++uniqueId;
            phasesList.add(new Phases(gameId, startColor));

            for (ClientThread clientThread : clientThreads)
                if (clientThread.getUsername().equals(fromServer)) {
                    clientThread.setEnemy(username);
                    clientThread.setGameId(gameId);
                }

            waitingList.remove(fromServer);
            inGameList.add(fromServer);
            lobbyList.remove(username);
            inGameList.add(username);

            sentToEveryone("MENU_LIST_WAITING_REMOVE_" + fromServer);
            sentToEveryone("MENU_LIST_INGAME_ADD_" + fromServer);
            sentToEveryone("MENU_LIST_LOBBY_REMOVE_" + username);
            sentToEveryone("MENU_LIST_INGAME_ADD_" + username);

            write("GAME_BUILD");
            write("GAME_"
                        + "START_"
                        + "SCHWARZ_"
                        + (startColor == Color.BLACK.getRGB() ? "SCHWARZ" : "WEISS"));

            sentTo(enemy, "GAME_"
                    + "START_"
                    + "WEISS_"
                    + (startColor == Color.BLACK.getRGB() ? "SCHWARZ" : "WEISS"));
            System.out.println("geschafft");
        }




        private void game(int fromClient) {
            for (Phases phases : phasesList) {
                if (phases.getGameId() == gameId)
                    this.phase = phases;
            }
            phase.check(fromClient);
            System.out.println();
            System.out.println(phase.turn);
            System.out.println(phase.phase);
            System.out.println(phase.moveCount);
            System.out.println(phase.mill);
            System.out.println(phase.selected);
            System.out.println("Mein" + phase.defaultColor.getRGB());
            for (Brick brick : phase.bricks)
                System.out.println(brick.getBackground().getRGB());
            System.out.println();
            if (phase.successful()) {
                String nextStep = phase.getNextStep(),
                        fieldToColor = phase.getAllFields(),
                        colorChange = String.valueOf(phase.getColorChange());

                switch (nextStep) {
                    case "SET", "TAKE", "END"-> {
                        write("GAME_" + nextStep + "_FALSE_" + fieldToColor + "_" + colorChange);
                        sentTo(enemy, "GAME_" + nextStep + "_TRUE_" + fieldToColor + "_" + colorChange);
                        if (nextStep.equals("END"))
                            phasesList.remove(phase);
                    }
                    case "PLACE" -> {
                        write("GAME_" + nextStep + "_TRUE_" + fieldToColor);
                        sentTo(enemy, "GAME_" + nextStep + "_FALSE_");
                    }
                    case "DELETE" -> {
                        write("GAME_DELETE_TRUE_" + fieldToColor + "_" + colorChange);
                        sentTo(enemy, "GAME_DELETE_FALSE_" + fieldToColor + "_" + colorChange);
                    }
                    case "AGAIN" -> {
                        write("GAME_" + nextStep + "_TRUE_" + fieldToColor + "_" + colorChange);
                        sentTo(enemy, "GAME_" + nextStep + "_FALSE_");
                    }
                }
            }
        }



        private void close() throws IOException {
            if (writer != null) writer.close();
            if (reader != null) reader.close();
            if (client != null) client.close();
        }

        public void write(String toClient) {
            writer.println(toClient);
            writer.flush();
        }

        public String getUsername() {return username;}

        public void setEnemy(String enemy) {this.enemy = enemy;}

        public void setGameId(int gameId) {this.gameId = gameId;}
    }
}
