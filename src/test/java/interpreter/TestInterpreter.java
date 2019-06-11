package interpreter;

import org.junit.Before;
import org.junit.Test;
import parser.Operator;
import parser.node.ArithmeticOperationNode;
import parser.node.AssignationNode;
import parser.node.DeclarationNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.PrintNode;
import parser.node.ProgramNode;
import parser.node.StringNode;

public class TestInterpreter {

    private Interpreter interpreter;

    @Before
    public void setUp() {
        interpreter = new InterpreterImpl();
    }

    @Test
    public void testSimplePrint() {
        interpreter.interpret(new PrintNode(new ArithmeticOperationNode(1, 2, Operator.PLUS)));
    }

    @Test
    public void testSimplePrint2() {
        interpreter.interpret(new PrintNode(new ArithmeticOperationNode(10, 2, Operator.DIVIDE)));
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

    @Test (expected = InterpreterException.class)
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
}
