parser grammar MonkeyParser;

options{
    tokenVocab= MonkeyScanner;
}

program  		: statement*                                            #prog_Mky;
statement  	: LET letStatement                                          #st_let_Mky
            | RETURN returnStatement                                    #st_return_Mky
            | expressionStatement                                       #st_expr_Mky;
letStatement          	: ID ASSIGN expression (PyCOMA | )              #lt_asign_Mky;
returnStatement	: expression ( PyCOMA | )                               #returnSt_Mky;
expressionStatement 	: expression ( PyCOMA | )                       #exprSt_Mky;
expression             	: additionExpression comparison                 #expr_Mky;
comparison            	: (MENOR additionExpression)*                   #compMenor_Mky
                         |(MAYOR additionExpression)*                   #compMayor_Mky
                         |(MENORIGUAL additionExpression)*              #compMenorIg_Mky
                         |(MAYORIGUAL additionExpression)*              #compMayorIg_Mky
                         |(IGUALCOMP additionExpression)*               #compIgComp_Mky;
additionExpression	: multiplicationExpression additionFactor           #addExpr_Mky;
additionFactor       	:  (SUM multiplicationExpression)*              #addFactSum_Mky
                          |(SUB multiplicationExpression)*              #addFactSub_Mky;
multiplicationExpression : elementExpression multiplicationFactor       #multExpr_Mky;
multiplicationFactor	: (MUL elementExpression)*                      #multFactMul_Mky
                         |(DIV elementExpression)*                      #multFactDiv_Mky;
elementExpression 	: primitiveExpression elementAccess                 #elemExprElemAccess_Mky
                    | primitiveExpression callExpression                #elemExprCallExpr_Mky
                    | primitiveExpression                               #elemExprPExpr_Mky;
elementAccess       	: CIZQ expression CDER                          #elemAccess_Mky;
callExpression	: PIZQ expressionList PDER                              #callExpr_Mky;
primitiveExpression	:     INTEGER                                       #pExprInt_Mky
                        | STRING                                        #pExprStrMky
                        | ID                                            #pExprID_Mky
                        | TRUE                                          #pExprTRUE_Mky
                        | FALSE                                         #pExprFALSE_Mky
                        | PIZQ expression PDER                          #pExprCallExpr_Mky
                        | arrayLiteral                                  #pExprArrayLit_Mky
                        | arrayFunctions PIZQ expressionList PDER       #pExprArrayFunc_Mky
                        | functionLiteral                               #pExprFuncDecl_Mky
                        | hashLiteral                                   #pExprHashLit_Mky
                        | printExpression                               #pExprPrint_Mky
                        | ifExpression                                  #pExprIfExpr_Mky;
arrayFunctions:  LEN                                                    #arrayFuncLen_Mky
               | FIRST                                                  #arrayFuncFirst_Mky
               | LAST                                                   #arrayFuncLast_Mky
               | REST                                                   #arrayFuncRest_Mky
               | PUSH                                                   #arrayFuncPush_Mky;
arrayLiteral        	: CIZQ expressionList CDER                      #arrayLit_Mky;
functionLiteral	: FN PIZQ functionParameters PDER blockStatement        #funcLit_Mky;
functionParameters	: ID moreIdentifiers                                #funcParams_Mky;
moreIdentifiers	: (COMA ID)*                                            #moreIdentifiers_Mky;
hashLiteral		: LIZQ hashContent moreHashContent LDER                 #hashLit_Mky;
hashContent	: expression DOSPUN expression                              #hashCont_Mky;
moreHashContent	: (COMA hashContent)*                                   #moreHashCont_Mky;
expressionList       	: expression moreExpressions                    #exprList_Mky
                        |                                               #exprListEmpty_Mky;
moreExpressions    	: (COMA expression)*                                #moreExpr_Mky;
printExpression      	: PUTS PIZQ expression PDER                     #printExpr_Mky;
ifExpression	: IF expression blockStatement (ELSE blockStatement | ) #ifExpr_Mky;
blockStatement	: LIZQ statement* LDER                                  #blockSt_Mky;