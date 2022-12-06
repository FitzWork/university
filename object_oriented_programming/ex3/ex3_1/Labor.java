package Aufgabenblatt_3.A1;

public class Labor<Praktika> extends Hoersaal {
    protected Praktika[] praktikas;

    public Labor(int plz, Adresse adr, Praktika[] praktikas) {
        super(plz, adr); this.praktikas = praktikas;
    }
    public Praktika[] getPraktikas() {
        return praktikas;
    }
    public int getPlaetze() {
        return plaetze;
    }
    public Adresse getAdresse() {
        return adr;
    }
}
