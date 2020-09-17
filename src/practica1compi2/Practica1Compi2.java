package practica1compi2;

import Analizadores.Cup.parser;
import Analizadores.Flex.Lexer;
import GUI.EditorTexto;
import Instructions.Asignacion;
import Instructions.BaseIf;
import Instructions.DoWhile;
import Instructions.Else;
import Instructions.Elsif;
import Instructions.For;
import Instructions.If;
import Instructions.Instruction;
import Instructions.Print;
import Instructions.Println;
import Instructions.While;
import Objetos.Condicional;
import Objetos.Dato;
import Objetos.Expresion;
import Valores.Numero;
import Objetos.Cuarteto;
import Tablas.TablaSimbolos.Simbolos;
import Tablas.TablaSimbolos.TablaSimbolos;
import Tablas.TablaTipos.TablaTipos;
import Tablas.TablaTipos.Tipos;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author jpmazate
 */
public class Practica1Compi2 {

    public static void main(String[] args) {

        try {


           
            EditorTexto editor = new EditorTexto();
            editor.dispose();
            editor.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

/*
jaja saludos
Expresion expr1 = new Expresion(new Dato("HOLA","Integer",5, false), null, null);
        Expresion expr2 = new Expresion(new Dato("JAJA","Integer",884848, false), null, null);
        Expresion expr3= new Expresion(new Dato("3","STRING",5.5f, false), null, null);
        Expresion expr4 = new Expresion(new Dato("4","STRING",5.4f, false), null, null);
        Expresion expr5 = new Expresion("+",expr1,expr2,null);
        Expresion expr6 = new Expresion("*",expr3,expr4,null);   
        Expresion exprFINAL = new Expresion("%",expr5,expr6,null);
        
        
        

        Condicional cond1 = new Condicional(new Dato("miVar1", "VARIABLE",null, false), null);
        Condicional cond2 = new Condicional(new Dato("miVar2", "VARIABLE", null,false), null);
        Condicional cond3 = new Condicional("AND", cond1, cond2);
        Condicional cond4 = new Condicional(new Dato("JEJE", "STRING", 9.0f, false), null);
        Condicional cond5 = new Condicional(new Dato("JIJI", "STRING", 9, false), null);
        Condicional cond6 = new Condicional("<=", cond4, cond5);
        Condicional cond7 = new Condicional("AND", cond3, cond6);
        Condicional cond8 = new Condicional("NOT", cond1);

        List<Cuarteto> lista = cond8.generar3D();

        List<Dato> datos = new ArrayList<>();
        datos.add(new Dato("TOKEN", null, lista, true));
        datos.add(new Dato("LUIS", null, lista, true));
        Println printLN = new Println(datos);
        Print print = new Print(datos);
        List<Instruction> instruction = new ArrayList<>();
        instruction.add(printLN);
        instruction.add(print);
        DoWhile lile = new DoWhile(cond8,instruction );
        //List<Cuarteto> list = lile.execute();
        
        
        
        For forr = new For(expr1, expr5, cond3 , new Dato("IDASIGNACION","VARIABLE", lile, true), true, instruction,new TablaSimbolos(),new TablaTipos());
        
        List<Instruction> ja = new ArrayList<>();
        ja.add(forr);
        
        List<Cuarteto> list = forr.execute();
        
        
        Else elsee = new Else(ja);
        BaseIf base = new BaseIf(cond3, instruction);
        Elsif elseif = new Elsif(cond3, instruction);
        Elsif elseiff = new Elsif(cond6, instruction);
        List<Elsif> listado = new ArrayList<>();
        listado.add(elseif);
        listado.add(elseiff);
        If iff = new If(base, listado ,elsee);
        list = iff.execute();
        
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).lineConverter());
            
        }
        try {
            
            TablaTipos tipos = new TablaTipos();
            
            Tipos tstring = new Tipos(1,"String",null,null,null,null,1,null,"String");
            Tipos tint = new Tipos(2,"Integer",null,null,null,null,1,null,"Number");
            Tipos tbyte = new Tipos(3,"Byte",null,null,null,null,1,2,"Number");
            Tipos tlong = new Tipos(4,"Long",null,null,null,null,1,3,"Number");
            Tipos tfloat= new Tipos(5,"Float",null,null,null,null,1,4,"Number");
            Tipos tdouble= new Tipos(6,"Double",null,null,null,null,1,5,"Number");
            
            Tipos tboolean = new Tipos(7,"Boolean",null,null,null,null,1,null,"Boolean");
            Tipos tchar = new Tipos(8,"String",null,null,null,null,1,null,"String");
            
            tipos.addElement(tstring);
            tipos.addElement(tint);
            tipos.addElement(tbyte);
            tipos.addElement(tlong);
            tipos.addElement(tfloat);
            tipos.addElement(tdouble);
            tipos.addElement(tboolean);
            tipos.addElement(tchar);
            
            Long longg= Long.parseLong("9");
            System.out.println(longg);
            
            TablaSimbolos tabla = new TablaSimbolos();
            tabla.addElement(new Simbolos(1, "miVar1", true, "a", 7, null, null, 1,null));
            tabla.addElement(new Simbolos(2, "miVar2", true, "a", 7, null, null, 1,null));
            
            String v = cond8.validarClases(tipos, tabla);
            
            System.out.println(v);

 */
