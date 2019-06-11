package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

abstract class AbstractHandler<T> implements Handler<T> {
    private Rule rule;

    AbstractHandler(Rule rule) {
        this.rule = rule;
    }

    public Optional<T> handle(List<Token> tokens) {
        if (!rule.matches(getTokenTypes(tokens))) {
            return Optional.empty();
        }
        return handleInternal(tokens);
    }

    abstract Optional<T> handleInternal(List<Token> tokens);

    private List<TokenType> getTokenTypes(List<Token> tokens) {
        return tokens.stream().map(Token::getType).collect(Collectors.toList());
    }
}
