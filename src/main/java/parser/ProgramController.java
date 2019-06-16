package parser;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.node.regular.ASTNode;
import parser.node.regular.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramController {
    private StatementController statementController;

    public ProgramController() {
        this.statementController = new StatementController();
    }

    ProgramNode parseProgram(List<Token> program) {
        ProgramNode programNode = new ProgramNode();
        getStatementsFromProgram(program)
                .forEach(statement -> {
                    ASTNode statementNode = statementController.parseStatement(statement);
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
