package lexer.state;

import common.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.TokenType;

public class StringState extends AbstractState {

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
            return new InitialState(context, consumer);
        }
        return null;
    }

    @Override
    public void end() {
        // ignore
    }
}
