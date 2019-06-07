package parser;

import java.util.List;

public interface ASTNode {
    void addChild(ASTNode child);
    List<ASTNode> getChildren();
}
