package lexer;

import lexer.state.InitialState;
import lexer.state.LexerState;
import lexer.state.context.ContextImpl;
import lexer.token.Token;

import java.util.List;
import java.util.function.Supplier;

public class LexerAutomaton implements Lexer {

    private LexerState actualState;
    private TokenConsumer consumer;

    public LexerAutomaton(TokenConsumer consumer) {
        this.consumer = consumer;
        actualState = new InitialState(new ContextImpl(), consumer);
    }

    @Override
    public List<Token> lex(Supplier<Character> supplier) {
        Character actual = supplier.get();
        while (actual != null) {
            actualState = actualState.next(actual);
            actual = supplier.get();
        }
        actualState.end();
        return consumer.getResult();
    }
}
