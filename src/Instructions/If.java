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
public class If implements Instruction {

    private BaseIf baseIf;
    private List<Elsif> elsif;
    private Else sino;
    private Logica logica;
    private TablaTipos tablaTipos;
    private TablaSimbolos tablaSimbolos;

    public If(BaseIf baseIf, List<Elsif> elsif, Else sino, TablaSimbolos tablaSimbolos, TablaTipos tablaTipos) {
        this.baseIf = baseIf;
        this.elsif = elsif;
        this.sino = sino;
        this.tablaSimbolos = tablaSimbolos;
        this.tablaTipos = tablaTipos;
        logica = new Logica();
    }

    @Override
    public List<Cuarteto> execute() {
        List<Cuarteto> lista = new ArrayList<>();
        boolean banderaIf = false, banderaElse = true, banderaElsif = false;

        String condicion = baseIf.getCondicion().validarClases(tablaTipos, tablaSimbolos);
        if (condicion != null && (condicion.equals("Boolean") || condicion.equals("true"))) {
            banderaIf = true;
        }

        if (elsif != null) {
            for (int i = 0; i < elsif.size(); i++) {
                String valor = elsif.get(i).getCondicion().validarClases(tablaTipos, tablaSimbolos);
                if (valor != null && (valor.equals("Boolean") || valor.equals("true"))) {
                    banderaElsif = true;
                } else {
                    banderaElsif = false;
                }
            }
        } else {
            banderaElsif = true;
        }

        if (banderaIf && banderaElsif && banderaElse) {
            lista = generar3D();
        } else {
            if (banderaIf == false) {
                Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: PROBLEMAS SOBRE LA CONDICION DEL IF, EN LA ESTRUCTURA IF", null, null);

            }
            if (banderaElsif == false) {
                Errores.Errores.crearError("SEMANTICO", "ERROR SEMANTICO: PROBLEMAS SOBRE LAS CONDICIONES DEL ELSIF, EN LA ESTRUCTURA IF", null, null);

            }

            System.out.println("PROBLEMAS CON CONDICIONES DE LA INSTRUCCION IF");
        }

        return lista;

    }

