package interpreter;

import parser.ASTNode;

public interface Interpreter {

    void interpret(ASTNode node);
}
