package parser;

import lexer.token.Token;
import lexer.token.TokenImpl;
import lexer.token.TokenType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TestProgramController {

    @Test
    public void testSplitStatements() {
        ProgramController programController = new ProgramController();
        Token let = new TokenImpl(0, 0, "let", TokenType.LET);
        Token var = new TokenImpl(0, 0, "i", TokenType.IDENTIFIER);
        Token equals = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token numb = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token semicolon = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        Token print = new TokenImpl(0, 0, "print", TokenType.PRINT);
        Token lparenthesis = new TokenImpl(0, 0, "(", TokenType.LPARENTHESIS);
        Token rparenthesis = new TokenImpl(0, 0, ")", TokenType.RPARENTHESIS);
        List<Token> program = Arrays.asList(let, var, equals, numb, semicolon, print, lparenthesis, var, rparenthesis, semicolon);
        List<List<Token>> result = programController.getStatementsFromProgram(program);

        assertThat(result, hasSize(2));
    }

    @Test(expected = ParseException.class)
    public void testSplitStatementNotEndingWithSemicolonShouldThrowException() {
        ProgramController programController = new ProgramController();
        Token let = new TokenImpl(0, 0, "let", TokenType.LET);
        Token var = new TokenImpl(0, 0, "i", TokenType.IDENTIFIER);
        Token equals = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token numb = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token semicolon = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        Token print = new TokenImpl(0, 0, "print", TokenType.PRINT);
        Token lparenthesis = new TokenImpl(0, 0, "(", TokenType.LPARENTHESIS);
        Token rparenthesis = new TokenImpl(0, 0, ")", TokenType.RPARENTHESIS);
        List<Token> program = Arrays.asList(let, var, equals, numb, semicolon, print, lparenthesis, var, rparenthesis);
        programController.getStatementsFromProgram(program);
    }
}
