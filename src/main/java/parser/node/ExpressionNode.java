package parser.node;

import interpreter.ExpressionVisitor;
import parser.Operator;

import java.util.Map;

public class ExpressionNode implements ASTExpressionNode {
    private ASTExpressionNode left;
    private ASTExpressionNode right;
    private Operator operator;

    public ExpressionNode(ASTExpressionNode left, ASTExpressionNode right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ASTExpressionNode getLeft() {
        return left;
    }

    public ASTExpressionNode getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack) {
        return expressionVisitor.visitExpression(this, variableStack);
    }
}
