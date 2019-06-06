package parser.rules;

import lexer.token.TokenType;

import java.util.List;

abstract class AbstractRule implements Rule {

    boolean doesntContain(List<TokenType> statement, TokenType tokenType) {
        return statement.stream().noneMatch(st -> st.equals(tokenType));
    }

    boolean contains(List<TokenType> statement, TokenType tokenType) {
        return statement.stream().anyMatch(st -> st.equals(tokenType));
    }

    boolean startsWith(List<TokenType> statement, TokenType tokenType) {
        return statement.get(0).equals(tokenType);
    }
}
