package parser;

import lexer.token.Token;
import parser.handler.ExpressionHandler;
import parser.handler.PrintHandler;
import parser.rules.ExpressionRule;
import parser.rules.PrintRule;

import java.util.List;
import java.util.Optional;

class StatementController {

    private PrintHandler printHandler;
    private ExpressionHandler expressionHandler;

    StatementController() {
        printHandler = new PrintHandler(new PrintRule());
        expressionHandler = new ExpressionHandler(new ExpressionRule());
    }

    ASTNode parseStatement(List<Token> statement) {
        Optional<ASTNode> printNode = printHandler.handle(statement);
        if (printNode.isPresent()) {
            return printNode.get();
        }
        throw new ParseException("malformed expression");
    }
}
