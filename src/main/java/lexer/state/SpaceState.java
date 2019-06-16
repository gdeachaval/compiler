package lexer.state;

import common.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.TokenType;

public class SpaceState extends AbstractState {
    SpaceState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.NEW_LINE)) {
            adjustContextNewLine(character);
            return new SpaceState(context, consumer);
        }
        if (charAsString.matches(Constants.SPACE)) {
            adjustContext(character);
            return new SpaceState(context, consumer);
        }
        return defaultHandle(character, getTokenType(context));
    }

    @Override
    public void end() {
        generateToken(getTokenType(context));
    }

    private TokenType getTokenType(Context context) {
        String accum = context.getAccum();
        if (accum.matches(Constants.NEW_LINES)) {
            return TokenType.NEW_LINE;
        }
        if (accum.matches(Constants.SPACES)) {
            return TokenType.SPACE;
        }
        return null;
    }
}
