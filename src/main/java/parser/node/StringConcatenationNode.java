package parser.node;

public class StringConcatenationNode extends AbstractNode {
    private String value1;
    private String value2;

    public StringConcatenationNode(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
