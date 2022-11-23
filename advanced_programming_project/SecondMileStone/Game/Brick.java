package SecondMileStone.Game;

import javax.swing.*;
import java.awt.*;

public class Brick extends JPanel{
    Brick[] neighbors, mills;

    public void paint(Graphics g) {
        if(this.getBackground().getRGB() == Color.BLACK.getRGB()  || this.getBackground().getRGB() == Color.WHITE.getRGB()
                || this.getBackground().getRGB() == Color.BLUE.getRGB()  || this.getBackground().getRGB() == Color.GREEN.getRGB()) {
            g.setColor(getBackground());
            g.fillOval(0, 0, 39, 39);
        }
    }

    public Brick[] getNeighbors() {
        return neighbors;
    }

    public Brick() {}

    public Brick(int x, int y) {
        this.setOpaque(false);
        this.setBounds(x, y, 40, 40);
    }

    public void addNeighbors(Brick[] bricks, int[] neighborCount, int[] millCount) {
        this.neighbors = new Brick[neighborCount.length];
        for(int i = 0; i < neighborCount.length; i++)
            neighbors[i] = bricks[neighborCount[i]];
        this.mills = new Brick[millCount.length];
        for(int i = 0; i < millCount.length; i++)
            mills[i] = bricks[millCount[i]];
    }

    public boolean inMill() {
        return mills[0].getBackground() == getBackground() && mills[1].getBackground() == getBackground()
                || mills[2].getBackground() == getBackground() && mills[3].getBackground() == getBackground();
    }
}
