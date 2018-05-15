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
import java.util.Hashtable;
import java.util.List;

public class Interpreter extends MonkeyParserBaseVisitor{

    private dataStorage DataStorage;
    private ProgramStack evaluationStack;

    private final int TYPE_ERROR=-1;
    private final int STATEMENT_TYPE= -2;

    /**
     * to control all related to expression List rule, how many params to call a function, how many elements
     * does an array contains.
     */
    private int elementsCount;

    private final int INTEGER=1;
    private final int STRING=2;
    private final int BOOLEAN=3;
    private final int ARRAY_LITERAL =4;
    private final int HASH_LITERAL =5;
    private final int FUNCTION =6;


    public Interpreter(){
        this.DataStorage = new dataStorage();
        this.evaluationStack = new ProgramStack();
        this.elementsCount=0;
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
        this.DataStorage.openScope();
        for(MonkeyParser.StatementContext elem: ctx.statement()) {
            visit(elem);
        }
        /*here we don't close scope because var values are necesary for the moment in that we have to execute something
         through the command line
        */
        System.out.println(this.DataStorage.toString());

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

        this.DataStorage.addData(((MonkeyParser.Id_MkyContext)ctx.identifier()).ID().getText(),
                element.getValue(),this.DataStorage.getCurrentIndex(),
                element.getType(),ctx);

        this.DataStorage.printDataStorage();
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


    private ProgramStackElement makeOperation(ProgramStackElement value1, ProgramStackElement value2,String operator){
        ProgramStackElement res = null;
        if (operator.equals("+")){
            if (value1.getType()==this.STRING && value2.getType()==this.STRING){
                return new ProgramStackElement((String.valueOf(value1.getValue()))+String.valueOf(value2.getValue()),this.STRING);
            }
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString()))+
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }

        }
        else if (operator.equals("-")){
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) -
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
        }
        else if (operator.equals("*")){
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) *
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
        }

        //div operator
        else{
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) /
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
        }
        return res;

    }


    public void addSumSub(List<MonkeyParser.MultiplicationExpressionContext> ctx, String operator){

        ProgramStackElement value1;
        ProgramStackElement value2;
        int size= ctx.size();

        visit(ctx.get(0));
        value2= this.evaluationStack.pop();
        value1= this.evaluationStack.pop();
        this.evaluationStack.push(this.makeOperation(value1,value2,operator));

        for (int i = 1; i < size; i++) {
            visit(ctx.get(i));
            value2= this.evaluationStack.pop();
            value1= this.evaluationStack.pop();
            this.evaluationStack.push(this.makeOperation(value1,value2,operator));
        }


    }

    @Override
    public Object visitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            this.addSumSub(ctx.multiplicationExpression(), "+");
        }
        return 0;
    }



    @Override
    public Object visitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            this.addSumSub(ctx.multiplicationExpression(), "-");
        }

        return 0;
    }

    @Override
    public Object visitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx) {

        Object a = visit(ctx.elementExpression());
        Object b = visit(ctx.multiplicationFactor());
        return a;
    }

    public void mutlFactMulDiv(List<MonkeyParser.ElementExpressionContext>ctx,String operator) {

        ProgramStackElement value1;
        ProgramStackElement value2;

        int size= ctx.size();

        visit(ctx.get(0));
        value2=this.evaluationStack.pop();
        value1= this.evaluationStack.pop();
        this.evaluationStack.push(this.makeOperation(value1,value2,operator));

        for (int i = 1; i < size; i++) {
            visit(ctx.get(i));
            value2=this.evaluationStack.pop();
            value1= this.evaluationStack.pop();
            this.evaluationStack.push(this.makeOperation(value1,value2,operator));
        }

    }

    @Override
    public Object visitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx) {
        if (ctx.elementExpression().size()>0){
            this.mutlFactMulDiv(ctx.elementExpression(),"*");
        }
        return 0;
    }

    @Override
    public Object visitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx) {
        if (ctx.elementExpression().size()>0){
            this.mutlFactMulDiv(ctx.elementExpression(),"/");
        }
        return 0;
    }

    @Override
    public Object visitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx) {

        //visit the name of the array or hash literal to get the array value or hash value
        visit(ctx.primitiveExpression());

        ProgramStackElement element= this.evaluationStack.pop();

        if ( (element.getType() != this.ARRAY_LITERAL && element.getType()!= this.HASH_LITERAL )){ //if it's not a neutral type, or array or hash literal

            return this.TYPE_ERROR;
        }

        visit(ctx.elementAccess());

        ProgramStackElement index = this.evaluationStack.pop();

        /**
         * If there are error types in hash literal
         */
        if ( index.getType() ==this.TYPE_ERROR){
            return this.TYPE_ERROR;
        }

        //if primitive expression is an array it can't be indexed through an string index
        if (element.getType() == this.ARRAY_LITERAL && index.getType() ==this.STRING){
            return this.TYPE_ERROR;
        }

        /**
         * IF WE ARE INDEXING A HASH LITERAL, the index must to be a key in string
         */
        if (element.getType()== this.HASH_LITERAL){
            if(index.getType()== this.STRING){
                //indexing hash literal in a simple way

            }
            else{
                return this.TYPE_ERROR;
            }
        }
        else{
            // restart stack to use or not in the special call
            this.evaluationStack.push(element);
            this.evaluationStack.push(index);
            visit(ctx.specialCall());
        }
        return null;

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

        int type = (Integer) visit(ctx.expression());
        if (type == this.STRING){
            
        }
        return 0;
    }


    @Override
    public Object visitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx) {

        int type;
        /**
        if (ctx.expressionList().getChildCount()> 0){
            int temp=globalCounterParams;  //por si está siendo utilizado en algún otro lugar
            this.globalCounterParams=0;

            // backup special array name because value could change in visit expresion list
            String specialArray= this.specialArrayName;
            // backup special array name because value could change in visit expresion list
            int indexBackup= this.specialIndex;
            type= (Integer) visit (ctx.expressionList());

            this.specialArrayName=specialArray;
            this.specialIndex=indexBackup;

            // if there are problems with the function call
            if (type==-1){
                this.errorsList.add("Error: with types or functions parameters.At line:"+ ctx.expressionList().getStart().getLine()+
                        " column: "+ ctx.expressionList().getStart().getCharPositionInLine());
            }
            else{
                type=this.evaluateSpecialFunctionCall(this.specialArrayName,this.specialIndex,this.globalCounterParams ,ctx.expressionList());
            }
            this.globalCounterParams=temp; //reasignación del valor previo que poseía.
        }

        else{
            type= this.evaluateSpecialFunctionCall(this.specialArrayName,this.specialIndex,0,ctx.expressionList());
        }
        return type;
         **/
        /***********************************/
        visit (ctx.expressionList());

        return 0;//CAMBIAR
    }

    @Override
    public Object visitSpecialCallEmpty_Mky(MonkeyParser.SpecialCallEmpty_MkyContext ctx) {

        //here we know that is a normal array indexation without a special call
        ProgramStackElement index= this.evaluationStack.pop();
        ProgramStackElement element= this.evaluationStack.pop();
        ArrayList temp= ( ArrayList ) element.getValue();
        int ind= (Integer) index.getValue();

        /**
         * check if the index is inside the array
         */
        if (ind < temp.size()){
            //obtener tipo de dato del elemento en el array, ahorita solo va a servir para números enteros
            this.evaluationStack.push(new ProgramStackElement(temp.get(ind),this.INTEGER));
        }
        else{
            return this.TYPE_ERROR;
        }

        return 0; // here it always goes to return a valid value.
    }

    @Override
    public Object visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx) {
        return (Integer) visit(ctx.expressionList());
    }

    @Override
    public Object visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx) {

        this.evaluationStack.push( new ProgramStackElement(Integer.parseInt(ctx.getText()),this.INTEGER) );
        return this.INTEGER;
    }

    @Override
    public Object visitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx)
    {
        this.evaluationStack.push(new ProgramStackElement(String.valueOf(ctx.getText().replace("\"","")),this.STRING));
        return this.STRING;
    }

    @Override
    public Object visitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx) {
        /*
        look for the identifier position in the data storage

        PENDING CHECK IF WORKS WITH MANY LEVELs, HOWEVER IT SHOULD WORKS
         */
        int identifierIndex= ((MonkeyParser.LetStatementContext) ctx.identifier().decl).storageIndex;
        dataStorageItem element= this.DataStorage.getData(identifierIndex);
        if (element.getType()!= this.FUNCTION){
            this.evaluationStack.push(new ProgramStackElement(element.getValue(),element.getType()));
        }

        return element.getType();
    }

    @Override
    public Object visitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx) {

        this.evaluationStack.push(new ProgramStackElement(Boolean.parseBoolean(ctx.getText()),this.BOOLEAN));
        return this.BOOLEAN;
    }

    @Override
    public Object visitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx) {
        this.evaluationStack.push(new ProgramStackElement(Boolean.parseBoolean(ctx.getText()),this.BOOLEAN));
        return this.BOOLEAN;
    }

    @Override
    public Object visitPExprCallExpr_Mky(MonkeyParser.PExprCallExpr_MkyContext ctx) {
        return (Integer)visit(ctx.expression());
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

        System.out.println("*****************************"+ " elementos en array"+ " ********************+++");

        ArrayList newArray= new ArrayList();
        this.elementsCount=0;
        /* Visit array elements*/
        visit(ctx.expressionList());

        int i=0;
        while (i < this.elementsCount){
            //always insert at the beginning to keep array original order
            newArray.add(0, this.evaluationStack.pop().getValue());
            i++;
        }

        // insert to the stack
        this.evaluationStack.push(new ProgramStackElement(newArray,this.ARRAY_LITERAL));

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
        Hashtable<Object, Object> hashElement = new Hashtable<Object, Object>();
        Object key = null, value = null;

        int type1 = (Integer) visit(ctx.hashContent()), result;
        int type2 = (Integer) visit(ctx.moreHashContent());

        if (type1 == this.TYPE_ERROR || type2 == this.TYPE_ERROR)
            return this.TYPE_ERROR;

        for (int i = 0; i < (ctx.moreHashContent().getChildCount()/2 + 1); i++) {
            value = evaluationStack.pop().getValue();
            key = evaluationStack.pop().getValue();
            hashElement.put(key,value);
        }

        ProgramStackElement element = new ProgramStackElement(hashElement,this.HASH_LITERAL);
        evaluationStack.push(element);

        return this.HASH_LITERAL;
    }

    @Override
    public Object visitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx) {
        int type1 = (Integer) visit(ctx.expression(0)), type2 = -1;

        //if the key it's an integer or string
        if(type1 == this.INTEGER || type1 == this.STRING){

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

        this.elementsCount++;

       //backup global var value
        int resp=this.elementsCount;
        visit(ctx.expression());

        this.elementsCount=resp;
        //
        if (ctx.moreExpressions().getChildCount()>0){
            visit(ctx.moreExpressions());

        }

        return null;
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


        int temp;
        for(int i = 0; i < ctx.expression().size(); i++){

            // backup global var value
            temp=this.elementsCount;
            visit(ctx.expression(i));
            this.elementsCount=temp;
            this.elementsCount++;
        }

        return null;
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
