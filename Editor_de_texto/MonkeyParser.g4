parser grammar MonkeyParser;

options{
    tokenVocab= MonkeyScanner;
}

program  		: statement*                                            #program_Mky;
statement  	: LET letStatement                                          #statement_LET_Mky
            | RETURN returnStatement                                    #statement_RETURN_Mky
            | expressionStatement                                       #statement_EXPRESSIONSTATEMENT_Mky;
letStatement          	: ID ASSIGN expression (PyCOMA | )              #letStatement_Mky;
returnStatement	: expression ( PyCOMA | )                               #returnSatetement_Mky;
expressionStatement 	: expression ( PyCOMA | )                       #expressionStatement_Mky;
expression             	: additionExpression comparison                 #expression_Mky;
comparison            	: (MENOR additionExpression)*                   #comparison_MENOR_Mky
                         |(MAYOR additionExpression)*                   #comparison_MAYOR_Mky
                         |(MENORIGUAL additionExpression)*              #comparison_MENORIGUAL_Mky
                         |(MAYORIGUAL additionExpression)*              #comparison_MAYORIGUAL_Mky
                         |(IGUALCOMP additionExpression)*               #comparison_IGUALCOMP_Mky;
additionExpression	: multiplicationExpression additionFactor           #additionExpression_Mky;
additionFactor       	:  (SUM multiplicationExpression)*              #additionFactor_SUM_Mky
                          |(SUB multiplicationExpression)*              #additionFactor_SUB_Mky;
multiplicationExpression : elementExpression multiplicationFactor       #multiplicationExpression_Mky;
multiplicationFactor	: (MUL elementExpression)*                      #multiplicationFactor_MUL_Mky
                         |(DIV elementExpression)*                      #multiplicationFactor_DIV_Mky;
elementExpression 	: primitiveExpression elementAccess                 #elementExpression_PRIMEXP_ELEMENTACESS_Mky
                    | primitiveExpression callExpression                #elementExpression_PRIMEXP_CALLEXPRESSION_Mky
                    | primitiveExpression                               #elementExpression_PRIMEXP_PRIMITIVEEXPRESSION_Mky;
elementAccess       	: CIZQ expression CDER                          #elementAccess_Mky;
callExpression	: PIZQ expressionList PDER                              #callExpression_Mky;
primitiveExpression	:     INTEGER                                       #primitiveExpression_INTEGER_Mky
                        | STRING                                        #primitiveExpression_STRING_Mky
                        | ID                                            #primitiveExpression_ID_Mky
                        | TRUE                                          #primitiveExpression_TRUE_Mky
                        | FALSE                                         #primitiveExpression_FALSE_Mky
                        | PIZQ expression PDER                          #primitiveExpression_CALLEXPRESSION_Mky
                        | arrayLiteral                                  #primitiveExpression_ARRAYLITERAL_Mky
                        | arrayFunctions PIZQ expressionList PDER       #primitiveExpression_ARRAYOPERATION_Mky
                        | functionLiteral                               #primitiveExpression_FUNCTIONLITERAL_Mky
                        | hashLiteral                                   #primitiveExpression_HASHLITERAL_Mky
                        | printExpression                               #primitiveExpression_PRINTEXPRESSION_Mky
                        | ifExpression                                  #primitiveExpression_IFEXPRESSION_Mky;
arrayFunctions:  LEN                                                    #arrayFunctions_LEN_Mky
               | FIRST                                                  #arrayFunctions_FIRST_Mky
               | LAST                                                   #arrayFunctions_LAST_Mky
               | REST                                                   #arrayFunctions_REST_Mky
               | PUSH                                                   #arrayFunctions_PUSH_Mky;
arrayLiteral        	: CIZQ expressionList CDER                      #arrayLiteral_Mky;
functionLiteral	: FN PIZQ functionParameters PDER blockStatement        #functionLiteral_Mky;
functionParameters	: ID moreIdentifiers                                #functionParameters_Mky;
moreIdentifiers	: (COMA ID)*                                            #moreIdentifiers_Mky;
hashLiteral		: LIZQ hashContent moreHashContent LDER                 #hashLiteral_Mky;
hashContent	: expression DOSPUN expression                              #hashContent_Mky;
moreHashContent	: (COMA hashContent)*                                   #moreHashContent_Mky;
expressionList       	: expression moreExpressions                    #expressionList_MULTIPLEEXPRESSION_Mky
                        |                                               #expressionList_EXPRESSION_Mky;
moreExpressions    	: (COMA expression)*                                #moreExpressions_Mky;
printExpression      	: PUTS PIZQ expression PDER                     #printExpression_Mky;
ifExpression	: IF expression blockStatement (ELSE blockStatement | ) #ifExpression_Mky;
blockStatement	: LIZQ statement* LDER                                  #blockStatement_Mky;