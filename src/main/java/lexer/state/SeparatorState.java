package lexer.state;

import lexer.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.TokenType;

public class SeparatorState extends AbstractState {
    SeparatorState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.NEW_LINE)) {
            context.setLine(context.getLine() + 1);
            context.setColumn(0);
            context.setAccum(context.getAccum() + character);
            return new SeparatorState(context, consumer);
        }
        if (charAsString.matches(Constants.SEPARATOR)) {
            adjustContext(character);
            return new SeparatorState(context, consumer);
        }
        return defaultHandle(character, getTokenType(context));
    }

    @Override
    public void end() {
        generateToken(TokenType.SPACE);
    }

    private TokenType getTokenType(Context context) {
        String accum = context.getAccum();
        if (accum.equals(" ")) {
            return TokenType.SPACE;
        }
        if (accum.equals(";")) {
            return TokenType.SEMICOLON;
        }
        if (accum.equals(":")) {
            return TokenType.COLON;
        }
        if (accum.equals("\n")) {
            return TokenType.NEW_LINE;
        }
        return null;
    }
}
