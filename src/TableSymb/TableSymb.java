package TableSymb;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TableSymb {

    private HashMap<String, Object> table;

    public TableSymb() {
        table = new HashMap<>();
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

}
