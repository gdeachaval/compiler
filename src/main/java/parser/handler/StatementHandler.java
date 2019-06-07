package parser.handler;

import lexer.token.Token;
import parser.ASTNode;
import parser.ParseException;

import java.util.List;
import java.util.Optional;

class StatementHandler {

    private PrintHandler printHandler;
    private ExpressionHandler expressionHandler;

    StatementHandler() {
        printHandler = new PrintHandler();
        expressionHandler = new ExpressionHandler();
    }

    ASTNode handle(List<Token> statement) {
        Optional<ASTNode> printNode = printHandler.handle(statement);
        if (printNode.isPresent()) {
            return printNode.get();
        }
        throw new ParseException("malformed expression");
    }
}
