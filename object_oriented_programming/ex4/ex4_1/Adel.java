package Aufgabenblatt_4.A1;

public class Adel extends Einwohner{
    @Override
    public int steuer() {return (int) (Math.max((zuVersteuerndesEinkommen() + 5) / prozent, 20));}
}
