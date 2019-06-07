package parser.handler;

import lexer.token.Token;
import parser.ASTNode;
import parser.node.PrintNode;
import parser.rules.ExpressionRule;
import parser.rules.Rule;

import java.util.List;
import java.util.Optional;

public class PrintHandler extends AbstractHandler {

    private ExpressionHandler expressionHandler;

    public PrintHandler(Rule rule) {
        super(rule);
        this.expressionHandler = new ExpressionHandler(new ExpressionRule());
    }

    @Override
    Optional<ASTNode> handleInternal(List<Token> tokens) {
        List<Token> expression = filter(tokens);
        Optional<ASTNode> handle = expressionHandler.handle(expression);
        return handle.map(PrintNode::new);
    }

    private List<Token> filter(List<Token> printStatement) {
        // should filter PRINT and ( and )
        return printStatement.subList(2, printStatement.size() - 1);
    }
}
