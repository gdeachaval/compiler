package parser.node.regular;

import interpreter.ASTNodeVisitor;

public interface ASTNode {
    void accept(ASTNodeVisitor visitor);
}
