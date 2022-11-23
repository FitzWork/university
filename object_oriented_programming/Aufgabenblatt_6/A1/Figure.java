package Aufgabenblatt_6.A1;

abstract class Figure {
    protected double x, y;
    public Figure() {}
    public Figure(double x, double y) {this.x = x; this.y = y;}
    public double getX() {return x;}
    public double getY() {return y;}
    public String print() {return "X: " + x + " | Y: " + y;}
}
