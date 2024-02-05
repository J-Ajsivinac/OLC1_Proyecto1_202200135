package Analyzers;
import java_cup.runtime.*;

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
ID_ARRAY "@"{ID}
STRING = \"({CONTENT}*)\"
DOUBLE = [0-9]+\.[0-9]+
COMMENT = “!”([^\r\n]*)?
COMMENTS = ”<!”(\n)*?”!>”

%{
	StringBuffer string = new StringBuffer();
	
	private Symbol symbol(int type){
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value){
		return new Symbol(type, yyline, yycolumn, value);
	}
%}



%eofval{
	return symbol(ParserSym.EOF);
%eofval} 
%%

<YYINITIAL> "var" {return symbol(ParserSym.TK_VAR, yytext());}
<YYINITIAL> ":" {return symbol(ParserSym.TK_COLON, yytext());}
<YYINITIAL> "double" {return symbol(ParserSym.TK_DOUBLE, yytext());}
<YYINITIAL> "[" {return symbol(ParserSym.TK_LBRACKET, yytext());}
<YYINITIAL> "]" {return symbol(ParserSym.TK_RBRACKET, yytext());}
<YYINITIAL> {ID|ID_ARRAY} {return symbol(ParserSym.TK_ID, yytext());}
<YYINITIAL> "-" {return symbol(ParserSym.TK_MINUS, yytext());} 
<YYINITIAL> "<" {return symbol(ParserSym.TK_LT, yytext());}
<YYINITIAL> {STRING} {return symbol(ParserSym.TK_STRING, yytext());}
<YYINITIAL> {DOUBLE} {return symbol(ParserSym.TK_DOUBLE, yytext());}
<YYINITIAL> ";" {return symbol(ParserSym.TK_SEMICOLON, yytext());}
<YYINITIAL> "arr" {return symbol(ParserSym.TK_ARR, yytext());}
<YYINITIAL> "," {return symbol(ParserSym.TK_COMMA, yytext());}
<YYINITIAL> "(" {return symbol(ParserSym.TK_LPAREN, yytext());}
<YYINITIAL> ")" {return symbol(ParserSym.TK_RPAREN, yytext());}
<YYINITIAL> "sum" {return symbol(ParserSym.TK_SUM, yytext());}
<YYINITIAL> "res" {return symbol(ParserSym.TK_RES, yytext());}
<YYINITIAL> "mul" {return symbol(ParserSym.TK_MUL, yytext());}
<YYINITIAL> "div" {return symbol(ParserSym.TK_DIV, yytext());}
<YYINITIAL> "mod" {return symbol(ParserSym.TK_MOD, yytext());}
<YYINITIAL> "media" {return symbol(ParserSym.TK_MEDIA, yytext());}
<YYINITIAL> "mediana" {return symbol(ParserSym.TK_MEDIANA, yytext());}
<YYINITIAL> "print" {return symbol(ParserSym.TK_PRINT, yytext());}
<YYINITIAL> "column" {return symbol(ParserSym.TK_COLUMN, yytext());}
<YYINITIAL> "console" {return symbol(ParserSym.TK_CONSOLE, yytext());}
<YYINITIAL> "graphbar" {return symbol(ParserSym.TK_GRAPHBAR, yytext());}
<YYINITIAL> "titulo" {return symbol(ParserSym.TK_TITULO, yytext());}
<YYINITIAL> "ejex" {return symbol(ParserSym.TK_EJEX, yytext());}
<YYINITIAL> "ejey" {return symbol(ParserSym.TK_EJEY, yytext());}
<YYINITIAL> "titulox" {return symbol(ParserSym.TK_TITULOX, yytext());}
<YYINITIAL> "tituloy" {return symbol(ParserSym.TK_TITULOY, yytext());}
<YYINITIAL> "exec" {return symbol(ParserSym.TK_EXEC, yytext());}
<YYINITIAL> "graphline" {return symbol(ParserSym.TK_GRAPHLINE, yytext());}
<YYINITIAL> "graphpie" {return symbol(ParserSym.TK_GRAPHPIE, yytext());}
<YYINITIAL> "label" {return symbol(ParserSym.TK_LABEL, yytext());}
<YYINITIAL> "values" {return symbol(ParserSym.TK_VALUES, yytext());}
<YYINITIAL> "histogram" {return symbol(ParserSym.TK_HISTOGRAM, yytext());}
<YYINITIAL> "end" {return symbol(ParserSym.TK_END, yytext());}
\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENT}              {}