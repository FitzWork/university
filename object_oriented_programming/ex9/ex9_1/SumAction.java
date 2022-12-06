package Aufgabenblatt_9.A1;

public class SumAction implements ActionObject{
    protected int sum = 0;
    @Override
    public void action(Node n) {
        if(n.data instanceof Integer){
            Integer tmp = (Integer) n.data;
            if(tmp > 0)
                sum += tmp;
        }
    }
}
