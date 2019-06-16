package lexer.state;

import common.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.TokenType;

public class IdentifierState extends AbstractState {

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
        return defaultHandle(character, getTokenType(context));
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
        if (accum.equals("print")) {
            return TokenType.PRINT;
        }
        return TokenType.IDENTIFIER;
    }
}
