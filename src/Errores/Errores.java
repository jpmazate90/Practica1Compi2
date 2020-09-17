
package Errores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Errores {
 
    private static final List<Error> errores = new ArrayList<>();
     
    
    public static List<Error> getErrores(){
        return errores;
    }
    
    public static void crearError(String tipo, String descripcion, Integer fila, Integer columna){
        errores.add(new Error(tipo, descripcion, fila, columna));
    }
    
    public static Error ultimoError(){
        return errores.get(errores.size()-1);
    }
    
    public static void vaciarErrores(){
        errores.clear();
    }
}
