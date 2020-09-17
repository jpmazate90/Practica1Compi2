
package Tablas.TablaTipos;

/**
 *
 * @author jpmazate
 */
public class Tipos {
    private Integer numeroElemento;
    private String nombre;
    private Integer tipoBase;
    private Integer padre;
    private Integer dimension;
    private Integer minimo;
    private Integer maximo;
    private Integer ambito;
    private Integer datoPrincipal;
    private String conjuntoDatos;

    public Tipos(Integer numeroElemento, String nombre, Integer tipoBase, Integer padre, Integer dimension, Integer minimo, Integer ambito) {
        this.numeroElemento = numeroElemento;
        this.nombre = nombre;
        this.tipoBase = tipoBase;
        this.padre = padre;
        this.dimension = dimension;
        this.minimo = minimo;
        this.ambito = ambito;
    }

    public Tipos(Integer numeroElemento, String nombre, Integer tipoBase, Integer padre, Integer dimension, Integer minimo, Integer ambito, Integer datoPrincipal,String conjuntoDatos) {
        this.numeroElemento = numeroElemento;
        this.nombre = nombre;
        this.tipoBase = tipoBase;
        this.padre = padre;
        this.dimension = dimension;
        this.minimo = minimo;
        this.ambito = ambito;
        this.datoPrincipal = datoPrincipal;
        this.conjuntoDatos = conjuntoDatos;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public String getConjuntoDatos() {
        return conjuntoDatos;
    }

    public void setConjuntoDatos(String conjuntoDatos) {
        this.conjuntoDatos = conjuntoDatos;
    }
    
    
    
    

    public Integer getNumeroElemento() {
        return numeroElemento;
    }

    public void setNumeroElemento(Integer numeroElemento) {
        this.numeroElemento = numeroElemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipoBase() {
        return tipoBase;
    }

    public void setTipoBase(Integer tipoBase) {
        this.tipoBase = tipoBase;
    }

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    public Integer getDatoPrincipal() {
        return datoPrincipal;
    }

    public void setDatoPrincipal(Integer datoPrincipal) {
        this.datoPrincipal = datoPrincipal;
    }
    
    
    
}
