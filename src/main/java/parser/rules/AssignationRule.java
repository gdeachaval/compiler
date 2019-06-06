package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public class AssignationRule extends AbstractRule {

    // (^let) (^print) =
    @Override
    public boolean matches(List<TokenType> statement) {
        return doesntContain(statement, TokenType.LET) &&
                doesntContain(statement, TokenType.PRINT) &&
                contains(statement, TokenType.EQUALS);
    }
}
