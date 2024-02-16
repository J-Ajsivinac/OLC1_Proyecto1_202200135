package Tools;


public class StatisticalExp {
    private String type_s;
    private Object values;

    public StatisticalExp(Object values, String type_s) {
        this.values = values;
        this.type_s = type_s;
    }

    public String getType_s() {
        return type_s;
    }

    public void setType_s(String type_s) {
        this.type_s = type_s;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }
    
    
}
