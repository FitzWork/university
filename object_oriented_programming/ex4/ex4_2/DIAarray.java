package Aufgabenblatt_4.A2;

public class DIAarray extends DynIntArray{
    private int s = 0;
    private int[] A = new int[1];

    @Override
    public void add(int e) {
        if (s > 0) {
            int[] help = new int[A.length+1];
            System.arraycopy(A, 0, help, 0, A.length);
            A = new int[A.length + 1];
            System.arraycopy(help, 0, A, 0, help.length);
        }
        A[s] = e;
        s++;
    }
    @Override
    public void setElementAt(int i, int e) {
        if (A.length > i && i >= 0)
            A[i] = e;
    }
    @Override
    public int getElementAt(int i) {
        if (A.length > i && i >= 0)
            return A[i];
        return 0;
    }
    @Override
    public int getElementCount() { return s; }
    @Override
    public void print() { System.out.println(java.util.Arrays.toString(A)); }
}
