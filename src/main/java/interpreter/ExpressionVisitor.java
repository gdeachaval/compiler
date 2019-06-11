package interpreter;

import parser.node.ArithmeticOperationNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringConcatenationNode;
import parser.node.StringNode;

import java.util.Map;

public interface ExpressionVisitor {
    Integer visitExpression(ArithmeticOperationNode node);
    String visitExpression(StringConcatenationNode node);
    Integer visitExpression(NumberNode node);
    String visitExpression(StringNode node);
    Object visitExpression(IdentifierNode node, Map<String, Object> vars);

}
