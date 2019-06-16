package parser.node.regular;

import interpreter.ASTNodeVisitor;
import parser.node.expression.ASTExpressionNode;

public class AssignationNode implements ASTNode {
    private ASTExpressionNode expression;
    private String identifier;

    public AssignationNode(ASTExpressionNode expression, String identifier) {
        this.expression = expression;
        this.identifier = identifier;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    public ASTExpressionNode getExpression() {
        return expression;
    }

    public String getIdentifier() {
        return identifier;
    }
}
