package interpreter;

import parser.node.regular.ASTNode;

public interface Interpreter {

    void interpret(ASTNode node);
}
