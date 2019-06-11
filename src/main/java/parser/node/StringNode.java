package parser.node;

import interpreter.ASTNodeVisitor;

public class StringNode extends AbstractNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
