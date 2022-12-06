package Aufgabenblatt_9.A2;

public class Node<T extends Number>{
    public T data;
    public Node<T> left, right;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public Node(T data) {
        this.data = data;
    }
}

