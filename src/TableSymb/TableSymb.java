package TableSymb;

import java.util.Map;
import java.util.TreeMap;

public class TableSymb {

    public Map<String, Map<String, Data>> filesJSON;

    public TableSymb() {
        filesJSON = new TreeMap<>();
    }

    public void addFile(String nameFile) {
        filesJSON.put(nameFile, new TreeMap<>());
    }

    public void addAttribute(String nameFile, String attribute, String value, boolean isString) {
        Map<String, Data> fileAttributes = filesJSON.get(nameFile);
        if (isString) {
            fileAttributes.put(attribute, new Data(isString, value));
            return;
        }
        fileAttributes.put(attribute, new Data(isString, Double.parseDouble(value)));
    }

    public void displayValues() {
        for (Map.Entry<String, Map<String, Data>> fileEntry : filesJSON.entrySet()) {
            String fileName = fileEntry.getKey();
            Map<String, Data> fileAttributes = fileEntry.getValue();

            System.out.println("Archivo: " + fileName);

            for (Map.Entry<String, Data> attributeEntry : fileAttributes.entrySet()) {
                String attributeName = attributeEntry.getKey();
                Data attributeValue = attributeEntry.getValue();

                System.out.printf("\t %-8s %20s \n", attributeName, attributeValue);
            }

            System.out.println(); // Línea en blanco para separar archivos
        }
    }

    public Data getValue(String nameFile, String attribute) {
        ;
        if (filesJSON.containsKey(nameFile)) {
            Map<String, Data> fileAttributes = filesJSON.get(nameFile);
            if (fileAttributes.containsKey(attribute)) {
                return fileAttributes.get(attribute);
            }
        }
        return null;
    }
}
