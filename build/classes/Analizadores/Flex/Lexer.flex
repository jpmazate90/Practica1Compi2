/* codigo de usuario */
package Analizadores.Flex;

import Errores.Errores;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import Analizadores.Cup.sym;
import Objetos.Logica;
import javax.swing.JTextArea;

%% //separador de area



 /* opciones y declaraciones de jflex */

%class Lexer
%public
%cup
%cupdebug
%line
%column

%state STRING
%state COMENTARIO_LINEA
%state COMENTARIO_BLOQUE


WhiteSpace = [\r|\n|\r\n] | [ \t\f]
SaltoLinea = [\n]



BOOLEAN = "boolean"
CHAR = "char"
BYTE = "byte"
INT = "int"
LONG = "long"
FLOAT = "float"
DOUBLE = "double"
STRING = "string"

NOT = "NOT"
AND = "AND"
OR = "OR"

TRUEE = "TRUE"
FALSEE = "FALSE"

ARRAY = "ARRAY"

IF = "IF"
ELSIF = "ELSIF"
ELSE = "ELSE"

WHILE = "WHILE"
DO = "DO"

FOR = "FOR"


PRINT = "PRINT"
PRINTLN = "PRINTLN"



PUNTO_COMA = ";"

COMILLA = "\""

MENOR = "<"
MAYOR = ">"
MENOR_IGUAL = "<="
MAYOR_IGUAL = ">="
DIFERENTE = "!="
IGUAL = "=="

PUNTO = "."
COMA = ","
PUNTO_COMA = ";"
DOS_PUNTOS = ":"
F = "f"


PARENTESIS_ABIERTO = "("
PARENTESIS_CERRADO = ")"
CORCHETE_ABIERTO = "["
CORCHETE_CERRADO = "]"

COMENTARIO_LINEA = "--"
INICIO_COMENTARIO_BLOQUE = "<!--"
FIN_COMENTARIO_BLOQUE = "-->"

SUMA = "+"
RESTA = "-"
MULTIPLICACION = "*"
DIVISION = "/"
MODULO = "%"
ASIGNACION = "<-"


NUMERO = [0-9]
NUMERO_SIN_CERO = [1-9]
LETRA = [a-zA-Z]



IDD =    ({LETRA})({LETRA}|{NUMERO}|{RESTA})*
NUMERO_ENTERO = ({RESTA}|{SUMA})?({NUMERO_SIN_CERO}({NUMERO})* | "0")
NUMERO_DOUBLE = ({RESTA}|{SUMA})?({NUMERO_SIN_CERO}({NUMERO})* | "0") {PUNTO} {NUMERO}+
NUMERO_FLOAT =  ({RESTA}|{SUMA})?({NUMERO_SIN_CERO}({NUMERO})* | "0") {PUNTO} {NUMERO}+ {F}




%{

	String lexema = "";
	String lexemaError = "";

	String lexemaMandar = "";
        ArrayList<Errores> errores;
        JTextArea area;
        Logica logica;
	public Lexer(java.io.Reader in,JTextArea area,ArrayList<Errores> errores) {
    		this.zzReader = in;
		this.area=area;
                this.errores = errores;
		this.area=area;
		this.logica = new Logica();

  	}
  	
  	
  	

	public void crearLexema(String mandar){
		lexema = lexema+mandar;	
	}
	public void crearLexemaMandar(String mandar){
		lexemaMandar = lexemaMandar+mandar;	
	}
	public void crearLexemaError(String mandar){
		lexemaError = lexemaError+ mandar;
	}
	public void verificarError(){
		if(!lexemaError.equals("")){
			error(lexemaError);
		}
		lexemaError="";
	}
	

  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  private void error(String message) {
      System.out.println("\nError Lexico en Fila: " + (yyline + 1) + ", columna " + (yycolumn + 1) + " : " + message + "\n");
        Errores.crearError("LEXICO", "ERROR LEXICO CON LEXEMA: " + message, yyline + 1, yycolumn + 1);
        logica.mostrarError(Errores.ultimoError(),area);
  }
%}



%% /* separador de areas*/

