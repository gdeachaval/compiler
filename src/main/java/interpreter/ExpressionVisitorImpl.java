package interpreter;

import common.Constants;
import parser.Operator;
import parser.ParseException;
import parser.node.expression.ASTExpressionNode;
import parser.node.expression.ExpressionNode;
import parser.node.expression.IdentifierNode;
import parser.node.expression.NumberNode;
import parser.node.expression.StringNode;

import java.util.Map;

public class ExpressionVisitorImpl implements ExpressionVisitor {

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
        if (vars.containsKey(value) && vars.get(value) != null) {
            return vars.get(value);
        }
        throw new InterpreterException(value + " not defined yet");
    }

    @Override
    public Object visitExpression(ExpressionNode node, Map<String, Object> vars) {
        ASTExpressionNode right = node.getRight();
        ASTExpressionNode left = node.getLeft();
        Operator operator = node.getOperator();
        Object leftResult = left.accept(this, vars);
        Object rightResult = right.accept(this, vars);
        String leftValue = leftResult.toString();
        String rightValue = rightResult.toString();
        if (leftValue.matches(Constants.STRING) || rightValue.matches(Constants.STRING)) {
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
