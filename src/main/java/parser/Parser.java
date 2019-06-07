package parser;

import lexer.token.Token;
import parser.node.ProgramNode;

import java.util.List;

public interface Parser {

    ProgramNode parse(List<Token> tokens);
}
