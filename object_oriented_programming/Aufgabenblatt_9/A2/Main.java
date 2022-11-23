package Aufgabenblatt_9.A2;

public class Main {
    public static void main(String[] args) {
        Tree<Number> tree = new Tree<>(
                new Node<>(4,
                        new Node<>(2,
                                new Node<>(1),
                                new Node<>(3)),
                        new Node<>(6,
                                new Node<>(5),
                                new Node<>(7)
                        )
                )
        );
        SumAction<Number> sum = new SumAction<>();
        tree.traverseAndApply(sum);
        System.out.println("sum: " + sum.sum);
    }
}
