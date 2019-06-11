package parser.node;

import interpreter.ExpressionVisitor;

public interface ASTExpressionNode {
    Object accept(ExpressionVisitor expressionVisitor);
}
