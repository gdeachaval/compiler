package parser;

import lexer.token.Token;
import lexer.token.TokenImpl;
import lexer.token.TokenType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TestParser {

    private Parser parser;

    @Before
    public void setUp() {
        parser = new ParserImpl();
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

        ASTNode programNode = parser.parse(simplePrint);
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

        ASTNode programNode = parser.parse(simplePrint);
        assertThat(programNode.getChildren(), hasSize(1));
    }

    @Test (expected = ParseException.class)
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

        ASTNode programNode = parser.parse(simplePrint);
        assertThat(programNode.getChildren(), hasSize(1));
    }
}
