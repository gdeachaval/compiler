package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;
import lexer.TokenType;

public class SpaceState extends AbstractState implements LexerState {
    SpaceState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.SPACE)) {
            adjustContext(character);
            return new SpaceState(context, consumer);
        }
        return defaultHandle(character, TokenType.SPACE);
    }

    @Override
    public void end() {
        generateToken(TokenType.SPACE);
    }
}
