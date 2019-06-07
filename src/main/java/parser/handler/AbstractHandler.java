package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;

import java.util.List;
import java.util.stream.Collectors;

abstract class AbstractHandler implements Handler {

    List<TokenType> getTokenTypes(List<Token> tokens) {
        return tokens.stream().map(Token::getType).collect(Collectors.toList());
    }
}
