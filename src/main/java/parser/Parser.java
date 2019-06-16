package parser;

import lexer.token.Token;
import parser.node.regular.ASTNode;

import java.util.List;

public interface Parser {

    ASTNode parse(List<Token> tokens);
}
