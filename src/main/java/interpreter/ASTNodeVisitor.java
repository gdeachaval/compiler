package interpreter;

import parser.node.regular.AssignationDeclarationNode;
import parser.node.regular.AssignationNode;
import parser.node.regular.DeclarationNode;
import parser.node.regular.PrintNode;
import parser.node.regular.ProgramNode;

public interface ASTNodeVisitor {
    void visit(AssignationDeclarationNode node);

    void visit(AssignationNode node);

    void visit(DeclarationNode node);

    void visit(ProgramNode node);

    void visit(PrintNode node);
}
