package lexer;

public class TokenImpl implements Token {
    private int column;
    private int line;
    private TokenType tokenType;
    private String value;

    public TokenImpl(int column, int line, String value, TokenType tokenType) {
        this.column = column;
        this.line = line;
        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public TokenType getType() {
        return tokenType;
    }

    @Override
    public String getValue() {
        return value;
    }
}
