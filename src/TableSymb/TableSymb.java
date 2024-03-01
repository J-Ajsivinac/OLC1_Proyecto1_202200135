package TableSymb;

import Tools.TypeVariable;
import Tools.VariableValue;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TableSymb {

    private LinkedHashMap<String, Object> table;

    public TableSymb() {
        table = new LinkedHashMap<>();
    }

    public void put(String key, Object value) {
        table.put(key, value);
    }

    public Object get(String key) {
        return table.get(key);
    }

    public Set<String> keySet() {
        return table.keySet();
    }

    public String addRows() {
        String report = "";
        int i = 0;
        for (Map.Entry<String, Object> entry : table.entrySet()) {
            i++;
            String temp = (String) entry.getKey();
            System.out.println(temp + ", " + table.get(temp));
            Information info = (Information) table.get(temp);
            Object resp = info.getValue();
            if (resp instanceof ArrayList) {
                ArrayList<VariableValue> arrayT = (ArrayList<VariableValue>) resp;
                String array = "[";
                int x = 0;
                for (VariableValue variableValue : arrayT) {
                    if (variableValue.getType() == TypeVariable.ID) {
                        String temp1 = (String) variableValue.getValue();
                        Information info2 = (Information) table.get(temp1);
                        Object r = info2.getValue();
                        String add = (x == arrayT.size()-1) ? String.valueOf(r) : r + ",";
                        array += add;

                    } else {
                        String add = (x == arrayT.size()-1) ? String.valueOf(variableValue.getValue()) : variableValue.getValue() + ",";
                        array += add;
                    }
                    x++;
                }
                array += "]";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + i + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + entry.getKey() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + info.getType_() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + array + "</td>\n";

                report += "</tr>\n";
            } else {
                report += "<tr>";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + i + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + entry.getKey() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + info.getType_() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + resp + "</td>\n";

                report += "</tr>\n";
            }
        }
        return report;
    }

    public void printTable() {
        for (Map.Entry<String, Object> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
