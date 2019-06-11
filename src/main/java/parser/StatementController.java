package parser;

import lexer.token.Token;
import parser.handler.AssignationDeclarationHandler;
import parser.handler.AssignationHandler;
import parser.handler.DeclarationHandler;
import parser.handler.ExpressionHandler;
import parser.handler.PrintHandler;
import parser.node.ASTNode;
import parser.rules.AssignationDeclarationRule;
import parser.rules.AssignationRule;
import parser.rules.DeclarationRule;
import parser.rules.ExpressionRule;
import parser.rules.PrintRule;

import java.util.List;
import java.util.Optional;

class StatementController {

    private PrintHandler printHandler;
    private DeclarationHandler declarationHandler;
    private AssignationHandler assignationHandler;
    private AssignationDeclarationHandler assignationDeclarationHandler;

    StatementController() {
        ExpressionHandler expressionHandler = new ExpressionHandler(new ExpressionRule());
        printHandler = new PrintHandler(new PrintRule(), expressionHandler);
        declarationHandler = new DeclarationHandler(new DeclarationRule());
        assignationHandler = new AssignationHandler(new AssignationRule(), expressionHandler);
        assignationDeclarationHandler = new AssignationDeclarationHandler(new AssignationDeclarationRule(), expressionHandler);
    }

    ASTNode parseStatement(List<Token> statement) {
        Optional<ASTNode> printNode = printHandler.handle(statement);
        if (printNode.isPresent()) {
            return printNode.get();
        }
        Optional<ASTNode> declarationNode = declarationHandler.handle(statement);
        if (declarationNode.isPresent()) {
            return declarationNode.get();
        }
        Optional<ASTNode> assignationNode = assignationHandler.handle(statement);
        if (assignationNode.isPresent()) {
            return assignationNode.get();
        }
        Optional<ASTNode> assignationDeclarationNode = assignationDeclarationHandler.handle(statement);
        if (assignationDeclarationNode.isPresent()) {
            return assignationDeclarationNode.get();
        }
        throw new ParseException("malformed expression");
    }
}
