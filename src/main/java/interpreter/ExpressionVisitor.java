package interpreter;

import parser.node.expression.ExpressionNode;
import parser.node.expression.IdentifierNode;
import parser.node.expression.NumberNode;
import parser.node.expression.StringNode;

import java.util.Map;

public interface ExpressionVisitor {
    Integer visitExpression(NumberNode node);

    String visitExpression(StringNode node);

    Object visitExpression(IdentifierNode node, Map<String, Object> vars);

    Object visitExpression(ExpressionNode node, Map<String, Object> vars);
}
