package interpreter;

import parser.node.ASTNode;

public interface Interpreter {

    void interpret(ASTNode node);
}
