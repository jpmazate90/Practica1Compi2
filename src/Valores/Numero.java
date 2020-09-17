/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Valores;

/**
 *
 * @author jpmazate
 */
public class Numero<E> {
    
    protected E valor;
    
    public <T> Numero(T valor){
        this.valor = (E) valor;
    }

    public E getValor() {
        return valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }
    
    
    
    
}
