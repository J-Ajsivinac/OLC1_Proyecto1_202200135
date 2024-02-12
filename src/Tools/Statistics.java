package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java_cup.runtime.Symbol;

public class Statistics {

    public static double Mean(String data) {
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));

        double suma = 0;
        for (String symbol : lista) {
            suma += Double.parseDouble(symbol);
        }
        return suma / lista.size();
    }

    public static String Median(String data) {
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));
        Collections.sort(lista, (s1, s2) -> {
            double valor1 = Double.parseDouble(s1);
            double valor2 = Double.parseDouble(s2);
            return Double.compare(valor1, valor2);
        });

        int tamaño = lista.size();
        if (tamaño % 2 == 0) {
            double valor1 = Double.parseDouble(lista.get(tamaño / 2 - 1));
            double valor2 = Double.parseDouble(lista.get(tamaño / 2));
            return Double.toString((valor1 + valor2) / 2.0);
        } else {
            return (lista.get(tamaño / 2));
        }
    }

    public static String Mode(String data) {
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));
        Collections.sort(lista, (s1, s2) -> {
            double valor1 = Double.parseDouble(s1);
            double valor2 = Double.parseDouble(s2);
            return Double.compare(valor1, valor2);
        });

        String moda = lista.get(0);
        int maximoConteo = 0;

        for (int i = 0; i < lista.size(); i++) {
            int conteo = 0;
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j).equals(lista.get(i))) {
                    conteo++;
                }
            }
            if (conteo > maximoConteo) {
                moda = lista.get(i);
                maximoConteo = conteo;
            }
        }
        return moda;
    }

    public static double Variance(String data) {
        double media = Mean(data);
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));

        double sumaDiferenciasCuadrado = 0;
        for (String symbol : lista) {
            double valor = Double.parseDouble(symbol);
            sumaDiferenciasCuadrado += Math.pow(valor - media, 2);
        }
        return sumaDiferenciasCuadrado / lista.size();
    }

    public static String Maximum(String data) {
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));
        Collections.sort(lista, (s1, s2) -> {
            double valor1 = Double.parseDouble(s1);
            double valor2 = Double.parseDouble(s2);
            return Double.compare(valor1, valor2);
        });
        return lista.get(lista.size() - 1);
    }

    public static String Minimum(String data) {
        String[] parts = data.split(",", -1);
        List<String> lista = new ArrayList<>(Arrays.asList(parts));
        Collections.sort(lista, (s1, s2) -> {
            double valor1 = Double.parseDouble(s1);
            double valor2 = Double.parseDouble(s2);
            return Double.compare(valor1, valor2);
        });
        return lista.get(0);
    }
}
