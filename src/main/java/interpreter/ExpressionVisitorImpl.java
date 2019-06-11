package interpreter;

import parser.Operator;
import parser.node.ArithmeticOperationNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringConcatenationNode;
import parser.node.StringNode;

import java.util.Map;

public class ExpressionVisitorImpl implements ExpressionVisitor {

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
}
