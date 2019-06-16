package parser.node.expression;

import interpreter.ExpressionVisitor;

import java.util.Map;

public interface ASTExpressionNode {
    Object accept(ExpressionVisitor expressionVisitor, Map<String, Object> variableStack);
}
