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
        key = key.toLowerCase();
        table.put(key, value);
    }

    public Object get(String key) {
        key = key.toLowerCase();
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
            Information info = (Information) table.get(temp);
            Object resp = info.getValue();
            System.out.println("->" + resp.getClass());
            if (resp instanceof ArrayList) {
                ArrayList<Object> arrayT = (ArrayList<Object>) resp;
                String array = "[";
                int x = 0;
                for (Object variableValue : arrayT) {

                    String add = (x == arrayT.size() - 1) ? String.valueOf(variableValue) : variableValue + ",";
                    array += add;

                    x++;
                }
                array += "]";
                report += "<tr class=\"border-b last:border-b-0 border-b-[#434343] text-[#9ca3af]\">";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + i + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + entry.getKey() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + info.getType_() + "</td>\n";
                report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + array + "</td>\n";
                report += "</tr class=\"border-b last:border-b-0 border-b-[#434343] text-[#9ca3af]\">\n";
                
            } else {
                report += "<tr class=\"border-b last:border-b-0 border-b-[#434343] text-[#9ca3af]\">";
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

    public boolean isEmpty() {
        return table.isEmpty();
    }
}
