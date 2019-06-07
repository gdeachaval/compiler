package parser.handler;

import lexer.token.Token;
import parser.ASTNode;
import parser.node.PrintNode;
import parser.rules.PrintRule;

import java.util.List;
import java.util.Optional;

class PrintHandler extends AbstractHandler {

    private PrintRule printRule;
    private ExpressionHandler expressionHandler;

    PrintHandler() {
        this.printRule = new PrintRule();
        this.expressionHandler = new ExpressionHandler();
    }

    public Optional<ASTNode> handle(List<Token> tokens) {
        if (!printRule.matches(getTokenTypes(tokens))) {
            return Optional.empty();
        }
        return handleInternal(tokens);
    }

    private Optional<ASTNode> handleInternal(List<Token> tokens) {
        PrintNode printNode = new PrintNode();
        List<Token> expression = filter(tokens);
        Optional<ASTNode> handle = expressionHandler.handle(expression);
        if (handle.isPresent()) {
            printNode.addChild(handle.get());
            return Optional.of(printNode);
        }
        return Optional.empty();
    }

    private List<Token> filter(List<Token> printStatement) {
        // should filter PRINT and ( and )
        return printStatement.subList(2, printStatement.size() - 1);
    }
}
