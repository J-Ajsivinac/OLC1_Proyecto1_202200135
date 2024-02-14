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
            if (v.getType() == TypeVariable.DECLARATION) {
//                System.out.println(v.getValue());
                VariableDeclaration aux = (VariableDeclaration) v.getValue();
                String variable = (String) aux.getId();
                System.out.print(variable +" = ");
                VariableValue value = (VariableValue) aux.getValue();
                
                if (value.getType()==TypeVariable.STRING) {
                    String temp = (String) value.getValue();
                    System.out.println(temp);
                }else if(value.getType()==TypeVariable.DOUBLE){
                    Double temp = (Double) value.getValue();
                    System.out.println(temp);
                }else {
                    System.out.println(value.getValue());
                }
                
            }
        }
    }

}
