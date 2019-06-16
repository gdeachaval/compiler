package interpreter;

import org.junit.Before;
import org.junit.Test;
import parser.Operator;
import parser.node.expression.ASTExpressionNode;
import parser.node.expression.ExpressionNode;
import parser.node.expression.IdentifierNode;
import parser.node.expression.NumberNode;
import parser.node.expression.StringNode;
import parser.node.regular.AssignationNode;
import parser.node.regular.DeclarationNode;
import parser.node.regular.PrintNode;
import parser.node.regular.ProgramNode;

public class TestInterpreter {

    private Interpreter interpreter;

    @Before
    public void setUp() {
        interpreter = new InterpreterImpl();
    }

    @Test
    public void testSimplePrint3() {
        interpreter.interpret(new PrintNode(new NumberNode(5)));
    }

    @Test
    public void testSimplePrint4() {
        interpreter.interpret(new PrintNode(new StringNode("foo")));
    }

    @Test
    public void testSimpleProgram() {
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(new PrintNode(new StringNode("program")));
        interpreter.interpret(programNode);
    }

    @Test
    public void testSimpleIdentifier() {
        String identifier = "two";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);
        AssignationNode assignationNode = new AssignationNode(new NumberNode(2), identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(declarationNode);
        programNode.addChild(assignationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }

    @Test(expected = InterpreterException.class)
    public void testSimpleIdentifierNotDefined() {
        String identifier = "two";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);
        AssignationNode assignationNode = new AssignationNode(new NumberNode(2), identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(assignationNode);
        programNode.addChild(declarationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }

    @Test
    public void testSimpleDeclarationWithExpression() {
        String identifier = "five";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);

        ASTExpressionNode left1 = new NumberNode(10);

        ASTExpressionNode left2 = new NumberNode(2);
        ASTExpressionNode right2 = new NumberNode(3);
        ExpressionNode right1 = new ExpressionNode(left2, right2, Operator.PLUS);

        ExpressionNode expressionNode = new ExpressionNode(left1, right1, Operator.MINUS);

        AssignationNode assignationNode = new AssignationNode(expressionNode, identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(declarationNode);
        programNode.addChild(assignationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }

    @Test
    public void testSimpleDeclarationWithExpression2() {
        String identifier = "five";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);

        ASTExpressionNode left2a = new NumberNode(2);
        ASTExpressionNode right2a = new NumberNode(3);
        ExpressionNode left1 = new ExpressionNode(left2a, right2a, Operator.PLUS);

        ASTExpressionNode left2b = new NumberNode(3);
        ASTExpressionNode right2b = new NumberNode(2);
        ExpressionNode right1 = new ExpressionNode(left2b, right2b, Operator.MINUS);

        ExpressionNode expressionNode = new ExpressionNode(left1, right1, Operator.MULTIPLY);

        AssignationNode assignationNode = new AssignationNode(expressionNode, identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(declarationNode);
        programNode.addChild(assignationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }

    @Test
    public void testSimpleDeclarationWithExpression4() {
        String identifier = "five";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);

        ASTExpressionNode left2a = new StringNode("\"he\"");
        ASTExpressionNode right2a = new StringNode("\"2 \"");
        ExpressionNode left1 = new ExpressionNode(left2a, right2a, Operator.PLUS);

        ASTExpressionNode left2b = new StringNode("\"wo\"");
        ASTExpressionNode right2b = new StringNode("\"3\"");
        ExpressionNode right1 = new ExpressionNode(left2b, right2b, Operator.PLUS);

        ExpressionNode expressionNode = new ExpressionNode(left1, right1, Operator.PLUS);

        AssignationNode assignationNode = new AssignationNode(expressionNode, identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(declarationNode);
        programNode.addChild(assignationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }

    @Test
    public void testSimpleDeclarationWithExpression5() {
        String identifier = "five";
        DeclarationNode declarationNode = new DeclarationNode("let", identifier);

        ASTExpressionNode left2a = new StringNode("1");
        ASTExpressionNode right2a = new StringNode("2");
        ExpressionNode left1 = new ExpressionNode(left2a, right2a, Operator.PLUS);

        ASTExpressionNode left2b = new StringNode("3");
        ASTExpressionNode right2b = new StringNode("4");
        ExpressionNode right1 = new ExpressionNode(left2b, right2b, Operator.PLUS);

        ExpressionNode expressionNode = new ExpressionNode(left1, right1, Operator.PLUS);

        AssignationNode assignationNode = new AssignationNode(expressionNode, identifier);
        IdentifierNode identifierNode = new IdentifierNode(identifier);
        PrintNode printNode = new PrintNode(identifierNode);
        ProgramNode programNode = new ProgramNode();
        programNode.addChild(declarationNode);
        programNode.addChild(assignationNode);
        programNode.addChild(printNode);
        interpreter.interpret(programNode);
    }
}
