package parser.mapper;

import lexer.token.TokenType;
import parser.Operator;
import parser.ParseException;

public class OperatorMapper {
    private OperatorMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Operator fromTokenTypeToOperator(TokenType tokenType) {
        switch (tokenType) {
            case PLUS:
                return Operator.PLUS;
            case MINUS:
                return Operator.MINUS;
            case MULTIPLY:
                return Operator.MULTIPLY;
            case DIVIDE:
                return Operator.DIVIDE;
            default:
                throw new ParseException("not an operator");
        }
    }
}
