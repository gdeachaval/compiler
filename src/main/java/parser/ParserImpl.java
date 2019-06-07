package parser;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.handler.ProgramHandler;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {

    @Override
    public ASTNode parse(List<Token> program) {
        ProgramHandler programHandler = new ProgramHandler();
        ArrayList<Token> filteredProgram = removeSpaces(program);
        return programHandler.handle(filteredProgram);
    }

    private ArrayList<Token> removeSpaces(List<Token> program) {
        ArrayList<Token> wrapper = new ArrayList<>(program);
        wrapper.removeIf(t -> t.getType().equals(TokenType.SPACE) || t.getType().equals(TokenType.NEW_LINE));
        return wrapper;
    }
}
