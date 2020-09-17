
package Tablas.TablaSimbolos;

import Objetos.Expresion;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Simbolos {
    private Integer numeroElemento;
    private String nombre;
    private Object valor;
    private String categoria;
    private Integer tipo;
    private Integer numeroParameteros;
    private List<Object> listaParametros;
    private Integer ambito;
    private Expresion valoresArreglo;

    public Simbolos(Integer numeroElemento, String nombre, Object valor, String categoria, Integer tipo, Integer numeroParameteros, List<Object> listaParametros, Integer ambito, Expresion valoresArreglo) {
        this.numeroElemento = numeroElemento;
        this.nombre = nombre;
        this.valor = valor;
        this.categoria = categoria;
        this.tipo = tipo;
        this.numeroParameteros = numeroParameteros;
        this.listaParametros = listaParametros;
        this.ambito = ambito;
        this.valoresArreglo = valoresArreglo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getNumeroParameteros() {
        return numeroParameteros;
    }

    public void setNumeroParameteros(Integer numeroParameteros) {
        this.numeroParameteros = numeroParameteros;
    }

    public List<Object> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<Object> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    public Expresion getValoresArreglo() {
        return valoresArreglo;
    }

    public void setValoresArreglo(Expresion valoresArreglo) {
        this.valoresArreglo = valoresArreglo;
    }

    

    public Integer getNumeroElemento() {
        return numeroElemento;
    }

    public void setNumeroElemento(Integer numeroElemento) {
        this.numeroElemento = numeroElemento;
    }
    
    
}
