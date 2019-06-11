package interpreter;

import parser.node.ArithmeticOperationNode;
import parser.node.StringConcatenationNode;

public interface ExpressionVisitor {
    Integer visitExpression(ArithmeticOperationNode node);
    String visitExpression(StringConcatenationNode node);
}
