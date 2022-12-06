package Aufgabenblatt_9.A2;

public class SumAction<T extends Number> implements TreeNodeActionObject<T>{
    protected double sum = 0;
    @Override
    public void action(Node<T> n) {
        if(n.data != null){
            T tmp = n.data;
            sum += tmp.doubleValue();
        }
    }
}
