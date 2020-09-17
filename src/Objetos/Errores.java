
package Objetos;

/**
 *
 * @author jcsr
 */
public class Errores {
    private String tipo;
    private int fila;
    private int columna;
    private String motivo;
    private String archivo;
    private int numeroError;

    public Errores(String tipo, int fila, int columna, String motivo, String archivo) {
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
        this.motivo = motivo;
        this.archivo = archivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getNumeroError() {
        return numeroError;
    }

    public void setNumeroError(int numeroError) {
        this.numeroError = numeroError;
    }
    
    
    
}
