package parser.node;


import interpreter.ASTNodeVisitor;

public class PrintNode extends AbstractNode {
    private ASTExpressionNode expression;

    public PrintNode(ASTExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    public ASTExpressionNode getExpression() {
        return expression;
    }
}
