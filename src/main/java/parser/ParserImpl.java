package parser;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.node.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {

    @Override
    public ProgramNode parse(List<Token> program) {
        ProgramController programController = new ProgramController();
        ArrayList<Token> filteredProgram = removeSpaces(program);
        return programController.parseProgram(filteredProgram);
    }

    private ArrayList<Token> removeSpaces(List<Token> program) {
        ArrayList<Token> wrapper = new ArrayList<>(program);
        wrapper.removeIf(t -> t.getType().equals(TokenType.SPACE) || t.getType().equals(TokenType.NEW_LINE));
        return wrapper;
    }
}
