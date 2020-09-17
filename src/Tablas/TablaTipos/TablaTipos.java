package Tablas.TablaTipos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class TablaTipos {

    private List<Tipos> tablaTipos;

    public TablaTipos() {
        this.tablaTipos = new ArrayList<>();
    }

    public boolean addElement(Tipos element) {
        if (!exists(element.getNombre(), element.getAmbito())) {
            tablaTipos.add(element);
            return true;
        }
        return false;
    }

    public Tipos createElement(String nombre, int tipoBase, int padre, int dimension, int minimo, int max, int ambito) {
        Tipos tipos = new Tipos(tablaTipos.size() + 1, nombre, tipoBase, padre, dimension, minimo, ambito);
        tipos.setMaximo(max);
        return tipos;
    }

    public boolean exists(String nombre, int ambito) {
        for (int i = 0; i < tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(nombre) && 1 == tablaTipos.get(i).getAmbito()) {
                return true;
            } 
        }
        return false;
    }

    public Tipos posicionTipo(String tipo) {
        for (int i = 0; i < tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(tipo)) {
                return tablaTipos.get(i);
            }
        }
        return null;
    }

    public Boolean pertenecenAlMismoDato(String uno, String dos) {
        String tipoUno = null, tipoDos = null;
        for (int i = 0; i < this.tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(uno)) {
                tipoUno = tablaTipos.get(i).getConjuntoDatos();
            }
        }

        for (int i = 0; i < this.tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(dos)) {
                tipoDos = tablaTipos.get(i).getConjuntoDatos();
            }
        }

        if (tipoUno != null && tipoDos != null) {
            if (tipoUno.equals(tipoDos)) {
                return true;
            } else {
                return false;
            }
        }

        return null;
    }

    public Tipos returnTipo(String nombre, int ambito) {
        for (int i = 0; i < tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(nombre) && tablaTipos.get(i).getAmbito() == 1) {
                return tablaTipos.get(i);
            }
        }
        return null;
    }

    public String crearElemento(String nombre, String categoria, String tipo, Integer numeroParametros, List<Object> parametros, Integer ambito, List<Object> valoresArreglo, TablaTipos tablaTipos) {
        try {
            int numeroElemento = tablaTipos.tipoElemento(tipo, ambito);

            int tipoBase = tipoElemento(tipo, ambito);
            int maximo;
            if (valoresArreglo != null) {
                maximo = valoresArreglo.size();
            } else {
                maximo = 0;
            }
            if (tipoBase != -1 && numeroElemento != -1) {
                Tipos simbol = createElement(nombre, tipoBase, ambito, numeroParametros, 0, maximo, ambito);
                addElement(simbol);
                return "TRUE";
            } else {
                return "NO SE HA PODIDO AGREGAR EL ELEMENTO :" + nombre + " A LA TABLA DE SIMBOLOS NI DE TIPOS";
            }

        } catch (Exception e) {
            return "NO SE HA PODIDO AGREGAR EL ELEMENTO :" + nombre + " A LA TABLA DE SIMBOLOS NI DE TIPOS";
        }

    }

    public int tipoElemento(String nombre, int ambito) {
        for (int i = 0; i < tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(nombre) && tablaTipos.get(i).getAmbito() == 1) {
                return tablaTipos.get(i).getNumeroElemento();
            }
        }
        return -1;
    }

    public Tipos getElemento(String nombre, int ambito) {
        for (int i = 0; i < tablaTipos.size(); i++) {
            if (tablaTipos.get(i).getNombre().equals(nombre) && tablaTipos.get(i).getAmbito() == 1) {
                return tablaTipos.get(i);
            }

        }
        return null;
    }

    public List<Tipos> getTabla() {
        return this.tablaTipos;
    }
}
