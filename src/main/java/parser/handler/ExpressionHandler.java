package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.Operator;
import parser.ParseException;
import parser.mapper.OperatorMapper;
import parser.node.expression.ASTExpressionNode;
import parser.node.expression.ExpressionNode;
import parser.node.expression.IdentifierNode;
import parser.node.expression.NumberNode;
import parser.node.expression.StringNode;
import parser.rules.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class ExpressionHandler extends AbstractHandler<ASTExpressionNode> {

    private static final List<TokenType> precedence1 = Arrays.asList(TokenType.MULTIPLY, TokenType.DIVIDE);
    private static final List<TokenType> precedence2 = Arrays.asList(TokenType.PLUS, TokenType.MINUS);

    public ExpressionHandler(Rule rule) {
        super(rule);
    }

    @Override
    Optional<ASTExpressionNode> handleInternal(List<Token> tokens) {
        return handleInternal(tokens, 0, tokens.size());
    }

    private Optional<ASTExpressionNode> handleInternal(List<Token> tokens, int start, int end) {
        if (end - start == 1) {
            Token first = tokens.get(start);
            return Optional.of(parseValue(first));
        }
        Optional<Integer> plusOrMinusOptionalIndex = findOperator(tokens, precedence2, start, end);
        if (plusOrMinusOptionalIndex.isPresent()) {
            return getAstExpressionNode(tokens, start, end, plusOrMinusOptionalIndex.get());
        }
        Optional<Integer> divideOrMultiplyOptionalIndex = findOperator(tokens, precedence1, start, end);
        if (divideOrMultiplyOptionalIndex.isPresent()) {
            return getAstExpressionNode(tokens, start, end, divideOrMultiplyOptionalIndex.get());
        }
        return Optional.empty();
    }

    private Optional<ASTExpressionNode> getAstExpressionNode(List<Token> tokens, int start, int end, int index) {
        Optional<ASTExpressionNode> left = handleInternal(tokens, start, index);
        Optional<ASTExpressionNode> right = handleInternal(tokens, index + 1, end);
        Operator operator = OperatorMapper.fromTokenTypeToOperator(tokens.get(index).getType());

        if (left.isPresent() && right.isPresent()) {
            return Optional.of(new ExpressionNode(left.get(), right.get(), operator));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> findOperator(List<Token> tokens, List<TokenType> operators, int start, int end) {
        for (int i = start; i < end; i++) {
            TokenType type = tokens.get(i).getType();
            if (operators.stream().anyMatch(tokenType -> tokenType.equals(type))) return Optional.of(i);
        }
        return Optional.empty();
    }

    private ASTExpressionNode parseValue(Token token) {
        TokenType tokenType = token.getType();
        if (tokenType.equals(TokenType.NUMBER)) return new NumberNode(parseInt(token.getValue()));
        if (tokenType.equals(TokenType.IDENTIFIER)) return new IdentifierNode(token.getValue());
        if (tokenType.equals(TokenType.STRING)) return new StringNode(token.getValue());
        throw new ParseException("Not a valid expression");
    }
}
