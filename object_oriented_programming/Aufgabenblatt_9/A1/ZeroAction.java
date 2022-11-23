package Aufgabenblatt_9.A1;

public class ZeroAction implements ActionObject{
    protected Boolean zeroExist = false;
    @Override
    public void action(Node n) {
        if(n.data.equals(0))
            zeroExist = true;
    }
}
