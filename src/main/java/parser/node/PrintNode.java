package parser.node;


import parser.ASTNode;

public class PrintNode extends AbstractNode {
    private ASTNode expression;

    public PrintNode(ASTNode expression) {
        this.expression = expression;
    }
}
