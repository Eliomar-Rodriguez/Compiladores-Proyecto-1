parser grammar MonkeyParser;

options{
    tokenVocab= Scanner;
}

program  		: statement*;
statement  	: LET letStatement | RETURN returnStatement | expressionStatement;
letStatement          	: IDENTIFIER = expression ( PyCOMA | ε);
returnStatement	: expression ( PyCOMA | ε);
expressionStatement 	: expression ( PyCOMA | ε);
expression             	: additionExpression comparison;
comparison            	: ((MENOR|MAYOR|MENORIGUAL|MAYORIGUAL|IGUALCOMP) additionExpression)*;
additionExpression	: multiplicationExpression additionFactor;
additionFactor       	: ((SUM|SUB) multiplicationExpression)*;
multiplicationExpression : elementExpression multiplicationFactor;
multiplicationFactor	: ((MUL|DIV) elementExpression)*;
elementExpression 	: primitiveExpression elementAccess;
elementAccess       	: (CIZQ expression CDER);
primitiveExpression	: INTEGER | STRING | IDENTIFIER | BOOLEAN | PIZQ expression PDER | arrayLiteral | arrayFunctions PIZQ expressionList PDER | functionLiteral | hashLiteral | functionCallExpression | printExpression | ifExpression;
arrayFunctions	: LEN | FIRST | LAST | REST | PUSH;
arrayLiteral        	: CIZQ expressionList CDER;
funtionLiteral	: FN PIZQ functionParameters PDER blockStatement;
functionParameters	: IDENTIFIER moreIdentifiers;
moreIdentifiers	: (COMA IDENTIFIER)*;
hashLiteral		: LZQ hashContent moreHasContent LDER;
hashContent	: expression DOSPUN expression;
moreHashContent	: (COMA hashContent)*;
functionCallExpression   : expression PIZQ expressionList PDER;
expressionList       	: expression moreExpressions | ε;
moreExpressions    	: (COMA expression)*;
printExpressión      	: PUTS PIZQ expression PDER;
ifExpression	: IF expression blockStatement (ELSE blockStatement | ε);
blockStatement	: LIZQ statement* PDER;



