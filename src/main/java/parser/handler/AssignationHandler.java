package parser.handler;

import lexer.token.Token;
import parser.ASTNode;
import parser.node.AssignationNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

public class AssignationHandler extends AbstractHandler {

    private ExpressionHandler expressionHandler;

    public AssignationHandler(Rule rule, ExpressionHandler expressionHandler) {
        super(rule);
        this.expressionHandler = expressionHandler;
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        Token identifier = tokens.get(0);
        // skip equals
        Optional<ASTNode> expression = expressionHandler.handle(tokens.subList(2, tokens.size()));

        return expression.map(exp -> new AssignationNode(exp, identifier.getValue()));
    }
}
