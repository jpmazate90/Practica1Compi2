package Tablas.TablaSimbolos;

import Objetos.AccionVariable;
import Objetos.Token;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jpmazate
 */
public class TablaSimbolos {

    List<Simbolos> tablaSimbolos;

    public TablaSimbolos() {
        this.tablaSimbolos = new ArrayList<>();
    }

    public boolean addElement(Simbolos element) {
        if (!exists(element.getNombre(), element.getAmbito())) {
            tablaSimbolos.add(element);
            return true;
        }
        return false;
    }

    public boolean exists(String nombre, int ambito) {
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (tablaSimbolos.get(i).getNombre().equals(nombre) && 1 == tablaSimbolos.get(i).getAmbito()) {
                return true;
            } 
        }
        return false;
    }

    public Object getIdValue(String id, int ambito) {
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (tablaSimbolos.get(i).getNombre().equals(id) && 1 == tablaSimbolos.get(i).getAmbito()) {
                return tablaSimbolos.get(i).getValor();

            }
        }
        return null;
    }
    
    public String getTipoDato(String id, int ambito,TablaTipos tipos){
         for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (tablaSimbolos.get(i).getNombre().equals(id) && 1 == tablaSimbolos.get(i).getAmbito()) {
                for (int j = 0; j < tipos.getTabla().size(); j++) {
                    if(tipos.getTabla().get(j).getNumeroElemento()==tablaSimbolos.get(i).getTipo()){
                        return tipos.getTabla().get(j).getNombre();
                    }
                }

            }
        }
        return null;
    }

    public boolean assignValueToId(Token id, Object value, int ambito) {
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (tablaSimbolos.get(i).getNombre().equals(id.getLexeme()) && 1 == tablaSimbolos.get(i).getAmbito()) {
                tablaSimbolos.get(i).setValor(value);
                return true;
            }
        }
        return false;
    }

    public boolean removeParameter(Token id, int ambito) {
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (tablaSimbolos.get(i).getNombre().equals(id.getLexeme()) && 1 == tablaSimbolos.get(i).getAmbito()) {
                tablaSimbolos.remove(i);
                return true;
            }
        }
        return false;
    }

    public void cleanAll() {
        this.tablaSimbolos.clear();
    }

    public int size() {
        return this.tablaSimbolos.size();
    }

    

}
