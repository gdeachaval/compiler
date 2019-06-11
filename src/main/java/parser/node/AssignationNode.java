package parser.node;

import interpreter.ASTNodeVisitor;
import parser.ASTNode;

public class AssignationNode extends AbstractNode {
    private ASTNode expression;
    private String identifier;

    public AssignationNode(ASTNode expression, String identifier) {
        this.expression = expression;
        this.identifier = identifier;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    public ASTNode getExpression() {
        return expression;
    }

    public String getIdentifier() {
        return identifier;
    }
}
