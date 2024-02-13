package Tools;


public class VariableValue {
    private Object value;
    private TypeVariable type_;
    public VariableValue(Object value, TypeVariable type_) {
        this.value = value;
        this.type_ = type_;
    }

    public Object getValue() {
        return value;
    }
    
    public TypeVariable getType(){
        return type_;
    }
}
