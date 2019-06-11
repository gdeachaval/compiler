package parser.node;

import interpreter.ASTNodeVisitor;
import interpreter.ExpressionVisitor;

public class StringConcatenationNode extends ExpressionNode {
    private String value1;
    private String value2;

    public StringConcatenationNode(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitExpression(this);
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }
}
