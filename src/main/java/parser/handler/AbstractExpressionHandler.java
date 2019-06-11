package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.node.ASTExpressionNode;
import parser.node.ASTNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractExpressionHandler {
    private Rule rule;

    AbstractExpressionHandler(Rule rule) {
        this.rule = rule;
    }

    public Optional<ASTExpressionNode> handle(List<Token> tokens) {
        if (!rule.matches(getTokenTypes(tokens))) {
            return Optional.empty();
        }
        return handleInternal(tokens);
    }

    abstract Optional<ASTExpressionNode> handleInternal(List<Token> tokens);

    private List<TokenType> getTokenTypes(List<Token> tokens) {
        return tokens.stream().map(Token::getType).collect(Collectors.toList());
    }
}
