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
import Objetos.Temporales;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class While implements Instruction {

    private Logica logica;
    private Condicional condicion;
    private List<Instruction> lista;
    private TablaTipos tablaTipos;
    private TablaSimbolos tablaSimbolos;

    public While(Condicional condicion, List<Instruction> lista,TablaSimbolos tablaSimbolos, TablaTipos tablaTipos) {
        this.condicion = condicion;
        this.lista = lista;
        logica = new Logica();
        this.tablaSimbolos=tablaSimbolos;
        this.tablaTipos = tablaTipos;
    }

    public List<Cuarteto> execute() {
        List<Cuarteto> lista = new ArrayList<>();
        String validar = condicion.validarClases(tablaTipos, tablaSimbolos);
        if(validar!=null && (validar.equals("Boolean") || validar.equals("true"))){
            lista =generar3D();
        }else{
            Errores.Errores.crearError("SEMANTICO","ERROR SEMANTICO: PROBLEMAS CON LA CONDICION DEL WHILE",null,null);
            System.out.println("PROBLEMAS CON LA CONDICION DEL WHILE");
        }
        
        return lista;
        
    }
    
    public List<Cuarteto> generar3D(){
        
        List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();
        listaCondicion = condicion.generar3D();
        String etiquetaInicio = Etiquetas.siguienteEtiqueta();
        String etiquetaInstrucciones = Etiquetas.siguienteEtiqueta();
        String etiquetaSalida = Etiquetas.siguienteEtiqueta();
        String temporalCondicion = logica.returnTemporal(listaCondicion);
        Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;
        
        cuartetoEtiqueta = new Cuarteto();
        cuartetoEtiqueta.setOperador("ET");
        cuartetoEtiqueta.setResultado(etiquetaInicio);
        lista.add(cuartetoEtiqueta);
        
        Cuarteto.unirCuartetos(lista, listaCondicion);
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

        
        for (int i = 0; i < this.lista.size(); i++) {
            Cuarteto.unirCuartetos(aux, this.lista.get(i).execute());
        }
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
