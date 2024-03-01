package Errores;

public class Errores {
    // 0 -> léxico
    // 1 -> Sintáctico
    private int tipoError;
    private String descripcion;
    private String linea;
    private String columna;

    public Errores(int tipo, String descripcion, String linea, String columna) {
        this.tipoError = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public int getTipoError() {
        return tipoError;
    }

    public void setTipoError(int tipoError) {
        this.tipoError = tipoError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }
    
    
}
