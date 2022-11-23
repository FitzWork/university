package SecondMileStone.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Home extends JPanel implements ActionListener {
    private final JPanel panel = new JPanel();
    private Boolean p1confirm = false,
            p2confirm = false;
    private final String[] player = {"",""};
    private final JLabel text1 = new JLabel(""),
            text2 = new JLabel(""),
            label1 = new JLabel("Spieler: 1"),
            label2 = new JLabel("Spieler: 2");
    private final JTextField textField1 = new JTextField("Spieler 1"),
            textField2 = new JTextField("Spieler 2");
    private final JButton confirm1 = new JButton("Bestätigen Spieler 1"),
            confirm2 = new JButton("Bestätigen Spieler 2");

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(25, 25, 700, 700);
        g.drawRect(125, 125, 500, 500);
        g.drawRect(225, 225, 300, 300);
    }

    public JPanel buildScreen() {
        panel.setLayout(null);
        panel.setBackground(new Color(0x123456));
        Home screen = new Home();
        screen.setSize(775, 950);
        panel.setBounds(0,0,775, 950);
        panel.add(screen);
        panel.add(new JButton("start"));

        text1.setBounds(25,750,700,30);
        text2.setBounds(25,800,700,30);
        label1.setBounds(25,780,200,30);
        label2.setBounds(300,780,200,30);
        textField1.setBounds(25,820,200,30);
        textField2.setBounds(300,820,200,30);
        confirm1.setBounds(25,860,200,30);
        confirm2.setBounds(300,860,200,30);

        confirm1.addActionListener(this);
        confirm2.addActionListener(this);

        panel.add(text1);
        panel.add(text2);
        panel.add(label1);
        panel.add(label2);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(confirm1);
        panel.add(confirm2);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == confirm1 || e.getSource() == confirm2) {
                if(e.getSource() == confirm1 && textField1.getText().length() != 0) {
                    player[0] = textField1.getText();
                    p1confirm = true;
                    textField1.setText("Bestätigt");
                }
                if(e.getSource() == confirm2 && textField2.getText().length() != 0) {
                    player[1] = textField2.getText();
                    p2confirm = true;
                    textField2.setText("Bestätigt");
                }
                if(p1confirm && p2confirm) {
                    label1.setVisible(false);
                    label2.setVisible(false);
                    textField1.setVisible(false);
                    textField2.setVisible(false);
                    confirm1.setVisible(false);
                    confirm2.setVisible(false);
                    if (new Random().nextInt(2)+1 == 1) {
                        text1.setText(player[0] + " beginnt und ist Weiß");
                    } else {
                        text1.setText(player[1] + " beginnt und ist Weiß");
                        String hilfe = player[0];
                        player[0] = player[1];
                        player[1] = hilfe;
                    }
                }
            }
        } catch (Exception error) {
            text1.setText("Geben sie einen Namen ein!");
        }
    }
}
