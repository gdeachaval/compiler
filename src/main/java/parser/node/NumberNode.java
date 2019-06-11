package parser.node;

import interpreter.ExpressionVisitor;

import java.util.Map;

public class NumberNode implements ASTExpressionNode {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    @Override
    public Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack) {
        return expressionVisitor.visitExpression(this);
    }
}
