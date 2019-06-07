package parser.node;

import parser.ASTNode;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNode implements ASTNode {

    private List<ASTNode> children;

    AbstractNode() {
        children = new ArrayList<>();
    }
}
