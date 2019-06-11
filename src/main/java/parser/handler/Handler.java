package parser.handler;

import lexer.token.Token;
import parser.node.ASTNode;

import java.util.List;
import java.util.Optional;

public interface Handler {
    Optional<ASTNode> handle(List<Token> tokens);
}
