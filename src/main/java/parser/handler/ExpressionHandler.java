package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.Operator;
import parser.ParseException;
import parser.node.ASTExpressionNode;
import parser.node.ExpressionNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class ExpressionHandler extends AbstractHandler<ASTExpressionNode> {


    public ExpressionHandler(Rule rule) {
        super(rule);
    }

    @Override
    Optional<ASTExpressionNode> handleInternal(List<Token> tokens) {
        return handleInternal(tokens, 0, tokens.size());
    }

    private Optional<ASTExpressionNode> handleInternal(List<Token> tokens, int start, int end) {
        if (start > end) return Optional.empty();
        if (end - start == 1) {
            Token first = tokens.get(0);
            return Optional.of(parseValue(first));
        }
        Optional<Integer> plusOrMinusOptionalIndex = findPlusOrMinus(tokens, start, end);
        if (plusOrMinusOptionalIndex.isPresent()) {
            int index = plusOrMinusOptionalIndex.get();
            Optional<ASTExpressionNode> left = handleInternal(tokens, start, index);
            Optional<ASTExpressionNode> right = handleInternal(tokens, index + 1, end);
            Operator operator = toOperator(tokens.get(index).getType());

            if (left.isPresent() && right.isPresent()) {
                return Optional.of(new ExpressionNode(left.get(), right.get(), operator));
            } else {
                return Optional.empty();
            }
        }
        Optional<Integer> divideOrMultiplyOptionalIndex = findMultiplyOrDivide(tokens, start, end);
        if (divideOrMultiplyOptionalIndex.isPresent()) {
            int index = divideOrMultiplyOptionalIndex.get();
            Optional<ASTExpressionNode> left = handleInternal(tokens, start, index);
            Optional<ASTExpressionNode> right = handleInternal(tokens, index + 1, end);
            Operator operator = toOperator(tokens.get(index).getType());

            if (left.isPresent() && right.isPresent()) {
                return Optional.of(new ExpressionNode(left.get(), right.get(), operator));
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private Optional<Integer> findPlusOrMinus(List<Token> tokens, int start, int end) {
        for (int i = start; i < end; i++) {
            TokenType type = tokens.get(i).getType();
            if (type.equals(TokenType.PLUS) || type.equals(TokenType.MINUS)) return Optional.of(i);
        }
        return Optional.empty();
    }

    private Optional<Integer> findMultiplyOrDivide(List<Token> tokens, int start, int end) {
        for (int i = start; i < end; i++) {
            TokenType type = tokens.get(i).getType();
            if (type.equals(TokenType.MULTIPLY) || type.equals(TokenType.DIVIDE)) return Optional.of(i);
        }
        return Optional.empty();
    }

    private ASTExpressionNode parseValue(Token token) {
        TokenType tokenType = token.getType();
        if (tokenType.equals(TokenType.NUMBER)) return new NumberNode(parseInt(token.getValue()));
        if (tokenType.equals(TokenType.IDENTIFIER)) return new IdentifierNode(token.getValue());
        if (tokenType.equals(TokenType.STRING))
            return new StringNode(token.getValue().substring(1, token.getValue().length() - 1));
        throw new ParseException("Not a valid expression");
    }

    private Operator toOperator(TokenType tokenType) {
        switch (tokenType) {
            case PLUS:
                return Operator.PLUS;
            case MINUS:
                return Operator.MINUS;
            case MULTIPLY:
                return Operator.MULTIPLY;
            case DIVIDE:
                return Operator.DIVIDE;
            default:
                throw new ParseException("not an operator");
        }
    }
}
