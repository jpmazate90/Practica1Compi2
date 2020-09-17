/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.util.List;
import Instructions.Instruction;
import java.util.ArrayList;
import Errores.Errores;
import Errores.Error;
import javax.swing.JTextArea;

/**
 *
 * @author jpmazate
 */
public class Logica {

    public void operar(List<Instruction> lista, JTextArea area) {
        List<Cuarteto> cuartetos = new ArrayList<>(), aux;
        for (int i = 0; i < lista.size(); i++) {
            aux = lista.get(i).execute();
            Cuarteto.unirCuartetos(cuartetos, aux);
        }

        List<Error> errores = Errores.getErrores();

        if (errores.isEmpty()) {
            area.setText("");
            area.append("SIN ERRORES EN EL TEXTO, SE PUEDE GENERAR EL CODIGO 3 DIRECCIONES\n");
            area.append("CODIGO 3 DIRECCIONES: \n");
            for (int i = 0; i < cuartetos.size(); i++) {
                area.append(cuartetos.get(i).lineConverter() + "\n");

            }
        } else {
            area.setText("");
            area.append("HAY ERRORES EN EL TEXTO, POR LO CUAL NO SE PUEDE GENERAR EL CODIGO 3 DIRECCIONES+\n");
            area.append("ERRORES: \n");

            for (int i = 0; i < errores.size(); i++) {
                if (errores.get(i).getTipo().equals("SINTACTICO") || errores.get(i).getTipo().equals("LEXICO")) {

                } else {
                    area.append(returnError(errores.get(i)));
                }

            }
        }

    }
    
    public void mostrarError(Error error, JTextArea area){
        area.append(returnError(error));
    }

    public String returnError(Error error) {
        String mandar = "";
        mandar = error.getDescripcion();
        if (error.getColumna() != null) {
            mandar = mandar + ". FILA: " + error.getLinea() + ", COLUMNA: " + error.getColumna();
        }
        mandar+="\n";
        return mandar;
    }

    public Object devolverValor(String valor) {

        if (valor.equals("String")) {
            String value = "d";
            return value;

        } else if (valor.equals("Double")) {
            Double value = 0.0;
            return value;
        } else if (valor.equals("Float")) {
            Float value = 0.0f;
            return value;

        } else if (valor.equals("Char")) {
            String value = "c";
            return value;
        } else if (valor.equals("Boolean")) {
            Boolean value = true;
            return value;
        } else if (valor.equals("Long")) {
            Long value = (long) 2.0;
            return value;
        } else if (valor.equals("Byte")) {
            Byte value = 0;
            return value;
        } else if (valor.equals("Integer")) {
            Integer value = 1;
            return value;

        }
        String value = "";
        return value;
    }

    public String compararClases(String operador1, String operador2, TablaTipos tablaTipos) {

        if (operador1 == null || operador2 == null) {
            return null;
        }
        Boolean valor = tablaTipos.pertenecenAlMismoDato(operador1, operador2);
        if (valor != null) {
            if (valor) {
                Tipos tipoUno = tablaTipos.returnTipo(operador1, 1);
                Tipos tipoDos = tablaTipos.returnTipo(operador2, 1);

                if (tipoUno != null && tipoDos != null) {
                    if (tipoUno.getNumeroElemento() >= tipoDos.getNumeroElemento()) {
                        return tipoUno.getNombre();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public String returnTemporal(List<Cuarteto> lista1) {
        String resultado1;
        if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
            resultado1 = lista1.get(lista1.size() - 3).getValorT();
        } else {
            resultado1 = lista1.get(lista1.size() - 1).getValorT();
        }
        return resultado1;
    }

    public String returnUltimaEtiqueta(List<Cuarteto> lista1) {
        String resultado1;
        if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
            resultado1 = lista1.get(lista1.size() - 1).getResultado();
        } else {
            resultado1 = lista1.get(lista1.size() - 3).getResultado();
        }
        return resultado1;
    }

    public String returnUltimoTemporalExpresion(List<Cuarteto> lista1) {
        return lista1.get(lista1.size() - 1).getValorT();
    }

    public static TablaTipos crearTabla() {

        TablaTipos tipos = new TablaTipos();

        Tipos tstring = new Tipos(1, "String", null, null, null, null, 1, null, "String");
        Tipos tint = new Tipos(2, "Integer", null, null, null, null, 1, null, "Number");
        Tipos tbyte = new Tipos(3, "Byte", null, null, null, null, 1, 2, "Number");
        Tipos tlong = new Tipos(4, "Long", null, null, null, null, 1, 3, "Number");
        Tipos tfloat = new Tipos(5, "Float", null, null, null, null, 1, 4, "Number");
        Tipos tdouble = new Tipos(6, "Double", null, null, null, null, 1, 5, "Number");

        Tipos tboolean = new Tipos(7, "Boolean", null, null, null, null, 1, null, "Boolean");
        Tipos tchar = new Tipos(8, "String", null, null, null, null, 1, null, "String");

        tipos.addElement(tstring);
        tipos.addElement(tint);
        tipos.addElement(tbyte);
        tipos.addElement(tlong);
        tipos.addElement(tfloat);
        tipos.addElement(tdouble);
        tipos.addElement(tboolean);
        tipos.addElement(tchar);

        return tipos;
    }
}
