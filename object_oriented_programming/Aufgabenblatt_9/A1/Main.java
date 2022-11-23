package Aufgabenblatt_9.A1;

public class Main {
    public static void main(String[] args) {
        int a = 5, b = 5;
        List l = new List();
        l.mkNode('a');
        l.mkNode('b');
        l.mkNode('c');
        l.mkNode("abc");
        l.mkNode(a-b);
        l.mkNode(1);
        l.mkNode(2);
        Show s = new Show();
        s.showContainsZero(l);
        s.showPosSum(l);
    }
}
