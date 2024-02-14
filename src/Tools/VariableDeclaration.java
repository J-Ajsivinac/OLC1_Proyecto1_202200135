package Tools;


public class VariableDeclaration {
    private Object id;
    private Object value;
    
    public VariableDeclaration(Object id, Object value) {
        this.id = id;
        this.value = value;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
}
