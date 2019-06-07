package parser.node;

public class DeclarationNode extends AbstractNode {
    private String declarator;
    private String identifier;

    public DeclarationNode(String declarator, String identifier) {
        this.declarator = declarator;
        this.identifier = identifier;
    }
}
