package Aufgabenblatt_3.A2;

public class Flight {
    protected int flightNumber;
    protected String location, gate, time;
    protected boolean inOut;

    public Flight (int fn, String l, String g, String t, Boolean io) {
        this.flightNumber = fn; this.location = l; this.gate = g; this.time = t; this.inOut = io;
    }
}
