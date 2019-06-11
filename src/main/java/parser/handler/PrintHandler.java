package parser.handler;

import lexer.token.Token;
import parser.node.ASTExpressionNode;
import parser.node.ASTNode;
import parser.node.PrintNode;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

public class PrintHandler extends AbstractHandler {

    private ExpressionHandler expressionHandler;

    public PrintHandler(Rule rule, ExpressionHandler expressionHandler) {
        super(rule);
        this.expressionHandler = expressionHandler;
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        List<Token> expression = filter(tokens);
        Optional<ASTExpressionNode> handle = expressionHandler.handle(expression);
        return handle.map(PrintNode::new);
    }

    private List<Token> filter(List<Token> printStatement) {
        // should filter PRINT and ( and )
        return printStatement.subList(2, printStatement.size() - 1);
    }
}
