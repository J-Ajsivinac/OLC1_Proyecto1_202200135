package Tools;

import TableSymb.TableSymb;
import java.util.ArrayList;

public class Instructions {

    private ArrayList<VariableValue> ins;
    private TableSymb table;

    public Instructions() {
        this.ins = new ArrayList<>();
        table = new TableSymb();
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
            //System.out.println(v.getType());
            if (v.getType() == TypeVariable.DECLARATION) {
                VariableDeclaration aux = (VariableDeclaration) v.getValue();
                String variable = (String) aux.getId();
                //System.out.print(variable + " = ");
                VariableValue value = (VariableValue) aux.getValue();

                if (value.getType() == TypeVariable.STRING) {
                    String temp = (String) value.getValue();
                    table.put(variable, temp);
                } else if (value.getType() == TypeVariable.DOUBLE) {
                    Double temp = (Double) value.getValue();
                    table.put(variable, temp);
                } else if (value.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) value.getValue();
                    double res = evaluateArith(temp);
                    table.put(variable, res);
                } else if (value.getType() == TypeVariable.ARRAY) {
                    ArrayList<VariableValue> temp = (ArrayList<VariableValue>) value.getValue();

                    //VariableValue temp2 = (VariableValue) temp;
                    //table.put(variable, temp);
//                    for (VariableValue variableValue : temp) {
//                        System.out.println(variableValue.getValue());
//                    }
                    table.put(variable, temp);
                } else if (value.getType() == TypeVariable.ST) {
                    //System.out.println(" .-->"+value.getValue());
                    StatisticalExp e = (StatisticalExp) value.getValue();
                    double res = evaluateStats(e);
                    System.out.println(res);
                } else {
                    System.out.println(value.getValue());
                }
            }
        }
        table.printTable();
    }

    private double evaluateArith(ArithmeticExp data) {
        double resultado = 0.0f;
        VariableValue v1 = (VariableValue) data.getV1();
        VariableValue v2 = (VariableValue) data.getV2();
        String op = data.getOp();
        //Buscar v1 en la tabla de simbolos si es una variable
        //Buscar v2 en la tabla de simbolos si es una variable
        double operando1, operando2;
        if (v1.getType() == TypeVariable.AR) {
            ArithmeticExp temp = (ArithmeticExp) v1.getValue();
            operando1 = evaluateArith(temp);
        } else {
            operando1 = (double) v1.getValue();
        }
        if (v2.getType() == TypeVariable.AR) {
            ArithmeticExp temp = (ArithmeticExp) v2.getValue();
            operando2 = evaluateArith(temp);
        } else {
            operando2 = (double) v2.getValue();
        }

        switch (op) {
            case "/":
                resultado = operando1 / operando2;
                break;
            case "*":
                resultado = operando1 * operando2;
                break;
            case "%":
                resultado = operando1 % operando2;
                break;
            case "+":
                resultado = operando1 + operando2;
                break;
            case "-":
                resultado = operando1 - operando2;
                break;
            default:
                resultado = 0;
        }

        return resultado;
    }

    private double evaluateStats(StatisticalExp data) {
        double resultado = 0.0f;
        String op = data.getType_s();
        VariableValue v = (VariableValue) data.getValues();
        ArrayList<VariableValue> array = (ArrayList<VariableValue>) v.getValue();
        //System.out.println(v.getValue());
        ArrayList<Double> arrayListDeDoubles = new ArrayList<>();
        for (VariableValue variableValue : array) {
            if (variableValue.getType() == TypeVariable.DOUBLE) {
                arrayListDeDoubles.add((Double) variableValue.getValue());
            }else if (variableValue.getType() == TypeVariable.AR){
                ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                arrayListDeDoubles.add(evaluateArith(temp));
            }
            //System.out.println(" .-.->"+variableValue.getValue()+" - "+ variableValue.getType());
        }

        //System.out.println();

        switch (op) {
            case "Media":
                resultado = Statistics.Mean(arrayListDeDoubles);
                break;
            case "Mediana":
                resultado = Statistics.Median(arrayListDeDoubles);
                break;
            case "Moda":
                resultado = Statistics.Mode(arrayListDeDoubles);
                break;
            case "Varianza":
                resultado = Statistics.Variance(arrayListDeDoubles);
                break;
            case "Max":
                resultado = Statistics.Maximum(arrayListDeDoubles);
                break;
            case "Min":
                resultado = Statistics.Minimum(arrayListDeDoubles);
                break;
            default:
                throw new AssertionError();
        }

        return resultado;
    }

}
