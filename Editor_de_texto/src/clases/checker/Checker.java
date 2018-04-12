package clases.checker;

import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import org.antlr.runtime.Token;

import java.util.ArrayList;

public class Checker extends MonkeyParserBaseVisitor {

    private FunctionsTable functionsTable;
    private IdentifiersTable identifiersTable;
    private int globalCounterReturn = 0;
    private int globalCounterFunctions = 0;
    private int globalCounterParams = 0;

    /**
     * tipo neutro se va a representar con un 0
     */

    private ArrayList<String> errorsList;

    public Checker(){
        this.errorsList= new ArrayList<String>();
        this.functionsTable= new FunctionsTable();
    }

    @Override
    public Object visitProg_Mky(MonkeyParser.Prog_MkyContext ctx) {

        for(MonkeyParser.StatementContext elem: ctx.statement())
            visit(elem);

        return null;
    }

    @Override
    public Object visitSt_let_Mky(MonkeyParser.St_let_MkyContext ctx) {
        visit(ctx.letStatement());
        return null;
    }

    @Override
    public Object visitSt_return_Mky(MonkeyParser.St_return_MkyContext ctx) {
        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitSt_expr_Mky(MonkeyParser.St_expr_MkyContext ctx) {
        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLt_asign_Mky(MonkeyParser.Lt_asign_MkyContext ctx) {
        /*
        * Check if there is a function declaration in a variable and add it to FunctionsTable
        * and increment the functions counter
        * */
        int type = (Integer) visit(ctx.expression());

        if (ctx.toStringTree().contains("fn(") | ctx.toStringTree().contains("fn (")){
            FuncTableElement function = functionsTable.insert(ctx.ID().getSymbol(),globalCounterParams,type,ctx);
            if(function != null)
                globalCounterFunctions++;
            else
                System.out.println("Error: The function " + ctx.ID().getText() + "it's already declared.");
        }
        else{
            IdentifierElement ID = IdentifiersTable.insert(ctx.ID().getSymbol(),type,ctx);
            if (ID == null)
                System.out.println("Error: The variable " + ctx.ID().getText() + "it's already declared.");
        }
        return null;
    }

    @Override
    public Object visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx) {
        globalCounterReturn++;
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExprSt_Mky(MonkeyParser.ExprSt_MkyContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExpr_Mky(MonkeyParser.Expr_MkyContext ctx) {
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx) {
        for(MonkeyParser.AdditionExpressionContext elem: ctx.additionExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx) {
        for(MonkeyParser.AdditionExpressionContext elem: ctx.additionExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx) {
        for(MonkeyParser.AdditionExpressionContext elem: ctx.additionExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx) {
        for(MonkeyParser.AdditionExpressionContext elem: ctx.additionExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx) {
        for(MonkeyParser.AdditionExpressionContext elem: ctx.additionExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx) {
        visit(ctx.multiplicationExpression());
        visit(ctx.additionFactor());
        return null;
    }

    @Override
    public Object visitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx) {
        for (MonkeyParser.MultiplicationExpressionContext elem: ctx.multiplicationExpression() ){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx) {
        for (MonkeyParser.MultiplicationExpressionContext elem: ctx.multiplicationExpression() ){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx) {
        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());
        return null;
    }

    @Override
    public Object visitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx) {
        for(MonkeyParser.ElementExpressionContext elem: ctx.elementExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx) {
        for(MonkeyParser.ElementExpressionContext elem: ctx.elementExpression()){
            visit(elem);
        }
        return null;
    }

    @Override
    public Object visitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx) {
        visit(ctx.primitiveExpression());
        visit(ctx.elementAccess());
        return null;
    }

    @Override
    public Object visitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx) {
        visit(ctx.primitiveExpression());
        visit(ctx.callExpression());
        return null;
    }

    @Override
    public Object visitElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx) {
        visit(ctx.primitiveExpression());
        return null;
    }

    @Override
    public Object visitElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx) {
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx) {
        return 0; //tipo neutro

    }

    @Override
    public Object visitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx) {
        return 0;
    }

    @Override
    public Object visitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx) {
        //buscar en la tabla y retornar el tipo
        return 0;
    }

    @Override
    public Object visitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx) {
        return 0;
    }

    @Override
    public Object visitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx) {
        return 0;
    }

    @Override
    public Object visitPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx) {
         visit(ctx.expression());
         return null;
    }

    @Override
    public Object visitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx) {
        visit(ctx.arrayLiteral());
        return null;
    }

    @Override
    public Object visitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx) {
        visit(ctx.arrayFunctions());
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx) {
        visit(ctx.functionLiteral());
        return null;
    }

    @Override
    public Object visitPExprHashLit_Mky(MonkeyParser.PExprHashLit_MkyContext ctx) {
        visit(ctx.hashLiteral());
        return null;
    }

    @Override
    public Object visitPExprPrint_Mky(MonkeyParser.PExprPrint_MkyContext ctx) {
        visit(ctx.printExpression());
        return null;
    }

    @Override
    public Object visitPExprIfExpr_Mky(MonkeyParser.PExprIfExpr_MkyContext ctx) {
        visit(ctx.ifExpression());
        return null;
    }

    @Override
    public Object visitArrayFuncLen_Mky(MonkeyParser.ArrayFuncLen_MkyContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx) {
        return super.visitArrayFuncLast_Mky(ctx);
    }

    @Override
    public Object visitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx) {
        return super.visitArrayFuncRest_Mky(ctx);
    }

    @Override
    public Object visitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx) {
        return super.visitArrayFuncPush_Mky(ctx);
    }

    @Override
    public Object visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx) {
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx) {
        visit(ctx.functionParameters());
        visit(ctx.blockStatement());
        return null;
    }

    @Override
    public Object visitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx) {
        //habría que guardar esta id
        //ctx.ID()
        visit(ctx.moreIdentifiers());
        return null;
    }

    @Override
    public Object visitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx) {
        for (int i = 0; i < ctx.ID().size(); i++){
            // meter IDs en la tabla
            // ctx.ID(i);
        }
        return null;
    }

    @Override
    public Object visitHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx) {
        visit(ctx.hashContent());
        visit(ctx.moreHashContent());
        return null;
    }

    @Override
    public Object visitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx) {
        for(MonkeyParser.ExpressionContext exp : ctx.expression()){
            visit(exp);
        }
        return null;
    }

    @Override
    public Object visitMoreHashCont_Mky(MonkeyParser.MoreHashCont_MkyContext ctx) {
        for (MonkeyParser.HashContentContext hashC : ctx.hashContent()){
            visit(hashC);
        }
        return null;
    }

    @Override
    public Object visitExprList_Mky(MonkeyParser.ExprList_MkyContext ctx) {
        visit(ctx.expression());
        visit(ctx.moreExpressions());
        return null;
    }

    @Override
    public Object visitExprListEmpty_Mky(MonkeyParser.ExprListEmpty_MkyContext ctx) {
        /*
        * poner el retorno
        * */
        return null;
    }

    @Override
    public Object visitMoreExpr_Mky(MonkeyParser.MoreExpr_MkyContext ctx) {
        for(MonkeyParser.ExpressionContext exp : ctx.expression()){
            visit(exp);
        }
        return null;
    }

    @Override
    public Object visitPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx) {
        visit(ctx.expression());
        for(int i = 0; i < ctx.blockStatement().size(); i++)
            visit(ctx.blockStatement(i));
        return null;
    }

    @Override
    public Object visitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx) {
        for(MonkeyParser.StatementContext stm : ctx.statement()){
            visit(stm);
        }
        return null;
    }
}
