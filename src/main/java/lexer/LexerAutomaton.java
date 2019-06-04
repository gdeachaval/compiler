package lexer;

import lexer.state.LexerState;

import java.util.function.Supplier;

public class LexerAutomaton implements Lexer {

    private LexerState actualState;

    LexerAutomaton(LexerState actualState) {
        this.actualState = actualState;
    }

    @Override
    public void lex(Supplier<Character> supplier) {
        Character actual = supplier.get();
        while (actual != null) {
            actualState = actualState.next(actual);
            actual = supplier.get();
        }
        actualState.end();
    }
}
