package lexer;

public interface Context {
    String getAccum();

    int getLine();

    int getColumn();

    void setAccum(String string);

    void addLine();

    void addColumn();
}
