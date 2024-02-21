package Tools;


public class VariableValue {
    private Object value;
    private TypeVariable type_;
    private TypeVariableG typeG;
    public VariableValue(Object value, TypeVariable type_) {
        this.value = value;
        this.type_ = type_;
    }
    
    public VariableValue(Object value, TypeVariableG type_) {
        this.value = value;
        this.typeG = type_;
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
    
    
}
