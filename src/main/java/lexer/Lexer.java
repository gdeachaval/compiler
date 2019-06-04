package lexer;

import java.util.function.Supplier;

public interface Lexer {
    void lex(Supplier<Character> supplier);
}
