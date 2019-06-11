package parser.node;

import interpreter.ExpressionVisitor;
import parser.Operator;

import java.util.Map;

public class ArithmeticOperationNode implements ASTExpressionNode {
    private int number1;
    private int number2;
    private Operator operator;

    public ArithmeticOperationNode(int number1, int number2, Operator operator) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;
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

    @Override
    public Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack) {
        return expressionVisitor.visitExpression(this);
    }
}
