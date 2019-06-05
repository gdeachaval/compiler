package lexer.state;

import lexer.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.Token;
import lexer.token.TokenImpl;
import lexer.token.TokenType;

abstract class AbstractState implements LexerState {
    Context context;
    TokenConsumer consumer;

    AbstractState(Context context, TokenConsumer consumer) {
        this.context = context;
        this.consumer = consumer;
    }

    void adjustContext(Character character) {
        context.setColumn(context.getColumn() + 1);
        context.setAccum(context.getAccum() + character);
    }

    void generateToken(TokenType tokenType) {
        Token token = new TokenImpl(context.getColumn(), context.getLine(), context.getAccum(), tokenType);
        consumer.accept(token);
        context.setAccum("");
    }

    LexerState defaultHandle(Character character, TokenType self) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.SPACE)) {
            generateToken(self);
            adjustContext(character);
            return new SeparatorState(context, consumer);
        }
        if (charAsString.matches(Constants.OPERATOR)) {
            generateToken(self);
            adjustContext(character);
            return new OperatorState(context, consumer);
        }
        if (charAsString.matches(Constants.NUMBER)) {
            generateToken(self);
            adjustContext(character);
            return new NumberState(context, consumer);
        }
        if (charAsString.matches(Constants.LETTER)) {
            generateToken(self);
            adjustContext(character);
            return new IdentifierState(context, consumer);
        }
        return null;
    }
}
