package Aufgabenblatt_4.A1;

public class Leibeigener extends Einwohner{
    @Override
    public int zuVersteuerndesEinkommen() { return Math.max(einkommen - 12, 0); }
}
