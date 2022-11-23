package SecondMileStone.Server;

import SecondMileStone.Game.Brick;
import SecondMileStone.Window.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ClientGUI {
    Client client;

    Grid grid = new Grid(750, 750);

    private final JFrame clientGUI = new JFrame(){
        @Override
        public void dispose() {
            if (board.isVisible()) {
                removeBoard();
                setTitle("Mühle - Menu");
                client.sentToServer("MOVE_BACK_TO_LOBBY_");
                if (client.gotEnemy())
                    client.sentToServer("QUIT");
                client.removeEnemy();
                client.setCanWrite();
                join.setEnabled(false);
                loadMenu();
            }
            else if (menu.isVisible()) {
                unloadMenu();
                setTitle("Mühle - Login");
                client.sentToServer("LOGOUT_");
                client.setCanWrite();
                loadLogin();
            }
        }
    };

    private final JPanel login = new JPanel(),
            menu = new JPanel(),
            board = new JPanel();

    private final JLabel top = new JLabel("", SwingConstants.CENTER),
            mid = new JLabel("", SwingConstants.CENTER),
            bot = new JLabel("", SwingConstants.CENTER);

    DefaultListModel<String> lobbylist = new DefaultListModel<>(),
            waitingList = new DefaultListModel<>(),
            inGameList = new DefaultListModel<>();

    private final JList<String> lobby = new JList<>(lobbylist),
            waiting = new JList<>(waitingList),
            inGame = new JList<>(inGameList);

    String gegner;

    JButton join;


    public ClientGUI(Client client) {
        this.client = client;
        clientGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientGUI.setLayout(null);
        clientGUI.setResizable(false);
        clientGUI.getContentPane().setBackground(new Color(0x123456));
        clientGUI.setLocationRelativeTo(null);

        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setSize(int width, int height) {clientGUI.setSize(width, height);}

    public void setVisible(boolean set) {clientGUI.setVisible(set);}

    public void setTitle(String text) {clientGUI.setTitle("Mühle - " + text);}

    public void setWaitingUnselected() {waiting.clearSelection();}

    public void joinDisable() {if (join != null) join.setEnabled(false);}



    public String getGegner() {
        join.setEnabled(false);
        return gegner;
    }

    public String getUsername() {return username;}

    public String getPassword() {return password;}



    public void updateLobby(String fromServer, boolean add) {
        if (add) lobbylist.addElement(fromServer);
        else lobbylist.removeElement(fromServer);
    }

    public void updateWaiting(String fromServer, boolean add) {
        if (add) waitingList.addElement(fromServer);
        else waitingList.removeElement(fromServer);
        if (waitingList.size() == 0) join.setEnabled(false);
    }

    public void updateInGame(String fromServer, boolean add) {
        if (add) inGameList.addElement(fromServer);
        else inGameList.removeElement(fromServer);
    }



    String username, password;
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    public void buildLogin(JButton registrieren, JButton anmelden) {
        board.setVisible(false);
        menu.setVisible(false);

        setTitle("Login");

        login.setLayout(null);
        login.setBackground(new Color(0x123456));
        login.setSize(767, 790);

        JLabel un = new JLabel("Nutzername");
        un.setFont(new Font("Dialog", Font.BOLD, 16));
        un.setForeground(Color.WHITE);
        un.setBounds(25, 25, 93, 16);

        JLabel pw = new JLabel("Passwort");
        pw.setFont(new Font("Dialog", Font.BOLD, 16));
        pw.setForeground(Color.WHITE);
        pw.setBounds(46, 66, 72, 16);

        usernameField.setBackground(new Color(0x2468AC));
        usernameField.setFont(new Font("Dialog", Font.BOLD, 16));
        usernameField.setForeground(Color.WHITE);
        usernameField.setBounds(143, 20, 200, 26);

        passwordField.setBackground(new Color(0x2468AC));
        passwordField.setFont(new Font("Dialog", Font.BOLD, 16));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBounds(143, 61, 200, 26);

        registrieren.setFont(new Font("Dialog", Font.BOLD, 16));
        registrieren.setBackground(new Color(0x123456));
        registrieren.setBounds(25, 100, 146, 25);

        anmelden.setFont(new Font("Dialog", Font.BOLD, 16));
        anmelden.setBackground(new Color(0x123456));
        anmelden.setBounds(196, 100, 146, 25);

        login.add(un);
        login.add(pw);
        login.add(usernameField);
        login.add(passwordField);
        login.add(registrieren);
        login.add(anmelden);

        clientGUI.add(login);
        loadLogin();
    }
    
    public boolean loginPossible() {
        if (!Objects.equals(usernameField.getText(), "") && passwordField.getPassword().length != 0) {
            username = usernameField.getText();
            password = String.valueOf(passwordField.getPassword());
            return true;
        }
        return false;
    }

    public void loadLogin() {
        clientGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientGUI.setSize(384, 188);
        login.setVisible(true);
    }
    
    public void unloadLogin() {
        clientGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientGUI.setSize(767, 790);
        login.setVisible(false);
    }



    public void buildMenu(JButton start, JButton join) {
        setTitle("Menu");
        this.join = join;

        menu.setLayout(null);
        menu.setBackground(new Color(0x123456));
        menu.setSize(767, 790);

        start.setFont(new Font("Dialog", Font.BOLD, 16));
        start.setBackground(new Color(0x123456));
        start.setBounds(80, 375, 80, 25);
        this.join.setEnabled(false);
        this.join.setFont(new Font("Dialog", Font.BOLD, 16));
        this.join.setBackground(new Color(0x123456));
        this.join.setBounds(240, 375, 80, 25);

        JLabel lobbyTitle = new JLabel("LOBBY", SwingConstants.CENTER),
                waitingTitle = new JLabel("QUEUE", SwingConstants.CENTER),
                inGameTitle = new JLabel("INGAME", SwingConstants.CENTER);

        lobbyTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        lobbyTitle.setForeground(Color.WHITE);
        lobbyTitle.setBackground(new Color(0x123456));
        lobbyTitle.setBounds(25, 13, 100, 25);

        waitingTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        waitingTitle.setForeground(Color.WHITE);
        waitingTitle.setBackground(new Color(0x123456));
        waitingTitle.setBounds(150, 13, 100, 25);

        inGameTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        inGameTitle.setForeground(Color.WHITE);
        inGameTitle.setBackground(new Color(0x123456));
        inGameTitle.setBounds(275, 13, 100, 25);

        lobby.setBounds(25, 50, 100, 300);
        lobby.setFont(new Font("Dialog", Font.BOLD, 16));
        lobby.setForeground(Color.WHITE);
        lobby.setEnabled(false);
        waiting.setBounds(150, 50, 100, 300);
        waiting.setFont(new Font("Dialog", Font.BOLD, 16));
        waiting.setForeground(Color.WHITE);
        waiting.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        waiting.addListSelectionListener(e -> {
            gegner = waiting.getSelectedValue();
            this.join.setEnabled(true);
        });
        inGame.setBounds(275, 50, 100, 300);
        inGame.setFont(new Font("Dialog", Font.BOLD, 16));
        inGame.setForeground(Color.WHITE);
        inGame.setEnabled(false);

        menu.add(lobbyTitle);
        menu.add(waitingTitle);
        menu.add(inGameTitle);
        menu.add(start);
        menu.add(join);
        menu.add(lobby);
        menu.add(waiting);
        menu.add(inGame);

        clientGUI.add(menu);
        loadMenu();
    }

    public void loadMenu() {
        clientGUI.setSize(417, 475);
        menu.setVisible(true);
    }

    public void unloadMenu() {
        menu.setVisible(false);
        clientGUI.setSize(767, 790);;
    }




    public void buildBoard(Brick[] bricks) {
        setTitle("Board");

        board.setLayout(null);
        board.setBackground(new Color(0x123456));
        board.setSize(767, 790);

        for (Brick brick : bricks)
            board.add(brick);

        top.setBounds(230, 300, 290, 50);
        top.setFont(new Font("Dialog", Font.BOLD, 20));
        top.setForeground(Color.WHITE);
        board.add(top);

        mid.setBounds(230, 350, 290, 50);
        mid.setFont(new Font("Dialog", Font.BOLD, 20));
        mid.setForeground(Color.WHITE);
        board.add(mid);
        bot.setBounds(230, 400, 290, 50);
        bot.setFont(new Font("Dialog", Font.BOLD, 20));
        bot.setForeground(Color.WHITE);
        board.add(bot);

        board.add(grid);

        clientGUI.add(board);
        board.setVisible(true);
    }

    private void removeBoard() {
        board.setVisible(false);
        clientGUI.remove(board);
        board.removeAll();
    }

    public void setTurnText(String top, String mid, String bot) {
        this.top.setText(top);
        this.mid.setText(mid);
        this.bot.setText(bot);
    }
}
