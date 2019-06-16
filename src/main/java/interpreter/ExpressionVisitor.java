package interpreter;

import parser.node.ExpressionNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringNode;

import java.util.Map;

public interface ExpressionVisitor {
    Integer visitExpression(NumberNode node);

    String visitExpression(StringNode node);

    Object visitExpression(IdentifierNode node, Map<String, Object> vars);

    Object visitExpression(ExpressionNode node, Map<String, Object> vars);
}
