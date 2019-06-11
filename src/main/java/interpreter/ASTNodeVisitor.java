package interpreter;

import parser.node.AssignationDeclarationNode;
import parser.node.AssignationNode;
import parser.node.DeclarationNode;
import parser.node.PrintNode;
import parser.node.ProgramNode;

public interface ASTNodeVisitor {
    void visit(AssignationDeclarationNode node);
    void visit(AssignationNode node);
    void visit(DeclarationNode node);
    void visit(ProgramNode node);
    void visit(PrintNode node);
}
