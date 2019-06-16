package parser.node.expression;

import interpreter.ExpressionVisitor;

import java.util.Map;

public class StringNode implements ASTExpressionNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack) {
        return expressionVisitor.visitExpression(this);
    }
}
