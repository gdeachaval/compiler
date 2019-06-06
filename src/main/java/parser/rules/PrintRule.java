package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public class PrintRule extends AbstractRule {

    // PRINT *
    @Override
    public boolean matches(List<TokenType> statement) {
        return startsWith(statement, TokenType.PRINT);
    }
}
