package interpreter;

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

public interface ASTNodeVisitor {
    void visit(ArithmeticOperationNode node);
    void visit(AssignationDeclarationNode node);
    void visit(AssignationNode node);
    void visit(DeclarationNode node);
    void visit(IdentifierNode node);
    void visit(NumberNode node);
    void visit(ProgramNode node);
    void visit(PrintNode node);
    void visit(StringNode node);
    void visit(StringConcatenationNode node);
}
