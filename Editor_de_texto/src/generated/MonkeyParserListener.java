// Generated from C:/Users/anton/Desktop/Compiladores.EliomarRodriguez-JosuaCarranza.PrimerProgra/Editor_de_texto\MonkeyParser.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MonkeyParser}.
 */
public interface MonkeyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code prog_Mky}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProg_Mky(MonkeyParser.Prog_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prog_Mky}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProg_Mky(MonkeyParser.Prog_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code st_let_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSt_let_Mky(MonkeyParser.St_let_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code st_let_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSt_let_Mky(MonkeyParser.St_let_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code st_return_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSt_return_Mky(MonkeyParser.St_return_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code st_return_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSt_return_Mky(MonkeyParser.St_return_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code st_expr_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSt_expr_Mky(MonkeyParser.St_expr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code st_expr_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSt_expr_Mky(MonkeyParser.St_expr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lt_asign_Mky}
	 * labeled alternative in {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void enterLt_asign_Mky(MonkeyParser.Lt_asign_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lt_asign_Mky}
	 * labeled alternative in {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void exitLt_asign_Mky(MonkeyParser.Lt_asign_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnSt_Mky}
	 * labeled alternative in {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnSt_Mky}
	 * labeled alternative in {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSt_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExprSt_Mky(MonkeyParser.ExprSt_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSt_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExprSt_Mky(MonkeyParser.ExprSt_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_Mky}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpr_Mky(MonkeyParser.Expr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_Mky}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpr_Mky(MonkeyParser.Expr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compMenor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compMenor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compMayor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compMayor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compMenorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compMenorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compMayorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compMayorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compIgComp_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compIgComp_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addFactSum_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void enterAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addFactSum_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void exitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addFactSub_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void enterAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addFactSub_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void exitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multFactMul_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void enterMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multFactMul_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void exitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multFactDiv_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void enterMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multFactDiv_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void exitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elemExprElemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elemExprElemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elemExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elemExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elemExprPExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elemExprPExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void enterElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void exitElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specialCall_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 */
	void enterSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specialCall_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 */
	void exitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specialCallEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 */
	void enterSpecialCallEmpty_Mky(MonkeyParser.SpecialCallEmpty_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specialCallEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 */
	void exitSpecialCallEmpty_Mky(MonkeyParser.SpecialCallEmpty_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprInt_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprInt_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprStrMky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprStrMky(MonkeyParser.PExprStrMkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprStrMky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprID_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprID_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprTRUE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprTRUE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprFALSE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprFALSE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprArrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprArrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprArrayFunc_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprArrayFunc_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprFuncDecl_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprFuncDecl_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprHashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprHashLit_Mky(MonkeyParser.PExprHashLit_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprHashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprHashLit_Mky(MonkeyParser.PExprHashLit_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprPrint_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprPrint_Mky(MonkeyParser.PExprPrint_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprPrint_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprPrint_Mky(MonkeyParser.PExprPrint_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pExprIfExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPExprIfExpr_Mky(MonkeyParser.PExprIfExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pExprIfExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPExprIfExpr_Mky(MonkeyParser.PExprIfExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFuncLen_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFuncLen_Mky(MonkeyParser.ArrayFuncLen_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFuncLen_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFuncLen_Mky(MonkeyParser.ArrayFuncLen_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFuncFirst_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFuncFirst_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFuncLast_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFuncLast_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFuncRest_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFuncRest_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFuncPush_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFuncPush_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcLit_Mky}
	 * labeled alternative in {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcLit_Mky}
	 * labeled alternative in {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcParams_Mky}
	 * labeled alternative in {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void enterFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcParams_Mky}
	 * labeled alternative in {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void exitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moreIdentifiers_Mky}
	 * labeled alternative in {@link MonkeyParser#moreIdentifiers}.
	 * @param ctx the parse tree
	 */
	void enterMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moreIdentifiers_Mky}
	 * labeled alternative in {@link MonkeyParser#moreIdentifiers}.
	 * @param ctx the parse tree
	 */
	void exitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void enterHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void exitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moreHashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void enterMoreHashCont_Mky(MonkeyParser.MoreHashCont_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moreHashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void exitMoreHashCont_Mky(MonkeyParser.MoreHashCont_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprList_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExprList_Mky(MonkeyParser.ExprList_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprList_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExprList_Mky(MonkeyParser.ExprList_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprListEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExprListEmpty_Mky(MonkeyParser.ExprListEmpty_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprListEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExprListEmpty_Mky(MonkeyParser.ExprListEmpty_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moreExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMoreExpr_Mky(MonkeyParser.MoreExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moreExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMoreExpr_Mky(MonkeyParser.MoreExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockSt_Mky}
	 * labeled alternative in {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockSt_Mky}
	 * labeled alternative in {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx);
}