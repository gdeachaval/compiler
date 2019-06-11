package parser.node;

import interpreter.ExpressionVisitor;

public class NumberNode implements ASTExpressionNode {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitExpression(this);
    }
}
