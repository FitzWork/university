package Aufgabenblatt_8.A1;

public class Rectangle implements ResizeableGO, MoveableGO{
    protected float flaeche, x, y, breite, hoehe;
    public Rectangle(float breite, float hoehe, float x, float y) {
        this.flaeche = breite * hoehe;
        this.breite =  breite;
        this.hoehe = hoehe;
        this.x = x;
        this.y = y;
    }
    public void setColor(int x) {
        System.out.println("setColor");
    }
    public void show() {
        System.out.println("show");
    }
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void resize(float scale) {
        breite *= scale;
        hoehe *= scale;
        flaeche = breite * hoehe;
    }
    @Override
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
}
