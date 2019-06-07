package parser;

import lexer.token.Token;

import java.util.List;

public interface Parser {

    ASTNode parse(List<Token> tokens);
}
