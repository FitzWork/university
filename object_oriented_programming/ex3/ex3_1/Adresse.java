package Aufgabenblatt_3.A1;

public class Adresse {
    private String strasse, plz, ort;

    public Adresse (String strasse, String plz, String ort) {
        this.strasse = strasse; this.plz = plz; this.ort = ort;
    }
    public String getStrasse() {
        return strasse;
    }
    public String getPLZ() {
        return plz;
    }
    public String getOrt(){
        return ort;
    }
}