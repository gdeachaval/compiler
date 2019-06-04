package lexer;

public class ContextImpl implements Context {
    private String accum;
    private int line;
    private int column;

    ContextImpl() {
        this.accum = "";
        this.line = 0;
        this.column = 0;
    }

    @Override
    public String getAccum() {
        return accum;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setAccum(String string) {
        accum = string;
    }

    @Override
    public void addLine() {
        line++;
    }

    @Override
    public void addColumn() {
        column++;
    }
}
