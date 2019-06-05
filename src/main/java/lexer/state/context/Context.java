package lexer.state.context;

public interface Context {
    String getAccum();

    int getLine();

    int getColumn();

    void setAccum(String string);

    void setLine(int line);

    void setColumn(int column);
}
