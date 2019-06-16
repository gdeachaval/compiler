package lexer.state;

import common.Constants;
import lexer.TokenConsumer;
import lexer.state.context.Context;
import lexer.token.TokenType;

public class NumberState extends AbstractState {

    NumberState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.NUMBER)) {
            adjustContext(character);
            return new NumberState(context, consumer);
        }
        return defaultHandle(character, TokenType.NUMBER);
    }

    @Override
    public void end() {
        generateToken(TokenType.NUMBER);
    }
}
