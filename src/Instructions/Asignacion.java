/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.Cuarteto;
import Objetos.Dato;
import Objetos.Expresion;
import Objetos.Logica;
import Objetos.Temporales;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import java.util.ArrayList;
import java.util.List;
import Errores.Errores;

/**
 *
 * @author jpmazate
 */
public class Asignacion implements Instruction {

    private Dato id;
    private Integer ambito;
    private Expresion expresion;
    private Logica logica;
    private boolean esArreglo;
    private Integer posicion;
    private TablaSimbolos tablaSimbolos;
    private TablaTipos tablaTipos;

    public Asignacion(Dato id, Integer ambito, Expresion expresion, boolean esArreglo, Integer posicion, TablaSimbolos tablaSimbolos, TablaTipos tablaTipos) {
        this.id = id;
        this.ambito = ambito;
        this.expresion = expresion;
        this.esArreglo = esArreglo;
        this.posicion = posicion;
        this.tablaSimbolos = tablaSimbolos;
        this.tablaTipos = tablaTipos;
        logica = new Logica();
    }

    @Override
    public List<Cuarteto> execute() {
        boolean bandera = true, banderaArreglo = false, banderaId = true;
        List<Cuarteto> lista = new ArrayList<>(), aux;

        Object valor = tablaSimbolos.getIdValue(id.getNombre(), 1);
        String clase = null;
        if (valor != null) {
            clase = valor.getClass().getSimpleName();
        } else {
            Errores.crearError("SEMANTICO", "ERROR SEMANTICO: EL ID: " + id.getNombre() + ", NO EXISTE EN LA TABLA DE SIMBOLOS, NI DE TIPOS", null, null);
            banderaId = false;
            
        }

        if (banderaId) {

            String validar = expresion.validarClases(tablaTipos, tablaSimbolos);
            String resultado;
            if (validar != null && clase != null) {

                if (id.getTipo().equals("ARREGLO") && posicion != null) {
                    resultado = logica.compararClases(clase, validar, tablaTipos);
                    banderaArreglo = true;
                } else if (id.getTipo().equals("VARIABLE")) {
                    resultado = logica.compararClases(clase, validar, tablaTipos);
                } else {
                    resultado = null;
                }

            } else {
                resultado = null;
            }

            if (resultado != null) {
                Cuarteto cuarteto;
                aux = expresion.generar3D();
                Cuarteto.unirCuartetos(lista, aux);
                String temporal = logica.returnUltimoTemporalExpresion(lista);
                if (banderaArreglo) {
                    cuarteto = new Cuarteto(id.getNombre() + "[" + posicion + "]", temporal);
                } else {
                    cuarteto = new Cuarteto(id.getNombre(), temporal);
                }

                lista.add(cuarteto);
            } else {
                if (validar != null && clase != null) {
                    Errores.crearError("SEMANTICO", "ERROR SEMANTICO: NO CONCUERDAN LOS DOS TIPOS DE CLASES DE LA ASIGNACION: " + clase + " , " + validar + ", SOBRE EL ID: " + id.getNombre(), null, null);
                    System.out.println("NO CONCUERDAN LOS DOS TIPOS DE CLASES DE ASIGNACION DEL ID:" + id.getNombre());
                } else {
                    Errores.crearError("SEMANTICO", "ERROR AL HACER LA ASIGNACION DEL ID: " + id.getNombre(), null, null);
                    System.out.println("ERROR AL HACER LA ASIGNACION DEL ID: " + id.getNombre());
                }
            }
        }
        return lista;
    }

}
