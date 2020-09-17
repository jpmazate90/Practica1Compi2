/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.Condicional;
import Objetos.Cuarteto;
import Objetos.Etiquetas;
import Objetos.Logica;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class DoWhile implements Instruction {

    private Condicional condicion;
    private List<Instruction> lista;
    private Logica logica;
    private TablaSimbolos tablaSimbolos;
    private TablaTipos tablaTipos;

    public DoWhile(Condicional condicion, List<Instruction> lista, TablaTipos tablaTipos, TablaSimbolos tablaSimbolos) {
        this.condicion = condicion;
        this.lista = lista;
        this.tablaSimbolos = tablaSimbolos;
        this.tablaTipos = tablaTipos;
        logica = new Logica();
    }

    @Override
    public List<Cuarteto> execute() {
        boolean banderaCondicion = false;
        List<Cuarteto> lista = new ArrayList<>();
        String valeCondicion = condicion.validarClases(tablaTipos, tablaSimbolos);
        if (valeCondicion != null && (valeCondicion.equals("Boolean") || valeCondicion.equals("true"))) {
            banderaCondicion = true;
        }

        if (banderaCondicion) {
            lista = generar3D();
        }else{
            Errores.Errores.crearError("SEMANTICO","ERROR SEMANTICO: LA CONDICION DE LA ESTRUCTURA WHILE NO ESTA CORRECTA" ,null,null);
            System.out.println("ERROR CON LA CONDICION DEL DO WHILE ");
        }

        return lista;

    }

    public List<Cuarteto> generar3D() {
        List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();

        String etiquetaInicio = Etiquetas.siguienteEtiqueta();
        String etiquetaSalida = Etiquetas.siguienteEtiqueta();
        Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;

        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaInicio);
        lista.add(cuartetoEtiqueta);

        for (int i = 0; i < this.lista.size(); i++) {
            Cuarteto.unirCuartetos(aux, this.lista.get(i).execute());
        }
        Cuarteto.unirCuartetos(lista, aux);
        listaCondicion = condicion.generar3D();
        String temporalCondicion = logica.returnTemporal(listaCondicion);
        Cuarteto.unirCuartetos(lista, listaCondicion);
        cuartetoIf = new Cuarteto();
        cuartetoIf.setOperador("IF");
        cuartetoIf.setResultado(temporalCondicion);
        lista.add(cuartetoIf);

        cuartetoGoto = new Cuarteto();
        cuartetoGoto.setOperador("GOTO");
        cuartetoGoto.setResultado(etiquetaInicio);
        lista.add(cuartetoGoto);

        cuartetoGoto = new Cuarteto();
        cuartetoGoto.setOperador("GOTO");
        cuartetoGoto.setResultado(etiquetaSalida);
        lista.add(cuartetoGoto);

        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaSalida);
        lista.add(cuartetoEtiqueta);

        return lista;
    }

}
