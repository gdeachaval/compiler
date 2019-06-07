package parser.rules;

import lexer.token.TokenType;

import java.util.List;

public class AssignationDeclarationRule extends AbstractRule {

    // let * = * (^print)
    @Override
    public boolean matches(List<TokenType> statement) {
        return startsWith(statement, TokenType.LET) &&
                contains(statement, TokenType.EQUALS) &&
                containsTypes(statement) &&
                doesntContain(statement, TokenType.PRINT) &&
                sizeBiggerOrEqualThan(statement, 4);
    }
}
