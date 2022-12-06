package Aufgabenblatt_9.A1;

public class Show {
    public void showContainsZero(List list) {
        ZeroAction z = new ZeroAction();
        list.traverseAndApply(z);
        System.out.println("0 " + (z.zeroExist ? "exist" : "didnt exist"));
    }
    public void showPosSum(List list) {
        SumAction s = new SumAction();
        list.traverseAndApply(s);
        System.out.println("sum: " + s.sum);
    }
}
