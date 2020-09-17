/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Analizadores.Cup.sym;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.TabableView;

/**
 *
 * @author jpmazate
 */
public class Expresion {

    private String operator;
    private Expresion left;
    private Expresion right;
    private TablaSimbolos table;
    private TablaTipos tablaTipos;
    private Token id;
    private Dato literalValue;
    private Integer ambito;
    private String tipoExpresion;
    private boolean tieneString;

    public Expresion(String operator, Expresion left, Expresion right, TablaTipos tablaTipos) {
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.tablaTipos = tablaTipos;
    }

    public Expresion(String operator, Expresion right, TablaTipos tablaTipos) {
        this.operator = operator;
        this.right = right;
        this.tablaTipos = tablaTipos;
    }

    public Expresion(Dato literalValue, TablaSimbolos table, TablaTipos tablaTipos) {
        this.literalValue = literalValue;
        this.table = table;
        this.tablaTipos = tablaTipos;
    }

    public Expresion(Token id, TablaSimbolos table, TablaTipos tablaTipos) {
        this.table = table;
        this.id = id;
        this.tablaTipos = tablaTipos;
    }

    public boolean tieneString() {
        if (operator != null && !operator.equals("--")) {
            return left.tieneString() || right.tieneString();
        } else if (operator != null && operator.equals("--")) {
            return right.tieneString();
        } else {
            try {
                String value = getValue();
                if (value.equals("String")) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        }

    }

    public void asignarTieneString(boolean tiene) {
        this.tieneString = tiene;
        if (left != null) {
            left.asignarTieneString(tiene);
        }
        if (right != null) {
            right.asignarTieneString(tiene);
        }
    }

    public String validarClases(TablaTipos tipos, TablaSimbolos simbolos) {
        boolean bandera = tieneString();
        asignarTieneString(bandera);
        if (bandera) {
            if (estanOperadoresSumas()) {
                return "String";
            }
            return null;
        } else {
            asignarTablas(tipos, simbolos);
            return operarClases();
        }
    }

    public Boolean estanOperadoresSumas() {
        if (operator != null && operator.equals("+")) {
            return left.estanOperadoresSumas() && right.estanOperadoresSumas();
        } else if (operator != null) {
            return false;
        } else {
            return true;
        }
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

    //uno = Double, dos = Float
    public String operarClases() {
        if (operator != null && !operator.equals("--")) {
            return compararClases(left.operarClases(), right.operarClases());
        } else if (operator != null && !operator.equals("--") && !operator.equals("+")) {
            String valor = compararClases(left.operarClases(), right.operarClases());
            Tipos tipo = tablaTipos.returnTipo(valor, 1);
            if (tipo != null) {
                if (tipo.getConjuntoDatos().equals("Number")) {
                    return valor;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } else if (operator != null && operator.equals("--")) {

            String valor = right.operarClases();
            Tipos tipo = tablaTipos.returnTipo(valor, 1);
            if (tipo != null) {
                if (tipo.getConjuntoDatos().equals("Number")) {
                    return valor;
                } else {
                    return null;
                }
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
                Tipos tipoDos = tablaTipos.returnTipo(operador2, 1);

                if (tipoUno != null && tipoDos != null) {
                    if (tipoUno.getNumeroElemento() >= tipoDos.getNumeroElemento()) {
                        return tipoUno.getNombre();
                    } else {
                        return tipoDos.getNombre();
                    }
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
        //return literalValue.getLexema().getClass().getSimpleName();
    }

    public List<Cuarteto> generar3D() {
        List<Cuarteto> lista, lista1, lista2;
        Cuarteto cuarteto;
        String resultado1, resultado2;

        if (operator != null && !operator.equals("--")) {
            lista = new ArrayList<>();
            lista1 = left.generar3D();
            lista2 = right.generar3D();
            resultado1 = lista1.get(lista1.size() - 1).getValorT();
            resultado2 = lista2.get(lista2.size() - 1).getValorT();
            cuarteto = new Cuarteto(Temporales.siguienteTemporal(), operator, resultado1, resultado2);
            Cuarteto.unirCuartetos(lista1, lista2, cuarteto);
            return lista1;

        } else if (operator != null && operator.equals("--")) {
            lista = new ArrayList<>();
            lista2 = right.generar3D();
            resultado2 = lista2.get(lista2.size() - 1).getValorT();
            cuarteto = new Cuarteto(Temporales.siguienteTemporal(), operator, resultado2);
            lista2.add(cuarteto);
            return lista2;
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

    public String getOperator() {
        return operator;
    }

    public Expresion getLeft() {
        return left;
    }

    public Expresion getRight() {
        return right;
    }

    public TablaSimbolos getTable() {
        return table;
    }

    public void setTable(TablaSimbolos table) {
        this.table = table;
    }

    public Token getId() {
        return id;
    }

    public void setId(Token id) {
        this.id = id;
    }

    public Dato getLiteralValue() {
        return literalValue;
    }

    public void setLiteralValue(Dato literalValue) {
        this.literalValue = literalValue;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    public String getTipoExpresion() {
        return tipoExpresion;
    }

    public void setTipoExpresion(String tipoExpresion) {
        this.tipoExpresion = tipoExpresion;
    }

}


/*

public Object operate() {
        if (operator != null) {
            switch (operator) {
                case "+":
                    return sumar(left.operate(), right.operate());
                case "-":
                    return restar(left.operate(), right.operate());
                case "*":
                    return multiplicar(left.operate(), right.operate());
                case "/":
                    return dividir(left.operate(), right.operate());
                case "%":
                    return modulo(left.operate(), right.operate());
                case "--":
                    return cambioSigno(right.operate());
                default:
                    return getValue();
            }
        } else {
            return getValue();
        }

    }

    private Object sumar(Object left, Object right) {
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        String valor = operarClases(leftClass, rightClass);

        if (tieneString) {
            return ""+left + right;
        }
        if (valor != null) {
            switch (valor) {
                case "Integer":
                    return Integer.parseInt(left.toString()) + Integer.parseInt(right.toString());
                case "Byte":
                    return Byte.parseByte(left.toString()) + Byte.parseByte(right.toString());
                case "Long":
                    return Long.parseLong(left.toString()) + Long.parseLong(right.toString());
                case "Float":
                    return Float.parseFloat( left.toString()) + Float.parseFloat(right.toString());
                case "Double":
                    return Double.parseDouble( left.toString()) + Double.parseDouble(right.toString());
                default:
                    return null;
            }
        }
        return null;
    }


private Object restar(Object left, Object right) {
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        String valor = operarClases(leftClass, rightClass);

        if (valor != null) {
             switch (valor) {
                case "Integer":
                    return Integer.parseInt(left.toString()) - Integer.parseInt(right.toString());
                case "Byte":
                    return Byte.parseByte(left.toString()) - Byte.parseByte(right.toString());
                case "Long":
                    return Long.parseLong(left.toString()) - Long.parseLong(right.toString());
                case "Float":
                    return Float.parseFloat( left.toString()) - Float.parseFloat(right.toString());
                case "Double":
                    return Double.parseDouble( left.toString()) - Double.parseDouble(right.toString());
                default:
                    return null;
            }
        }
        return null;
    }

    private Object multiplicar(Object left, Object right) {
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        String valor = operarClases(leftClass, rightClass);

        if (valor != null) {
             switch (valor) {
                case "Integer":
                    return Integer.parseInt(left.toString()) * Integer.parseInt(right.toString());
                case "Byte":
                    return Byte.parseByte(left.toString()) * Byte.parseByte(right.toString());
                case "Long":
                    return Long.parseLong(left.toString()) * Long.parseLong(right.toString());
                case "Float":
                    return Float.parseFloat( left.toString()) * Float.parseFloat(right.toString());
                case "Double":
                    return Double.parseDouble( left.toString()) * Double.parseDouble(right.toString());
                default:
                    return null;
            }
        }
        return null;
    }

    private Object dividir(Object left, Object right) {
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        String valor = operarClases(leftClass, rightClass);

        if (valor != null) {
             switch (valor) {
                case "Integer":
                    return Integer.parseInt(left.toString()) / Integer.parseInt(right.toString());
                case "Byte":
                    return Byte.parseByte(left.toString()) / Byte.parseByte(right.toString());
                case "Long":
                    return Long.parseLong(left.toString()) / Long.parseLong(right.toString());
                case "Float":
                    return Float.parseFloat( left.toString()) / Float.parseFloat(right.toString());
                case "Double":
                    return Double.parseDouble( left.toString()) / Double.parseDouble(right.toString());
                default:
                    return null;
            }
        }
        return null;
    }

    private Object modulo(Object left, Object right) {
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        String valor = operarClases(leftClass, rightClass);

        if (valor != null) {
             switch (valor) {
                case "Integer":
                    return Integer.parseInt(left.toString()) % Integer.parseInt(right.toString());
                case "Byte":
                    return Byte.parseByte(left.toString()) % Byte.parseByte(right.toString());
                case "Long":
                    return Long.parseLong(left.toString()) % Long.parseLong(right.toString());
                case "Float":
                    return Float.parseFloat( left.toString()) % Float.parseFloat(right.toString());
                case "Double":
                    return Double.parseDouble( left.toString()) % Double.parseDouble(right.toString());
                default:
                    return null;
            }
        }
        return null;
    }

    private Object cambioSigno(Object right) {
        Class rightClass = right.getClass();

        switch (rightClass.getSimpleName()) {
            case "Integer":
                return -1 * Integer.parseInt(right.toString());
            case "Byte":
                return -1 * Byte.parseByte(right.toString());
            case "Long":
                return -1 * Long.parseLong(right.toString());
            case "Float":
                return -1 * Float.parseFloat(right.toString());
            case "Double":
                return -1 * Double.parseDouble( right.toString());
            default:
                return null;
        }
    }

*/