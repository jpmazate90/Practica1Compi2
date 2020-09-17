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
public class Temporales {

    private static String temporal = "t";
    private static int contador = 0;

    public static String siguienteTemporal() {
        temporal = "t" + contador;
        contador++;
        return temporal;
    }

    public static String ultimoTemporal() {
        return temporal;
    }

    public static void reiniciar() {
        contador = 0;
    }

    public static String getTemporal() {
        return temporal;
    }

    public static void setTemporal(String temporal) {
        temporal = temporal;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        contador = contador;
    }

}
