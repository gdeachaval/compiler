package parser;

import lexer.token.Token;
import lexer.token.TokenImpl;
import lexer.token.TokenType;
import org.junit.Before;
import org.junit.Test;
import parser.node.regular.ProgramNode;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TestParser {

    private Parser parser;

    @Before
    public void setUp() {
        parser = new ParserImpl(new ProgramController());
    }

    @Test
    public void testSimpleOperationPrintParse() {
        Token first = new TokenImpl(5, 0, "print", TokenType.PRINT);
        Token second = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token third = new TokenImpl(7, 0, "2", TokenType.NUMBER);
        Token fourth = new TokenImpl(8, 0, "+", TokenType.PLUS);
        Token fifth = new TokenImpl(9, 0, "2", TokenType.NUMBER);
        Token sixth = new TokenImpl(10, 0, ")", TokenType.RPARENTHESIS);
        Token seventh = new TokenImpl(11, 0, ";", TokenType.SEMICOLON);
        List<Token> simplePrint = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh);

        ProgramNode programNode = (ProgramNode) parser.parse(simplePrint);
        assertThat(programNode.getChildren(), hasSize(1));

    }

    @Test
    public void testSimpleIdentifierPrintParse() {
        Token first = new TokenImpl(5, 0, "print", TokenType.PRINT);
        Token second = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token third = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token fourth = new TokenImpl(10, 0, ")", TokenType.RPARENTHESIS);
        Token fifth = new TokenImpl(11, 0, ";", TokenType.SEMICOLON);
        List<Token> simplePrint = Arrays.asList(first, second, third, fourth, fifth);

        parser.parse(simplePrint);
    }

    @Test(expected = ParseException.class)
    public void testMalformedPrintParse() {
        Token first = new TokenImpl(5, 0, "print", TokenType.PRINT);
        Token second = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token third = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token fourth = new TokenImpl(10, 0, ")", TokenType.RPARENTHESIS);
        Token fifth = new TokenImpl(11, 0, ";", TokenType.SEMICOLON);
        List<Token> simplePrint = Arrays.asList(first, second, third, fourth, fifth);

        parser.parse(simplePrint);
    }

    @Test
    public void testSpacedPrintParse() {
        Token first = new TokenImpl(5, 0, "print", TokenType.PRINT);
        Token second = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token third = new TokenImpl(6, 0, " ", TokenType.SPACE);
        Token fourth = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token fifth = new TokenImpl(0, 0, " ", TokenType.SPACE);
        Token sixth = new TokenImpl(10, 0, ")", TokenType.RPARENTHESIS);
        Token seventh = new TokenImpl(11, 0, ";", TokenType.SEMICOLON);
        List<Token> simplePrint = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh);

        parser.parse(simplePrint);

    }

    @Test(expected = ParseException.class)
    public void testMalformedPrint() {
        Token zero = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token first = new TokenImpl(5, 0, "print", TokenType.PRINT);
        Token second = new TokenImpl(6, 0, "(", TokenType.LPARENTHESIS);
        Token third = new TokenImpl(6, 0, " ", TokenType.SPACE);
        Token fourth = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token fifth = new TokenImpl(0, 0, " ", TokenType.SPACE);
        Token sixth = new TokenImpl(10, 0, ")", TokenType.RPARENTHESIS);
        Token seventh = new TokenImpl(11, 0, ";", TokenType.SEMICOLON);
        List<Token> simplePrint = Arrays.asList(zero, first, second, third, fourth, fifth, sixth, seventh);

        parser.parse(simplePrint);
    }

    @Test
    public void testSimpleDeclarationParse() {
        Token first = new TokenImpl(0, 0, "let", TokenType.LET);
        Token second = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token third = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleDeclaration = Arrays.asList(first, second, third);

        parser.parse(simpleDeclaration);

    }

    @Test(expected = ParseException.class)
    public void testSimpleDeclarationMisformedParse() {
        Token first = new TokenImpl(0, 0, "let", TokenType.LET);
        Token second = new TokenImpl(0, 0, "\"foo\"", TokenType.STRING);
        Token third = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleDeclaration = Arrays.asList(first, second, third);

        parser.parse(simpleDeclaration);
    }

    @Test
    public void testSimpleAssignationDeclarationWithTypeParse() {
        Token first = new TokenImpl(0, 0, "let", TokenType.LET);
        Token second = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token third = new TokenImpl(0, 0, ":", TokenType.COLON);
        Token fourth = new TokenImpl(0, 0, "string", TokenType.STRING_TYPE);
        Token fifth = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token sixth = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token seventh = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleAssignationDeclarationWithType = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh);

        parser.parse(simpleAssignationDeclarationWithType);

    }

    @Test
    public void testSimpleAssignationDeclarationWithTypeParseAndArithmeticExpression() {
        Token first = new TokenImpl(0, 0, "let", TokenType.LET);
        Token second = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token third = new TokenImpl(0, 0, ":", TokenType.COLON);
        Token fourth = new TokenImpl(0, 0, "string", TokenType.STRING_TYPE);
        Token fifth = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token sixth = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token seventh = new TokenImpl(0, 0, "*", TokenType.MULTIPLY);
        Token eight = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token ninth = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleAssignationDeclarationWithType = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight, ninth);

        parser.parse(simpleAssignationDeclarationWithType);

    }

    @Test
    public void testSimpleAssignation() {
        Token first = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token second = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token third = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token fourth = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleAssignation = Arrays.asList(first, second, third, fourth);

        parser.parse(simpleAssignation);

    }

    @Test
    public void testComplexAssignation() {
        Token first = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token second = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token third = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token fourth = new TokenImpl(0, 0, "+", TokenType.PLUS);
        Token fifth = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token sixth = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleAssignation = Arrays.asList(first, second, third, fourth, fifth, sixth);

        parser.parse(simpleAssignation);

    }

    @Test
    public void testComplexAssignation2() {
        Token first = new TokenImpl(0, 0, "foo", TokenType.IDENTIFIER);
        Token second = new TokenImpl(0, 0, "=", TokenType.EQUALS);
        Token third = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token fourth = new TokenImpl(0, 0, "+", TokenType.PLUS);
        Token fifth = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token sixth = new TokenImpl(0, 0, "*", TokenType.MULTIPLY);
        Token seventh = new TokenImpl(0, 0, "2", TokenType.NUMBER);
        Token eight = new TokenImpl(0, 0, ";", TokenType.SEMICOLON);
        List<Token> simpleAssignation = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight);

        parser.parse(simpleAssignation);
    }
}
