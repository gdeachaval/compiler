package parser.node;

public class IdentifierNode extends AbstractNode {
    private String value;

    public IdentifierNode(String value) {
        this.value = value;
    }
}
