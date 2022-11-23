package Aufgabenblatt_4.A2;

public class DIAlist extends DynIntArray{
    private Node top, help;
    private int s;

    @Override
    public void add(int e) {
        top = new Node(e, top);
        s++;
    }
    @Override
    public void setElementAt(int i, int e) {
        i = s - i;
        help = top;

        if(i <= s && i >= 0) {
            if (i != 0)
                for (int c = 1; c < i; c++)
                    help = help.next;
            help.e = e;
        }
    }
    @Override
    public int getElementAt(int i) {
        if(i <= s && i >= 0){
            i = s - i;
            help = top;
            if (i != 0)
                for (int c = 1; c < i; c++)
                    help = help.next;
            return help.e;
        }
        return 0;
    }
    @Override
    public int getElementCount (){ return s; }
    @Override
    public void print() {
        help = top;
        int recount;
        System.out.print("[");
        for(int i = s; i > 0; i--) {
            recount = i;
            while(--recount > 0)
                help = help.next;
            System.out.print(help.e);
            if(i > 1)
                System.out.print(", ");
            help = top;
        }
        System.out.println("]");
    }
}
