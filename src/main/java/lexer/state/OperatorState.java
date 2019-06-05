package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;
import lexer.TokenType;

public class OperatorState extends AbstractState implements LexerState {

    OperatorState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.OPERATOR)) {
            adjustContext(character);
            return new OperatorState(context, consumer);
        }
        return defaultHandle(character, getTokenType(context))
;    }

    @Override
    public void end() {
        generateToken(getTokenType(context));
    }

    private TokenType getTokenType(Context context) {
        String accum = context.getAccum();
        if (accum.equals("+")) {
            return TokenType.PLUS;
        }
        if (accum.equals("-")) {
            return TokenType.MINUS;
        }
        if (accum.equals("*")) {
            return TokenType.MULTIPLY;
        }
        if (accum.equals("/")) {
            return TokenType.DIVIDE;
        }
        return null;
    }
}
