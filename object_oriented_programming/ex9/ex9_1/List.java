package Aufgabenblatt_9.A1;

public class List {
    private Node first;
    public void mkNode(Object obj){
        Node tmp = new Node();
        tmp.next = first;
        tmp.data = obj;
        first = tmp;
    }
    public void traverseAndApply(ActionObject p){
        for(Node cursor = first; cursor != null; cursor = cursor.next)
            p.action(cursor);
    }
}

