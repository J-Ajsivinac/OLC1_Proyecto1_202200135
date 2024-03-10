package Tools;


public class VariableValue {
    private Object value;
    private TypeVariable type_;
    private TypeVariableG typeG;
    private int row;
    private int col;
    
    public VariableValue(Object value, TypeVariable type_) {
        this.value = value;
        this.type_ = type_;
    }
    
    public VariableValue(Object value, TypeVariable type_, int row, int col) {
        this.value = value;
        this.type_ = type_;
        this.row = row; 
        this.col = col;
    }
    
    public VariableValue(Object value, TypeVariableG type_) {
        this.value = value;
        this.typeG = type_;
        this.row = 0;
        this.col= 0;
    }

    public Object getValue() {
        return value;
    }
    
    public TypeVariable getType(){
        return type_;
    }

    public TypeVariable getType_() {
        return type_;
    }

    public void setType_(TypeVariable type_) {
        this.type_ = type_;
    }

    public TypeVariableG getTypeG() {
        return typeG;
    }

    public void setTypeG(TypeVariableG typeG) {
        this.typeG = typeG;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    
    
}
