package interpreter;

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
        String identifier = node.getIdentifier();
        variableStack.put(identifier, expressionResult);
    }

    @Override
    public void visit(AssignationNode node) {
        String identifier = node.getIdentifier();
        if (variableStack.containsKey(identifier)) {
            Object expressionResult = node.getExpression().accept(expressionVisitor, variableStack);
            variableStack.put(identifier, expressionResult);
        }
        else throw new InterpreterException(identifier + " not defined yet");
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
        System.out.println(expressionResult);
    }
}