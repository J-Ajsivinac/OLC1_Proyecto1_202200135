package Analyzers;

public class token {

    private ParserSym tokenType;
    private String lexema;
    private int line;
    private int column;

    public token(ParserSym tokenType, String lexema, int line, int column) {
        this.tokenType = tokenType;
        this.lexema = lexema;
        this.line = line;
        this.column = column;
    }

    public void setTokenType(ParserSym tokenType) {
        this.tokenType = tokenType;
    }

    public ParserSym gettokenType() {
        return this.tokenType;
    }

    public String getLexema() {
        return this.lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
