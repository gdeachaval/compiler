package parser.node;

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
}
