package TableSymb;


public class Information {
    private Object value;
    private String type_;

    public Information(Object value, String type_) {
        this.value = value;
        this.type_ = type_;
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
    
    
}
