package lexer.state;

import lexer.Constants;
import lexer.Context;
import lexer.TokenConsumer;

public class CommonState extends AbstractState implements LexerState {

    public CommonState(Context context, TokenConsumer consumer) {
        super(context, consumer);
    }

    @Override
    public LexerState next(Character character) {
        String charAsString = character.toString();
        if (charAsString.matches(Constants.DOUBLE_QUOTE)) {
            adjustContext(character);
            return new StringState(context, consumer);
        }
        if (charAsString.matches(Constants.NUMBER)) {
            adjustContext(character);
            return new NumberState(context, consumer);
        }
        if (charAsString.matches(Constants.LETTER)) {
            adjustContext(character);
            return new IdentifierState(context, consumer);
        }
        if (charAsString.matches(Constants.OPERATOR)) {
            adjustContext(character);
            return new OperatorState(context, consumer);
        } else return null;
    }

    @Override
    public void end() {
        // ignore
    }
}
