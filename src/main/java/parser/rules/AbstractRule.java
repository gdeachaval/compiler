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

    boolean endsWith(List<TokenType> statement, TokenType tokenType) {
        return statement.get(statement.size() - 1).equals(tokenType);
    }

    boolean secondIs(List<TokenType> statement, TokenType tokenType) {
        return statement.get(1).equals(tokenType);
    }

    boolean sizeBiggerOrEqualThan(List<TokenType> statement, int size) {
        return statement.size() >= size;
    }

    boolean containsTypes(List<TokenType> statement) {
        return statement.stream().anyMatch(st -> st.equals(TokenType.STRING_TYPE) || st.equals(TokenType.NUMBER_TYPE));
    }

    boolean oddAmount(List<TokenType> tokenTypes) {
        return tokenTypes.size() % 2 == 1;
    }
}
