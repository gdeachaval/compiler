package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public class ExpressionRule extends AbstractRule {

    // (^let) (^print) (^=)
    @Override
    public boolean matches(List<TokenType> statement) {
        return doesntContain(statement, TokenType.LET) &&
                doesntContain(statement, TokenType.EQUALS) &&
                doesntContain(statement, TokenType.PRINT);
    }
}
