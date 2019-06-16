package compiler;

import interpreter.Interpreter;
import lexer.CharacterSupplier;
import lexer.Lexer;
import parser.Parser;

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
        interpreter.interpret(parser.parse(lexer.lex(new CharacterSupplier(program))));
    }
}
