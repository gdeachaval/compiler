package interpreter;

import org.junit.Before;
import org.junit.Test;
import parser.Operator;
import parser.node.ArithmeticOperationNode;
import parser.node.NumberNode;
import parser.node.PrintNode;
import parser.node.StringNode;

public class TestInterpreter {

    private Interpreter interpreter;

    @Before
    public void setUp() throws Exception {
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
}
