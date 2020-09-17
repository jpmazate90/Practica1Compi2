/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.Cuarteto;
import Objetos.Dato;
import Objetos.Expresion;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Println extends Print implements Instruction {

    
    
    public Println(List<Dato> lista,TablaSimbolos tablaSimbolo,TablaTipos tablaTipos) {
        super(lista,tablaSimbolo,tablaTipos);
    }

    @Override
    public List<Cuarteto> execute() {
        List<Cuarteto> mandar = new ArrayList<>();
        boolean bandera = false, banderaArreglo = false;
        String print = "PRINTLN ";
        Cuarteto cuarteto;
        for (int i = 0; i < this.lista.size(); i++) {
            cuarteto = new Cuarteto();
            cuarteto.setOperador("PRINTLN");
            Object valor;
            if (lista.get(i).getTipo().equals("VARIABLE")) {
                valor = tablaSimbolo.getIdValue(lista.get(i).getNombre(), 1);
                if (valor != null) {
                    bandera = true;
                } else {
                    bandera = false;
                }
            } else if (lista.get(i).getTipo().equals("ARREGLO")) {
                valor = tablaSimbolo.getIdValue(lista.get(i).getNombre(), 1);
                if (valor != null) {
                    bandera = true;
                    banderaArreglo=true;
                } else {
                    bandera = false;
                    banderaArreglo=false;
                }
            } else {
                bandera = true;
            }
            if (bandera) {
                if (banderaArreglo) {
                    try {
                        Expresion expr = (Expresion) lista.get(i).getLexema();
                        String resultado = expr.validarClases(tablaTipos, tablaSimbolo);
                        if (resultado!=null && resultado.equals("Integer")) {
                            Cuarteto.unirCuartetos(mandar,expr.generar3D());
                            String temporalCondicion = mandar.get(mandar.size()-1).getValorT();
                            cuarteto.setResultado(print + lista.get(i).getNombre() + "[" + temporalCondicion + "]");
                            mandar.add(cuarteto);
                        }else{
                            Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: LA EXPRESION PARA EL ARREGLO DEL ID: " + lista.get(i).getNombre() + " NO ES DE TIPO Integer ,NO SE PUEDE EJECUTAR ORDEN PRINTLN", null, null);
                        }

                    } catch (Exception e) {
                        Errores.Errores.crearError("SEMANTICO","ERROR SEMANTICO: PROBLEMAS AL HACER PRINTLN SOBRE EL ID: "+lista.get(i).getNombre(),null,null);
                    }

                } else {
                    cuarteto.setResultado(print + lista.get(i).getNombre());
                    mandar.add(cuarteto);
                }

            } else {
                Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: EL ID: " + lista.get(i).getNombre() + ",NO EXISTE PARA PODER EJECUTAR ORDEN PRINTLN", null, null);
                System.out.println("NO EXISTE LA VARIABLE: " + lista.get(i).getNombre() + ", PARA HACERLE PRINTLN");
            }
            banderaArreglo = false;
            bandera=false;

        }
        
        return mandar;

    }

}
