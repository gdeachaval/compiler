package parser.node;

public class ArithmeticOperationNode extends AbstractNode {
    private int number1;
    private int number2;
    private String operator;

    public ArithmeticOperationNode(int number1, int number2, String operator) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;
    }
}
