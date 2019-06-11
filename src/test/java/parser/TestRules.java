package parser;

import lexer.token.TokenType;
import org.junit.Test;
import parser.rules.AssignationDeclarationRule;
import parser.rules.AssignationRule;
import parser.rules.DeclarationRule;
import parser.rules.ExpressionRule;
import parser.rules.PrintRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestRules {

    private PrintRule printRule;
    private DeclarationRule declarationRule;
    private AssignationRule assignationRule;
    private AssignationDeclarationRule assignationDeclarationRule;
    private ExpressionRule expressionRule;

    public TestRules() {
        printRule = new PrintRule();
        declarationRule = new DeclarationRule();
        assignationRule = new AssignationRule();
        assignationDeclarationRule = new AssignationDeclarationRule();
        expressionRule = new ExpressionRule();
    }

    @Test
    public void test001MatchSimplePrintSequence() {
        List<TokenType> simplePrint = Arrays.asList(
                TokenType.PRINT,
                TokenType.LPARENTHESIS,
                TokenType.NUMBER,
                TokenType.RPARENTHESIS);
        assertTrue(printRule.matches(simplePrint));
    }

    @Test
    public void test002MatchSimpleDeclaration() {
        List<TokenType> simpleDeclaration = Arrays.asList(
                TokenType.LET,
                TokenType.IDENTIFIER);
        assertTrue(declarationRule.matches(simpleDeclaration));
    }

    @Test
    public void test003MatchSimpleAssignation() {
        List<TokenType> simpleAssignation = Arrays.asList(
                TokenType.IDENTIFIER,
                TokenType.EQUALS,
                TokenType.NUMBER);
        assertTrue(assignationRule.matches(simpleAssignation));
    }

    @Test
    public void test004MatchSimpleAssignationDeclaration() {
        List<TokenType> simpleAssignationDeclaration = Arrays.asList(
                TokenType.LET,
                TokenType.IDENTIFIER,
                TokenType.COLON,
                TokenType.STRING_TYPE,
                TokenType.EQUALS,
                TokenType.NUMBER);
        assertTrue(assignationDeclarationRule.matches(simpleAssignationDeclaration));
    }

    @Test
    public void test005MatchSimpleExpression() {
        List<TokenType> simpleExpression = Arrays.asList(
                TokenType.NUMBER,
                TokenType.PLUS,
                TokenType.NUMBER);
        assertTrue(expressionRule.matches(simpleExpression));
    }

    @Test
    public void test006MatchSingleExpression() {
        List<TokenType> simpleExpression = Collections.singletonList(TokenType.NUMBER);
        assertTrue(expressionRule.matches(simpleExpression));
    }

    @Test
    public void test007MatchSingleExpression2() {
        List<TokenType> simpleExpression = Collections.singletonList(TokenType.STRING);
        assertTrue(expressionRule.matches(simpleExpression));
    }

    @Test
    public void test008MatchComplexExpression() {
        List<TokenType> complexExpression = Arrays.asList(
                TokenType.NUMBER,
                TokenType.PLUS,
                TokenType.STRING,
                TokenType.DIVIDE,
                TokenType.NUMBER);
        assertTrue(expressionRule.matches(complexExpression));
    }

    @Test
    public void test008MatchComplexFalseExpression() {
        List<TokenType> complexExpression = Arrays.asList(
                TokenType.NUMBER,
                TokenType.PLUS,
                TokenType.STRING,
                TokenType.DIVIDE,
                TokenType.NUMBER,
                TokenType.NUMBER);
        assertFalse(expressionRule.matches(complexExpression));
    }
}
