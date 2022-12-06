package Aufgabenblatt_9.A2;

public class Tree<T extends Number> {
    private Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }
    public void traverseAndApply(TreeNodeActionObject<T> ao) {
        traverseAndApply(root, ao);
    }
    private void traverseAndApply(Node<T> n, TreeNodeActionObject<T> ao) {
        if (n == null) return;
        traverseAndApply(n.left, ao);
        ao.action((Node<T>) n);
        traverseAndApply(n.right, ao);
    }
}