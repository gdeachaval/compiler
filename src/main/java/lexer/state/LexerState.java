package lexer.state;

public interface LexerState {
    LexerState next(Character character);

    void end();

}
