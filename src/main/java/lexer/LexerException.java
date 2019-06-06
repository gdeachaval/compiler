package lexer;

import lexer.state.context.Context;

public class LexerException extends RuntimeException {
    public LexerException(Context context) {
        super("Lex exception in line: " + context.getLine() + " and column: " + context.getColumn());
    }
}
