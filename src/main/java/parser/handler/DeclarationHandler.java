package parser.handler;

import lexer.token.Token;
import parser.node.regular.ASTNode;
import parser.node.regular.DeclarationNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

public class DeclarationHandler extends AbstractHandler<ASTNode> {

    public DeclarationHandler(Rule rule) {
        super(rule);
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        Token declarator = tokens.get(0);
        Token identifier = tokens.get(1);
        return Optional.of(new DeclarationNode(declarator.getValue(), identifier.getValue()));
    }
}
