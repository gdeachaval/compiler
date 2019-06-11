package parser.node;

import interpreter.ExpressionVisitor;

public class StringNode implements ASTExpressionNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitExpression(this);
    }
}
