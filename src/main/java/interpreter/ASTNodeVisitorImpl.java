package interpreter;

import lexer.Constants;
import parser.node.ASTExpressionNode;
import parser.node.ASTNode;
import parser.node.AssignationDeclarationNode;
import parser.node.AssignationNode;
import parser.node.DeclarationNode;
import parser.node.PrintNode;
import parser.node.ProgramNode;

import java.util.HashMap;
import java.util.Map;

public class ASTNodeVisitorImpl implements ASTNodeVisitor {
    private Map<String, Object> variableStack;
    private ExpressionVisitor expressionVisitor;

    ASTNodeVisitorImpl() {
        variableStack = new HashMap<>();
        expressionVisitor = new ExpressionVisitorImpl();
    }

    @Override
    public void visit(AssignationDeclarationNode node) {
        Object expressionResult = node.getExpression().accept(expressionVisitor, variableStack);
        String type = node.getType();
        assertType(expressionResult, type);
        String identifier = node.getIdentifier();
        variableStack.put(identifier, expressionResult);
    }

    private void assertType(Object expressionResult, String type) {
        String result = expressionResult.toString();
        if (result.matches(Constants.NUMBERS) && type.equals("string")) {
            throw new InterpreterException("wrong type");
        }
        if (result.matches(Constants.STRING) && type.equals("number")) {
            throw new InterpreterException("wrong type");
        }
    }

    @Override
    public void visit(AssignationNode node) {
        String identifier = node.getIdentifier();
        if (variableStack.containsKey(identifier)) {
            Object expressionResult = node.getExpression().accept(expressionVisitor, variableStack);
            variableStack.put(identifier, expressionResult);
        } else throw new InterpreterException(identifier + " not defined yet");
    }

    @Override
    public void visit(DeclarationNode node) {
        String identifier = node.getIdentifier();
        variableStack.put(identifier, null);
    }

    @Override
    public void visit(ProgramNode node) {
        for (ASTNode child : node.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(PrintNode node) {
        ASTExpressionNode expression = node.getExpression();
        Object expressionResult = expression.accept(expressionVisitor, variableStack);
        String noQuotes = expressionResult.toString().replace("\"", "");
        System.out.println(noQuotes);
    }
}
