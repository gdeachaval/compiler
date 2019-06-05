package lexer.state;

import lexer.Constants;
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

    LexerState defaultHandle(Character character, TokenType self) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.SPACE)) {
            generateToken(self);
            adjustContext(character);
            return new SpaceState(context, consumer);
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
