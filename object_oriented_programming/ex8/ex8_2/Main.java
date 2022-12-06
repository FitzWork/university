package Aufgabenblatt_8.A2;

public class Main {
    public static void main(String[] args) {
        final double produktion = 200;
        double volumen = 0;
        Behaeltnis[] bestellung = {
                new Tetrapack(2.3, 0.9, 0.7),
                new Dose(1.09 , 1.15),
                new Dose(1.09 , 1.15),
                new Tetrapack(1.1, 0.7, 0.7)
                // ...
        };
        for (Behaeltnis behaeltnis : bestellung) {
            behaeltnis.println();
            volumen += behaeltnis.volumen();
        }
        System.out.println("Gesamtvolumen der Bestellung: " + String.format("%.2f",volumen));
        System.out.println("Restvolumen der wöchtlichen Produktion: " + ( String.format("%.2f",(produktion - volumen) > 0 ? produktion-volumen : 0)));
    }
}
