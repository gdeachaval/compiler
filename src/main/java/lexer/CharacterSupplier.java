package lexer;

import java.util.function.Supplier;

public class CharacterSupplier implements Supplier<Character> {
    private String string;
    private int actual;

    public CharacterSupplier(String string) {
        this.string = string;
        this.actual = 0;
    }

    @Override
    public Character get() {
        Character result = null;
        if (string.length() > actual) {
            result = string.charAt(actual++);
        }
        return result;
    }
}
