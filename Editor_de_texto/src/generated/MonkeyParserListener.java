// Generated from C:/Users/anton/Desktop/Compiladores Proyecto 1/Editor_de_texto\MonkeyParser.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MonkeyParser}.
 */
public interface MonkeyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code program_Mky}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram_Mky(MonkeyParser.Program_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code program_Mky}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram_Mky(MonkeyParser.Program_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_LET_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_LET_Mky(MonkeyParser.Statement_LET_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_LET_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_LET_Mky(MonkeyParser.Statement_LET_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_RETURN_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_RETURN_Mky(MonkeyParser.Statement_RETURN_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_RETURN_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_RETURN_Mky(MonkeyParser.Statement_RETURN_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_EXPRESSIONSTATEMENT_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_EXPRESSIONSTATEMENT_Mky(MonkeyParser.Statement_EXPRESSIONSTATEMENT_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_EXPRESSIONSTATEMENT_Mky}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_EXPRESSIONSTATEMENT_Mky(MonkeyParser.Statement_EXPRESSIONSTATEMENT_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void enterLetStatement_Mky(MonkeyParser.LetStatement_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void exitLetStatement_Mky(MonkeyParser.LetStatement_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnSatetement_Mky}
	 * labeled alternative in {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnSatetement_Mky(MonkeyParser.ReturnSatetement_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnSatetement_Mky}
	 * labeled alternative in {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnSatetement_Mky(MonkeyParser.ReturnSatetement_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement_Mky(MonkeyParser.ExpressionStatement_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement_Mky(MonkeyParser.ExpressionStatement_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expression_Mky}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression_Mky(MonkeyParser.Expression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression_Mky}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression_Mky(MonkeyParser.Expression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison_MENOR_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison_MENOR_Mky(MonkeyParser.Comparison_MENOR_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison_MENOR_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison_MENOR_Mky(MonkeyParser.Comparison_MENOR_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison_MAYOR_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison_MAYOR_Mky(MonkeyParser.Comparison_MAYOR_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison_MAYOR_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison_MAYOR_Mky(MonkeyParser.Comparison_MAYOR_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison_MENORIGUAL_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison_MENORIGUAL_Mky(MonkeyParser.Comparison_MENORIGUAL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison_MENORIGUAL_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison_MENORIGUAL_Mky(MonkeyParser.Comparison_MENORIGUAL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison_MAYORIGUAL_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison_MAYORIGUAL_Mky(MonkeyParser.Comparison_MAYORIGUAL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison_MAYORIGUAL_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison_MAYORIGUAL_Mky(MonkeyParser.Comparison_MAYORIGUAL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison_IGUALCOMP_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison_IGUALCOMP_Mky(MonkeyParser.Comparison_IGUALCOMP_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison_IGUALCOMP_Mky}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison_IGUALCOMP_Mky(MonkeyParser.Comparison_IGUALCOMP_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additionExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpression_Mky(MonkeyParser.AdditionExpression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additionExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpression_Mky(MonkeyParser.AdditionExpression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additionFactor_SUM_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void enterAdditionFactor_SUM_Mky(MonkeyParser.AdditionFactor_SUM_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additionFactor_SUM_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void exitAdditionFactor_SUM_Mky(MonkeyParser.AdditionFactor_SUM_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additionFactor_SUB_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void enterAdditionFactor_SUB_Mky(MonkeyParser.AdditionFactor_SUB_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additionFactor_SUB_Mky}
	 * labeled alternative in {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void exitAdditionFactor_SUB_Mky(MonkeyParser.AdditionFactor_SUB_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpression_Mky(MonkeyParser.MultiplicationExpression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpression_Mky(MonkeyParser.MultiplicationExpression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationFactor_MUL_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationFactor_MUL_Mky(MonkeyParser.MultiplicationFactor_MUL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationFactor_MUL_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationFactor_MUL_Mky(MonkeyParser.MultiplicationFactor_MUL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationFactor_DIV_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationFactor_DIV_Mky(MonkeyParser.MultiplicationFactor_DIV_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationFactor_DIV_Mky}
	 * labeled alternative in {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationFactor_DIV_Mky(MonkeyParser.MultiplicationFactor_DIV_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementExpression_PRIMEXP_ELEMENTACESS_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElementExpression_PRIMEXP_ELEMENTACESS_Mky(MonkeyParser.ElementExpression_PRIMEXP_ELEMENTACESS_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementExpression_PRIMEXP_ELEMENTACESS_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElementExpression_PRIMEXP_ELEMENTACESS_Mky(MonkeyParser.ElementExpression_PRIMEXP_ELEMENTACESS_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementExpression_PRIMEXP_CALLEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElementExpression_PRIMEXP_CALLEXPRESSION_Mky(MonkeyParser.ElementExpression_PRIMEXP_CALLEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementExpression_PRIMEXP_CALLEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElementExpression_PRIMEXP_CALLEXPRESSION_Mky(MonkeyParser.ElementExpression_PRIMEXP_CALLEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementExpression_PRIMEXP_PRIMITIVEEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElementExpression_PRIMEXP_PRIMITIVEEXPRESSION_Mky(MonkeyParser.ElementExpression_PRIMEXP_PRIMITIVEEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementExpression_PRIMEXP_PRIMITIVEEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElementExpression_PRIMEXP_PRIMITIVEEXPRESSION_Mky(MonkeyParser.ElementExpression_PRIMEXP_PRIMITIVEEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void enterElementAccess_Mky(MonkeyParser.ElementAccess_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementAccess_Mky}
	 * labeled alternative in {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void exitElementAccess_Mky(MonkeyParser.ElementAccess_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpression_Mky(MonkeyParser.CallExpression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpression_Mky(MonkeyParser.CallExpression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_INTEGER_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_INTEGER_Mky(MonkeyParser.PrimitiveExpression_INTEGER_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_INTEGER_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_INTEGER_Mky(MonkeyParser.PrimitiveExpression_INTEGER_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_STRING_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_STRING_Mky(MonkeyParser.PrimitiveExpression_STRING_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_STRING_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_STRING_Mky(MonkeyParser.PrimitiveExpression_STRING_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_ID_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_ID_Mky(MonkeyParser.PrimitiveExpression_ID_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_ID_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_ID_Mky(MonkeyParser.PrimitiveExpression_ID_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_TRUE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_TRUE_Mky(MonkeyParser.PrimitiveExpression_TRUE_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_TRUE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_TRUE_Mky(MonkeyParser.PrimitiveExpression_TRUE_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_FALSE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_FALSE_Mky(MonkeyParser.PrimitiveExpression_FALSE_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_FALSE_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_FALSE_Mky(MonkeyParser.PrimitiveExpression_FALSE_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_CALLEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_CALLEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_CALLEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_CALLEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_CALLEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_CALLEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_ARRAYLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_ARRAYLITERAL_Mky(MonkeyParser.PrimitiveExpression_ARRAYLITERAL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_ARRAYLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_ARRAYLITERAL_Mky(MonkeyParser.PrimitiveExpression_ARRAYLITERAL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_ARRAYOPERATION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_ARRAYOPERATION_Mky(MonkeyParser.PrimitiveExpression_ARRAYOPERATION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_ARRAYOPERATION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_ARRAYOPERATION_Mky(MonkeyParser.PrimitiveExpression_ARRAYOPERATION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_FUNCTIONLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_FUNCTIONLITERAL_Mky(MonkeyParser.PrimitiveExpression_FUNCTIONLITERAL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_FUNCTIONLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_FUNCTIONLITERAL_Mky(MonkeyParser.PrimitiveExpression_FUNCTIONLITERAL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_HASHLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_HASHLITERAL_Mky(MonkeyParser.PrimitiveExpression_HASHLITERAL_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_HASHLITERAL_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_HASHLITERAL_Mky(MonkeyParser.PrimitiveExpression_HASHLITERAL_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_PRINTEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_PRINTEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_PRINTEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_PRINTEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_PRINTEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_PRINTEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveExpression_IFEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression_IFEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_IFEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveExpression_IFEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression_IFEXPRESSION_Mky(MonkeyParser.PrimitiveExpression_IFEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFunctions_LEN_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions_LEN_Mky(MonkeyParser.ArrayFunctions_LEN_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFunctions_LEN_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions_LEN_Mky(MonkeyParser.ArrayFunctions_LEN_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFunctions_FIRST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions_FIRST_Mky(MonkeyParser.ArrayFunctions_FIRST_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFunctions_FIRST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions_FIRST_Mky(MonkeyParser.ArrayFunctions_FIRST_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFunctions_LAST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions_LAST_Mky(MonkeyParser.ArrayFunctions_LAST_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFunctions_LAST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions_LAST_Mky(MonkeyParser.ArrayFunctions_LAST_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFunctions_REST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions_REST_Mky(MonkeyParser.ArrayFunctions_REST_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFunctions_REST_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions_REST_Mky(MonkeyParser.ArrayFunctions_REST_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayFunctions_PUSH_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions_PUSH_Mky(MonkeyParser.ArrayFunctions_PUSH_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayFunctions_PUSH_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions_PUSH_Mky(MonkeyParser.ArrayFunctions_PUSH_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral_Mky(MonkeyParser.ArrayLiteral_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral_Mky(MonkeyParser.ArrayLiteral_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFunctionLiteral_Mky(MonkeyParser.FunctionLiteral_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFunctionLiteral_Mky(MonkeyParser.FunctionLiteral_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionParameters_Mky}
	 * labeled alternative in {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameters_Mky(MonkeyParser.FunctionParameters_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionParameters_Mky}
	 * labeled alternative in {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameters_Mky(MonkeyParser.FunctionParameters_MkyContext ctx);
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
	 * Enter a parse tree produced by the {@code hashLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHashLiteral_Mky(MonkeyParser.HashLiteral_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashLiteral_Mky}
	 * labeled alternative in {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHashLiteral_Mky(MonkeyParser.HashLiteral_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hashContent_Mky}
	 * labeled alternative in {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void enterHashContent_Mky(MonkeyParser.HashContent_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashContent_Mky}
	 * labeled alternative in {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void exitHashContent_Mky(MonkeyParser.HashContent_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moreHashContent_Mky}
	 * labeled alternative in {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void enterMoreHashContent_Mky(MonkeyParser.MoreHashContent_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moreHashContent_Mky}
	 * labeled alternative in {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void exitMoreHashContent_Mky(MonkeyParser.MoreHashContent_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionList_MULTIPLEEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList_MULTIPLEEXPRESSION_Mky(MonkeyParser.ExpressionList_MULTIPLEEXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionList_MULTIPLEEXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList_MULTIPLEEXPRESSION_Mky(MonkeyParser.ExpressionList_MULTIPLEEXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionList_EXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList_EXPRESSION_Mky(MonkeyParser.ExpressionList_EXPRESSION_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionList_EXPRESSION_Mky}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList_EXPRESSION_Mky(MonkeyParser.ExpressionList_EXPRESSION_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moreExpressions_Mky}
	 * labeled alternative in {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMoreExpressions_Mky(MonkeyParser.MoreExpressions_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moreExpressions_Mky}
	 * labeled alternative in {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMoreExpressions_Mky(MonkeyParser.MoreExpressions_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpression_Mky(MonkeyParser.PrintExpression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpression_Mky(MonkeyParser.PrintExpression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression_Mky(MonkeyParser.IfExpression_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpression_Mky}
	 * labeled alternative in {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression_Mky(MonkeyParser.IfExpression_MkyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement_Mky(MonkeyParser.BlockStatement_MkyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStatement_Mky}
	 * labeled alternative in {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement_Mky(MonkeyParser.BlockStatement_MkyContext ctx);
}