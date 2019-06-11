package interpreter;

import parser.node.ASTExpressionNode;
import parser.node.ArithmeticOperationNode;
import parser.node.AssignationDeclarationNode;
import parser.node.AssignationNode;
import parser.node.DeclarationNode;
import parser.node.IdentifierNode;
import parser.node.NumberNode;
import parser.node.PrintNode;
import parser.node.ProgramNode;
import parser.node.StringConcatenationNode;
import parser.node.StringNode;

import java.util.HashMap;
import java.util.Map;

public class ASTNodeVisitorImpl implements ASTNodeVisitor {
    private Map<String, Object> variableStack;
    private ExpressionVisitor expressionVisitor;

    public ASTNodeVisitorImpl() {
        variableStack = new HashMap<>();
        expressionVisitor = new ExpressionVisitorImpl();
    }

    @Override
    public void visit(ArithmeticOperationNode node) {

    }

    @Override
    public void visit(AssignationDeclarationNode node) {

    }

    @Override
    public void visit(AssignationNode node) {

    }

    @Override
    public void visit(DeclarationNode node) {
        String identifier = node.getIdentifier();
        variableStack.put(identifier, null);
    }

    @Override
    public void visit(IdentifierNode node) {

    }

    @Override
    public void visit(NumberNode node) {

    }

    @Override
    public void visit(ProgramNode node) {

    }

    @Override
    public void visit(PrintNode node) {
        ASTExpressionNode expression = node.getExpression();
        Object accept = expression.accept(expressionVisitor);
        System.out.println(accept);


    }

    @Override
    public void visit(StringNode node) {

    }

    @Override
    public void visit(StringConcatenationNode node) {

    }
}
