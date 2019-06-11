package parser.rules;

import lexer.token.TokenType;

import java.util.Arrays;
import java.util.List;

public class ExpressionRule extends AbstractRule {

    private static final List<TokenType> operands = Arrays.asList(TokenType.NUMBER, TokenType.IDENTIFIER, TokenType.STRING);
    private static final List<TokenType> operators = Arrays.asList(TokenType.PLUS, TokenType.MULTIPLY, TokenType.MINUS, TokenType.DIVIDE);

    @Override
    public boolean matches(List<TokenType> statement) {
        return oddAmount(statement) &&
                isExpression(statement);
    }

    private boolean isExpression(List<TokenType> statement) {
        if (statement.isEmpty()) return false;
        boolean matchOperand = true;
        for (TokenType tokenType : statement) {
            if (matchOperand) {
                matchOperand = false;
                if (!operands.contains(tokenType)) return false;
            } else {
                matchOperand = true;
                if (!operators.contains(tokenType)) return false;
            }
        }
        return true;
    }
}
