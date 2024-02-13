package Tools;


public class ArithmeticExp {
    private Object v1;
    private Object v2;
    private String op;

    public ArithmeticExp(Object v1, Object v2, String op) {
        this.v1 = v1;
        this.v2 = v2;
        this.op = op;
    }

    public Object getV1() {
        return v1;
    }

    public void setV1(Object v1) {
        this.v1 = v1;
    }

    public Object getV2() {
        return v2;
    }

    public void setV2(Object v2) {
        this.v2 = v2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
    
    
}
