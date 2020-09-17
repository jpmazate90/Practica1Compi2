/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Objetos.Condicional;
import java.util.List;

/**
 *
 * @author jpmazate
 */
public class Elsif {
    
    private Condicional condicion;
    private List<Instruction> instructions;

    public Elsif(Condicional condicion, List<Instruction> instructions) {
        this.condicion = condicion;
        this.instructions = instructions;
    }

    public Condicional getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicional condicion) {
        this.condicion = condicion;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
    
    
    
}
