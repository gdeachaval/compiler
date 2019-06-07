package parser.handler;

import lexer.token.Token;
import parser.ASTNode;
import parser.node.AssignationDeclarationNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

public class AssignationDeclarationHandler extends AbstractHandler {

    private ExpressionHandler expressionHandler;

    public AssignationDeclarationHandler(Rule rule, ExpressionHandler expressionHandler) {
        super(rule);
        this.expressionHandler = expressionHandler;
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        Token declarator = tokens.get(0);
        Token identifier = tokens.get(1);
        // skip colon
        Token type = tokens.get(3);
        // skip equals
        List<Token> expressionTokens = tokens.subList(5, tokens.size());

        Optional<ASTNode> expression = expressionHandler.handle(expressionTokens);

        return expression.map(exp -> new AssignationDeclarationNode(declarator.getValue(), identifier.getValue(), exp, type.getValue()));
    }
}
