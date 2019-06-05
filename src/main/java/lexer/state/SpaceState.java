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
        if (charAsString.matches(Constants.OPERATOR)) {
            generateToken(TokenType.SPACE);
            adjustContext(character);
            return new OperatorState(context, consumer);
        }
        if (charAsString.matches(Constants.NUMBER)) {
            generateToken(TokenType.SPACE);
            adjustContext(character);
            return new NumberState(context, consumer);
        }
        return null;
    }

    @Override
    public void end() {
        // ignore
    }
}
