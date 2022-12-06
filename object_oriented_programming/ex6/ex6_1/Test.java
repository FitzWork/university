package Aufgabenblatt_6.A1;

public class Test {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(2,4,0,0);
        Rectangle r2 = new Rectangle(2.5,5,0,0);
        Rectangle r3 = new Rectangle(5,10,0,0);
        Rectangle r4 = new Rectangle(2.5,6.4, 1,1);
        Rectangle r5 = new Rectangle(4.245,7.5,0,0);

        System.out.println(r1.print() + "\n" + r2.print() + "\n" + r3.print() + "\n" + r4.print() + "\n" + r5.print());
        r1.increase(5);
        r2.increase(2);
        r2.compere(r3);
        r3.compere(r4);
        r5.move(5,5);
        r3.compere(r5);
        System.out.println(r1.print() + "\n" + r2.print() + "\n" + r3.print() + "\n" + r4.print() + "\n" + r5.print());
    }
}
