/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author jpmazate
 */
public class AccionVariable {
    private String tipoAccion;
    private String tipoDato;
    private Dato id;
    private Expresion expresion;

    public AccionVariable(String tipoAccion, String tipoDato, Dato id, Expresion expresion) {
        this.tipoAccion = tipoAccion;
        this.tipoDato = tipoDato;
        this.id = id;
        this.expresion = expresion;
    }

    public AccionVariable(String tipoAccion, Dato id, Expresion expresion) {
        this.tipoAccion = tipoAccion;
        this.id = id;
        this.expresion = expresion;
    }
    
    

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public Dato getId() {
        return id;
    }

    public void setId(Dato id) {
        this.id = id;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }
    
    
    
    
}
