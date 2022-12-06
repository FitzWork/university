package Aufgabenblatt_3.A1;

public class Hoersaal<Vorlesung> {
    protected int plaetze;
    protected Vorlesung[] vorlesungenen;
    protected Adresse adr;

    public Hoersaal(int plz, Adresse adr, Vorlesung[] vlg) {
        this.plaetze = plz; this.adr = adr; this.vorlesungenen = vlg;
    }
    public Hoersaal(int plz, Adresse adr) {
        this.plaetze = plz; this.adr =  adr;
    }
    public int getPlaetze() {
        return plaetze;
    }
    public Vorlesung[] getVorlesungenen() {
        return vorlesungenen;
    }
    public Adresse getAdresse() {
        return adr;
    }
}
