// Generated from C:/Users/anton/Desktop/Compiladores.EliomarRodriguez-JosuaCarranza.PrimerProgra/Editor_de_texto\MonkeyParser.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MonkeyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MonkeyParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code prog_Mky}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg_Mky(MonkeyParser.Prog_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code st_let_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSt_let_Mky(MonkeyParser.St_let_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code st_return_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSt_return_Mky(MonkeyParser.St_return_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code st_expr_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSt_expr_Mky(MonkeyParser.St_expr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt_asign_Mky}
	 * labeled alternative in {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt_asign_Mky(MonkeyParser.Lt_asign_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnSt_Mky}
	 * labeled alternative in {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSt_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSt_Mky(MonkeyParser.ExprSt_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_Mky}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_Mky(MonkeyParser.Expr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compMenor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compMayor_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compMenorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compMayorIg_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compIgComp_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addFactSum_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addFactSub_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multFactMul_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multFactDiv_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprElemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprPExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specialCall_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specialCallEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#specialCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialCallEmpty_Mky(MonkeyParser.SpecialCallEmpty_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprInt_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprStrMky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprID_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprTRUE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprFALSE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprCallExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprArrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprArrayFunc_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprFuncDecl_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprHashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprHashLit_Mky(MonkeyParser.PExprHashLit_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprPrint_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprPrint_Mky(MonkeyParser.PExprPrint_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprIfExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprIfExpr_Mky(MonkeyParser.PExprIfExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFuncLen_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFuncLen_Mky(MonkeyParser.ArrayFuncLen_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFuncFirst_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFuncLast_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFuncRest_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFuncPush_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLit_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcLit_Mky}
	 * labeled alternative in {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcParams_Mky}
	 * labeled alternative in {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreIdentifiers_Mky}
	 * labeled alternative in {@link MonkeyParser#moreIdentifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashLit_Mky}
	 * labeled alternative in {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreHashCont_Mky}
	 * labeled alternative in {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreHashCont_Mky(MonkeyParser.MoreHashCont_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprList_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList_Mky(MonkeyParser.ExprList_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListEmpty_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListEmpty_Mky(MonkeyParser.ExprListEmpty_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreExpr_Mky(MonkeyParser.MoreExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr_Mky}
	 * labeled alternative in {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockSt_Mky}
	 * labeled alternative in {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id_Mky}
	 * labeled alternative in {@link MonkeyParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_Mky(MonkeyParser.Id_MkyContext ctx);
}