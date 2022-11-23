package SecondMileStone.Window;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(25, 25, 700, 700);
        g.drawRect(125, 125, 500, 500);
        g.drawRect(225, 225, 300, 300);
        g.drawLine(375, 25, 375, 225);
        g.drawLine(375, 525, 375, 725);
        g.drawLine(25, 375, 225, 375);
        g.drawLine(525, 375, 725, 375);
    }
    public Grid (int width, int height) {this.setSize(width, height);}
}
