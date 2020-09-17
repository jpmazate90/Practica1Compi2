/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.Condicional;
import Objetos.Cuarteto;
import Objetos.Dato;
import Objetos.Etiquetas;
import Objetos.Expresion;
import Objetos.Logica;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import java.util.ArrayList;
import java.util.List;
import Errores.Errores;

/**
 *
 * @author jpmazate
 */
public class For implements Instruction {

    private Expresion expresionInicio;
    private Expresion expresionAumento;
    private Condicional condicion;
    private Dato dato;
    private boolean aumento;
    private List<Instruction> lista;
    private Logica logica;
    private TablaSimbolos tablaSimbolos;
    private TablaTipos tablaTipos;

    public For(Expresion expresionInicio, Expresion expresionAumento, Condicional condicion, Dato dato, boolean aumento, List<Instruction> lista, TablaSimbolos tablaSimbolos, TablaTipos tablaTipos) {
        this.expresionInicio = expresionInicio;
        this.expresionAumento = expresionAumento;
        this.condicion = condicion;
        this.dato = dato;
        this.aumento = aumento;
        this.lista = lista;
        this.logica = new Logica();
        this.tablaSimbolos = tablaSimbolos;
        this.tablaTipos = tablaTipos;
    }

    @Override
    public List<Cuarteto> execute() {
        boolean banderaId = false, banderaExpresionInicio = false, banderaCondicion = false, banderaAumento = false;
        List<Cuarteto> lista = new ArrayList<>();
        Object valor = tablaSimbolos.getIdValue(dato.getNombre(), 1);
        String clase = null;
        if (valor != null) {
            clase = valor.getClass().getSimpleName();
            if (clase != null && clase.equals("Integer")) {
                banderaId = true;
            }
        }

        String resultado = expresionInicio.validarClases(tablaTipos, tablaSimbolos);
        if (resultado != null && resultado.equals("Integer")) {
            banderaExpresionInicio = true;
        }

        String condicional = condicion.validarClases(tablaTipos, tablaSimbolos);
        if (condicional != null && (condicional.equals("Boolean") || condicional.equals("true"))) {
            banderaCondicion = true;
        }

        String aumento = expresionAumento.validarClases(tablaTipos, tablaSimbolos);
        if (aumento != null && aumento.equals("Integer")) {
            banderaAumento = true;
        }

        if (banderaId && banderaExpresionInicio && banderaCondicion && banderaAumento) {
            lista = generar3D();
        } else {
            if (banderaId == false) {
                Errores.crearError("SEMANTICO", "ERROR SEMANTICO: EL ID: " + dato.getNombre() + ", NO  EXISTE EN LA TABLA DE SIMBOLOS O NO ES DE TIPO Integer, EN EL CICLO FOR", null, null);
            }
            if (banderaExpresionInicio == false) {
                Errores.crearError("SEMANTICO", "ERROR SEMANTICO: LA EXPRESION PARA ASIGNAR AL ID: " + dato.getNombre() + ", NO ES CORRECTA, O NO ES DE TIPO Integer, EN EL CICLO FOR", null, null);
            }
            if (banderaCondicion == false) {
                Errores.crearError("SEMANTICO", "ERROR SEMANTICO: LA CONDICION DEL CICLO FOR NO ESTA CORRECTA", null, null);
            }
            if (banderaAumento == false) {
                Errores.crearError("SEMANTICO", "ERROR SEMANTICO: LA EXPRESION DE AUMENTO PARA ASIGNAR AL ID: " + dato.getNombre() + ", NO ES CORRECTA, O NO ES DE TIPO Integer, EN EL CICLO FOR", null, null);
            }
            System.out.println("PROBLEMAS CON LA ESTRUCTURA DEL FOR");
        }

        return lista;
    }

    public List<Cuarteto> generar3D() {

        Expresion expr = new Expresion(dato, tablaSimbolos, tablaTipos);
        String operador;
        if (aumento) {
            operador = "+";
        } else {
            operador = "-";
        }
        Expresion ultima = new Expresion(operador, expr, expresionAumento, tablaTipos);
        Asignacion asignacionInicio = new Asignacion(dato, 1, expresionInicio, false, null, tablaSimbolos, tablaTipos);

        List<Cuarteto> lista = new ArrayList<>(), aux;
        Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;
        Cuarteto.unirCuartetos(lista, asignacionInicio.execute());

        Asignacion asignacion = new Asignacion(dato, 1, ultima, false, null, tablaSimbolos, tablaTipos);

        String etiquetaInicio = Etiquetas.siguienteEtiqueta();
        String etiquetaInstrucciones = Etiquetas.siguienteEtiqueta();
        String etiquetaSalida = Etiquetas.siguienteEtiqueta();
        aux = condicion.generar3D();
        String temporalCondicion = logica.returnTemporal(aux);

        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaInicio);
        lista.add(cuartetoEtiqueta);

        Cuarteto.unirCuartetos(lista, aux);

        cuartetoIf = new Cuarteto();
        cuartetoIf.setOperador("IF");
        cuartetoIf.setResultado(temporalCondicion);
        lista.add(cuartetoIf);

        cuartetoGoto = new Cuarteto();
        cuartetoGoto.setOperador("GOTO");
        cuartetoGoto.setResultado(etiquetaInstrucciones);
        lista.add(cuartetoGoto);

        cuartetoGoto = new Cuarteto();
        cuartetoGoto.setOperador("GOTO");
        cuartetoGoto.setResultado(etiquetaSalida);
        lista.add(cuartetoGoto);

        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaInstrucciones);
        lista.add(cuartetoEtiqueta);

        aux = new ArrayList<>();
        for (int i = 0; i < this.lista.size(); i++) {
            Cuarteto.unirCuartetos(aux, this.lista.get(i).execute());
        }
        Cuarteto.unirCuartetos(lista, aux);
        aux = asignacion.execute();
        Cuarteto.unirCuartetos(lista, aux);

        cuartetoGoto = new Cuarteto();
        cuartetoGoto.setOperador("GOTO");
        cuartetoGoto.setResultado(etiquetaInicio);
        lista.add(cuartetoGoto);

        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaSalida);
        lista.add(cuartetoEtiqueta);

        return lista;
    }

}
