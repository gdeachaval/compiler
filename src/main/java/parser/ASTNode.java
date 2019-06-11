package parser;

import interpreter.ASTNodeVisitor;

public interface ASTNode {
    void accept(ASTNodeVisitor visitor);
}
