package TableSymb;


public class Information {
    private Object value;
    private String type_;
    private int row;
    private int column;

    public Information(Object value, String type_, int line, int column) {
        this.value = value;
        this.type_ = type_;
        this.row = line;
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public int getLine() {
        return row;
    }

    public void setLine(int line) {
        this.row = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }   
    
}
