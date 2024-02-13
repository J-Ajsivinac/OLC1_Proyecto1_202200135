package Tools;

import java.util.ArrayList;

public class Instructions {
    private ArrayList<VariableValue> ins;

    public Instructions(ArrayList<VariableValue> ins) {
        this.ins = ins;
    }

    public ArrayList<VariableValue> getIns() {
        return ins;
    }

    public void setIns(ArrayList<VariableValue> ins) {
        this.ins = ins;
    }

    public void push(VariableValue v) {
        ins.add(v);
    }

}
