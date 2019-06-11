package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.node.ASTExpressionNode;
import parser.node.ASTNode;
import parser.Operator;
import parser.ParseException;
import parser.node.ArithmeticOperationNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.StringConcatenationNode;
import parser.node.StringNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class ExpressionHandler extends AbstractExpressionHandler {

    public ExpressionHandler(Rule rule) {
        super(rule);
    }

    @Override
    Optional<ASTExpressionNode> handleInternal(List<Token> tokens) {
        if (tokens.size() == 1) {
            Token first = tokens.get(0);
            return Optional.of(parseValue(first));
        }
        return Optional.of(parseOperation(tokens));
    }

    private ASTExpressionNode parseValue(Token token) {
        TokenType tokenType = token.getType();
        if (tokenType.equals(TokenType.NUMBER)) return new NumberNode(parseInt(token.getValue()));
        if (tokenType.equals(TokenType.IDENTIFIER)) return new IdentifierNode(token.getValue());
        if (tokenType.equals(TokenType.STRING)) return new StringNode(token.getValue().substring(1, token.getValue().length()-1));
        throw new ParseException("Not a valid expression");
    }

    private ASTExpressionNode parseOperation(List<Token> tokens) {
        final Token first = tokens.get(0);
        final Token operator = tokens.get(1);
        final Token second = tokens.get(2);

        if (first.getType().equals(TokenType.STRING) || second.getType().equals(TokenType.STRING)) {
            if (operator.getType().equals(TokenType.PLUS)) {
                return new StringConcatenationNode(first.getValue(), second.getValue());
            } else {
                throw new ParseException("only string concat (+) supported");
            }
        }
        else return new ArithmeticOperationNode(parseInt(first.getValue()), parseInt(second.getValue()), toOperator(operator.getType()));
    }

    private Operator toOperator(TokenType tokenType) {
        switch (tokenType) {
            case PLUS: return Operator.PLUS;
            case MINUS: return Operator.MINUS;
            case MULTIPLY: return Operator.MULTIPLY;
            case DIVIDE: return Operator.DIVIDE;
            default: throw new ParseException("not an operator");
        }
    }
}
