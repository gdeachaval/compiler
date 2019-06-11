package parser.node;

import interpreter.ExpressionVisitor;

import java.util.Map;

public class StringConcatenationNode implements ASTExpressionNode {
    private String value1;
    private String value2;

    public StringConcatenationNode(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack) {
        return expressionVisitor.visitExpression(this);
    }
}
