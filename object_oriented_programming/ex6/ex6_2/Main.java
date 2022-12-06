package Aufgabenblatt_6.A2;

public class Main {
    public static void main(String[] args) {
        Roman r1 = new Roman(2179);
        Roman r2 = new Roman("V");
        Roman r3 = new Roman(250);
        System.out.println(r1.a + " = " + r1.r + " | " + r2.a + " = " + r2.r + " | " + r3.a + " = " + r3.r );
        r1 = r1.addition("V");
        System.out.println(r1.a + " = " + r1.r);
        r1 = r1.subtraktion("V");
        System.out.println(r1.a + " = " + r1.r);
        r1 = r1.multiplikation("XV");
        System.out.println(r1.a + " = " + r1.r);
        r3 = r3.division("L");
        System.out.println(r3.a + " = " + r3.r);
        if(r2.equals(r3)) System.out.println("2. und 3. sind gleich");
        System.out.println(r1.a + " = " + r1.r + " | " + r2.a + " = " + r3.r + " | " + r3.a + " = " + r3.r );
    }
}
