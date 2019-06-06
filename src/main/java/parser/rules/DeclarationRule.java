package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public class DeclarationRule extends AbstractRule {

    // let * (^=)
    @Override
    public boolean matches(List<TokenType> statement) {
        return startsWith(statement, TokenType.LET) &&
                doesntContain(statement, TokenType.EQUALS);
    }
}
