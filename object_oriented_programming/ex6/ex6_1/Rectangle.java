package Aufgabenblatt_6.A1;

public class Rectangle extends Figure implements MobileObject{
    private double höhe, breite;

    public Rectangle(double höhe, double breite) {super(); this.breite = breite; this.höhe = höhe;}

    public Rectangle(double höhe, double breite, double x, double y) {super(x, y); this.breite = breite; this.höhe = höhe;}

    public double getHöhe() {return höhe;}
    public double getBreite() {return breite;}

    @Override
    public String print() {return super.print() + " | Höhe: " + getHöhe() + " | Breite: " + getBreite();}

    @Override
    public void move(double x, double y) {this.x += x; this.y += y;}
    @Override
    public void increase(double typ) {this.höhe *= typ; this.breite *= typ;}
    @Override
    public void decrease(double typ) {this.höhe *= typ; this.breite *= typ;}

    public void compere(Rectangle r) {
        double max_x1 = getX() + getBreite(), max_y1 = getY() + getHöhe();
        double max_x2 = r.getX() + r.getBreite(), max_y2 = r.getY() + r.getHöhe();

        if(getX() == r.getX() && getY() == r.getY() && getHöhe() == r.getHöhe() && getBreite() == r.getBreite())
            System.out.println("same");
        else if((getX() <= r.getX() &&  getY() <= r.getY() && max_x1 >= max_x2 && max_y1 >= max_y2) ||
                (getX() >= r.getX() &&  getY() >= r.getY() && max_x1 <= max_x2 && max_y1 <= max_y2))
            System.out.println("contained");
        else if(r.getY() >= getY() || r.getY() <= max_y1 || max_y2 >= getY() || max_y2 <= max_y1)
            System.out.println("aligned");
        else if(r.getX() >= getX() || r.getX() <= max_x1 || max_x2 >= getX() || max_x2 <= max_x1)
            System.out.println("aligned");
        else System.out.println("disjoint");
    }
}
