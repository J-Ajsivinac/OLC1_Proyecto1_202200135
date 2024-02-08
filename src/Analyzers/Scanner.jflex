package Analyzers;
import java_cup.runtime.*;
import Errores.Errores;
import java.util.ArrayList;


%%
%public
%class Scanner
%char
%column
%full
%line
%cup   
%unicode
%ignorecase

UNUSED=[ \r\t]+
CONTENT = ([^\n\"\\]|\\.)
ID = (\_)*[a-zA-Z][a-zA-Z0-9\_]*
ID_ARRAY = "@"{ID}
STRING = [\"][^\"\n]+[\"]
DOUBLE = [0-9]+(\.[0-9])*
COMMENT = [!]([^\r\n]*)?
COMMENTS = [<!](\n)*?[!>]

%{
	StringBuffer string = new StringBuffer();
	
	private Symbol symbol(int type){
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value){
		return new Symbol(type, yyline, yycolumn, value);
	}


    public static ArrayList<token> lexemas = new ArrayList<token>();
    public static ArrayList<Errores> erroreslexicos = new ArrayList<Errores>();

%}



%eofval{
	return symbol(ParserSym.EOF);
%eofval} 
%%
<YYINITIAL> "programa" {return symbol(ParserSym.TK_programa, yytext());}
<YYINITIAL> "end" {return symbol(ParserSym.TK_end, yytext());}
<YYINITIAL> "var" {return symbol(ParserSym.TK_var, yytext());}
<YYINITIAL> ":" {return symbol(ParserSym.TK_colon, yytext());}
<YYINITIAL> "double" {return symbol(ParserSym.TK_double, yytext());}
<YYINITIAL> "[" {return symbol(ParserSym.TK_lbracket, yytext());}
<YYINITIAL> "]" {return symbol(ParserSym.TK_rbracket, yytext());}
<YYINITIAL> "=" {return symbol(ParserSym.TK_equal, yytext());}
<YYINITIAL> "-" {return symbol(ParserSym.TK_minus, yytext());} 
<YYINITIAL> "<" {return symbol(ParserSym.TK_lt, yytext());}
<YYINITIAL> ">" {return symbol(ParserSym.TK_gt, yytext());}
<YYINITIAL> ";" {return symbol(ParserSym.TK_semicolon, yytext());}
<YYINITIAL> "arr" {return symbol(ParserSym.TK_arr, yytext());}
<YYINITIAL> "," {return symbol(ParserSym.TK_comma, yytext());}
<YYINITIAL> "(" {return symbol(ParserSym.TK_lparen, yytext());}
<YYINITIAL> ")" {return symbol(ParserSym.TK_rparen, yytext());}
<YYINITIAL> "sum" {return symbol(ParserSym.TK_sum, yytext());}
<YYINITIAL> "res" {return symbol(ParserSym.TK_res, yytext());}
<YYINITIAL> "mul" {return symbol(ParserSym.TK_mul, yytext());}
<YYINITIAL> "div" {return symbol(ParserSym.TK_div, yytext());}
<YYINITIAL> "mod" {return symbol(ParserSym.TK_mod, yytext());}
<YYINITIAL> "media" {return symbol(ParserSym.TK_media, yytext());}
<YYINITIAL> "mediana" {return symbol(ParserSym.TK_mediana, yytext());}
<YYINITIAL> "print" {return symbol(ParserSym.TK_print, yytext());}
<YYINITIAL> "column" {return symbol(ParserSym.TK_column, yytext());}
<YYINITIAL> "console" {return symbol(ParserSym.TK_console, yytext());}
<YYINITIAL> "graphbar" {return symbol(ParserSym.TK_graphbar, yytext());}
<YYINITIAL> "titulo" {return symbol(ParserSym.TK_titulo, yytext());}
<YYINITIAL> "ejex" {return symbol(ParserSym.TK_ejex, yytext());}
<YYINITIAL> "ejey" {return symbol(ParserSym.TK_ejey, yytext());}
<YYINITIAL> "titulox" {return symbol(ParserSym.TK_titulox, yytext());}
<YYINITIAL> "tituloy" {return symbol(ParserSym.TK_tituloy, yytext());}
<YYINITIAL> "exec" {return symbol(ParserSym.TK_exec, yytext());}
<YYINITIAL> "graphline" {return symbol(ParserSym.TK_graphline, yytext());}
<YYINITIAL> "graphpie" {return symbol(ParserSym.TK_graphpie, yytext());}
<YYINITIAL> "label" {return symbol(ParserSym.TK_label, yytext());}
<YYINITIAL> "values" {return symbol(ParserSym.TK_values, yytext());}
<YYINITIAL> "histogram" {return symbol(ParserSym.TK_histogram, yytext());}
<YYINITIAL> ({ID}|{ID_ARRAY}) {return symbol(ParserSym.TK_id, yytext());}
<YYINITIAL> {STRING} {return symbol(ParserSym.TK_string, yytext());}
<YYINITIAL> {DOUBLE} {return symbol(ParserSym.TK_double, yytext());}

\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENT}              {}
{COMMENTS}              {}
<YYINITIAL> . 
{
erroreslexicos.add(new Errores(0,"El caracter : '"+yytext(), Integer.toString(yyline), Integer.toString(yychar)));
}