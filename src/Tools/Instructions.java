package Tools;

import java.util.ArrayList;

public class Instructions {
    private ArrayList<VariableValue> ins;

    public Instructions() {
        this.ins = new ArrayList<>();
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

    public void print() {
        for (VariableValue v : ins) {
            System.out.println(v.getType());
        }
    }

}
