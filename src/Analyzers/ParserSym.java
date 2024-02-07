package Analyzers;

public class ParserSym {

    /* terminals */
    public static final int EOF = 0;

    public static final int TK_PROGRAMA = 1;
    public static final int TK_VAR = 2;
    public static final int TK_COLON = 3;
    public static final int TK_DOUBLE = 4;
    public static final int TK_LBRACKET = 5;
    public static final int TK_RBRACKET = 6;
    public static final int TK_ID = 7;
    public static final int TK_MINUS = 8;
    public static final int TK_LT = 9;
    public static final int TK_STRING = 10;
    public static final int TK_SEMICOLON = 11;
    public static final int TK_ARR = 12;
    public static final int TK_AT = 13;
    public static final int TK_COMMA = 14;
    public static final int TK_LPAREN = 15;
    public static final int TK_RPAREN = 16;
    public static final int TK_SUM = 17;
    public static final int TK_RES = 18;
    public static final int TK_MUL = 19;
    public static final int TK_DIV = 20;
    public static final int TK_MOD = 21;
    public static final int TK_MEDIA = 22;
    public static final int TK_MEDIANA = 23;
    public static final int TK_PRINT = 24;
    public static final int TK_COLUMN = 25;
    public static final int TK_GRAPHBAR = 26;
    public static final int TK_TITULO = 27;
    public static final int TK_EJEX = 28;
    public static final int TK_EJEY = 29;
    public static final int TK_TITULOX = 30;
    public static final int TK_TITULOY = 31;
    public static final int TK_EXEC = 32;
    public static final int TK_GRAPHLINE = 33;
    public static final int TK_GRAPHPIE = 34;
    public static final int TK_LABEL = 35;
    public static final int TK_VALUES = 36;
    public static final int TK_HISTOGRAM = 37;
    public static final int TK_END = 38;
    public static final int TK_CONSOLE = 39;
    public static final int TK_EQUAL = 40;

    public static final String[] terminalNames = new String[]{
        "EOF",
        "error",
        "TK_PROGRAMA",
        "TK_VAR",
        "TK_COLON",
        "TK_DOUBLE",
        "TK_LBRACKET",
        "TK_RBRACKET",
        "TK_ID",
        "TK_MINUS",
        "TK_LT",
        "TK_STRING",
        "TK_DOUBLE",
        "TK_SEMICOLON",
        "TK_ARR",
        "TK_AT",
        "TK_COMMA",
        "TK_LPAREN",
        "TK_RPAREN",
        "TK_SUM",
        "TK_RES",
        "TK_MUL",
        "TK_DIV",
        "TK_MOD",
        "TK_MEDIA",
        "TK_MEDIANA",
        "TK_PRINT",
        "TK_COLUMN",
        "TK_GRAPHBAR",
        "TK_TITULO",
        "TK_EJEX",
        "TK_EJEY",
        "TK_TITULOX",
        "TK_TITULOY",
        "TK_EXEC",
        "TK_GRAPHLINE",
        "TK_GRAPHPIE",
        "TK_LABEL",
        "TK_VALUES",
        "TK_HISTOGRAM",
        "TK_END",
        "TK_CONSOLE",
        "TK_EQUAL"
    };
}
