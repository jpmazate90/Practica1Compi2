/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.AccionVariable;
import Objetos.Cuarteto;
import Objetos.Dato;
import Objetos.Expresion;
import Tablas.TablaSimbolos.Simbolos;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Declaracion implements Instruction {

    private String tipo;
    private Dato id;
    private TablaSimbolos simbolos;
    private TablaTipos tipos;
    private boolean esArreglo;
    private Object valor;
    private Expresion tamano;

    public Declaracion(Dato id, TablaSimbolos simbolos, TablaTipos tipos, boolean esArreglo, Object valor, Expresion tamano) {
        this.id = id;
        this.simbolos = simbolos;
        this.tipos = tipos;
        this.esArreglo = esArreglo;
        this.valor = valor;
        this.tamano = tamano;

    }

    public String getTipo() {

        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Dato getId() {
        return id;
    }

    public void setId(Dato id) {
        this.id = id;
    }

    @Override
    public List<Cuarteto> execute() {
        List<Cuarteto> lista = new ArrayList<>();
        String categoria;
        String valorClase ="";
        boolean bandera=true;
        if (esArreglo) {
            categoria = "ARREGLO";
            valorClase =tamano.validarClases(tipos, simbolos); 
            if (valorClase.equals("Integer")) {
                bandera = true;
            } else {
                bandera = false;
            }
        } else {
            categoria = "VARIABLE";
        }

        if (bandera) {

            if (!simbolos.exists(id.getNombre(), 1)) {
                simbolos.addElement(new Simbolos(simbolos.size() + 1, id.getNombre(), valor, categoria, null, null, null, 1, tamano));
                tipos.addElement(new Tipos(tipos.getTabla().size(), id.getNombre(), null, 0, 0, 0, 1));
            } else {
                Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: EL ID: "+id.getNombre()+", YA EXISTE EN LA TABLA DE SIMBOLOS Y DE TIPOS Y NO SE PUEDE VOLVER A DECLARAR",null,null);
                System.out.println("EL VALOR " + id.getNombre() + " YA EXISTE EN LA TABLA DE SIMBOLOS Y DE TIPOS");
            }
        }else{
            Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: LA EXPRESION DENTRO DEL ARREGLO DEL ID: "+id.getNombre()+", NO ES UNA EXPRESION DE TIPO: Integer, es de tipo: "+valorClase,null,null);
            System.out.println("LA EXPRESION DENTRO DEL ARREGLO NO ES UNA EXPRESION TIPO Integer, es de tipo: "+valorClase);
        }

        return lista;
    }

}
