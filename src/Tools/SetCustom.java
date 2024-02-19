package Tools;

import java.util.HashSet;
import java.util.Set;

public class SetCustom {

    private Set<VariableValue> conjunto;

    public SetCustom() {
        conjunto = new HashSet<>();
    }

    public void agregarOActualizar(VariableValue elemento) {
        for (VariableValue existingElement : conjunto) {
            if (existingElement.getType() == elemento.getType()) {
                conjunto.remove(existingElement);
                break; // Detenerse después de eliminar el primer elemento del mismo tipo
            }
        }
        conjunto.add(elemento);
    }

    // Método para agregar una serie de elementos al conjunto
    public void agregarElementos(VariableValue... elementos) {
        for (VariableValue elemento : elementos) {
            agregarOActualizar(elemento);
        }
    }

    public void mostrarConjunto() {
        System.out.println("Conjunto: " + conjunto);
    }
}
