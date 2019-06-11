package interpreter;

import parser.node.ASTNode;

public class InterpreterImpl implements Interpreter {

    private ASTNodeVisitor astNodeVisitor;

    public InterpreterImpl() {
        astNodeVisitor = new ASTNodeVisitorImpl();
    }

    @Override
    public void interpret(ASTNode node) {
        node.accept(astNodeVisitor);
    }
}
