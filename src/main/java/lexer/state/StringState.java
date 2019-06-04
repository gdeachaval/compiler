package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;
import lexer.TokenType;

public class StringState extends AbstractState implements LexerState {

    StringState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.NOT_A_DOUBLE_QUOTE)) {
            adjustContext(character);
            return new StringState(context, consumer);
        }
        if (charAsString.matches(Constants.DOUBLE_QUOTE)) {
            adjustContext(character);
            generateToken(TokenType.STRING);
            return new CommonState(context, consumer);
        }
        return null;
    }

    @Override
    public void end() {
        // ignore
    }
}
