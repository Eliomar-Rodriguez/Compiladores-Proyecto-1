lexer grammar Scanner;

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
EPS:'ε';
/*******************************
Tipos Primitivos
********************************/

ID: LETTER (LETTER |DIGIT)*;
BOOLEAN: TRUE|FALSE;
INTEGER: DIGIT DIGIT*;
STRING: '"' .*? '"';

/*********************************
Comentarios
**********************************/
//COMMENT: '/*' ~( '/''/' | '*''/'|'/''*')* '*/';
COMMENT: '/*' .*? '*/';
LINECOMMENT: '//' (~'\n')* '\n';

/*********************************
Fragment
**********************************/
fragment TRUE: 'true';
fragment FALSE: 'false';
fragment LETTER : 'a'..'z' | 'A'..'Z'|'_' ;
fragment DIGIT : '0'..'9';
fragment SYMBOLS : '!' | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

WS: [ \t\n\r]+ -> skip;
