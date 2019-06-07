package parser.node;

public class NumberNode extends AbstractNode {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
