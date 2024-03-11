package Tools;

import Interface.Charts;
import Interface.Principal;
import TableSymb.Information;
import TableSymb.TableSymb;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class Instructions {

    private ArrayList<VariableValue> ins;
    private TableSymb table;
    private JTextPane console;
    Charts c;

    public Instructions() {
        this.ins = new ArrayList<>();
        table = new TableSymb();
        c = new Charts();
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

    public TableSymb getTable() {
        return table;
    }

    public void execute() {
        boolean viewChart = false;
        for (VariableValue v : ins) {
            if (v.getType() == TypeVariable.DECLARATION) {
                VariableDeclaration aux = (VariableDeclaration) v.getValue();
                String variable = (String) aux.getId();
                VariableValue value = (VariableValue) aux.getValue();

                if (value.getType() == TypeVariable.STRING) {
                    String temp = (String) value.getValue();
                    table.put(variable, new Information(temp.replaceAll("\"", ""), "variable String", v.getRow(), v.getCol()));

                } else if (value.getType() == TypeVariable.DOUBLE) {
                    Double temp = (Double) value.getValue();
                    table.put(variable, new Information(temp, "variable Double", v.getRow(), v.getCol()));

                } else if (value.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) value.getValue();
                    double res = evaluateArith(temp);
                    table.put(variable, new Information(res, "variable Double", v.getRow(), v.getCol()));

                } else if (value.getType() == TypeVariable.ARRAY) {
                    ArrayList<VariableValue> temp = (ArrayList<VariableValue>) value.getValue();
                    addValuesArray(temp, aux.getTypeMain(), variable, v.getRow(), v.getCol());

                } else if (value.getType() == TypeVariable.ST) {
                    StatisticalExp e = (StatisticalExp) value.getValue();
                    double res = evaluateStats(e);
                    table.put(variable, new Information(res, "Variable Double", v.getRow(), v.getCol()));

                } else if (value.getType() == TypeVariable.ID) {
                    String temp = (String) value.getValue();
                    table.put(variable, table.get(temp));

                } else {
                    System.out.println("error -> " + value.getValue());
                }
            }

            if (v.getType() == TypeVariable.CONSOLE) {
                ArrayList<VariableValue> data = (ArrayList<VariableValue>) v.getValue();
                consoleSimple(data);
            }

            if (v.getType() == TypeVariable.PRINTARRAY) {
                consoleArray(v);
            }

            if (v.getType() == TypeVariable.GRAPH) {
                viewChart = true;
                VariableDeclaration temp = (VariableDeclaration) v.getValue();
                TypeVariableG graphType = (TypeVariableG) temp.getId();

                if (graphType == TypeVariableG.BARRAS) {
                    createBarChart(temp);
                } else if (graphType == TypeVariableG.PIE) {
                    createPieChart(temp);
                } else if (graphType == TypeVariableG.LINEA) {
                    createLineChart(temp);
                } else if (graphType == TypeVariableG.HISTOGRAMA) {
                    createHistogram(temp);
                }
            }
        }
        if (viewChart) {
            c.setVisible(true);
        }
        outputSimple("\n");
    }

    public void consoleSimple(ArrayList<VariableValue> values) {
        outputSimple(" -> ");
        String comma = ", ";
        int x = 0;
        for (VariableValue variableValue : values) {
            if (x == (values.size() - 1)) {
                comma = " ";
            }
            if (variableValue.getType() == TypeVariable.STRING) {
                String temp = (String) variableValue.getValue();
                String stringSinComillas = temp.replaceAll("\"", "");
                outputSimple(stringSinComillas + comma);
            } else if (variableValue.getType() == TypeVariable.DOUBLE) {
                Double temp = (Double) variableValue.getValue();
                outputSimple(temp + comma);
            } else if (variableValue.getType() == TypeVariable.ID) {
                String temp = (String) variableValue.getValue();
                Information info = (Information) table.get(temp);
                Object resp = info.getValue();
                if (resp instanceof Double) {
                    outputSimple(resp + comma);
                } else if (resp instanceof String) {
                    outputSimple(resp + comma);
                } else {
                    System.out.println("error -> " + resp.getClass() + resp);
                }
            } else if (variableValue.getType() == TypeVariable.AR) {
                ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                double res = evaluateArith(temp);
                outputSimple(res + comma);
            } else if (variableValue.getType() == TypeVariable.ST) {
                StatisticalExp e = (StatisticalExp) variableValue.getValue();
                double res = evaluateStats(e);
                outputSimple(res + comma);
            }
            x++;
        }
        outputSimple("\n");
    }

    public void consoleArray(VariableValue values) {
        VariableDeclaration valuesPrint = (VariableDeclaration) values.getValue();

        VariableValue temp = (VariableValue) valuesPrint.getValue();
        VariableValue tempTitle = (VariableValue) valuesPrint.getId();
        output("--------------------");
        if (tempTitle.getType() == TypeVariable.ID) {
            String tempVal = (String) tempTitle.getValue();
            Information info = (Information) table.get(tempVal);
            String title = (String) info.getValue();
            output(title);
        } else {
            String tit = (String) tempTitle.getValue();
            output(tit.replaceAll("\"", ""));
        }
        output("--------------------");

        if (temp.getType() == TypeVariable.ARRAY) {
            ArrayList<VariableValue> aux = (ArrayList<VariableValue>) temp.getValue();
            for (VariableValue variableValue : aux) {
                if (variableValue.getType() == TypeVariable.STRING) {
                    String temp2 = (String) variableValue.getValue();
                    String stringSinComillas = temp2.replaceAll("\"", "");
                    output(stringSinComillas);
                } else if (variableValue.getType() == TypeVariable.DOUBLE) {
                    Double temp2 = (Double) variableValue.getValue();
                    output(temp2);
                } else if (variableValue.getType() == TypeVariable.ID) {
                    String tempVal2 = (String) variableValue.getValue();
                    Information info = (Information) table.get(tempVal2);
                    if (info.getValue() instanceof String) {
                        output((String) info.getValue());
                    } else {
                        output((Double) info.getValue());
                    }
                } else if (variableValue.getType() == TypeVariable.AR) {
                    ArithmeticExp arit = (ArithmeticExp) variableValue.getValue();
                    double res = evaluateArith(arit);
                    output(res);
                } else if (variableValue.getType() == TypeVariable.ST) {
                    StatisticalExp e = (StatisticalExp) variableValue.getValue();
                    double res = evaluateStats(e);
                    output(res);
                }

            }
        } else if (temp.getType() == TypeVariable.ID) {
            output(printArray(temp.getValue()));
        }
    }

    public void printHistogram(double value, int f, int fa, double fr, String formato) {
        try {
            String row = String.format(formato, value, f, fa, fr + "%");
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), row.replaceAll("\"", "") + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addValuesArray(ArrayList<VariableValue> array, TypeVariable typeBase, String id, int row, int col) {
        ArrayList<Double> arrayDouble = new ArrayList<>();
        ArrayList<String> arrayString = new ArrayList<>();

        for (VariableValue variableValue : array) {
            if (typeBase == TypeVariable.DOUBLE) {
                if (variableValue.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                    double res = evaluateArith(temp);
                    arrayDouble.add(res);
                } else if (variableValue.getType() == TypeVariable.ST) {
                    StatisticalExp e = (StatisticalExp) variableValue.getValue();
                    double res = evaluateStats(e);
                    arrayDouble.add(res);
                } else if (variableValue.getType() == TypeVariable.ID) {
                    String tempVal = (String) variableValue.getValue();
                    Information info = (Information) table.get(tempVal);
                    try {
                        arrayDouble.add((Double) info.getValue());
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    arrayDouble.add((Double) variableValue.getValue());
                }
            } else {
                if (variableValue.getType() == TypeVariable.ID) {
                    String tempVal = (String) variableValue.getValue();
                    Information info = (Information) table.get(tempVal);
                    try {
                        arrayString.add(((String) info.getValue()).replaceAll("\"", ""));
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    arrayString.add(((String) variableValue.getValue()).replaceAll("\"", ""));
                }
            }
        }
        if (!arrayDouble.isEmpty()) {
            table.put(id, new Information(arrayDouble, "arreglo double", row, col));
        } else if (!arrayString.isEmpty()) {
            table.put(id, new Information(arrayString, "arreglo string", row, col));
        }

        return true;
    }

    public ArrayList<Double> addValuesA(ArrayList<VariableValue> array, TypeVariable typeBase) {
        ArrayList<Double> arrayDouble = new ArrayList<>();

        for (VariableValue variableValue : array) {
            if (variableValue.getType() == TypeVariable.AR) {
                ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                double res = evaluateArith(temp);
                arrayDouble.add(res);
            } else if (variableValue.getType() == TypeVariable.ST) {
                StatisticalExp e = (StatisticalExp) variableValue.getValue();
                double res = evaluateStats(e);
                arrayDouble.add(res);
            } else if (variableValue.getType() == TypeVariable.ID) {
                String tempVal = (String) variableValue.getValue();
                Information info = (Information) table.get(tempVal);
                try {
                    arrayDouble.add((Double) info.getValue());
                } catch (Exception e) {

                }
            } else {
                arrayDouble.add((Double) variableValue.getValue());
            }
        }
        return arrayDouble;
    }

    public ArrayList<String> addValuesAString(ArrayList<VariableValue> array, TypeVariable typeBase) {
        ArrayList<String> arrayString = new ArrayList<>();

        for (VariableValue variableValue : array) {
            if (variableValue.getType() == TypeVariable.ID) {
                String tempVal = (String) variableValue.getValue();
                Information info = (Information) table.get(tempVal);
                try {
                    arrayString.add(((String) info.getValue()).replaceAll("\"", ""));
                } catch (Exception e) {

                }
            } else {
                arrayString.add(((String) variableValue.getValue()).replaceAll("\"", ""));
            }
        }
        return arrayString;
    }

    public String getString(VariableValue data) {
        if (data.getType() == TypeVariable.ID) {
            try {
                String name = (String) data.getValue();
                Information info = (Information) table.get(name);
                Object resp = info.getValue();
                return ((String) resp).replaceAll("\"", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Interface.Principal.jPanel1, "Tipo de variable requerida String", "Error Algebraico", JOptionPane.ERROR_MESSAGE);
                return "";
            }
        } else {
            return (String) data.getValue();
        }
    }

    public Double getDoube(VariableValue data) {
        if (data.getType() == TypeVariable.ID) {
            try {
                String name = (String) data.getValue();
                Information info = (Information) table.get(name);
                Object resp = info.getValue();
                return (Double) resp;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Interface.Principal.jPanel1, "Tipo de variable requerido Double", "Error Algebraico", JOptionPane.ERROR_MESSAGE);
                return 0.0;
            }
        } else {
            return (Double) data.getValue();
        }
    }

    public boolean addStringValuesID(ArrayList<String> values, VariableValue variableValue) {
        if (variableValue.getType() == TypeVariable.ID) {
            String name = (String) variableValue.getValue();
            Information info = (Information) table.get(name);
            Object resp = info.getValue();
            values.add(((String) resp).replaceAll("\"", ""));
        }

        return false;
    }

    public ArrayList<Double> getArrayListDouble(VariableValue variableValue, TypeVariable t) {
        ArrayList<Double> values = new ArrayList<>();
        if (variableValue.getType() == TypeVariable.ID) {
            String name = (String) variableValue.getValue();
            Information info = (Information) table.get(name);
            Object resp = info.getValue();
            ArrayList<Object> arrayT = (ArrayList<Object>) resp;
            for (Object object : arrayT) {
                values.add((Double) object);
            }
        } else {
            ArrayList<VariableValue> tempv = (ArrayList<VariableValue>) variableValue.getValue();
            return (ArrayList<Double>) addValuesA(tempv, t).clone();
        }
        return values;
    }

    public ArrayList<String> getArrayListString(VariableValue variableValue, TypeVariable t) {
        ArrayList<String> values = new ArrayList<>();
        if (variableValue.getType() == TypeVariable.ID) {
            String name = (String) variableValue.getValue();
            Information info = (Information) table.get(name);
            Object resp = info.getValue();
            ArrayList<Object> arrayT = (ArrayList<Object>) resp;
//            System.out.println(arrayT);
            for (Object object : arrayT) {
                values.add((String) object);
            }

        } else {
            ArrayList<VariableValue> tempv = (ArrayList<VariableValue>) variableValue.getValue();

            return (ArrayList<String>) addValuesAString(tempv, t).clone();
            //System.out.println("v s"+values.size());
        }
        return values;
    }

    public boolean addDoubleValuesID(ArrayList<Double> values, VariableValue variableValue) {
        if (variableValue.getType() == TypeVariable.ID) {
            String name = (String) variableValue.getValue();
            Information info = (Information) table.get(name);
            Object resp = info.getValue();
            values.add((Double) resp);
        }

        return false;
    }

    public void createBarChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();

        String titulox = getString((VariableValue) values.get("titulox"));
        String tituloy = getString((VariableValue) values.get("tituloy"));
        String titulo = getString((VariableValue) values.get("titulo"));

        ArrayList<String> ejex = new ArrayList<>();
        ejex = getArrayListString((VariableValue) values.get("ejex"), TypeVariable.STRING);

        ArrayList<Double> ejey = new ArrayList<>();
        ejey = getArrayListDouble((VariableValue) values.get("ejey"), TypeVariable.DOUBLE);

        c.addBarChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);
    }

    public void createPieChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();

        String titulo = getString((VariableValue) values.get("titulo"));

        ArrayList<String> labels = new ArrayList<>();
        labels = getArrayListString((VariableValue) values.get("label"), TypeVariable.STRING);

        ArrayList<Double> val = new ArrayList<>();
        val = getArrayListDouble((VariableValue) values.get("values"), TypeVariable.DOUBLE);
        //System.out.println(ejey.size());
        c.addPieChart(val, labels, titulo.replaceAll("\"", ""));
    }

    public void createLineChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();

        String titulox = getString((VariableValue) values.get("titulox"));
        String tituloy = getString((VariableValue) values.get("tituloy"));

        String titulo = getString((VariableValue) values.get("titulo"));
        ArrayList<String> ejex = new ArrayList<>();
        ejex = getArrayListString((VariableValue) values.get("ejex"), TypeVariable.STRING);

        ArrayList<Double> ejey = new ArrayList<>();
        ejey = getArrayListDouble((VariableValue) values.get("ejey"), TypeVariable.DOUBLE);
        c.addLineChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);
    }

    public void createHistogram(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();
        //ArrayList<VariableValue> tempvalues = (ArrayList<VariableValue>) ((VariableValue) values.get("values")).getValue();
        String formato = " %-14s %-8s %-8s %-8s ";
        ArrayList<Double> datos = new ArrayList<>();
        datos = getArrayListDouble((VariableValue) values.get("values"), TypeVariable.DOUBLE);

        VariableValue gTitulo = (VariableValue) values.get("titulo");
        String titulo = getString(gTitulo);

        Map<Double, Integer> frecuencia = new LinkedHashMap<>();
        for (double num : datos) {
            frecuencia.put(num, frecuencia.getOrDefault(num, 0) + 1);
        }

        ArrayList<String> ejex = new ArrayList<>();
        ArrayList<Double> ejey = new ArrayList<>();

        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            ejex.add(String.valueOf(entry.getKey()));
            ejey.add(Double.valueOf(entry.getValue()));
        }
        // Calcular la frecuencia acumulada
        int frecuenciaAcumulada = 0;
        LinkedHashMap<Double, Integer> frecuenciaAcumuladaMap = new LinkedHashMap<>();
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            frecuenciaAcumulada += entry.getValue();
            frecuenciaAcumuladaMap.put(entry.getKey(), frecuenciaAcumulada);
        }

        // Calcular la frecuencia relativa
        LinkedHashMap<Double, Double> frecuenciaRelativa = new LinkedHashMap<>();
        int totalDatos = datos.size();
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            //resultado = Math.round(resultado * 100.0) / 100.0;
            double frecuenciaRel = ((double) entry.getValue() / totalDatos) * 100;
            frecuenciaRelativa.put(entry.getKey(), frecuenciaRel);
        }

        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), " * " + titulo.replaceAll("\"", "") + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);

        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int totalFb = 0;
        int totalFa = 0;
        double totalPerc = 0;
        String top = String.format(formato, "Valor ", "Fb", "Fa", "Fr");
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), top.replaceAll("\"", "") + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);

        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            double valor = entry.getKey();
            int frec = entry.getValue();
            totalFb += frec;
            int frecAcum = frecuenciaAcumuladaMap.get(valor);
            totalFa = frecAcum;
            double frecRel = Math.round(frecuenciaRelativa.get(valor) * 100.0) / 100.0;
            totalPerc += frecRel;
            printHistogram(valor, frec, frecAcum, frecRel, formato);
        }
        totalPerc = Math.round(totalPerc * 100.0) / 100.0;
        String bottom = String.format(formato, "Totales", totalFb, totalFa, totalPerc + "%");
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), bottom + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        c.addBarChart(titulo.replaceAll("\"", ""), "", "", ejey, ejex);
    }

    private boolean validateFieldsBar(String attrO, VariableValue values) {
        String[] attribute = {"titulo", "ejex", "ejey", "titulox", "tituloy"};
        TypeVariable[] types = {TypeVariable.STRING, TypeVariable.ARRAY, TypeVariable.ARRAY, TypeVariable.STRING, TypeVariable.STRING};

        for (int i = 0; i < attribute.length; i++) {
            if (attrO.equals(attribute[i])) {
                if (values.getType() != types[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateFieldsPie(String attrO, VariableValue values) {
        String[] attribute = {"titulo", "ejex", "ejey", "titulox", "tituloy"};
        TypeVariable[] types = {TypeVariable.STRING, TypeVariable.ARRAY, TypeVariable.ARRAY, TypeVariable.STRING, TypeVariable.STRING};

        for (int i = 0; i < attribute.length; i++) {
            if (attrO.equals(attribute[i])) {
                if (values.getType() != types[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void outputSimple(String text) {
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), text, null);
        } catch (Exception e) {
            System.err.println("error " + e);
        }
    }

    private void outputSimple(Double text) {
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), text + "", null);
        } catch (Exception e) {
            System.err.println("error " + e);
        }
    }

    private void output(String text) {
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), text + "\n", null);
        } catch (Exception e) {
            System.err.println("error " + e);
        }
    }

    private void output(Double text) {
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), text + "\n", null);
        } catch (Exception e) {
            System.err.println("error " + e);
        }
    }

    private double evaluateArith(ArithmeticExp data) {
        double resultado = 0.0f;
        VariableValue v1 = (VariableValue) data.getV1();
        VariableValue v2 = (VariableValue) data.getV2();
        String op = data.getOp();
        double operando1, operando2;

        if (v1.getType() == TypeVariable.AR) {
            ArithmeticExp temp = (ArithmeticExp) v1.getValue();
            operando1 = evaluateArith(temp);
        } else if (v1.getType() == TypeVariable.ID) {
            String temp = (String) v1.getValue();
            Information info = (Information) table.get(temp);
            Object resp = info.getValue();
            operando1 = (double) resp;
        } else if (v1.getType() == TypeVariable.ST) {
            StatisticalExp e = (StatisticalExp) v1.getValue();
            operando1 = evaluateStats(e);
        } else {
            operando1 = (double) v1.getValue();
        }

        if (v2.getType() == TypeVariable.AR) {
            ArithmeticExp temp = (ArithmeticExp) v2.getValue();
            operando2 = evaluateArith(temp);
        } else if (v2.getType() == TypeVariable.ID) {
            String temp = (String) v2.getValue();
            Information info = (Information) table.get(temp);
            Object resp = info.getValue();
            operando2 = 0;
            operando2 = (double) resp;
        } else if (v2.getType() == TypeVariable.ST) {
            StatisticalExp e = (StatisticalExp) v2.getValue();
            operando2 = evaluateStats(e);
        } else {
            operando2 = (double) v2.getValue();
        }

        switch (op) {
            case "/":
                if (operando2 == 0) {
                    resultado = 0;
                    JOptionPane.showMessageDialog(Interface.Principal.jPanel1, "No se puede efectuar la división por 0", "Error Algebraico", JOptionPane.ERROR_MESSAGE);
                } else {
                    resultado = operando1 / operando2;
                }
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
        resultado = Math.round(resultado * 100.0) / 100.0;
        return resultado;
    }

    private double evaluateStats(StatisticalExp data) {
        String op = data.getType_s();
        ArrayList<Double> arrayListDeDoubles = new ArrayList<>();

        if (data.getValues() instanceof String) {
            String temp = (String) data.getValues();
            Information info = (Information) table.get(temp);
            arrayListDeDoubles = (ArrayList<Double>) info.getValue();
        } else {
            VariableValue v = (VariableValue) data.getValues();
            ArrayList<VariableValue> array = (ArrayList<VariableValue>) v.getValue();

            for (VariableValue variableValue : array) {
                if (variableValue.getType() == TypeVariable.DOUBLE) {
                    arrayListDeDoubles.add((Double) variableValue.getValue());
                } else if (variableValue.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                    arrayListDeDoubles.add(evaluateArith(temp));
                } else if (variableValue.getType() == TypeVariable.ID) {
                    String id = (String) variableValue.getValue();
                    Information info = (Information) table.get(id);
                    Object resp = info.getValue();
//                operando2 = (double) resp;
                    if (resp instanceof Double) {
                        arrayListDeDoubles.add((double) resp);
                    }
                }
            }
        }

        return operate(arrayListDeDoubles, op);
    }

    public double operate(ArrayList<Double> list, String op) {
        double resultado = 0.0f;
        op = op.toLowerCase();
        switch (op) {
            case "media":
                resultado = Statistics.Mean(list);
                break;
            case "mediana":
                resultado = Statistics.Median(list);
                break;
            case "moda":
                resultado = Statistics.Mode(list);
                break;
            case "varianza":
                resultado = Statistics.Variance(list);
                break;
            case "max":
                resultado = Statistics.Maximum(list);
                break;
            case "min":
                resultado = Statistics.Minimum(list);
                break;
            default:
                JOptionPane.showMessageDialog(Interface.Principal.jPanel1, "Operación Estadistica erronea", "Error", JOptionPane.ERROR_MESSAGE);
        }
        resultado = Math.round(resultado * 100.0) / 100.0;
        return resultado;
    }

    public String printArray(Object id) {
        String print = "";
        String temp = (String) id;
        Information info = (Information) table.get(temp);
        if (info.getValue() instanceof ArrayList) {
            ArrayList<Object> arrayT = (ArrayList<Object>) info.getValue();
            for (Object obj : arrayT) {
                print += obj + "\n";
            }
        }
        return print;
    }

}
