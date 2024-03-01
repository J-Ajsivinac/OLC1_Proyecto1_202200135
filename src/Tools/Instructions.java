package Tools;

import Interface.Charts;
import Interface.Principal;
import TableSymb.Information;
import TableSymb.TableSymb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void execute() {
        boolean viewChart = false;
        for (VariableValue v : ins) {
            if (v.getType() == TypeVariable.DECLARATION) {
                VariableDeclaration aux = (VariableDeclaration) v.getValue();
                String variable = (String) aux.getId();
                VariableValue value = (VariableValue) aux.getValue();

                if (value.getType() == TypeVariable.STRING) {
                    String temp = (String) value.getValue();
                    table.put(variable, new Information(temp, "variable String"));
                } else if (value.getType() == TypeVariable.DOUBLE) {
                    Double temp = (Double) value.getValue();
                    table.put(variable, new Information(temp, "variable Double"));
                } else if (value.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) value.getValue();
                    double res = evaluateArith(temp);
                    table.put(variable, new Information(res, "variable String"));
                } else if (value.getType() == TypeVariable.ARRAY) {
                    ArrayList<VariableValue> temp = (ArrayList<VariableValue>) value.getValue();
                    table.put(variable, new Information(temp, "arreglo"));
                } else if (value.getType() == TypeVariable.ST) {
                    //System.out.println(" .-->"+value.getValue());
                    StatisticalExp e = (StatisticalExp) value.getValue();
                    double res = evaluateStats(e);
                    table.put(variable, new Information(value, "Variable Double"));
                    // System.out.println(res);
                } else if (value.getType() == TypeVariable.ID) {
                    String temp = (String) value.getValue();
                    table.put(variable, table.get(temp));
                } else {
                    System.out.println(value.getValue());
                }
            }
            if (v.getType() == TypeVariable.CONSOLE) {
                ArrayList<VariableValue> aux = (ArrayList<VariableValue>) v.getValue();
                for (VariableValue variableValue : aux) {
                    if (variableValue.getType() == TypeVariable.STRING) {
                        String temp = (String) variableValue.getValue();
                        String stringSinComillas = temp.replaceAll("\"", "");
                        output(stringSinComillas);
                    } else if (variableValue.getType() == TypeVariable.DOUBLE) {
                        Double temp = (Double) variableValue.getValue();
                        output(temp);
                    } else if (variableValue.getType() == TypeVariable.ID) {
                        String temp = (String) variableValue.getValue();
                        Information info = (Information) table.get(temp);
                        Object resp = info.getValue();
                        if (resp instanceof Double) {
                            output((Double) resp);
                        } else if (resp instanceof String) {
                            output((String) resp);
                        } else {
                            System.out.println("error -> ");
                        }
                    }

                }

            }
            if (v.getType() == TypeVariable.PRINTARRAY) {
                VariableValue temp = (VariableValue) v.getValue();

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
                        }

                    }
                }
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
    }

    public void printHistogram(double value, int f, int fa, double fr, String formato) {
        try {
            String row = String.format(formato, value, f, fa, fr);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), row + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createBarChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();

        boolean isBar = true;

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            VariableValue val = (VariableValue) entry.getValue();
            //System.out.println(entry.getKey() + " -> " + val.getType()+val.getValue());
            if (!validateFieldsBar(entry.getKey().toLowerCase(), val)) {
                isBar = false;
                continue;
            }     

        }
        ArrayList<VariableValue> tempx = (ArrayList<VariableValue>) ((VariableValue) values.get("ejex")).getValue();
        ArrayList<VariableValue> tempy = (ArrayList<VariableValue>) ((VariableValue) values.get("ejey")).getValue();
        String titulox = (String) ((VariableValue) values.get("titulox")).getValue();
        String tituloy = (String) ((VariableValue) values.get("tituloy")).getValue();
        
        String titulo = (String) ((VariableValue) values.get("titulo")).getValue();

        ArrayList<String> ejex = new ArrayList<>();
        for (VariableValue variableValue : tempx) {
            ejex.add(((String) variableValue.getValue()).replaceAll("\"", ""));
        }
        ArrayList<Double> ejey = new ArrayList<>();
        for (VariableValue variableValue : tempy) {
            ejey.add((Double) variableValue.getValue());
        }
        c.addBarChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);
    }

    public void createPieChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();
        ArrayList<VariableValue> tempvalues = (ArrayList<VariableValue>) ((VariableValue) values.get("values")).getValue();
        ArrayList<VariableValue> templabels = (ArrayList<VariableValue>) ((VariableValue) values.get("label")).getValue();

        ArrayList<String> labels = new ArrayList<>();
        for (VariableValue variableValue : templabels) {
            labels.add(((String) variableValue.getValue()).replaceAll("\"", ""));
        }
        ArrayList<Double> val = new ArrayList<>();
        for (VariableValue variableValue : tempvalues) {
            if (variableValue.getType() == TypeVariable.ID) {
                String name = (String) variableValue.getValue();
                Information info = (Information) table.get(name);
                Object resp = info.getValue();
                val.add((Double) resp);
            } else {
                val.add((Double) variableValue.getValue());
            }

        }
        c.addPieChart(val, labels);
    }

    public void createLineChart(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();
        ArrayList<VariableValue> tempx = (ArrayList<VariableValue>) ((VariableValue) values.get("ejex")).getValue();
        ArrayList<VariableValue> tempy = (ArrayList<VariableValue>) ((VariableValue) values.get("ejey")).getValue();
        String titulox = (String) ((VariableValue) values.get("titulox")).getValue();
        String tituloy = (String) ((VariableValue) values.get("tituloy")).getValue();

        String titulo = (String) ((VariableValue) values.get("titulo")).getValue();
        ArrayList<String> ejex = new ArrayList<>();
        for (VariableValue variableValue : tempx) {
            ejex.add(((String) variableValue.getValue()).replaceAll("\"", ""));
        }
        ArrayList<Double> ejey = new ArrayList<>();
        for (VariableValue variableValue : tempy) {
            ejey.add((Double) variableValue.getValue());
        }
        System.out.println(ejey);

        c.addLineChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);
    }

    public void createHistogram(VariableDeclaration base) {
        HashMap<String, Object> values = (HashMap<String, Object>) base.getValue();
        ArrayList<VariableValue> tempvalues = (ArrayList<VariableValue>) ((VariableValue) values.get("values")).getValue();
        String formato = " %-16s %-10s %-10s %-10s ";
        ArrayList<Double> datos = new ArrayList<>();
        String titulo = (String) ((VariableValue) values.get("titulo")).getValue();
        for (VariableValue variableValue : tempvalues) {
            if (variableValue.getType() == TypeVariable.ID) {
                String name = (String) variableValue.getValue();
                Information info = (Information) table.get(name);
                Object resp = info.getValue();
                datos.add((Double) resp);
            } else {
                datos.add((Double) variableValue.getValue());
            }
        }

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
            double frecuenciaRel = Math.round(((double) entry.getValue() / totalDatos) * 100);
            frecuenciaRelativa.put(entry.getKey(), frecuenciaRel);
        }

        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), " * "+titulo.replaceAll("\"", "") + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
        int totalFb = 0;
        int totalFa = 0;
        double totalPerc = 0;
        String top = String.format(formato, "Valor ", "Fb", "Fa", "Fr");
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), top + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            double valor = entry.getKey();
            int frec = entry.getValue();
            totalFb += frec;
            int frecAcum = frecuenciaAcumuladaMap.get(valor);
            totalFa = frecAcum;
            double frecRel = frecuenciaRelativa.get(valor);
            totalPerc += frecRel;
            printHistogram(valor, frec, frecAcum, frecRel, formato);
        }
        String bottom = String.format(formato, "Totales", totalFb, totalFa, totalPerc);
        try {
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), bottom + "\n", null);
            Principal.paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(), "───────────────────────────────────────────────" + "\n", null);

        } catch (BadLocationException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
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
        }
        return resultado;
    }

}
