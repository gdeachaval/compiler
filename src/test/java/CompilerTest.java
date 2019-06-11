import compiler.Compiler;
import compiler.CompilerImpl;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import lexer.Lexer;
import lexer.LexerAutomaton;
import lexer.TokenConsumer;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.ParserImpl;

public class CompilerTest {

    private Compiler compiler;

    @Before
    public void setUp() {
        TokenConsumer consumer = new TokenConsumer();
        Lexer lexer = new LexerAutomaton(consumer);
        Parser parser = new ParserImpl();
        Interpreter interpreter = new InterpreterImpl();

        compiler = new CompilerImpl(lexer, parser, interpreter);
    }

    @Test
    public void testSimpleCompilation001() {
        compiler.compile("print(2+2) ;");
    }

    @Test
    public void testSimpleCompilation002() {
        compiler.compile("let a ;\na = 2 ;\nprint(a) ;");
    }

    @Test
    public void testSimpleCompilation004() {
        compiler.compile("print(\"foo\") ;");
    }

    @Test
    public void testSimpleCompilation005() {
        compiler.compile("let a ;\na = 2 ;\nlet b ;\nb = \"b\" ;\nprint(a) ;\nprint(b) ;");
    }
}
