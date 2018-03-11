// Generated from C:/Users/anton/Desktop/Compiladores Proyecto 1/Editor_de_texto\MonkeyParser.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MonkeyParser}.
 */
public interface MonkeyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MonkeyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MonkeyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MonkeyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MonkeyParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void enterLetStatement(MonkeyParser.LetStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#letStatement}.
	 * @param ctx the parse tree
	 */
	void exitLetStatement(MonkeyParser.LetStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MonkeyParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MonkeyParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MonkeyParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MonkeyParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MonkeyParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MonkeyParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(MonkeyParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(MonkeyParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpression(MonkeyParser.AdditionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpression(MonkeyParser.AdditionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void enterAdditionFactor(MonkeyParser.AdditionFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#additionFactor}.
	 * @param ctx the parse tree
	 */
	void exitAdditionFactor(MonkeyParser.AdditionFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpression(MonkeyParser.MultiplicationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpression(MonkeyParser.MultiplicationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationFactor(MonkeyParser.MultiplicationFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationFactor(MonkeyParser.MultiplicationFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void enterElementExpression(MonkeyParser.ElementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 */
	void exitElementExpression(MonkeyParser.ElementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void enterElementAccess(MonkeyParser.ElementAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#elementAccess}.
	 * @param ctx the parse tree
	 */
	void exitElementAccess(MonkeyParser.ElementAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpression(MonkeyParser.CallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpression(MonkeyParser.CallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveExpression(MonkeyParser.PrimitiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveExpression(MonkeyParser.PrimitiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void enterArrayFunctions(MonkeyParser.ArrayFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 */
	void exitArrayFunctions(MonkeyParser.ArrayFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(MonkeyParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(MonkeyParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFunctionLiteral(MonkeyParser.FunctionLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#functionLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFunctionLiteral(MonkeyParser.FunctionLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameters(MonkeyParser.FunctionParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#functionParameters}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameters(MonkeyParser.FunctionParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#moreIdentifiers}.
	 * @param ctx the parse tree
	 */
	void enterMoreIdentifiers(MonkeyParser.MoreIdentifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#moreIdentifiers}.
	 * @param ctx the parse tree
	 */
	void exitMoreIdentifiers(MonkeyParser.MoreIdentifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHashLiteral(MonkeyParser.HashLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#hashLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHashLiteral(MonkeyParser.HashLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void enterHashContent(MonkeyParser.HashContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#hashContent}.
	 * @param ctx the parse tree
	 */
	void exitHashContent(MonkeyParser.HashContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void enterMoreHashContent(MonkeyParser.MoreHashContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#moreHashContent}.
	 * @param ctx the parse tree
	 */
	void exitMoreHashContent(MonkeyParser.MoreHashContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MonkeyParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MonkeyParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMoreExpressions(MonkeyParser.MoreExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#moreExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMoreExpressions(MonkeyParser.MoreExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpression(MonkeyParser.PrintExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#printExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpression(MonkeyParser.PrintExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression(MonkeyParser.IfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression(MonkeyParser.IfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MonkeyParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MonkeyParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonkeyParser#commentStatement}.
	 * @param ctx the parse tree
	 */
	void enterCommentStatement(MonkeyParser.CommentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonkeyParser#commentStatement}.
	 * @param ctx the parse tree
	 */
	void exitCommentStatement(MonkeyParser.CommentStatementContext ctx);
}