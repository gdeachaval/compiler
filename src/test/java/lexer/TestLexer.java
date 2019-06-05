package lexer;

import lexer.state.InitialState;
import lexer.state.LexerState;
import lexer.state.context.Context;
import lexer.state.context.ContextImpl;
import lexer.token.Token;
import lexer.token.TokenImpl;
import lexer.token.TokenType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestLexer {

    private Lexer lexer;
    private TokenConsumer consumer;

    @Before
    public void setup() {
        consumer = new TokenConsumer();
        Context context = new ContextImpl();
        LexerState state = new InitialState(context, consumer);
        lexer = new LexerAutomaton(state);
    }

    @Test
    public void test001SimpleString() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("\"test\"");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(6, 0, "\"test\"", TokenType.STRING);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test002SimpleNumber() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("123");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(3, 0, "123", TokenType.NUMBER);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test003SimpleIdentifier() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("identifier");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(10, 0, "identifier", TokenType.IDENTIFIER);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test004SimpleLet() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("let");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(3, 0, "let", TokenType.LET);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test005SimpleTypeNumber() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("number");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(6, 0, "number", TokenType.NUMBER_TYPE);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test006SimpleTypeString() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("string");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(6, 0, "string", TokenType.STRING_TYPE);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test007SimplePlusOperator() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("+");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(1, 0, "+", TokenType.PLUS);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test008SimpleMinusOperator() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("-");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(1, 0, "-", TokenType.MINUS);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test009SimpleMultiplyOperator() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("*");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(1, 0, "*", TokenType.MULTIPLY);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test010SimpleDivideOperator() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("/");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(1, 0, "/", TokenType.DIVIDE);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test011SimpleSpace() {
        // given
        Supplier<Character> supplier = new CharacterSupplier(" ");

        // when
        lexer.lex(supplier);

        // then
        Token expected = new TokenImpl(1, 0, " ", TokenType.SPACE);
        Token result = consumer.getResult().get(0);

        assertTokens(result, expected);
    }

    @Test
    public void test012SimpleOperation() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("2+2");

        // when
        lexer.lex(supplier);

        // then
        Token first = new TokenImpl(1, 0, "2", TokenType.NUMBER);
        Token second = new TokenImpl(2, 0, "+", TokenType.PLUS);
        Token third = new TokenImpl(3, 0, "2", TokenType.NUMBER);
        List<Token> expected = Arrays.asList(first, second, third);
        List<Token> result = consumer.getResult();

        assertTokenList(result, expected);
    }

    @Test
    public void test013OperationWithSpaces() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("2 + 2");

        // when
        lexer.lex(supplier);

        // then
        Token first = new TokenImpl(1, 0, "2", TokenType.NUMBER);
        Token second = new TokenImpl(2, 0, " ", TokenType.SPACE);
        Token third = new TokenImpl(3, 0, "+", TokenType.PLUS);
        Token fourth = new TokenImpl(4, 0, " ", TokenType.SPACE);
        Token fifth = new TokenImpl(5, 0, "2", TokenType.NUMBER);
        List<Token> expected = Arrays.asList(first, second, third, fourth, fifth);
        List<Token> result = consumer.getResult();

        assertTokenList(result, expected);
    }

    @Test
    public void test013IdentifierWithSpaces() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("a + a");

        // when
        lexer.lex(supplier);

        // then
        Token first = new TokenImpl(1, 0, "a", TokenType.IDENTIFIER);
        Token second = new TokenImpl(2, 0, " ", TokenType.SPACE);
        Token third = new TokenImpl(3, 0, "+", TokenType.PLUS);
        Token fourth = new TokenImpl(4, 0, " ", TokenType.SPACE);
        Token fifth = new TokenImpl(5, 0, "a", TokenType.IDENTIFIER);
        List<Token> expected = Arrays.asList(first, second, third, fourth, fifth);
        List<Token> result = consumer.getResult();

        assertTokenList(result, expected);
    }

    @Test
    public void test014SingleNewLine() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("\n");

        // when
        lexer.lex(supplier);

        // then
        Token result = consumer.getResult().get(0);
        Token expected = new TokenImpl(0, 1, "\n", TokenType.NEW_LINE);

        assertTokens(result, expected);
    }

    @Test
    public void test015NewLineThenIdentifier() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("\na");

        // when
        lexer.lex(supplier);

        // then
        List<Token> result = consumer.getResult();
        Token first = new TokenImpl(0, 1, "\n", TokenType.NEW_LINE);
        Token second = new TokenImpl(1, 1, "a", TokenType.IDENTIFIER);
        List<Token> expected = Arrays.asList(first, second);

        assertTokenList(result, expected);
    }

    @Test
    public void test016SimpleAssignationDeclaration() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("let foo=2");

        // when
        lexer.lex(supplier);

        // then
        List<Token> result = consumer.getResult();
        Token first = new TokenImpl(3, 0, "let", TokenType.LET);
        Token second = new TokenImpl(4, 0, " ", TokenType.SPACE);
        Token third = new TokenImpl(7, 0, "foo", TokenType.IDENTIFIER);
        Token fourth = new TokenImpl(8, 0, "=", TokenType.EQUALS);
        Token fifth = new TokenImpl(9, 0, "2", TokenType.NUMBER);
        List<Token> expected = Arrays.asList(first, second, third, fourth, fifth);

        assertTokenList(result, expected);
    }

    @Test
    public void test016SimpleAssignationDeclarationSpaced() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("let foo = 2");

        // when
        lexer.lex(supplier);

        // then
        List<Token> result = consumer.getResult();
        Token first = new TokenImpl(3, 0, "let", TokenType.LET);
        Token second = new TokenImpl(4, 0, " ", TokenType.SPACE);
        Token third = new TokenImpl(7, 0, "foo", TokenType.IDENTIFIER);
        Token fourth = new TokenImpl(8, 0, " ", TokenType.SPACE);
        Token fifth = new TokenImpl(9, 0, "=", TokenType.EQUALS);
        Token sixth = new TokenImpl(10, 0, " ", TokenType.SPACE);
        Token seventh = new TokenImpl(11, 0, "2", TokenType.NUMBER);
        List<Token> expected = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh);

        assertTokenList(result, expected);
    }

    @Test
    public void test017SimpleAssignationDeclarationWithType() {
        // given
        Supplier<Character> supplier = new CharacterSupplier("let foo:number = 2+2");

        // when
        lexer.lex(supplier);

        // then
        List<Token> result = consumer.getResult();
        Token first = new TokenImpl(3, 0, "let", TokenType.LET);
        Token second = new TokenImpl(4, 0, " ", TokenType.SPACE);
        Token third = new TokenImpl(7, 0, "foo", TokenType.IDENTIFIER);
        Token fourth = new TokenImpl(8, 0, ":", TokenType.COLON);
        Token fifth = new TokenImpl(14, 0, "number", TokenType.NUMBER_TYPE);
        Token sixth = new TokenImpl(15, 0, " ", TokenType.SPACE);
        Token seventh = new TokenImpl(16, 0, "=", TokenType.EQUALS);
        Token eight = new TokenImpl(17, 0, " ", TokenType.SPACE);
        Token ninth = new TokenImpl(18, 0, "2", TokenType.NUMBER);
        Token tenth = new TokenImpl(19, 0, "+", TokenType.PLUS);
        Token eleventh = new TokenImpl(20, 0, "2", TokenType.NUMBER);
        List<Token> expected = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight, ninth, tenth, eleventh);

        assertTokenList(result, expected);
    }


    private void assertTokens(Token actual, Token expected) {
        assertThat(actual.getColumn(), is(equalTo(expected.getColumn())));
        assertThat(actual.getLine(), is(equalTo(expected.getLine())));
        assertThat(actual.getType(), is(equalTo(expected.getType())));
        assertThat(actual.getValue(), is(equalTo(expected.getValue())));
    }

    private void assertTokenList(List<Token> actualList, List<Token> expectedList) {
        if (actualList.size() != expectedList.size()) throw new RuntimeException("Different size");
        for (int i = 0; i < actualList.size(); i++) {
            Token actual = actualList.get(i);
            Token expected = expectedList.get(i);
            assertThat(actual.getColumn(), is(equalTo(expected.getColumn())));
            assertThat(actual.getLine(), is(equalTo(expected.getLine())));
            assertThat(actual.getType(), is(equalTo(expected.getType())));
            assertThat(actual.getValue(), is(equalTo(expected.getValue())));
        }
    }
}
