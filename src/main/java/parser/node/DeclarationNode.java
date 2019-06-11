package parser.node;

import interpreter.ASTNodeVisitor;

public class DeclarationNode implements ASTNode {
    private String declarator;
    private String identifier;

    public DeclarationNode(String declarator, String identifier) {
        this.declarator = declarator;
        this.identifier = identifier;
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
}
