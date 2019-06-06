package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public interface Rule {
    boolean matches(List<TokenType> statement);
}
