package interpreter;

public interface Expression {
    Object accept(ExpressionVisitor expressionVisitor);
}
