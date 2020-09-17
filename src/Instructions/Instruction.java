
package Instructions;

import Objetos.Cuarteto;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public interface Instruction {
    
    public abstract List<Cuarteto> execute();
    
}
