/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Tablas.TablaSimbolos.Simbolos;
import Valores.Numero;
import Valores.Valor;

/**
 *
 * @author jpmazate
 */
public class Dato{
    private String nombre;
    private String tipo;
    private Object lexema;
    private boolean variable;
    

    public Dato(String nombre, String tipo, Object lexema, boolean variable) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.lexema = lexema;
        this.variable = variable;        
        AccionVariable e1;
        
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getLexema() {
        return lexema;
    }

    public void setLexema(Object lexema) {
        this.lexema = lexema;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }
    
    
    
    
}
