package lexer.state;

import lexer.Constants;
import lexer.LexerException;
import lexer.TokenConsumer;
import lexer.state.context.Context;

public class InitialState extends AbstractState {

    public InitialState(Context context, TokenConsumer consumer) {
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
        }
        if (charAsString.matches(Constants.NEW_LINE)) {
            adjustContextNewLine(character);
            return new SpaceState(context, consumer);
        }
        if (charAsString.matches(Constants.SPACE)) {
            adjustContext(character);
            return new SpaceState(context, consumer);
        }
        if (charAsString.matches(Constants.SEPARATOR)) {
            adjustContext(character);
            return new SeparatorState(context, consumer);
        }
        throw new LexerException(context);
    }

    @Override
    public void end() {
        // ignore
    }
}
