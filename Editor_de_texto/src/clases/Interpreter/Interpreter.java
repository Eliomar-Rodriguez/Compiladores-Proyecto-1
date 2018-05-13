package clases.Interpreter;

import clases.checker.FnSpecialElement;
import clases.checker.FuncTableElement;
import clases.checker.IdentifierElement;
import clases.checker.RecursivityObject;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Interpreter extends MonkeyParserBaseVisitor{

    private dataStorage DataStorage;
    private ProgramStack evaluationStack;

    private final int TYPE_ERROR=-1;
    private final int STATEMENT_TYPE= -2;

    private final int INTEGER=1;
    private final int STRING=2;
    private final int BOOLEAN=3;
    private final int ARRAY_LITERAL =4;
    private final int HASH_LITERAL =5;
    private final int FUNCTION =6;


    public Interpreter(){
        this.DataStorage = new dataStorage();
        this.evaluationStack = new ProgramStack();
    }

    /**
     *
     * @param name: name of the function to check if it's a default function
     * @return
     */
    public boolean isReservedWord(String name){

        String[] reservedWords= {"len","first","last","rest","push","puts"};
        int size=reservedWords.length;
        for (int i = 0; i < size ; i++) {
            if (reservedWords[i].equals(name)){
                return true;
            }
        }
        return false;
    }


    public int setSpecialIndex(String ArrayName,String text){
        int res;

        if (ArrayName==null){
            res= -2;
        }
        else if (ArrayName.equals("none")){
            res=-2;
        }
        else{
            try{
                res= Integer.parseInt(text);
            }
            catch (NumberFormatException e){
                res= -2;
            }
        }

        return res;

    }

    public String setSpecialArrayName(String possibleName){
        String res;
        try{
            res= String.valueOf(possibleName);
        }
        catch (Exception e){
            res="none";
        }
        return res;
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


    @Override
    public Object visitProg_Mky(MonkeyParser.Prog_MkyContext ctx) {
        for(MonkeyParser.StatementContext elem: ctx.statement()) {
            visit(elem);

        }
        DataStorage.toString();
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
        ProgramStackElement element = evaluationStack.pop();
        if (element == null)
            System.out.println("ERROR, NO EXISTE LA VARIABLE");
        else{
            this.DataStorage.addData(((MonkeyParser.Id_MkyContext)ctx.identifier()).ID().getText(),element.getValue(),this.DataStorage.getCurrentStorageIndex(),element.getType(),this.DataStorage.getCurrentLevel());
        }
        System.out.println(this.DataStorage.toString());
        //int type = (Integer) visit(ctx.expression());
        //this.DataStorage.toString();
        return -2;

    }

    @Override
    public Object visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx) {


        int type= (Integer) visit(ctx.expression());
        if (type==-1){
            return type;
        }
        return -2;
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

            return-1;
        }
        if (this.checkTypesCompatibility(type1,type2)==false){

            return -1;
        }
        return 0; //success operation
    }

    public int operatorComparisonMethod(List<MonkeyParser.AdditionExpressionContext> ctx, String operator){
        int type1=-1;
        int type2=-1;
        int size= ctx.size();

        type1= (Integer) visit(ctx.get(0));

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

        visit(ctx.get(0));

        for (int i = 1; i < size; i++) {
            visit(ctx.get(i));
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
        visit(ctx.primitiveExpression());


        visit(ctx.elementAccess());

        visit(ctx.specialCall());
        return 0; //CAMBIAR por el que es

    }

    //por el momento va a funcionar solo con funciones normales (no con la del arreglo)
    @Override
    public Object visitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx) {

        visit(ctx.primitiveExpression());

        visit(ctx.callExpression());

        return 0;//CAMBIAR
    }

    @Override
    public Object visitElemExprPExpr_Mky(MonkeyParser.ElemExprPExpr_MkyContext ctx) {
        int type= (Integer) visit(ctx.primitiveExpression());
        return type;
    }

    @Override
    public Object visitElemAccess_Mky(MonkeyParser.ElemAccess_MkyContext ctx) {

        visit(ctx.expression());

        return 0;
    }


    @Override
    public Object visitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx) {
        visit (ctx.expressionList());

        return 0;//CAMBIAR
    }

    @Override
    public Object visitSpecialCallEmpty_Mky(MonkeyParser.SpecialCallEmpty_MkyContext ctx) {
        return 0; // here it always goes to return a valid value.
    }

    @Override
    public Object visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx) {
        return (Integer) visit(ctx.expressionList());
    }

    @Override
    public Object visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx) {
        ProgramStackElement element = new ProgramStackElement(Integer.parseInt(ctx.INTEGER().getText()),1);
        this.evaluationStack.push(element);
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
        return 0;//CAMBIAR
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

        visit(ctx.expression());

        return 0;//CAMBIAR

    }

    @Override
    public Object visitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx) {
        return (Integer) visit(ctx.arrayLiteral());
    }


    //retorna el valor de retorno de la función al evaluarlo
    public int manageDefaultFunctions(MonkeyParser.PExprArrayFunc_MkyContext ctx){

        visit(ctx.expressionList());

        return 0;
    }

    @Override
    public Object visitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx) {
        return this.manageDefaultFunctions(ctx);
    }



    @Override
    public Object visitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx) {
        return (Integer) visit(ctx.functionLiteral());

    }

    @Override
    public Object visitPExprHashLit_Mky(MonkeyParser.PExprHashLit_MkyContext ctx) {
        return visit(ctx.hashLiteral());
    }

    @Override
    public Object visitPExprPrint_Mky(MonkeyParser.PExprPrint_MkyContext ctx) {
        return visit(ctx.printExpression());
    }

    @Override
    public Object visitPExprIfExpr_Mky(MonkeyParser.PExprIfExpr_MkyContext ctx) {
        return visit(ctx.ifExpression());
    }

    //al visitar las fuciones predefinidas retornar tipo neutro, o el elemento que representa a las funciones
    @Override
    public Object visitArrayFuncLen_Mky(MonkeyParser.ArrayFuncLen_MkyContext ctx) {
        return 0;//CAMBIAR
    }

    @Override
    public Object visitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx) {
        return 0;//CAMBIAR
    }

    @Override
    public Object visitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx) {
        return 0;//CAMBIAR
    }

    @Override
    public Object visitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx) {
        return 0;//CAMBIAR
    }

    @Override
    public Object visitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx) {
        return 0;//CAMBIAR
    }

    //array declaration
    @Override
    public Object visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx) {

        visit(ctx.expressionList());

        return 0;//CAMBIAR
    }

    @Override
    public Object visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx) {

        visit(ctx.functionParameters());

        visit(ctx.blockStatement());

        return 0;//CAMBIAR

    }

    @Override
    public Object visitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx) {
        //habría que guardar esta id
        //ctx.ID()

        return visit(ctx.moreIdentifiers());

    }

    @Override
    public Object visitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx) {
        int size = ctx.identifier().size();

        for (int i = 0; i <size; i++){

        }
        return 0;
    }

    @Override
    public Object visitHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx) {
        int type1 = (Integer) visit(ctx.hashContent()), result;
        int type2 = (Integer) visit(ctx.moreHashContent());
        if (type1==-1 || type2==-1){

            return -1;
        }
        return 5;
    }

    @Override
    public Object visitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx) {
        int type1 = (Integer) visit(ctx.expression(0)), type2 = -1;

        //if the key it's a string
        if(type1 ==1 || type1==2){

            type2 = (Integer) visit(ctx.expression(1));


            if (type2 != -1){
                return 0;
            }

        }
        return type1;

    }


    @Override
    public Object visitMoreHashCont_Mky(MonkeyParser.MoreHashCont_MkyContext ctx) {
        int type = 0; // is a valid type by default
        for (MonkeyParser.HashContentContext hashC : ctx.hashContent()){
            type = (Integer) visit(hashC);
        }
        return type;
    }


    @Override
    public Object visitExprList_Mky(MonkeyParser.ExprList_MkyContext ctx) {

        int type1= (Integer) visit(ctx.expression());



        return type1; // si el valor no es menos uno significa que no hubo errores

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
        int temp=0;
        for(int i = 0; i < ctx.expression().size(); i++){


            type = (Integer) visit(ctx.expression(i));


        }

        return type;
    }

    @Override
    public Object visitPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx) {

        int type = (Integer) visit(ctx.expression());
        if(type < 0) {
            /*this.errorsList.add("Error: Print error: "+ctx.expression().getStart().getLine()+
                    " column: "+ ctx.expression().getStart().getCharPositionInLine());
            */
        }
        return -2;
    }

    @Override
    public Object visitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx) {
        visit(ctx.expression());
        //requerido abrir un nivel aquí
        for(int i = 0; i < ctx.blockStatement().size(); i++) {
           /*
            this.identifierTable.OpenScope();
            this.fnSpecialTable.openScope();
            this.functionsTable.openScope(); */
            visit(ctx.blockStatement(i));
            /*
            this.identifierTable.closeScope();
            this.fnSpecialTable.closeScope();
            this.functionsTable.closeScope();
            this.identifierTable.imprimir();
            this.functionsTable.imprimir(); */

        }
        return -2;
    }

    @Override
    public Object visitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx) {

        int type;
        for(MonkeyParser.StatementContext stm : ctx.statement()){
            type= (Integer) visit(stm);
            if (type==-1){
                return type;
            }
        }


        return -2;
    }
}
