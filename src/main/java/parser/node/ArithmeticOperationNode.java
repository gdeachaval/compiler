package parser.node;

import interpreter.ASTNodeVisitor;
import interpreter.ExpressionVisitor;
import parser.Operator;

public class ArithmeticOperationNode extends ExpressionNode {
    private int number1;
    private int number2;
    private Operator operator;

    public ArithmeticOperationNode(int number1, int number2, Operator operator) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Integer accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitExpression(this);
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public Operator getOperator() {
        return operator;
    }
}
