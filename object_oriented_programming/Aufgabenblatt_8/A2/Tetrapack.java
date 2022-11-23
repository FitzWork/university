package Aufgabenblatt_8.A2;

public class Tetrapack extends Behaeltnis{
    final double breite, laenge, hoehe;
    public Tetrapack(double breite, double laenge, double hoehe) {
        this.breite = breite;
        this.laenge = laenge;
        this.hoehe = hoehe;
    }
    @Override
    public void println() {
        System.out.println("Tetrapack mit Kanten " + breite + ", " + laenge + ", " + hoehe);
    }
    @Override
    public double volumen() {
        return breite * laenge * hoehe;
    }
}
