package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java_cup.runtime.Symbol;

public class Statistics {

    public static double Mean(ArrayList<Double>  data) {
        double suma = 0;
        for (double numero : data) {
            suma += numero;
        }
        return suma / data.size();
    }

    public static double  Median(ArrayList<Double> lista) {
         Collections.sort(lista);
        int tamaño = lista.size();
        if (tamaño % 2 == 0) {
            return (lista.get(tamaño / 2 - 1) + lista.get(tamaño / 2)) / 2.0;
        } else {
            return lista.get(tamaño / 2);
        }
    }

    public static double Mode(ArrayList<Double> lista) {
        double moda = lista.get(0);
        int maximoConteo = 0;

        for (int i = 0; i < lista.size(); i++) {
            int conteo = 0;
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j) == lista.get(i)) {
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

    public static double Variance(ArrayList<Double> lista) {
        double media = Mean(lista);
        double sumaDiferenciasCuadrado = 0;
        for (double numero : lista) {
            sumaDiferenciasCuadrado += Math.pow(numero - media, 2);
        }
        return sumaDiferenciasCuadrado / lista.size();
    }

    public static double Maximum(ArrayList<Double> lista) {
        return Collections.max(lista);
    }

    public static double  Minimum(ArrayList<Double> lista) {
       return Collections.min(lista);
    }
}
