package Tools;


public class VariableDeclaration {
    private Object id;
    private Object value;
    private TypeVariable typeMain;

    public TypeVariable getTypeMain() {
        return typeMain;
    }

    public void setTypeMain(TypeVariable typeMain) {
        this.typeMain = typeMain;
    }
    
    public VariableDeclaration(Object id, Object value) {
        this.id = id;
        this.value = value;
    }
    
    public VariableDeclaration(Object id, Object value, TypeVariable t) {
        this.id = id;
        this.value = value;
        this.typeMain = t;
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
