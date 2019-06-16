package interpreter;

import parser.Operator;
import parser.ParseException;
import parser.node.ASTExpressionNode;
import parser.node.ArithmeticOperationNode;
import parser.node.ExpressionNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringConcatenationNode;
import parser.node.StringNode;

import java.util.Map;

public class ExpressionVisitorImpl implements ExpressionVisitor {

    private static final String STRING = "^\".*\"$";

    @Override
    public Integer visitExpression(ArithmeticOperationNode node) {
        int number1 = node.getNumber1();
        int number2 = node.getNumber2();
        Operator operator = node.getOperator();
        if (operator.equals(Operator.PLUS)) {
            return number1 + number2;
        }
        if (operator.equals(Operator.MINUS)) {
            return number1 - number2;
        }
        if (operator.equals(Operator.MULTIPLY)) {
            return number1 * number2;
        }
        if (operator.equals(Operator.DIVIDE)) {
            return number1 / number2;
        }
        return null;
    }

    @Override
    public String visitExpression(StringConcatenationNode node) {
        String value1 = node.getValue1();
        String value2 = node.getValue2();
        return value1 + value2;
    }

    @Override
    public Integer visitExpression(NumberNode node) {
        return node.getValue();
    }

    @Override
    public String visitExpression(StringNode node) {
        return node.getValue();
    }

    @Override
    public Object visitExpression(IdentifierNode node, Map<String, Object> vars) {
        String value = node.getValue();
        if (vars.containsKey(value)) {
            return vars.get(value);
        }
        throw new InterpreterException(value + " not defined yet");
    }

    @Override
    public Object visitExpression(ExpressionNode node) {
        ASTExpressionNode right = node.getRight();
        ASTExpressionNode left = node.getLeft();
        Operator operator = node.getOperator();
        Object leftResult = left.accept(this, null);
        Object rightResult = right.accept(this, null);
        String leftValue = leftResult.toString();
        String rightValue = rightResult.toString();
        if (leftValue.matches(STRING) || rightValue.matches(STRING)) {
            return operateStrings(operator, leftValue, rightValue);
        } else {
            return operateNumbers(operator, Integer.parseInt(leftValue), Integer.parseInt(rightValue));
        }
    }

    private String operateStrings(Operator operator, String leftValue, String rightValue) {
        if (operator.equals(Operator.PLUS)) {
            return leftValue.concat(rightValue);
        }
        throw new ParseException("Only + is supported when operating with strings");
    }

    private int operateNumbers(Operator operator, int left, int right) {
        switch (operator) {
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                return left / right;
            default:
                throw new ParseException("operator not valid");
        }
    }
}
