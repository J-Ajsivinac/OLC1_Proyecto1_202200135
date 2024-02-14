package TableSymb;

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

    public void printTable() {
        for (Map.Entry<String, Object> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
