package parser.node;

import interpreter.ASTNodeVisitor;
import parser.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends AbstractNode {
    private List<ASTNode> children;

    public ProgramNode() {
        children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
