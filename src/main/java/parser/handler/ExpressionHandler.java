package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.ASTNode;
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

public class ExpressionHandler extends AbstractHandler {

    public ExpressionHandler(Rule rule) {
        super(rule);
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        if (tokens.size() == 1) {
            Token first = tokens.get(0);
            return Optional.of(parseValue(first));
        }
        return parseOperation(tokens);
    }

    private ASTNode parseValue(Token token) {
        TokenType tokenType = token.getType();
        if (tokenType.equals(TokenType.NUMBER)) return new NumberNode(parseInt(token.getValue()));
        if (tokenType.equals(TokenType.IDENTIFIER)) return new IdentifierNode(token.getValue());
        if (tokenType.equals(TokenType.STRING)) return new StringNode(token.getValue());
        throw new ParseException("Not a valid expression");
    }

    private Optional<ASTNode> parseOperation(List<Token> tokens) {
        final Token first = tokens.get(0);
        final Token operator = tokens.get(1);
        final Token second = tokens.get(2);

        if (first.getType().equals(TokenType.STRING) || second.getType().equals(TokenType.STRING)) {
            return Optional.of(new StringConcatenationNode(first.getValue(), second.getValue()));
        }
        // nodos?
        ArithmeticOperationNode result = new ArithmeticOperationNode(parseInt(first.getValue()), parseInt(second.getValue()), operator.getValue());
        return Optional.of(result);
    }
}
