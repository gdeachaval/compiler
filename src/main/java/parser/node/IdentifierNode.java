package parser.node;

import interpreter.ASTNodeVisitor;

public class IdentifierNode extends AbstractNode {
    private String value;

    public IdentifierNode(String value) {
        this.value = value;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
