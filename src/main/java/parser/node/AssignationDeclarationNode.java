package parser.node;

import interpreter.ASTNodeVisitor;
import parser.ASTNode;

public class AssignationDeclarationNode extends AbstractNode {
    private String declarator;
    private String identifier;
    private ASTNode expression;
    private String type;

    public AssignationDeclarationNode(String declarator, String identifier, ASTNode expression, String type) {
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

    public ASTNode getExpression() {
        return expression;
    }

    public String getType() {
        return type;
    }
}
