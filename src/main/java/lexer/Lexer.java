package lexer;

import lexer.token.Token;

import java.util.List;
import java.util.function.Supplier;

public interface Lexer {
    List<Token> lex(Supplier<Character> supplier);
}