    public List<Cuarteto> generar3D() {

        if (elsif == null && sino == null) {

            List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();
            listaCondicion = baseIf.getCondicion().generar3D();
            Cuarteto.unirCuartetos(lista, listaCondicion);
            String etiquetaInstrucciones = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();
            String temporalCondicion = logica.returnTemporal(listaCondicion);
            Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;

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
            for (int i = 0; i < this.baseIf.getInstructions().size(); i++) {
                Cuarteto.unirCuartetos(aux, this.baseIf.getInstructions().get(i).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista.add(cuartetoEtiqueta);

            return lista;

        } else if (elsif != null && sino == null) {
            List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();
            listaCondicion = baseIf.getCondicion().generar3D();
            Cuarteto.unirCuartetos(lista, listaCondicion);

            List<String> etiquetaInicio = new ArrayList<>();
            List<String> etiquetaInstruccion = new ArrayList<>();

            String etiquetaIf = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();

            for (int i = 0; i < this.elsif.size(); i++) {
                etiquetaInicio.add(Etiquetas.siguienteEtiqueta());
                etiquetaInstruccion.add(Etiquetas.siguienteEtiqueta());
            }

            String temporalCondicion = logica.returnTemporal(listaCondicion);
            Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;

            cuartetoIf = new Cuarteto();
            cuartetoIf.setOperador("IF");
            cuartetoIf.setResultado(temporalCondicion);
            lista.add(cuartetoIf);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaIf);
            lista.add(cuartetoGoto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaInicio.get(0));
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaIf);
            lista.add(cuartetoEtiqueta);
            aux = new ArrayList<>();
            for (int i = 0; i < this.baseIf.getInstructions().size(); i++) {
                Cuarteto.unirCuartetos(aux, this.baseIf.getInstructions().get(i).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista.add(cuartetoGoto);

            for (int i = 0; i < etiquetaInicio.size(); i++) {
                cuartetoEtiqueta = new Cuarteto();
                cuartetoEtiqueta.setOperador("ET");
                cuartetoEtiqueta.setResultado(etiquetaInicio.get(i));
                lista.add(cuartetoEtiqueta);

                listaCondicion = this.elsif.get(i).getCondicion().generar3D();
                Cuarteto.unirCuartetos(lista, listaCondicion);
                temporalCondicion = logica.returnTemporal(listaCondicion);

                cuartetoIf = new Cuarteto();
                cuartetoIf.setOperador("IF");
                cuartetoIf.setResultado(temporalCondicion);
                lista.add(cuartetoIf);

                cuartetoGoto = new Cuarteto();
                cuartetoGoto.setOperador("GOTO");
                cuartetoGoto.setResultado(etiquetaInstruccion.get(i));
                lista.add(cuartetoGoto);

                boolean bandera;
                try {
                    etiquetaInicio.get(i + 1);
                    bandera = true;
                } catch (Exception e) {
                    bandera = false;
                }

                if (bandera) {
                    cuartetoGoto = new Cuarteto();
                    cuartetoGoto.setOperador("GOTO");
                    cuartetoGoto.setResultado(etiquetaInicio.get(i + 1));
                    lista.add(cuartetoGoto);
                } else {
                    cuartetoGoto = new Cuarteto();
                    cuartetoGoto.setOperador("GOTO");
                    cuartetoGoto.setResultado(etiquetaSalida);
                    lista.add(cuartetoGoto);
                }

                cuartetoEtiqueta = new Cuarteto();
                cuartetoEtiqueta.setOperador("ET");
                cuartetoEtiqueta.setResultado(etiquetaInstruccion.get(i));
                lista.add(cuartetoEtiqueta);
                aux = new ArrayList<>();
                for (int j = 0; j < this.elsif.get(i).getInstructions().size(); j++) {
                    Cuarteto.unirCuartetos(aux, this.elsif.get(i).getInstructions().get(j).execute());
                }
                Cuarteto.unirCuartetos(lista, aux);

                cuartetoGoto = new Cuarteto();
                cuartetoGoto.setOperador("GOTO");
                cuartetoGoto.setResultado(etiquetaSalida);
                lista.add(cuartetoGoto);

            }

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista.add(cuartetoEtiqueta);

            return lista;

        } else if (elsif == null && sino != null) {

            List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();
            listaCondicion = baseIf.getCondicion().generar3D();
            Cuarteto.unirCuartetos(lista, listaCondicion);
            String etiquetaInstrucciones = Etiquetas.siguienteEtiqueta();
            String etiquetaElse = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();
            String temporalCondicion = logica.returnTemporal(listaCondicion);
            Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;

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
            cuartetoGoto.setResultado(etiquetaElse);
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaInstrucciones);
            lista.add(cuartetoEtiqueta);
            aux = new ArrayList<>();
            for (int i = 0; i < this.baseIf.getInstructions().size(); i++) {
                Cuarteto.unirCuartetos(aux, this.baseIf.getInstructions().get(i).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaElse);
            lista.add(cuartetoEtiqueta);
            aux = new ArrayList<>();
            for (int i = 0; i < this.sino.getInstructions().size(); i++) {
                Cuarteto.unirCuartetos(aux, this.sino.getInstructions().get(i).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista.add(cuartetoEtiqueta);

            return lista;

        } else {

            List<Cuarteto> lista = new ArrayList<>(), listaCondicion = new ArrayList<>(), aux = new ArrayList<>();
            listaCondicion = baseIf.getCondicion().generar3D();
            Cuarteto.unirCuartetos(lista, listaCondicion);

            List<String> etiquetaInicio = new ArrayList<>();
            List<String> etiquetaInstruccion = new ArrayList<>();

            String etiquetaIf = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();

            for (int i = 0; i < this.elsif.size(); i++) {
                etiquetaInicio.add(Etiquetas.siguienteEtiqueta());
                etiquetaInstruccion.add(Etiquetas.siguienteEtiqueta());
            }

            String etiquetaElse = Etiquetas.siguienteEtiqueta();

            String temporalCondicion = logica.returnTemporal(listaCondicion);
            Cuarteto cuartetoEtiqueta, cuartetoIf, cuartetoGoto;

            cuartetoIf = new Cuarteto();
            cuartetoIf.setOperador("IF");
            cuartetoIf.setResultado(temporalCondicion);
            lista.add(cuartetoIf);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaIf);
            lista.add(cuartetoGoto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaInicio.get(0));
            lista.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaIf);
            lista.add(cuartetoEtiqueta);
            aux = new ArrayList<>();
            for (int i = 0; i < this.baseIf.getInstructions().size(); i++) {
                Cuarteto.unirCuartetos(aux, this.baseIf.getInstructions().get(i).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista.add(cuartetoGoto);

            for (int i = 0; i < etiquetaInicio.size(); i++) {
                cuartetoEtiqueta = new Cuarteto();
                cuartetoEtiqueta.setOperador("ET");
                cuartetoEtiqueta.setResultado(etiquetaInicio.get(i));
                lista.add(cuartetoEtiqueta);

                listaCondicion = this.elsif.get(i).getCondicion().generar3D();
                Cuarteto.unirCuartetos(lista, listaCondicion);
                temporalCondicion = logica.returnTemporal(listaCondicion);

                cuartetoIf = new Cuarteto();
                cuartetoIf.setOperador("IF");
                cuartetoIf.setResultado(temporalCondicion);
                lista.add(cuartetoIf);

                cuartetoGoto = new Cuarteto();
                cuartetoGoto.setOperador("GOTO");
                cuartetoGoto.setResultado(etiquetaInstruccion.get(i));
                lista.add(cuartetoGoto);

                boolean bandera;
                try {
                    etiquetaInicio.get(i + 1);
                    bandera = true;
                } catch (Exception e) {
                    bandera = false;
                }

                if (bandera) {
                    cuartetoGoto = new Cuarteto();
                    cuartetoGoto.setOperador("GOTO");
                    cuartetoGoto.setResultado(etiquetaInicio.get(i + 1));
                    lista.add(cuartetoGoto);
                } else {
                    cuartetoGoto = new Cuarteto();
                    cuartetoGoto.setOperador("GOTO");
                    cuartetoGoto.setResultado(etiquetaElse);
                    lista.add(cuartetoGoto);
                }

                cuartetoEtiqueta = new Cuarteto();
                cuartetoEtiqueta.setOperador("ET");
                cuartetoEtiqueta.setResultado(etiquetaInstruccion.get(i));
                lista.add(cuartetoEtiqueta);
                aux = new ArrayList<>();
                for (int j = 0; j < this.elsif.get(i).getInstructions().size(); j++) {
                    Cuarteto.unirCuartetos(aux, this.elsif.get(i).getInstructions().get(j).execute());
                }
                Cuarteto.unirCuartetos(lista, aux);

                cuartetoGoto = new Cuarteto();
                cuartetoGoto.setOperador("GOTO");
                cuartetoGoto.setResultado(etiquetaSalida);
                lista.add(cuartetoGoto);

            }

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaElse);
            lista.add(cuartetoEtiqueta);
            aux = new ArrayList<>();
            for (int j = 0; j < this.sino.getInstructions().size(); j++) {
                Cuarteto.unirCuartetos(aux, this.sino.getInstructions().get(j).execute());
            }
            Cuarteto.unirCuartetos(lista, aux);

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
}