/* reglas lexicas */
<YYINITIAL> {
{WhiteSpace} {verificarError();}
{COMILLA} {yybegin(STRING);}

{COMENTARIO_LINEA} {verificarError();yybegin(COMENTARIO_LINEA);}
{INICIO_COMENTARIO_BLOQUE} {verificarError();yybegin(COMENTARIO_BLOQUE);} 

{BOOLEAN} {verificarError();return symbol(sym.BOOLEAN,yytext());}
{CHAR} {verificarError();return symbol(sym.CHAR,yytext());}
{BYTE} {verificarError();return symbol(sym.BYTE,yytext());}
{INT} {verificarError();return symbol(sym.INT,yytext());}
{LONG} {verificarError();return symbol(sym.LONG,yytext());}
{FLOAT} {verificarError();return symbol(sym.FLOAT,yytext());}
{DOUBLE} {verificarError();return symbol(sym.DOUBLE,yytext());}
{STRING} {verificarError();return symbol(sym.STRING,yytext());}

{NOT} {verificarError();return symbol(sym.NOT,yytext());}
{AND} {verificarError();return symbol(sym.AND,yytext());}
{OR} {verificarError();return symbol(sym.OR,yytext());}

{TRUEE} {verificarError();return symbol(sym.TRUEE,yytext());}
{FALSEE} {verificarError();return symbol(sym.FALSEE,yytext());}

{ARRAY} {verificarError();return symbol(sym.ARRAY,yytext());}

{IF} {verificarError();return symbol(sym.IF,yytext());}
{ELSIF} {verificarError();return symbol(sym.ELSIF,yytext());}
{ELSE} {verificarError();return symbol(sym.ELSE,yytext());}

{WHILE} {verificarError();return symbol(sym.WHILE,yytext());}
{DO} {verificarError();return symbol(sym.DO,yytext());}

{FOR} {verificarError();return symbol(sym.FOR,yytext());}

{PRINT} {verificarError();return symbol(sym.PRINT,yytext());}
{PRINTLN} {verificarError();return symbol(sym.PRINTLN,yytext());}

{ASIGNACION} {verificarError();return symbol(sym.ASIGNACION,yytext());}
{MENOR} {verificarError();return symbol(sym.MENOR,yytext());}
{MAYOR} {verificarError();return symbol(sym.MAYOR,yytext());}
{MENOR_IGUAL} {verificarError();return symbol(sym.MENOR_IGUAL,yytext());}
{MAYOR_IGUAL} {verificarError();return symbol(sym.MAYOR_IGUAL,yytext());}
{DIFERENTE} {verificarError();return symbol(sym.DIFERENTE,yytext());}
{IGUAL} {verificarError();return symbol(sym.IGUAL,yytext());}

{COMA} {verificarError();return symbol(sym.COMA,yytext());}
{PUNTO_COMA} {verificarError();return symbol(sym.PUNTO_COMA,yytext());}
{DOS_PUNTOS} {verificarError();return symbol(sym.DOS_PUNTOS,yytext());}

{PARENTESIS_ABIERTO} {verificarError();return symbol(sym.PARENTESIS_ABIERTO,yytext());}
{PARENTESIS_CERRADO} {verificarError();return symbol(sym.PARENTESIS_CERRADO,yytext());}
{CORCHETE_ABIERTO} {verificarError();return symbol(sym.CORCHETE_ABIERTO,yytext());}
{CORCHETE_CERRADO} {verificarError();return symbol(sym.CORCHETE_CERRADO,yytext());}


{SUMA} {verificarError();return symbol(sym.SUMA,yytext());}
{RESTA} {verificarError();return symbol(sym.RESTA,yytext());}
{MULTIPLICACION} {verificarError();return symbol(sym.MULTIPLICACION,yytext());}
{DIVISION} {verificarError();return symbol(sym.DIVISION,yytext());}
{MODULO} {verificarError();return symbol(sym.MODULO,yytext());}
 
{IDD} {verificarError();return symbol(sym.IDD,yytext());}
{NUMERO_ENTERO} {verificarError(); if(Integer.parseInt(yytext())>Math.pow(2, 32)){return symbol(sym.NUMERO_LONG,yytext());}else{return symbol(sym.NUMERO_ENTERO,yytext());}}
{NUMERO_DOUBLE} {verificarError();return symbol(sym.NUMERO_DOUBLE,yytext());}
{NUMERO_FLOAT} {verificarError();return symbol(sym.NUMERO_FLOAT,yytext());}

}

<STRING> {
{COMILLA} {yybegin(YYINITIAL);return symbol(sym.LEXEMA_STRING,this.lexema);}
[^]              {crearLexema(yytext());}  
}

<COMENTARIO_LINEA>{ 
{SaltoLinea}  {verificarError();yybegin(YYINITIAL);}
[^] {verificarError();}
}

<COMENTARIO_BLOQUE>{
{FIN_COMENTARIO_BLOQUE} {verificarError();yybegin(YYINITIAL);}
[^] {verificarError();}
}


[^]                     {crearLexemaError(yytext());}
<<EOF>>                 {return symbol(sym.EOF);}


