package Aufgabenblatt_4.A1;

public class Einwohner {
    protected int einkommen;
    protected int prozent = 1;
    protected int zuVersteuerndesEinkommen() { return einkommen; }
    protected int steuer() {return (int) (Math.max((zuVersteuerndesEinkommen() + 5) / prozent, 1));}
    public void setEinkommen(int einkommen) { this.einkommen = einkommen;}
}
