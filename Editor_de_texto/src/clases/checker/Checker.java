package clases.checker;

import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checker extends MonkeyParserBaseVisitor {

    private FunctionsTable functionsTable;
    private IdentifiersTable identifierTable;
    private int globalCounterFunctions = 0;
    private int globalCounterReturn=0;
    private int globalCounterParams=0;
    private boolean returnInFunction; //para controlar si la sentencia return se encuentra dentro de una función



    /**
     * tipo neutro se va a representar con un 0, -1 representa que no existe tipo para el identificador
     * variable tipo arreglo= 4 , variable tipo diccionario = 5, variable normal -1
     */

    private ArrayList<String> errorsList;

    public Checker(){
        this.errorsList= new ArrayList<String>();
        this.functionsTable= new FunctionsTable();
        this.identifierTable= new IdentifiersTable();
        this.returnInFunction= false;
    }

    public ArrayList<String> getErrorsList() {
        return errorsList;
    }


//Funciones para validar otros aspectos no relacionados con estructura sino con compatibilidad

    public String getTypeName(int code){

        if (code==0){
            return "Neutral";
        }
        else if(code==1){
            return "Integer";

        }

        else if(code==2){
            return "String";
        }
        else if(code==3){
            return "Boolean";
        }
        else if(code==4){
            return "Array Literal";
        }
        else if(code==5){ // code 5
            return "Hash literal";
        }
        else{
            return "Statement or invalid type";
        }
    }

    public boolean checkTypesCompatibility(int type1,int type2){
        if (type1==-1||type2==-1){
            return false;
        }
        else if (type1==type2){
            return true;
        }
        else if ((type1==0 || type2==0)){
            return true;
        }
        return false;
    }

    public boolean isValidOperator(String operator,int type){
        //tipo neutro
        boolean res=false;
        if (type==0){
            res= true;
        }
        //tipo entero
        else if(type==1){
            res=true;
        }
        //permitir suma de strings
        else if (type==2 && operator.equals("+")){
            res=true;
        }

        else {
            //tipo boolean, string
            if ((type == 2 || type == 3 ) && operator.equals("==")) {
                res = true;
            }
        }
        return res;

    }

    public boolean isArrayOrHash(int type){
        if (type==4 || type==5){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Object visitProg_Mky(MonkeyParser.Prog_MkyContext ctx) {
        this.functionsTable.openScope();
        this.identifierTable.OpenScope();
        for(MonkeyParser.StatementContext elem: ctx.statement()) {
            visit(elem);
            this.identifierTable.imprimir();
            this.functionsTable.imprimir();
        }
        this.identifierTable.closeScope();
        this.functionsTable.closeScope();
        return null;
    }

    @Override
    public Object visitSt_let_Mky(MonkeyParser.St_let_MkyContext ctx) {
        return visit(ctx.letStatement());

    }

    @Override
    public Object visitSt_return_Mky(MonkeyParser.St_return_MkyContext ctx) {
        return visit(ctx.returnStatement());

    }

    @Override
    public Object visitSt_expr_Mky(MonkeyParser.St_expr_MkyContext ctx) {
        return visit(ctx.expressionStatement());

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
            if(function != null) {
                globalCounterFunctions++;
            }
            else {
                this.errorsList.add("Error: The function " + ctx.ID().getText() + " it's already declared. At line: " +
                        ctx.getStart().getLine() + " column: " + ctx.getStart().getCharPositionInLine());
                return -1;
            }
            this.functionsTable.imprimir();
        }
        else{
            //error in the expresion
            if (type==-1){
                return -1;
            }
            if (this.isArrayOrHash(type)!=true){ //check if type represent an array
                type=-1;
            }
                                                                                //neutro type
            IdentifierElement ID = this.identifierTable.insertar(ctx.ID().getSymbol(),0,type,ctx);
            if (ID == null) {
                this.errorsList.add("Error: The variable " + ctx.ID().getText() + " it's already declared. At line: " +
                        ctx.getStart().getLine() + " column :" + ctx.getStart().getCharPositionInLine());
            }
        }

        return type;
    }

    @Override
    public Object visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx) {

        if (this.returnInFunction==false){
            this.errorsList.add("Error a return statement has to be inside a function. At line: " +
                    ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
            return -1;
        }

        this.globalCounterReturn++;

        int type= (Integer) visit(ctx.expression());

        return type;
    }

    @Override
    public Object visitExprSt_Mky(MonkeyParser.ExprSt_MkyContext ctx) {
        return visit(ctx.expression());

    }

    @Override
    public Object visitExpr_Mky(MonkeyParser.Expr_MkyContext ctx) {
        int type1=-1;
        int type2=-1;
        int resType=-1;
        type1= (Integer) visit(ctx.additionExpression());
        type2=0;
        if (ctx.comparison().getChildCount()>0){
            type2= (Integer) visit(ctx.comparison());
        }


        if (this.checkTypesCompatibility(type1,type2)!=true){
            resType=-1;
        }
        else{
            resType= type1;
        }
        return resType;
    }

    public int checkRestrictions(int type1, int type2, String operator,ParserRuleContext ctx){
        if (this.isValidOperator(operator,type2)!=true){
            this.errorsList.add("Error: type: "+ this.getTypeName(type1)+" is not compatible with the operator "+operator+".At line: " +
                    ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
            return-1;
        }
        if (this.checkTypesCompatibility(type1,type2)==false){
            this.errorsList.add("Error: Incompatible types. At line:"+ ctx.getStart().getLine()+
                    " column: "+ ctx.getStart().getCharPositionInLine());
            return -1;
        }
        return 0; //success operation
    }

    public int operatorComparisonMethod(List<MonkeyParser.AdditionExpressionContext> ctx, String operator){
        int type1=-1;
        int type2=-1;
        int size= ctx.size();

        type1= (Integer) visit(ctx.get(0));
        if (this.isValidOperator(operator,type1)==false){
            this.errorsList.add("Error: type "+ this.getTypeName(type1)+" is not compatible with the operator "+operator+".At line: " +
                    ctx.get(0).getStart().getLine()+" column: "+ctx.get(0).getStart().getCharPositionInLine());
            return -1;
        }
        for (int i = 1; i < size; i++) {
            type2= (Integer) visit(ctx.get(i));

            if (this.checkRestrictions(type1,type2,operator,ctx.get(i))==0){
                type1=type2;
            }
            else{
                return -1;
            }

        }

        return type1;
    }

    @Override
    public Object visitCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx) {

        return this.operatorComparisonMethod(ctx.additionExpression(),"<");
    }

    @Override
    public Object visitCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx) {

        return this.operatorComparisonMethod(ctx.additionExpression(),">");
        }

    @Override
    public Object visitCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx) {

        return this.operatorComparisonMethod(ctx.additionExpression(),"<=");

    }

    @Override
    public Object visitCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx) {
        return this.operatorComparisonMethod(ctx.additionExpression(),">=");
    }

    @Override
    public Object visitCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx) {
        return this.operatorComparisonMethod(ctx.additionExpression(),"==");
    }

    @Override
    public Object visitAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx) {
        int resType=0;
        int type1= (Integer) visit(ctx.multiplicationExpression());
        int type2= (Integer) visit(ctx.additionFactor());
        if (this.checkTypesCompatibility(type1,type2)!=true){
            this.errorsList.add("Error: Incompatible types. At line:"+ ctx.getStart().getLine()+
                    " column: "+ ctx.getStart().getCharPositionInLine());
            resType=-1;
        }
        else{
            resType= type1;
        }
        return resType;
    }

    public int addSumSub(List<MonkeyParser.MultiplicationExpressionContext> ctx, String operator){
        int type1=-1;
        int type2=-1;
        int size= ctx.size();

        type1= (Integer) visit(ctx.get(0));
        if (this.isValidOperator(operator,type1)==false){
            this.errorsList.add("Error: type "+ this.getTypeName(type1)+" is not compatible with the operator "+operator+".At line: " +
                    ctx.get(0).getStart().getLine()+" column: "+ctx.get(0).getStart().getCharPositionInLine());
            return -1;
        }
        for (int i = 1; i < size; i++) {
            type2= (Integer) visit(ctx.get(i));

            if (this.checkRestrictions(type1,type2,operator,ctx.get(i))==0){
                type1=type2;
            }
            else{
                return -1;
            }

        }

        return type1;

    }

    @Override
    public Object visitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            return this.addSumSub(ctx.multiplicationExpression(), "+");
        }
        else{
            return 0; //tipo neutro
        }

    }



    @Override
    public Object visitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            return this.addSumSub(ctx.multiplicationExpression(), "-");
        }
        else{
            return 0; //tipo neutro
        }
    }

    @Override
    public Object visitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx) {
        int resType=0;
        int type1 = (Integer) visit(ctx.elementExpression());
        int type2= (Integer) visit(ctx.multiplicationFactor());

        if (this.checkTypesCompatibility(type1,type2)!=true){
            this.errorsList.add("Error: Incompatible types. At line:"+ ctx.getStart().getLine()+
                    " column: "+ ctx.getStart().getCharPositionInLine());
            resType=-1;
        }
        else{
            resType= type1;
        }

        return resType;
    }

    public int mutlFactMulDiv(List<MonkeyParser.ElementExpressionContext>ctx,String operator) {

        int type1=-1;
        int type2=-1;
        int size= ctx.size();
        type1= (Integer) visit(ctx.get(0));
        if (this.isValidOperator(operator,type1)==false){
            this.errorsList.add("Error: type "+ this.getTypeName(type1)+" is not compatible with the operator "+operator+".At line: " +
                    ctx.get(0).getStart().getLine()+" column: "+ctx.get(0).getStart().getCharPositionInLine());
            return -1;
        }
        for (int i = 1; i < size; i++) {
            type2= (Integer) visit(ctx.get(i));
            if (this.checkRestrictions(type1,type2,operator,ctx.get(i))==0){
                type1=type2;
            }
            else{
                return -1;
            }
        }

        return type1;
    }

    @Override
    public Object visitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx) {
            if (ctx.elementExpression().size()>0){
                return this.mutlFactMulDiv(ctx.elementExpression(),"*");
            }
            else{
                return 0;
            }

    }

    @Override
    public Object visitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx) {
        if (ctx.elementExpression().size()>0){
            return this.mutlFactMulDiv(ctx.elementExpression(),"/");
        }
        else{
            return 0;
        }

    }

    @Override
    public Object visitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx) {
        int type1= (Integer) visit(ctx.primitiveExpression());
        int type2= (Integer) visit(ctx.elementAccess());

        if (type1 !=0){
            this.errorsList.add("Error: Can't index a value that is not an array or hash literal. At line:"+ ctx.getStart().getLine()+
                    " column: "+ ctx.getStart().getCharPositionInLine());
            return -1;
        }
        if (type2==-1){
            return -1;
        }
        return 0; //tipo válido
    }

    //por el momento va a funcionar solo con funciones normales (no con la del arreglo)
    @Override
    public Object visitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx) {
        this.globalCounterParams=0;
        int resType=0;
        int type1= (Integer) visit(ctx.primitiveExpression());

        FuncTableElement elem = this.functionsTable.buscar(ctx.primitiveExpression().getText());
        if (elem == null) {
            this.errorsList.add("Error: "+ ctx.primitiveExpression().getText()+" is not a function. At line:"+ ctx.primitiveExpression().getStart().getLine()+
                    " column: "+ ctx.primitiveExpression().getStart().getCharPositionInLine());
            return -1;
        }
        int type2= (Integer) visit(ctx.callExpression());

        if (elem.getReturnType()==-1){
            this.errorsList.add("Error: Function "+ ctx.primitiveExpression().getText() +
                    " doesn't return nothing, it could generate a type error .At line:"+
                    ctx.primitiveExpression().getStart().getLine()+
                    " column: "+ ctx.primitiveExpression().getStart().getCharPositionInLine());
        }

        if (type2==-1){
            this.errorsList.add("Error: with types or functions parameters.At line:"+ ctx.callExpression().getStart().getLine()+
                    " column: "+ ctx.callExpression().getStart().getCharPositionInLine());
            resType= -1;
        }
        else if (elem.getParamsNumber()!=this.globalCounterParams){
            this.errorsList.add("Error: The numbers of sent params  is not the same number that the function " +
                    ctx.primitiveExpression().getText()+
                    " have to receive. At line: "
                    + ctx.callExpression().getStart().getLine()+
                    " column: "+ ctx.callExpression().getStart().getCharPositionInLine());
            resType= -1;
        }
        else{
            resType=elem.getReturnType();
        }
        this.globalCounterParams=0;
        return resType;
    }

    @Override
    public Object visitElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx) {
        int type= (Integer) visit(ctx.primitiveExpression());
        return type;
    }

    @Override
    public Object visitElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx) {
        int type= (Integer) visit(ctx.expression());
        if (type!=0 && type!=1){  //solo se permiten neutros o enteros para indexar arreglos
            this.errorsList.add("Error: Array or Hash literal must be indexed through an Int or neutral value " +
                    ".At line: "+ctx.expression().getStart().getLine()+" column: "+ctx.expression().getStart().getLine());

            type=-1; //error
        }
        return type;
    }

    @Override
    public Object visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx) {
        return (Integer) visit(ctx.expressionList());
    }

    @Override
    public Object visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx) {
        return 1; //tipo entero

    }

    @Override
    public Object visitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx)
    {
        return 2; //tipo string
    }

    @Override
    public Object visitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx) {
        //buscar en la tabla y retornar el tipo
        int resType= -1;
        IdentifierElement elem = this.identifierTable.buscar(ctx.ID().getText());
        FuncTableElement elem2= this.functionsTable.buscar(ctx.ID().getText());
        if (elem==null && elem2==null){
            this.errorsList.add("Error: Variable or function "+ ctx.ID().getText()+ " have not been declared. At line: "+
            ctx.ID().getSymbol().getLine()+" Column: "+ ctx.ID().getSymbol().getLine());
            return -1;
        }

        if (elem!= null) {
            if (elem.getArrayOrHashLiteral()!=-1){
                resType=elem.getArrayOrHashLiteral();
            }
            resType= elem.getType();
        }
        if (elem2!=null){
            resType= elem2.getReturnType();
        }

        return resType;
    }

    @Override
    public Object visitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx) {
        return 3; //tipo boolean
    }

    @Override
    public Object visitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx) {
        return 3;
    }

    @Override
    public Object visitPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx) {
         return visit(ctx.expression());

    }

    @Override
    public Object visitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx) {
        return (Integer) visit(ctx.arrayLiteral());

    }

    @Override
    public Object visitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx) {
        visit(ctx.arrayFunctions());
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx) {
        return (Integer) visit(ctx.functionLiteral());

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
        return 0;
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


    //array declaration
    @Override
    public Object visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx) {
        int res= (Integer)visit(ctx.expressionList());
        if (res==0){
            return 4; //type array
        }
        return res;

    }

    @Override
    public Object visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx) {
        int res=-1;
        this.identifierTable.OpenScope();
        this.functionsTable.openScope();
        this.globalCounterReturn=0; // reestablecer el contador
        visit(ctx.functionParameters());
        this.returnInFunction=true; //se permite dentro del blockstatement la sentencia return
        visit(ctx.blockStatement());
        this.returnInFunction=false;  //no se permite dentro de la sentencia el estatuto return

        this.identifierTable.closeScope();
        this.functionsTable.closeScope();
        if (this.globalCounterReturn>0){

            return 0; //tipo neutro para la función
        }
        else{
            res=-1; //función sin tipo
        }
        return res;
    }

    @Override
    public Object visitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx) {
        //habría que guardar esta id
        //ctx.ID()
        this.globalCounterParams=0;
        this.identifierTable.insertar(ctx.ID().getSymbol(),0,-1,ctx);
        this.globalCounterParams++;
        visit(ctx.moreIdentifiers());
        return null;
    }

    @Override
    public Object visitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx) {
        int size=ctx.ID().size();
        IdentifierElement elem;
        for (int i = 0; i <size; i++){
            elem= this.identifierTable.insertar(ctx.ID().get(i).getSymbol(),0,-1,ctx);
            if (elem!=null){
                this.globalCounterParams++;
            }
            else{
                this.errorsList.add("Error: Two or more params have the same name. At line: "+ctx.ID(i).getSymbol().getLine()+
                        " column: "+ ctx.ID(i).getSymbol().getCharPositionInLine());
                return null;
            }
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
        int type1= (Integer) visit(ctx.expression());
        this.globalCounterParams++;
        int type2=0;
        if (ctx.moreExpressions().getChildCount()>0){
            type2=(Integer) visit(ctx.moreExpressions());
        }
        if (type1==-1 || type2==-1){
            return -1;
        }

        return 0; //significa que no hubo errores
    }

    @Override
    public Object visitExprListEmpty_Mky(MonkeyParser.ExprListEmpty_MkyContext ctx) {
        /*
        * poner el retorno, significa valor válido
        * */
        return 0;
    }

    @Override
    public Object visitMoreExpr_Mky(MonkeyParser.MoreExpr_MkyContext ctx) {
        int type=0; //tipo valido
        for(MonkeyParser.ExpressionContext exp : ctx.expression()){
            type= (Integer) visit(exp);
            this.globalCounterParams++;
            if (type==-1){
                return -1;
            }
        }
        return type;
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
