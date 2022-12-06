package Aufgabenblatt_8.A2;

public class Dose extends Behaeltnis{
    final double flaeche, hoehe;
    public Dose(double flaeche, double hoehe) {
        this.flaeche = flaeche; this.hoehe = hoehe;
    }
    @Override
    public void println() {
        System.out.println("Dose mit Fläche " + flaeche + " und Höhe " + hoehe);
    }
    @Override
    public double volumen() {
        return flaeche * hoehe;
    }
}
