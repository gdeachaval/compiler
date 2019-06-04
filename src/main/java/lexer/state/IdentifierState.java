package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;
import lexer.TokenType;

public class IdentifierState extends AbstractState implements LexerState {

    IdentifierState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.ALPHANUMERIC)) {
            adjustContext(character);
            return new IdentifierState(context, consumer);
        }
        if (charAsString.matches(Constants.NOT_ALPHANUMERIC)) {
            adjustContext(character);
            generateToken(getTokenType(context));
            return new CommonState(context, consumer);
        }
        return null;
    }

    @Override
    public void end() {
        generateToken(getTokenType(context));
    }

    private TokenType getTokenType(Context context) {
        String accum = context.getAccum();
        if (accum.equals("let")) {
            return TokenType.LET;
        }
        if (accum.equals("number")) {
            return TokenType.NUMBER_TYPE;
        }
        if (accum.equals("string")) {
            return TokenType.STRING_TYPE;
        }
        return TokenType.IDENTIFIER;
    }
}
