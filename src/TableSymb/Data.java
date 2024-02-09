package TableSymb;


public class Data {
    boolean isString;
    public String datoS;
    public double datoD;

    public Data(boolean isString, double datoD) {
        this.isString = isString;
        this.datoD = datoD;
    }
    
    public Data(boolean isString, String datoS) {
        this.isString = isString;
        this.datoS = datoS;
    }
    
    
}
