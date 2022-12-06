package Aufgabenblatt_8.A1;

public class Main {
    public static void resizeAll(float r, ResizeableGO[] rgo) {
        for (ResizeableGO g : rgo) g.resize(r);
    }
    public static void moveAll(float dx, float dy, MoveableGO[] mgo) {
        for (MoveableGO m : mgo) m.move(dx, dy);
    }

    public static void main(String[] args) {
        Rectangle[] rects = {
                new Rectangle(2, 2, 2, 2),
                new Rectangle(1, 1, 1, 1),
                new Rectangle(1.5f, 2.5f, 3.5f, 4.5f)};
        System.out.println("resizeAll:");
        for (Rectangle rect : rects) System.out.println(rect.flaeche);
        resizeAll(2, rects);
        for (Rectangle rect : rects) System.out.println(rect.flaeche);

        System.out.println("\nmoveALL:");
        for (Rectangle rect : rects) System.out.println(rect.x + rect.y);
        moveAll(1.5f, 2, rects);
        for (Rectangle rect : rects) System.out.println(rect.x + rect.y);
    }
}
