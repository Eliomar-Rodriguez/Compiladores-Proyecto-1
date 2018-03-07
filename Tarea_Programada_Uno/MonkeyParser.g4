parser grammar monkeyParser;

options{
    tokenVocab= Scanner;
}

program  		: statement*;
statement  	: LET letStatement | RETURN returnStatement | expressionStatement;
letStatement          	: ID ASSIGN expression ( PyCOMA | EPS);
returnStatement	: expression ( PyCOMA |EPS);
expressionStatement 	: expression ( PyCOMA | EPS);
expression             	: additionExpression comparison;
comparison            	: ((MENOR|MAYOR|MENORIGUAL|MAYORIGUAL|IGUALCOMP) additionExpression)*;
additionExpression	: multiplicationExpression additionFactor;
additionFactor       	: ((SUM|SUB) multiplicationExpression)*;
multiplicationExpression : elementExpression multiplicationFactor;
multiplicationFactor	: ((MUL|DIV) elementExpression);
elementExpression 	: primitiveExpression (elementAccess | callExpression);
elementAccess       	: CIZQ expression CDER | EPS;
callExpression	: ( expressionList ) | EPS;
primitiveExpression	: INTEGER | STRING | ID | BOOLEAN | PIZQ expression PDER | arrayLiteral | arrayFunctions PIZQ expressionList PDER | functionLiteral | hashLiteral | printExpression | ifExpression;
arrayFunctions	: LEN | FIRST | LAST | REST | PUSH;
arrayLiteral        	: CIZQ expressionList CDER;
functionLiteral	: FN PIZQ functionParameters PDER blockStatement;
functionParameters	: ID moreIdentifiers;
moreIdentifiers	: (COMA ID)*;
hashLiteral		:  LIZQ hashContent moreHashContent LDER;
hashContent	: expression DOSPUN expression;
moreHashContent	: (COMA hashContent)*;
expressionList  : expression moreExpressions | EPS;
moreExpressions : (COMA expression)*;
printExpression : PUTS PIZQ expression PDER;
ifExpression	: IF expression blockStatement (ELSE blockStatement | EPS);
blockStatement	: LIZQ statement* LDER;




