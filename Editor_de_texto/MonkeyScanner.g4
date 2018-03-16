lexer grammar MonkeyScanner;

/****************************
    Símbolos del lenguaje
****************************/

PyCOMA : ';';
COMA: ',';
CIZQ: '[';
CDER: ']';
PIZQ: '(';
PDER: ')';
LIZQ: '{';
LDER: '}';
DOSPUN: ':';

/****************************
Operadores Lógicos
****************************/
MENOR: '<';
MAYOR: '>';
MENORIGUAL: '<=';
MAYORIGUAL: '>=';
ASSIGN: '=';
IGUALCOMP: '==';
/****************************
Operadores Matemáticos
****************************/
SUM: '+';
SUB: '-';
MUL: '*';
DIV: '/';

/*****************************
Palabras reservadas
******************************/
LET: 'let';
RETURN: 'return';
LEN: 'len';
FIRST: 'first';
LAST: 'last';
REST: 'rest';
PUSH: 'push';
FN: 'fn';
PUTS: 'puts';
IF: 'if';
ELSE: 'else';
TRUE: 'true';
FALSE: 'false';

/*******************************
Tipos Primitivos
********************************/
ID: LETTER (LETTER |DIGIT)*;
INTEGER: DIGIT DIGIT*;
STRING: '"' ( ~["\r\n] | '""' )* '"';

/*********************************
Fragment
**********************************/
fragment LETTER : 'a'..'z' | 'A'..'Z'|'_' ;
fragment DIGIT : '0'..'9';
fragment SYMBOLS : '!' |'¡' | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

/*********************************
Comentarios
**********************************/

COMMENT: '/*' .*? '*/' -> skip;//channel(HIDDEN);
LINECOMMENT: '//' (~'\n')* '\n' -> skip;
WS: [ \t\n\r]+ -> skip;
