package lexer;

public final class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String NUMBER = "[0-9]";
    public static final String NOT_A_NUMBER = "^[0-9]";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String NOT_A_DOUBLE_QUOTE = "[^\"]";
    public static final String SEPARATOR = ";|:|\n|\t|\\s";
    public static final String LETTER = "[a-zA-Z]";
    public static final String ALPHANUMERIC = "[a-zA-Z|0-9]";
    public static final String NOT_ALPHANUMERIC = "^[a-zA-Z|0-9]";
    public static final String OPERATOR = "\\+|-|\\*|/";
    public static final String NOT_AN_OPERATOR = "^[\\+|-|\\*|/]";
}
