package lexer;

import lexer.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TokenConsumer implements Consumer<Token> {

    private List<Token> tokenList;

    public TokenConsumer() {
        tokenList = new ArrayList<>();
    }

    @Override
    public void accept(Token token) {
        tokenList.add(token);
    }

    List<Token> getResult() {
        return tokenList;
    }
}
