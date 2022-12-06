package Aufgabenblatt_7.A2;

abstract class AbstrakteStringMenge implements StringMenge{
    String[] s;
    int count;

    public int getCharCount() {
        if(isEmpty())
            return 0;
        count = 0;
        for (String value : s) count += value.length();
        return count;
    }

    public boolean isEmpty() {return s == null;}

    public void print() {System.out.print(java.util.Arrays.toString(s));}
}
