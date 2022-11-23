package SecondMileStone.Server;

import SecondMileStone.Game.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Client {
    static ClientGUI clientGUI;

    PrintWriter writer;
    BufferedReader reader;

    Brick[] bricks;

    final Color defaultColor = new Color(240, 240, 240);

    static boolean canWrite = true;

    int last;

    public String playerColor, username, gegner;

    DefaultListModel<String> lobbyList = new DefaultListModel<>(),
            waitingList = new DefaultListModel<>(),
            inGameList = new DefaultListModel<>();



    public static void main(String[] args) {
        Client client = new Client();
        clientGUI = new ClientGUI(client);
        client.start();
    }

    public void start() {
        try (
                final Socket client = new Socket("localhost", 4444)
        ) {
            System.err.println("connected to server!");
            talkToServer(client);
            System.err.println("disconnected from server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void talkToServer(final Socket client) throws IOException {
        String fromServer;
        try (
                final PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8));
                final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8))
        ) {
            this.writer = writer;
            this.reader = reader;
            while (true) {
                fromServer = reader.readLine();
                if (fromServer.startsWith("LOGIN")) login(writer, fromServer.replace("LOGIN_", ""));
                else if (fromServer.startsWith("MENU")) menu(writer, fromServer.replace("MENU_", ""));
                else if (fromServer.startsWith("GAME")) game(writer, fromServer.replace("GAME_", ""));
                else if (fromServer.startsWith("BACK_TO_LOBBY"))
                    moveFromInGameToLobby(fromServer.replace("BACK_TO_LOBBY_", ""));
                else if (fromServer.startsWith("LOGOUT")) logout(fromServer.replace("LOGOUT_", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sentToServer(String toServer) {
        writer.println(toServer + username);
        writer.flush();
    }



    public void removeEnemy() {gegner = null;}

    public boolean gotEnemy() {return gegner == null;}



    public void setCanWrite() {canWrite = true;}



    private void buildLogin(PrintWriter writer) {
        canWrite = true;
        JButton anmelden = new JButton("Anmelden"),
                registrieren = new JButton("Registrieren");

        anmelden.addActionListener(e -> {
            if (clientGUI.loginPossible() && canWrite) {
                writer.println("LOGIN_"
                        + "FIRST_" + false
                        + "USERNAME_" + clientGUI.getUsername()
                        + "PASSWORD_" + clientGUI.getPassword());
                writer.flush();
                canWrite = false;
            }
        });
        registrieren.addActionListener(e -> {
            if (clientGUI.loginPossible() && canWrite) {
                writer.println("LOGIN_"
                        + "FIRST_" + true
                        + "USERNAME_" + clientGUI.getUsername()
                        + "PASSWORD_" + clientGUI.getPassword());
                writer.flush();
                canWrite = false;
            }
        });

        clientGUI.buildLogin(anmelden, registrieren);
        clientGUI.setVisible(true);
    }

    private void login(PrintWriter writer, String fromServer) {
        if (fromServer.startsWith("BUILD"))
            buildLogin(writer);
        if (fromServer.startsWith("SUCCESSFUL")) {
            clientGUI.unloadLogin();
            clientGUI.setSize(767, 790);
            username = clientGUI.username;
        }
        if (fromServer.startsWith("AGAIN")) {
            canWrite = true;
            fromServer = fromServer.replace("AGAIN_", "");
            if (fromServer.startsWith("LOGIN"))
                clientGUI.setTitle("falsch Eingabe");
            if (fromServer.startsWith("REGISTER"))
                clientGUI.setTitle("schon vergeben");
        }
    }

    public void logout(String username) {
        if (lobbyList.contains(username)) {
            lobbyList.removeElement(username);
            clientGUI.updateLobby(username, false);
        }
        else if (waitingList.contains(username)) {
            waitingList.removeElement(username);
            clientGUI.updateWaiting(username, false);
        }
        else if (inGameList.contains(username)) {
            inGameList.removeElement(username);
            clientGUI.updateInGame(username, false);
        }
    }



    private void menu(PrintWriter writer, String fromServer) {
        if (fromServer.startsWith("BUILD"))
            buildMenu(writer);
        else if (fromServer.startsWith("AGAIN"))
            canWrite = true;
        else if (fromServer.startsWith("LIST")) {
            fromServer = fromServer.replace("LIST_", "");
            list(fromServer);
        }
    }

    private void buildMenu(PrintWriter writer) {
        canWrite = true;

        JButton start = new JButton("OPEN"),
                join = new JButton("JOIN");

        start.addActionListener(e -> {
            if (canWrite) {
                writer.println("MENU_OPEN");
                writer.flush();
                canWrite = false;
            }
        });
        join.addActionListener(e -> {
            if (canWrite) {
                gegner = clientGUI.getGegner();
                clientGUI.joinDisable();
                writer.println("MENU_JOIN_" + gegner);
                writer.flush();
                canWrite = false;
            }
        });

        clientGUI.buildMenu(start, join);
    }

    private void list(String fromServer) {
        clientGUI.joinDisable();
        clientGUI.setWaitingUnselected();

        if (fromServer.startsWith("LOBBY")) {
            fromServer = fromServer.replace("LOBBY_", "");
            if (fromServer.startsWith("ADD")) {
                fromServer = fromServer.replace("ADD_", "");
                if (!lobbyList.contains(fromServer)) {
                    lobbyList.addElement(fromServer);
                    clientGUI.updateLobby(fromServer, true);
                }
            }
            else if (fromServer.startsWith("REMOVE")) {
                fromServer = fromServer.replace("REMOVE_", "");
                if (lobbyList.contains(fromServer)) {
                    lobbyList.removeElement(fromServer);
                    clientGUI.updateLobby(fromServer, false);
                }
            }
        }

        else if (fromServer.startsWith("WAITING")) {
            fromServer = fromServer.replace("WAITING_", "");
            if (fromServer.startsWith("ADD")) {
                fromServer = fromServer.replace("ADD_", "");
                if (!waitingList.contains(fromServer)) {
                    waitingList.addElement(fromServer);
                    clientGUI.updateWaiting(fromServer, true);
                }
            }
            else if (fromServer.startsWith("REMOVE")) {
                fromServer = fromServer.replace("REMOVE_", "");
                if (waitingList.contains(fromServer)) {
                    waitingList.removeElement(fromServer);
                    clientGUI.updateWaiting(fromServer, false);
                }
            }
        }

        else if (fromServer.startsWith("INGAME")) {
            fromServer = fromServer.replace("INGAME_", "");
            if (fromServer.startsWith("ADD")) {
                fromServer = fromServer.replace("ADD_", "");
                if (!inGameList.contains(fromServer)) {
                    inGameList.addElement(fromServer);
                    clientGUI.updateInGame(fromServer, true);
                }
            }
            else if (fromServer.startsWith("REMOVE")) {
                fromServer = fromServer.replace("REMOVE_", "");
                if (inGameList.contains(fromServer)) {
                    inGameList.removeElement(fromServer);
                    clientGUI.updateInGame(fromServer, false);
                }
            }
        }
        clientGUI.setVisible(true);
    }

    public void moveFromInGameToLobby(String username) {
        if (inGameList.contains(username)) {
            inGameList.removeElement(username);
            clientGUI.updateInGame(username, false);
        }
        else {
            waitingList.removeElement(username);
            clientGUI.updateWaiting(username, false);
        }
        lobbyList.addElement(username);
        clientGUI.updateLobby(username, true);
    }



    private void game(PrintWriter writer, String fromServer) {
        String[] split;
        if (fromServer.startsWith("BUILD")) buildGame(writer);
        else if (fromServer.startsWith("WAIT")) {
            clientGUI.setTitle("Du wartest auf einen Gegner");
            clientGUI.setTurnText("", "Auf Gegner warten.", "");
        }
        else if (fromServer.startsWith("START")) {
            fromServer = fromServer.replace("START_", "");

            split = fromServer.split("_");

            playerColor = split[0];
            clientGUI.setTitle("Du bist " + playerColor);

            if (Objects.equals(split[1], playerColor)) {
                clientGUI.setTurnText("Du bist", playerColor, "und beginnst!");
                canWrite = true;
            }
            else clientGUI.setTurnText("Du bist", playerColor + "." , split[1] + " beginnt!");
        }
        else if (fromServer.startsWith("SET")) {
            fromServer = fromServer.replace("SET_", "");

            if (fromServer.startsWith("TRUE")) {
                fromServer = fromServer.replace("TRUE_", "");
                split = fromServer.split("_");

                canWrite = true;
                clientGUI.setTurnText("Du musst", "ein Stein", "setzen");
                bricks[Integer.parseInt(split[0])].setBackground(new Color(Integer.parseInt(split[1])));
            } else {
                fromServer = fromServer.replace("FALSE_", "");
                split = fromServer.split("_");


                clientGUI.setTurnText("Der Gegner", "muss einen", "Stein setzen.");
                bricks[Integer.parseInt(split[0])].setBackground(new Color(Integer.parseInt(split[1])));
            }
        }
        else if (fromServer.startsWith("TAKE")) {
            fromServer = fromServer.replace("TAKE_", "");
            if (fromServer.startsWith("TRUE")) {
                fromServer = fromServer.replace("TRUE_", "");
                canWrite = true;
                clientGUI.setTurnText("Du musst", "ein Stein", "nehmen.");
            } else {
                fromServer = fromServer.replace("FALSE_", "");
                clientGUI.setTurnText("Der Gegner", "muss einen", "Stein nehmen.");
            }

            colorInTakeAndDeleteAndEnd(fromServer);
        }
        else if (fromServer.startsWith("PLACE")) {
            fromServer = fromServer.replace("PLACE_", "");

            if (fromServer.startsWith("TRUE")) {
                fromServer = fromServer.replace("TRUE_", "");
                canWrite = true;

                clientGUI.setTurnText("Du musst", "deinen Stein", "platzieren.");

                if (fromServer.startsWith("NEIGHBORS"))
                    colorNeighbors(fromServer.replace("NEIGHBORS_", ""));
                else colorAllFree(fromServer.replace("ALLFREE_", ""));
            } else clientGUI.setTurnText("Der Gegner", "muss seinen", "Stein setzen.");
        }
        else if (fromServer.startsWith("DELETE")) {
            fromServer = fromServer.replace("DELETE_", "");

            if (fromServer.startsWith("TRUE")) {
                fromServer = fromServer.replace("TRUE_", "");

                canWrite = true;
                clientGUI.setTurnText("Du musst", "ein Stein", "entfernen.");
            } else {
                fromServer = fromServer.replace("FALSE_", "");

                clientGUI.setTurnText("Der Gegner", "muss einen", "Stein entfernen.");
            }

            colorInTakeAndDeleteAndEnd(fromServer);
        }
        else if (fromServer.startsWith("AGAIN")) {
            fromServer = fromServer.replace("AGAIN_", "");

            if (fromServer.startsWith("TRUE")) {

                canWrite = true;
                clientGUI.setTurnText("Probier", "es bitte", "noch einmal");

                if (!fromServer.startsWith("NOTHING")) {
                    split = fromServer.split("_");
                    recolorAllGreen();
                    bricks[Integer.parseInt(split[0])].setBackground(Color.decode(split[1]));
                }
            }
        }
        else if (fromServer.startsWith("END")) {
            fromServer = fromServer.replace("END_", "");

            if (fromServer.startsWith("TRUE")) {
                fromServer = fromServer.replace("TRUE_", "");

                clientGUI.setTurnText("Das Spiel", "ist vorbei, du", "hast VERLOREN!");
            }
            else {
                fromServer = fromServer.replace("FALSE_", "");

                clientGUI.setTurnText("Das Spiel", "ist vorbei, du", "hast GEWONNEN!");
            }

            if (!fromServer.startsWith("QUIT"))
                colorInTakeAndDeleteAndEnd(fromServer);
            else canWrite = false;
            gegner = "";
        }
    }

    private void buildGame(PrintWriter writer) {
        clientGUI.unloadMenu();
        bricks = createBricks();
        for (int i = 0; i < 24; i++) {
            int finalI = i;
            bricks[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (canWrite) {
                        last = finalI;
                        writer.println("GAME_" + finalI);
                        writer.flush();
                        canWrite = false;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }
        clientGUI.buildBoard(bricks);
    }

    private Brick[] createBricks() {
        Brick[] list = new Brick[24];
        list[0] = new Brick(5, 5);
        list[1] = new Brick(355, 5);
        list[2] = new Brick(705, 5);

        list[3] = new Brick(105, 105);
        list[4] = new Brick(355, 105);
        list[5] = new Brick(605, 105);

        list[6] = new Brick(205, 205);
        list[7] = new Brick(355, 205);
        list[8] = new Brick(505, 205);

        list[9] = new Brick(5, 355);
        list[10] = new Brick(105, 355);
        list[11] = new Brick(205, 355);
        list[12] = new Brick(505, 355);
        list[13] = new Brick(605, 355);
        list[14] = new Brick(705, 355);

        list[15] = new Brick(205, 505);
        list[16] = new Brick(355, 505);
        list[17] = new Brick(505, 505);

        list[18] = new Brick(105, 605);
        list[19] = new Brick(355, 605);
        list[20] = new Brick(605, 605);

        list[21] = new Brick(5, 705);
        list[22] = new Brick(355, 705);
        list[23] = new Brick(705, 705);

        list[0]. addNeighbors(list, new int[] {1, 9},           new int[] {1, 2, 9, 21}     );
        list[1]. addNeighbors(list, new int[] {0, 2, 4},        new int[] {0, 2, 4, 7}      );
        list[2]. addNeighbors(list, new int[] {1, 14},          new int[] {0, 1, 14, 23}    );
        list[3]. addNeighbors(list, new int[] {4, 10},          new int[] {4, 5, 10, 18}    );
        list[4]. addNeighbors(list, new int[] {1, 3, 5, 7},     new int[] {3, 5, 1, 7}      );
        list[5]. addNeighbors(list, new int[] {4, 13},          new int[] {3, 4, 13, 20}    );
        list[6]. addNeighbors(list, new int[] {7, 11},          new int[] {7, 8, 11, 15}    );
        list[7]. addNeighbors(list, new int[] {4, 6, 8},        new int[] {6, 8, 1, 4}      );
        list[8]. addNeighbors(list, new int[] {7, 12},          new int[] {6, 7, 12, 17}    );
        list[9]. addNeighbors(list, new int[] {0, 10, 21},      new int[] {10, 11, 0, 21}   );
        list[10].addNeighbors(list, new int[] {3, 9, 11, 18},   new int[] {9, 11, 3, 18}    );
        list[11].addNeighbors(list, new int[] {6, 10, 15},      new int[] {9, 10, 6, 15}    );
        list[12].addNeighbors(list, new int[] {8, 13, 17},      new int[] {13, 14, 8, 17}   );
        list[13].addNeighbors(list, new int[] {5, 12, 14, 20},  new int[] {12, 14, 5, 20}   );
        list[14].addNeighbors(list, new int[] {2, 13, 23},      new int[] {12, 13, 2, 23}   );
        list[15].addNeighbors(list, new int[] {11, 16},         new int[] {16, 17, 6, 11}   );
        list[16].addNeighbors(list, new int[] {15, 17, 19},     new int[] {15, 17, 19, 22}  );
        list[17].addNeighbors(list, new int[] {12, 16},         new int[] {15, 16, 8, 12}   );
        list[18].addNeighbors(list, new int[] {10, 19},         new int[] {19, 20, 3, 10}   );
        list[19].addNeighbors(list, new int[] {16, 18, 20, 22}, new int[] {18, 20, 16, 22}  );
        list[20].addNeighbors(list, new int[] {13, 19},         new int[] {18, 19, 5, 13}   );
        list[21].addNeighbors(list, new int[] {9, 22},          new int[] {22, 23, 0, 9}    );
        list[22].addNeighbors(list, new int[] {19, 21, 23},     new int[] {21, 23, 16, 19}  );
        list[23].addNeighbors(list, new int[] {14, 22},         new int[] {21, 22, 2, 14}   );

        return list;
    }

    private void colorInTakeAndDeleteAndEnd(String fromServer) {
        if (fromServer.startsWith("ALLGREEN")) {
            fromServer = fromServer.replace("ALLGREEN_", "");
            String[] split = fromServer.split("_");

            recolorAllGreen();
            bricks[Integer.parseInt(split[0])].setBackground(Color.decode(split[1]));
        }
        else {
            String[] split = fromServer.split("_");
            bricks[Integer.parseInt(split[0])].setBackground(Color.decode(split[1]));
        }
    }

    private void colorNeighbors(String index) {
        bricks[Integer.parseInt(index)].setBackground(Color.BLUE);
        Brick[] neighbors = bricks[Integer.parseInt(index)].getNeighbors();
        for (Brick neighbor : neighbors) {
            if (neighbor.getBackground() == defaultColor)
                neighbor.setBackground(Color.GREEN);
        }
    }

    private void colorAllFree(String index) {
        bricks[Integer.parseInt(index)].setBackground(Color.BLUE);
        for (Brick brick : bricks)
            if (brick.getBackground() == defaultColor)
                brick.setBackground(Color.GREEN);
    }

    private void recolorAllGreen() {
        for (Brick brick : bricks) {
            if (brick.getBackground() == Color.GREEN || brick.getBackground() == Color.BLUE)
                brick.setBackground(defaultColor);
        }
    }
}
