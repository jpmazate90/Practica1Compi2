/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Arreglo {
    
    private String tipo;
    private Expresion expresion;
    private List<Dato> lista;

    public Arreglo(String tipo, Expresion expresion, List<Dato> lista) {
        this.tipo = tipo;
        this.expresion = expresion;
        this.lista = lista;
    }

    public String getTipo() {
        
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public List<Dato> getLista() {
        return lista;
    }

    public void setLista(List<Dato> lista) {
        this.lista = lista;
    }

     
    
    
    
}
