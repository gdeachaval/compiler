package parser.rules;

import lexer.token.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionRule extends AbstractRule {

    private static final List<TokenType> operands = Arrays.asList(TokenType.NUMBER, TokenType.IDENTIFIER, TokenType.STRING);
    private static final List<TokenType> operators = Arrays.asList(TokenType.PLUS, TokenType.MULTIPLY, TokenType.MINUS, TokenType.DIVIDE);

    @Override
    public boolean matches(List<TokenType> statement) {
        return oddAmount(statement) &&
                isExpression2(statement);
    }

    private boolean isExpression2(List<TokenType> statement) {
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

    private boolean isExpression(List<TokenType> statement) {
        if (statement.isEmpty()) return false;
        if (statement.size() == 1) return operands.contains(statement.get(0));
        if (statement.size() == 3) {
            return !operands.contains(statement.get(0)) ||
                    !operators.contains(statement.get(1)) ||
                    !operands.contains(statement.get(2));
        }

        List<TokenType> temp = new ArrayList<>(statement);
        boolean result = true;
        while (!temp.isEmpty()) {
            if (!operands.contains(temp.get(0)) || !operators.contains(temp.get(1))) result = false;
            if (temp.size() == 2) break;
            temp = temp.subList(3, temp.size());
        }
        return result;
    }
}
