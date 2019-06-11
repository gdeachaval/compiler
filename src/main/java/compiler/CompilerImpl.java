package compiler;

import interpreter.Interpreter;
import lexer.CharacterSupplier;
import lexer.Lexer;
import lexer.token.Token;
import parser.Parser;
import parser.node.ProgramNode;

import java.util.List;

public class CompilerImpl implements Compiler {

    private Lexer lexer;
    private Parser parser;
    private Interpreter interpreter;

    public CompilerImpl(Lexer lexer, Parser parser, Interpreter interpreter) {
        this.lexer = lexer;
        this.parser = parser;
        this.interpreter = interpreter;
    }

    @Override
    public void compile(String program) {
        List<Token> tokens = lexer.lex(new CharacterSupplier(program));
        ProgramNode node = parser.parse(tokens);
        interpreter.interpret(node);
    }
}
