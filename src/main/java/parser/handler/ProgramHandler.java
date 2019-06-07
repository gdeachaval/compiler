package parser.handler;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.ASTNode;
import parser.ParseException;
import parser.node.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramHandler {
    private StatementHandler statementHandler;

    public ProgramHandler() {
        this.statementHandler = new StatementHandler();
    }

    public ASTNode handle(List<Token> tokens) {
        ProgramNode programNode = new ProgramNode();
        getStatementsFromProgram(tokens)
                .forEach(statement -> {
                    ASTNode statementNode = statementHandler.handle(statement);
                    programNode.addChild(statementNode);
                });
        return programNode;
    }

    List<List<Token>> getStatementsFromProgram(List<Token> tokens) {
        List<List<Token>> result = new ArrayList<>();
        List<Token> accum = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getType().equals(TokenType.SEMICOLON)) {
                result.add(accum);
                accum = new ArrayList<>();
            } else {
                accum.add(token);
            }
        }
        if (!accum.isEmpty()) throw new ParseException("statement does not end with semicolon");
        return result;
    }
}
