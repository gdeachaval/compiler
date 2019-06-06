package parser;

import lexer.token.TokenType;
import org.junit.Test;
import parser.rules.AssignationDeclarationRule;
import parser.rules.AssignationRule;
import parser.rules.DeclarationRule;
import parser.rules.PrintRule;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TestRules {

    private PrintRule printRule;
    private DeclarationRule declarationRule;
    private AssignationRule assignationRule;
    private AssignationDeclarationRule assignationDeclarationRule;

    public TestRules() {
        printRule = new PrintRule();
        declarationRule = new DeclarationRule();
        assignationRule = new AssignationRule();
        assignationDeclarationRule = new AssignationDeclarationRule();
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
        List<TokenType> simpleAssignation = Arrays.asList(
                TokenType.LET,
                TokenType.IDENTIFIER,
                TokenType.EQUALS,
                TokenType.NUMBER);
        assertTrue(assignationDeclarationRule.matches(simpleAssignation));
    }
}
