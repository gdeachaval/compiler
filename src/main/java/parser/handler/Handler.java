package parser.handler;

import lexer.token.Token;

import java.util.List;
import java.util.Optional;

public interface Handler<T> {
    Optional<T> handle(List<Token> tokens);
}
