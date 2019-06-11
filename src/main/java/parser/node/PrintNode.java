package parser.node;


import interpreter.ASTNodeVisitor;

public class PrintNode extends AbstractNode {
    private ExpressionNode expression;

    public PrintNode(ExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
