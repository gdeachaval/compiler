package parser.node;

import parser.ASTNode;

public class AssignationNode extends AbstractNode {
    private ASTNode expression;
    private String identifier;

    public AssignationNode(ASTNode expression, String identifier) {
        this.expression = expression;
        this.identifier = identifier;
    }
}
