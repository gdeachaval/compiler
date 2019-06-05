package lexer.state.context;

public class ContextImpl implements Context {
    private String accum;
    private int line;
    private int column;

    public ContextImpl() {
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
    public void setAccum(String accum) {
        this.accum = accum;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }
}
