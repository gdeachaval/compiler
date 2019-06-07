package parser.node;

public class StringNode extends AbstractNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
