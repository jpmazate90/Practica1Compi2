/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Cuarteto {

    private String valorT;
    private String operador;
    private String izquierda;
    private String derecha;
    private String resultado;

    public Cuarteto(String valorT, String operador, String izquierda, String derecha) {
        this.valorT = valorT;
        this.operador = operador;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.resultado = resultado;
    }

    public Cuarteto(String valorT, String resultado) {
        this.valorT = valorT;
        this.resultado = resultado;
    }

    public Cuarteto(String valorT, String operador, String resultado) {
        this.valorT = valorT;
        this.operador = operador;
        this.resultado = resultado;
    }

    public Cuarteto() {

    }

    public String lineConverter() {
        String mandar = "";
        if (operador == null) {
            mandar = valorT + " = " + resultado;
        } else {
            switch (operador) {
                case "+":
                    mandar = valorT + "=" + izquierda + " + " + derecha;
                    break;
                case "-":
                    mandar = valorT + "=" + izquierda + " - " + derecha;
                    break;
                case "*":
                    mandar = valorT + "=" + izquierda + " * " + derecha;
                    break;
                case "/":
                    mandar = valorT + "=" + izquierda + " / " + derecha;
                    break;
                case "%":
                    mandar = valorT + "=" + izquierda + " % " + derecha;
                    break;
                case "--":
                    mandar = valorT + "=" + " - " + resultado;
                    break;
                case "IF":
                    mandar = "IF(" + resultado + ") ";
                    break;
                case "GOTO":
                    mandar = "GOTO " + resultado;
                    break;
                case "ET":
                    mandar = resultado + ": ";
                    break;
                case ">":
                    mandar = valorT + "=" + izquierda + " > " + derecha;
                    break;
                case "<":
                    mandar = valorT + "=" + izquierda + " < " + derecha;
                    break;
                case ">=":
                    mandar = valorT + "=" + izquierda + " >= " + derecha;
                    break;
                case "<=":
                    mandar = valorT + "=" + izquierda + " <= " + derecha;
                    break;
                case "!=":
                    mandar = valorT + "=" + izquierda + " != " + derecha;
                    break;
                case "==":
                    mandar = valorT + "=" + izquierda + " == " + derecha;
                    break;
                case "PRINT":
                    mandar = "" + resultado;
                    break;
                case "PRINTLN":
                    mandar = "" + resultado;
                    break;

                default:
                    break;
            }

        }
        return mandar;
    }

    public static void unirCuartetos(List<Cuarteto> uno, List<Cuarteto> dos, Cuarteto cuarteto) {
        for (int i = 0; i < dos.size(); i++) {
            uno.add(dos.get(i));
        }
        uno.add(cuarteto);
    }

    public static void unirCuartetos(List<Cuarteto> uno, List<Cuarteto> dos) {
        for (int i = 0; i < dos.size(); i++) {
            uno.add(dos.get(i));
        }

    }

    public String getValorT() {
        return valorT;
    }

    public void setValorT(String valorT) {
        this.valorT = valorT;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(String izquierda) {
        this.izquierda = izquierda;
    }

    public String getDerecha() {
        return derecha;
    }

    public void setDerecha(String derecha) {
        this.derecha = derecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
