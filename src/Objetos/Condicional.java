/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Condicional {

    private String operator;
    private Condicional left;
    private Condicional right;
    private TablaSimbolos table;
    private TablaTipos tablaTipos;
    private Dato literalValue;
    private Integer ambito = 1;

    private Token id;//se va a eliminar

    public Condicional(String operator, Condicional left, Condicional right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
        ambito = 1;
    }

    public Condicional(String operator, Condicional right) {
        this.operator = operator;
        this.right = right;
        ambito = 1;
    }

    public Condicional(Dato literalValue, TablaSimbolos table) {
        this.literalValue = literalValue;
        this.table = table;
        ambito = 1;
    }

    public List<Cuarteto> generar3D() {
        List<Cuarteto> lista, lista1, lista2;
        Cuarteto cuarteto, cuartetoIf1, cuartetoIf2, cuartetoGoto, cuartetoEtiqueta;
        String resultado1, resultado2;

        if (operator != null && operator.equals("OR")) {
            String valor = Temporales.getTemporal();
            String etiquetaTrue = Etiquetas.siguienteEtiqueta();
            String etiquetaFalse = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();
            String etiqueta1 = Etiquetas.siguienteEtiqueta();
            String etiqueta2 = Etiquetas.siguienteEtiqueta();

            lista = new ArrayList<>();
            lista1 = left.generar3D();
            lista2 = right.generar3D();
            String valorFinal = Temporales.siguienteTemporal();
            if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
                resultado1 = lista1.get(lista1.size() - 3).getResultado();
            } else {
                resultado1 = lista1.get(lista1.size() - 1).getValorT();
            }
            cuartetoIf1 = new Cuarteto();
            cuartetoIf1.setOperador("IF");
            cuartetoIf1.setResultado(resultado1);

            lista1.add(cuartetoIf1);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaTrue);
            lista1.add(cuartetoGoto);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiqueta1);
            lista1.add(cuartetoGoto);
            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiqueta1);
            lista1.add(cuartetoEtiqueta);

            Cuarteto.unirCuartetos(lista1, lista2);

            if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
                resultado1 = lista1.get(lista1.size() - 3).getValorT();
            } else {
                resultado1 = lista1.get(lista1.size() - 1).getValorT();
            }
            cuartetoIf1 = new Cuarteto();
            cuartetoIf1.setOperador("IF");
            cuartetoIf1.setResultado(resultado1);
            lista1.add(cuartetoIf1);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaTrue);
            lista1.add(cuartetoGoto);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaFalse);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaTrue);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "1");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaFalse);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "0");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista1.add(cuartetoEtiqueta);

            return lista1;

        } else if (operator != null && operator.equals("AND")) {
            String valor = Temporales.getTemporal();
            String etiquetaTrue = Etiquetas.siguienteEtiqueta();
            String etiquetaFalse = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();
            String etiqueta1 = Etiquetas.siguienteEtiqueta();
            String etiqueta2 = Etiquetas.siguienteEtiqueta();

            lista = new ArrayList<>();
            lista1 = left.generar3D();
            lista2 = right.generar3D();

            String valorFinal = Temporales.siguienteTemporal();
            if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
                resultado1 = lista1.get(lista1.size() - 3).getValorT();
            } else {
                resultado1 = lista1.get(lista1.size() - 1).getValorT();
            }
            cuartetoIf1 = new Cuarteto();
            cuartetoIf1.setOperador("IF");
            cuartetoIf1.setResultado(resultado1);

            lista1.add(cuartetoIf1);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiqueta1);
            lista1.add(cuartetoGoto);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaFalse);
            lista1.add(cuartetoGoto);
            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiqueta1);
            lista1.add(cuartetoEtiqueta);

            Cuarteto.unirCuartetos(lista1, lista2);

            if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
                resultado1 = lista1.get(lista1.size() - 3).getValorT();
            } else {
                resultado1 = lista1.get(lista1.size() - 1).getValorT();
            }
            cuartetoIf1 = new Cuarteto();
            cuartetoIf1.setOperador("IF");
            cuartetoIf1.setResultado(resultado1);
            lista1.add(cuartetoIf1);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaTrue);
            lista1.add(cuartetoGoto);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaFalse);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaTrue);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "1");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaFalse);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "0");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista1.add(cuartetoEtiqueta);

            return lista1;

        } else if (operator != null && operator.equals("NOT")) {
            String valor = Temporales.getTemporal();
            String etiquetaTrue = Etiquetas.siguienteEtiqueta();
            String etiquetaFalse = Etiquetas.siguienteEtiqueta();
            String etiquetaSalida = Etiquetas.siguienteEtiqueta();

            lista = new ArrayList<>();
            lista1 = right.generar3D();
            String valorFinal = Temporales.siguienteTemporal();

            if (lista1.get(lista1.size() - 1).getOperador() != null && lista1.get(lista1.size() - 1).getOperador().startsWith("ET")) {
                resultado1 = lista1.get(lista1.size() - 3).getValorT();
            } else {
                resultado1 = lista1.get(lista1.size() - 1).getValorT();
            }
            cuartetoIf1 = new Cuarteto();
            cuartetoIf1.setOperador("IF");
            cuartetoIf1.setResultado(resultado1);

            lista1.add(cuartetoIf1);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaFalse);
            lista1.add(cuartetoGoto);
            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaTrue);
            lista1.add(cuartetoGoto);
            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaTrue);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "1");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaFalse);
            lista1.add(cuartetoEtiqueta);

            cuarteto = new Cuarteto(valorFinal, "0");
            lista1.add(cuarteto);

            cuartetoGoto = new Cuarteto();
            cuartetoGoto.setOperador("GOTO");
            cuartetoGoto.setResultado(etiquetaSalida);
            lista1.add(cuartetoGoto);

            cuartetoEtiqueta = new Cuarteto();
            cuartetoEtiqueta.setOperador("ET");
            cuartetoEtiqueta.setResultado(etiquetaSalida);
            lista1.add(cuartetoEtiqueta);

            return lista1;

        } else if (operator != null) {
            lista = new ArrayList<>();
            lista1 = left.generar3D();
            lista2 = right.generar3D();
            resultado1 = lista1.get(lista1.size() - 1).getValorT();
            resultado2 = lista2.get(lista2.size() - 1).getValorT();
            cuarteto = new Cuarteto(Temporales.siguienteTemporal(), operator, resultado1, resultado2);
            Cuarteto.unirCuartetos(lista1, lista2, cuarteto);
            return lista1;
        } else {
            lista = new ArrayList<>();
            if (literalValue.getTipo().equals("ARREGLO")) {

            cuarteto = new Cuarteto(Temporales.siguienteTemporal(), literalValue.getNombre()+"["+literalValue.getLexema().toString()+"]");
            lista.add(cuarteto);
            } else {

                cuarteto = new Cuarteto(Temporales.siguienteTemporal(), literalValue.getNombre());
                lista.add(cuarteto);
            }

            return lista;
        }

    }

    public String validarClases(TablaTipos tipos, TablaSimbolos simbolos) {
        asignarTablas(tipos, simbolos);
        return operarClases();

    }

    public void asignarTablas(TablaTipos tipos, TablaSimbolos simbolos) {
        this.tablaTipos = tipos;
        this.table = simbolos;
        if (left != null) {
            left.asignarTablas(tipos, simbolos);
        }
        if (right != null) {
            right.asignarTablas(tipos, simbolos);
        }

    }

    public String operarClases() {
        if (operator != null && operator.equals("AND")) {

            String izquierda = left.operarClases();
            String derecha = right.operarClases();

            if (izquierda == null || derecha == null) {
                return null;
            }
            if ((izquierda.equals("true") || izquierda.equals("Boolean")) && (derecha.equals("true") || derecha.equals("Boolean"))) {
                return "true";
            } else {
                return null;
            }

        } else if (operator != null && operator.equals("OR")) {

            String izquierda = left.operarClases();
            String derecha = right.operarClases();

            if (izquierda == null || derecha == null) {
                return null;
            }
            if (izquierda.equals("true") && derecha.equals("true")) {
                return "true";
            } else if (izquierda.equals("Boolean") || derecha.equals("Boolean")) {
                return "true";
            } else {
                return null;
            }

        } else if (operator != null && operator.equals("NOT")) {
            String derecha = right.operarClases();

            if (derecha == null) {
                return null;
            }
            if (derecha.equals("true")) {
                return "true";
            } else if (derecha.equals("Boolean")) {
                return "true";
            } else {
                return null;
            }

        } else if (operator != null) {

            String valor = compararClases(left.operarClases(), right.operarClases());
            if (valor != null) {
                return valor;
            } else {
                return null;
            }

        } else {
            return getValue();
        }
    }

    public String compararClases(String operador1, String operador2) {

        if (operador1 == null || operador2 == null) {
            return null;
        }
        Boolean valor = this.tablaTipos.pertenecenAlMismoDato(operador1, operador2);
        if (valor != null) {
            if (valor) {
                Tipos tipoUno = tablaTipos.returnTipo(operador1, 1);
                if (tipoUno.getConjuntoDatos().equals("Number")) {
                    return "true";
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

    private String getValue() {
        if (literalValue != null) {
            if (literalValue.getTipo().equals("VARIABLE")) {
                if (table.getIdValue(literalValue.getNombre(), 1) != null) {
                    return table.getIdValue(literalValue.getNombre(), 1).getClass().getSimpleName();
                } else {
                    return null;
                }
            } else {
                return literalValue.getLexema().getClass().getSimpleName();
            }

        } else {
            return null;
        }
    }

    public String getOperator() {
        return operator;
    }

    public Condicional getLeft() {
        return left;
    }

    public Condicional getRight() {
        return right;
    }

}
