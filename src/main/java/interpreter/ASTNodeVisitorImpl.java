package interpreter;

import common.Constants;
import parser.node.expression.ASTExpressionNode;
import parser.node.regular.ASTNode;
import parser.node.regular.AssignationDeclarationNode;
import parser.node.regular.AssignationNode;
import parser.node.regular.DeclarationNode;
import parser.node.regular.PrintNode;
import parser.node.regular.ProgramNode;

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
        String noQuotes = expressionResult.toString().replace("\"", "").replace("\'", "");
        System.out.println(noQuotes);
    }
}
