package parser.node;

import interpreter.ASTNodeVisitor;

public interface ASTNode {
    void accept(ASTNodeVisitor visitor);
}
