package parser.node.regular;

import interpreter.ASTNodeVisitor;
import parser.node.expression.ASTExpressionNode;

public class AssignationDeclarationNode implements ASTNode {
    private String declarator;
    private String identifier;
    private ASTExpressionNode expression;
    private String type;

    public AssignationDeclarationNode(String declarator, String identifier, ASTExpressionNode expression, String type) {
        this.declarator = declarator;
        this.identifier = identifier;
        this.expression = expression;
        this.type = type;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    public String getDeclarator() {
        return declarator;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ASTExpressionNode getExpression() {
        return expression;
    }

    public String getType() {
        return type;
    }
}
