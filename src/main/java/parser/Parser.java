package parser;

import lexer.token.Token;

import java.util.List;

public interface Parser {

    void parse(List<Token> tokens);
}
