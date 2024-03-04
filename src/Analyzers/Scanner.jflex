package Analyzers;
import java_cup.runtime.*;
import Errores.Errores;
import java.util.ArrayList;


%%

%public
%class Scanner
%cupsym TOK
%char
%column
%full
%line
%cup   
%unicode
%ignorecase

%init{ 
	yyline = 1; 
	yychar = 1; 
        yycolumn = 1;
%init} 


UNUSED=[ \r\t]+
CONTENT = ([^\n\"\\]|\\.)
ID = (\_)*[a-zA-Z][a-zA-Z0-9\_]*
ID_ARRAY = "@"{ID}
STRING = [\"][^\"\n]+[\"]
DOUBLE = [0-9]+(\.[0-9])*
COMMENT = [!]([^\r\n]*)?
COMMENTS= [<][!][^!]*[!]+([^<!][^!]*[!]+)*[>]


%{
	StringBuffer string = new StringBuffer();
	
	private Symbol symbol(int type){
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value){
		return new Symbol(type, yyline, yycolumn, value);
	}


    private ArrayList<token> lexemas = new ArrayList<token>();
    private ArrayList<Errores> erroreslexicos = new ArrayList<Errores>();

	public ArrayList<token> getLexemas() {
		return lexemas;
	}

	public ArrayList<Errores> getErroresL  () {
		return erroreslexicos;
	}

%}



%eofval{
	return symbol(ParserSym.EOF);
%eofval} 
%%

<YYINITIAL> "program" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_program], yytext(), yyline, yychar)); return symbol(ParserSym.TK_program, yytext());}
<YYINITIAL> "end" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_end], yytext(), yyline, yychar)); return symbol(ParserSym.TK_end, yytext());}
<YYINITIAL> "var" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_var], yytext(), yyline, yychar)); return symbol(ParserSym.TK_var, yytext());}
<YYINITIAL> ":" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_colon], yytext(), yyline, yychar)); return symbol(ParserSym.TK_colon, yytext());}
<YYINITIAL> "double" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_double], yytext(), yyline, yychar)); return symbol(ParserSym.TK_double, yytext());}
<YYINITIAL> "char" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_char], yytext(), yyline, yychar)); return symbol(ParserSym.TK_char, yytext());}
<YYINITIAL> "[" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_lbracket], yytext(), yyline, yychar)); return symbol(ParserSym.TK_lbracket, yytext());}
<YYINITIAL> "]" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_rbracket], yytext(), yyline, yychar)); return symbol(ParserSym.TK_rbracket, yytext());}
<YYINITIAL> "=" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_equal], yytext(), yyline, yychar)); return symbol(ParserSym.TK_equal, yytext());}
<YYINITIAL> "-" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_minus], yytext(), yyline, yychar)); return symbol(ParserSym.TK_minus, yytext());} 
<YYINITIAL> "<" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_lt], yytext(), yyline, yychar)); return symbol(ParserSym.TK_lt, yytext());}
<YYINITIAL> ">" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_gt], yytext(), yyline, yychar)); return symbol(ParserSym.TK_gt, yytext());}
<YYINITIAL> ";" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_semicolon], yytext(), yyline, yychar)); return symbol(ParserSym.TK_semicolon, yytext());}
<YYINITIAL> "arr" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_arr], yytext(), yyline, yychar)); return symbol(ParserSym.TK_arr, yytext());}
<YYINITIAL> "," {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_comma], yytext(), yyline, yychar)); return symbol(ParserSym.TK_comma, yytext());}
<YYINITIAL> "(" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_lparen], yytext(), yyline, yychar)); return symbol(ParserSym.TK_lparen, yytext());}
<YYINITIAL> ")" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_rparen], yytext(), yyline, yychar)); return symbol(ParserSym.TK_rparen, yytext());}
<YYINITIAL> "sum" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_sum], yytext(), yyline, yychar)); return symbol(ParserSym.TK_sum, yytext());}
<YYINITIAL> "res" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_res], yytext(), yyline, yychar)); return symbol(ParserSym.TK_res, yytext());}
<YYINITIAL> "mul" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_mul], yytext(), yyline, yychar)); return symbol(ParserSym.TK_mul, yytext());}
<YYINITIAL> "div" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_div], yytext(), yyline, yychar)); return symbol(ParserSym.TK_div, yytext());}
<YYINITIAL> "mod" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_mod], yytext(), yyline, yychar)); return symbol(ParserSym.TK_mod, yytext());}
<YYINITIAL> "media" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_media], yytext(), yyline, yychar)); return symbol(ParserSym.TK_media, yytext());}
<YYINITIAL> "mediana" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_mediana], yytext(), yyline, yychar)); return symbol(ParserSym.TK_mediana, yytext());}
<YYINITIAL> "moda" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_moda], yytext(), yyline, yychar)); return symbol(ParserSym.TK_moda, yytext());}
<YYINITIAL> "varianza" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_varianza], yytext(), yyline, yychar)); return symbol(ParserSym.TK_varianza, yytext());}
<YYINITIAL> "max" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_max], yytext(), yyline, yychar)); return symbol(ParserSym.TK_max, yytext());}
<YYINITIAL> "min" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_min], yytext(), yyline, yychar)); return symbol(ParserSym.TK_min, yytext());}
<YYINITIAL> "print" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_print], yytext(), yyline, yychar)); return symbol(ParserSym.TK_print, yytext());}
<YYINITIAL> "column" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_column], yytext(), yyline, yychar)); return symbol(ParserSym.TK_column, yytext());}
<YYINITIAL> "console" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_console], yytext(), yyline, yychar)); return symbol(ParserSym.TK_console, yytext());}
<YYINITIAL> "graphbar" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_graphbar], yytext(), yyline, yychar)); return symbol(ParserSym.TK_graphbar, yytext());}
<YYINITIAL> "ejex" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_ejex], yytext(), yyline, yychar)); return symbol(ParserSym.TK_ejex, yytext());}
<YYINITIAL> "ejey" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_ejey], yytext(), yyline, yychar)); return symbol(ParserSym.TK_ejey, yytext());}
<YYINITIAL> "titulox" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_titulox], yytext(), yyline, yychar)); return symbol(ParserSym.TK_titulox, yytext());}
<YYINITIAL> "tituloy" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_tituloy], yytext(), yyline, yychar)); return symbol(ParserSym.TK_tituloy, yytext());}
<YYINITIAL> "titulo" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_titulo], yytext(), yyline, yychar)); return symbol(ParserSym.TK_titulo, yytext());}
<YYINITIAL> "exec" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_exec], yytext(), yyline, yychar)); return symbol(ParserSym.TK_exec, yytext());}
<YYINITIAL> "graphline" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_graphline], yytext(), yyline, yychar)); return symbol(ParserSym.TK_graphline, yytext());}
<YYINITIAL> "graphpie" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_graphpie], yytext(), yyline, yychar)); return symbol(ParserSym.TK_graphpie, yytext());}
<YYINITIAL> "label" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_label], yytext(), yyline, yychar)); return symbol(ParserSym.TK_label, yytext());}
<YYINITIAL> "values" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_values], yytext(), yyline, yychar)); return symbol(ParserSym.TK_values, yytext());}
<YYINITIAL> "histogram" {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_histogram], yytext(), yyline, yychar)); return symbol(ParserSym.TK_histogram, yytext());}
<YYINITIAL> ({ID}|{ID_ARRAY}) {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_id], yytext(), yyline, yychar)); return symbol(ParserSym.TK_id, yytext());}
<YYINITIAL> {STRING} {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_string], yytext(), yyline, yychar)); return symbol(ParserSym.TK_string, yytext());}
<YYINITIAL> {DOUBLE} {lexemas.add(new token(Analyzers.ParserSym.terminalNames[ParserSym.TK_double], yytext(), yyline, yychar)); return symbol(ParserSym.TK_double, yytext());}

\n                      {yychar = 1; yycolumn=1;}
{UNUSED}                {}
{COMMENT}              {}
{COMMENTS}              {}
<YYINITIAL> . 
{
erroreslexicos.add(new Errores(0,"El caracter : '"+yytext()+"' No pertenece al lenguaje", Integer.toString(yyline), Integer.toString(yychar)));
}