package lexer;

public interface Token {
    int getColumn();

    int getLine();

    TokenType getType();

    String getValue();
}
