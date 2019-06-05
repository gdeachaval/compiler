package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;
import lexer.TokenType;

public class NumberState extends AbstractState implements LexerState {

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
