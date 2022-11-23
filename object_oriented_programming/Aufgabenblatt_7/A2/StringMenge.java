package Aufgabenblatt_7.A2;

public interface StringMenge {
    void add(String s);
    void remove(String s);
    boolean contains(String s);
    boolean isEmpty();
    int getSize();
    String[] getElements();
    int getCharCount();
    void print();
}
