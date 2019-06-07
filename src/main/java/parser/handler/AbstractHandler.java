package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.ASTNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

abstract class AbstractHandler implements Handler {
    private Rule rule;

    AbstractHandler(Rule rule) {
        this.rule = rule;
    }

    public Optional<ASTNode> handle(List<Token> tokens) {
        if (!rule.matches(getTokenTypes(tokens))) {
            return Optional.empty();
        }
        return handleInternal(tokens);
    }

    abstract Optional<ASTNode> handleInternal(List<Token> tokens);

    private List<TokenType> getTokenTypes(List<Token> tokens) {
        return tokens.stream().map(Token::getType).collect(Collectors.toList());
    }
}
