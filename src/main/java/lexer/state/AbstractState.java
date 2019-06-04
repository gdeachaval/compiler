package lexer.state;

import lexer.Context;
import lexer.Token;
import lexer.TokenConsumer;
import lexer.TokenImpl;
import lexer.TokenType;

abstract class AbstractState {
    Context context;
    TokenConsumer consumer;

    AbstractState(Context context, TokenConsumer consumer) {
        this.context = context;
        this.consumer = consumer;
    }

    void adjustContext(Character character) {
        context.addColumn();
        context.setAccum(context.getAccum() + character);
    }

    void generateToken(TokenType tokenType) {
        Token token = new TokenImpl(context.getColumn(), context.getLine(), context.getAccum(), tokenType);
        consumer.accept(token);
        context.setAccum("");
    }
}
