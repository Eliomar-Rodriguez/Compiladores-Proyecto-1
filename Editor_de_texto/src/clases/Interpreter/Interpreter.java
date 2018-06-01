package clases.Interpreter;

import clases.TextEditorController;
import clases.checker.FnSpecialElement;
import clases.checker.FuncTableElement;
import clases.checker.IdentifierElement;
import clases.checker.RecursivityObject;
//import com.sun.org.apache.xpath.internal.operations.String;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import javax.jws.Oneway;
import javax.swing.*;
import javax.xml.crypto.Data;

import java.io.FileReader;
import java.awt.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Interpreter extends MonkeyParserBaseVisitor{

    private dataStorage DataStorage = null;
    private ProgramStack evaluationStack;
    private Frame temporal;
    private final int TYPE_ERROR=-1;
    private final int STATEMENT_TYPE= -2;

    private boolean haveToReturn;

    private MonkeyParser.IdentifierContext ArrayIdentifierTemp=null;


    private final JTextArea console;
    private int flagConsole = 0; // if is 0 then find by storageIndex else, is 1 find by for


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
    //private Frame currentFrame;
    private FrameList programFrames;


    public Interpreter(JTextArea c){
        this.programFrames= new FrameList();
        //set stack and data storage of the current frame
        this.DataStorage = this.programFrames.getCurrentProgramFrame().getStorage();
        this.evaluationStack = this.programFrames.getCurrentProgramFrame().getStack();
        this.elementsCount=0;
        this.haveToReturn=false;
        this.console = c;


    }

    public void setFlagConsole(int flagConsole) {
        this.flagConsole = flagConsole;
    }

    /**
     *
     * @param value: value, important to know the value type
     * @return
     */
    public int getTypeOfElement(Object value){

        if (value instanceof Integer){
            return this.INTEGER;
        }
        else if (value instanceof String){
            return this.STRING;
        }
        else if (value instanceof Boolean){
            return this.BOOLEAN;
        }
        else if (value instanceof ArrayList){
            return this.ARRAY_LITERAL;
        }
        else if (value instanceof Hashtable){
            return this.HASH_LITERAL;
        }
        else{
            return this.FUNCTION;
        }
    }


    public void printInConsole(String msj){
        this.console.append("\n" + msj + "\n");

    }

    /**
     *
     * @param value1  primer operando de la comparación;
     * @param value2  segundo operando de la comparación;
     * @param operator operador que aplica sobre los dos operandos
     */
    public void evaluateComparison(ProgramStackElement value1, ProgramStackElement value2, String operator){
        if (operator.equals("<")){
            this.evaluationStack.push(new ProgramStackElement((Integer)  value1.getValue() < (Integer) value2.getValue(),this.BOOLEAN));
        }
        else if (operator.equals(">")){
            this.evaluationStack.push(new ProgramStackElement((Integer)  value1.getValue() > (Integer) value2.getValue(),this.BOOLEAN));
        }
        else if (operator.equals("<=")){
            this.evaluationStack.push(new ProgramStackElement((Integer)  value1.getValue() <= (Integer) value2.getValue(),this.BOOLEAN));
        }
        else if (operator.equals(">=")){
            this.evaluationStack.push(new ProgramStackElement((Integer)  value1.getValue() >= (Integer) value2.getValue(),this.BOOLEAN));
        }
        else{
            //not matter which or the two values you use, because they have the same type
            //integer type
            if (value1.getValue() instanceof Integer){
                this.evaluationStack.push(new ProgramStackElement((Integer)  value1.getValue() == (Integer) value2.getValue(),this.BOOLEAN));
            }
            //boolean type
            else  if (value1.getValue() instanceof Boolean){
                this.evaluationStack.push(new ProgramStackElement((Boolean)  value1.getValue() == (Boolean) value2.getValue(),this.BOOLEAN));
            }
            //string type
            else{
                this.evaluationStack.push(new ProgramStackElement(String.valueOf(value1.getValue()).equals(value2.getValue()),this.BOOLEAN));
            }
        }
    }




    @Override
    public Object visitProg_Mky(MonkeyParser.Prog_MkyContext ctx) {
        if(this.DataStorage.getProgramData().size() == 0)
            this.DataStorage.openScope();

        for(MonkeyParser.StatementContext elem: ctx.statement()) {
            visit(elem);
        }

        //here we can verify is there something in the stack to write in console.
        if (this.flagConsole ==1 && this.evaluationStack.size() > 0){
            this.printInConsole(this.evaluationStack.pop().getValue().toString());

        }

        /*here we don't close scope because var values are necesary for the moment in that we have to execute something
         through the command line
        */
        System.out.println(this.DataStorage.toString());
        System.out.println(this.evaluationStack.toString());

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


        visit(ctx.expression());

        ProgramStackElement element = evaluationStack.pop();

        //if the identifier have a declaration linked we update directly the data
        if (ctx.identifier().decl !=null ){
            MonkeyParser.LetStatementContext letCtx= (MonkeyParser.LetStatementContext) ctx.identifier().decl;
            dataStorageItem temp= this.DataStorage.getData(letCtx.storageIndex);
            temp.setValue(element.getValue());
            temp.setType(element.getType());

        }

        //aqui cambiar para que desde consola no se le cambie el valor a la variable
        else {
            this.DataStorage.addData(((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getText(),
                    element.getValue(), this.DataStorage.getCurrentIndex(),
                    element.getType(), ctx);

        }
        this.DataStorage.printDataStorage();
        //int type = (Integer) visit(ctx.expression());
        //this.DataStorage.toString();

        return null;
    }

    @Override
    public Object visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx) {
        visit(ctx.expression());
        this.haveToReturn=true;  //set return flag to true value
        //then remove the frame after get the value in the stack
       // this.programFrames.deleteCurrentFrame();
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

        if (ctx.comparison().getChildCount()>0)
            visit(ctx.comparison());
        return null;

    }

    public  void checkRestrictionsForComparison(ProgramStackElement value1, ProgramStackElement value2, String operator,Token t){


        if (operator.equals("==")){
            if ((value1.getType()!= this.ARRAY_LITERAL && value1.getType()!= this.HASH_LITERAL && value1.getType() != this.FUNCTION)
                    && (value2.getType()!= this.ARRAY_LITERAL && value2.getType()!= this.HASH_LITERAL && value2.getType() != this.FUNCTION))
                {
                    //types error for the comparisons,
                    if (value1.getType()!=value2.getType()){
                        throw new InterpreterException("Operator "+ operator + " require that the comparison's elements are of the same type. "+
                                "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
                    }


                }
            else{
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int, String or Boolean type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());

            }
        }
        else{
            // FOR <,>,<=,>= Operators, the two values have to be Integer
            if (value1.getType() != this.INTEGER || value2.getType()!=this.INTEGER){
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
            }

        }
           //success operation
    }


    public void operatorComparisonMethod(List<MonkeyParser.AdditionExpressionContext> ctx, String operator,Token t){

        ProgramStackElement value1;
        ProgramStackElement value2;

        int size= ctx.size();

        for (int i = 0 ; i < size; i++) {

            visit(ctx.get(i));
            //  get values to the comparison
            value2= this.evaluationStack.pop();
            value1= this.evaluationStack.pop();
            this.checkRestrictionsForComparison(value1,value2,operator,t);
            this.evaluateComparison(value1,value2,operator);

        }

    }

    @Override
    public Object visitCompMenor_Mky(MonkeyParser.CompMenor_MkyContext ctx) {

        this.operatorComparisonMethod(ctx.additionExpression(),"<",ctx.MENOR(0).getSymbol());
        return null;

    }

    @Override
    public Object visitCompMayor_Mky(MonkeyParser.CompMayor_MkyContext ctx) {
        this.operatorComparisonMethod(ctx.additionExpression(),">",ctx.MAYOR(0).getSymbol());
        return null;
    }

    @Override
    public Object visitCompMenorIg_Mky(MonkeyParser.CompMenorIg_MkyContext ctx) {

        this.operatorComparisonMethod(ctx.additionExpression(),"<=",ctx.MENORIGUAL(0).getSymbol() );
        return null;

    }

    @Override
    public Object visitCompMayorIg_Mky(MonkeyParser.CompMayorIg_MkyContext ctx) {
        this.operatorComparisonMethod(ctx.additionExpression(),">=",ctx.MAYORIGUAL(0).getSymbol());
        return null;
    }

    @Override
    public Object visitCompIgComp_Mky(MonkeyParser.CompIgComp_MkyContext ctx) {
        this.operatorComparisonMethod(ctx.additionExpression(),"==", ctx.IGUALCOMP(0).getSymbol());
        return null;
    }

    @Override
    public Object visitAddExpr_Mky(MonkeyParser.AddExpr_MkyContext ctx) {
        visit(ctx.multiplicationExpression());
        visit(ctx.additionFactor());

        return null;
    }


    private ProgramStackElement makeOperation(ProgramStackElement value1, ProgramStackElement value2,String operator,Token t){
        ProgramStackElement res = null;
        if (operator.equals("+")){
            if (value1.getType()==this.STRING && value2.getType()==this.STRING){
                return new ProgramStackElement((String.valueOf(value1.getValue()))+String.valueOf(value2.getValue()),this.STRING);
            }
            else if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString()))+
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
            else{
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int and String type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
            }


        }
        else if (operator.equals("-")){
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) -
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
            else{
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
            }
        }
        else if (operator.equals("*")){
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) *
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
            else{
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
            }
        }

        //div operator
        else{
            if (value1.getType()==this.INTEGER && value2.getType()==this.INTEGER){
                res= new ProgramStackElement((Integer.parseInt(value1.getValue().toString())) /
                        Integer.parseInt(value2.getValue().toString()),this.INTEGER);
            }
            else{
                throw new InterpreterException("Operator "+ operator + " is compatible only with Int type. " +
                        "in line: "+ t.getLine()+" column: "+ t.getCharPositionInLine());
            }
        }
        return res;

    }


    public void addSumSub(List<MonkeyParser.MultiplicationExpressionContext> ctx, String operator,Token op){

        ProgramStackElement value1;
        ProgramStackElement value2;
        int size= ctx.size();

        visit(ctx.get(0));
        //error en devolución de funcion aqui
        value2= this.evaluationStack.pop();
        value1= this.evaluationStack.pop();
        this.evaluationStack.push(this.makeOperation(value1,value2,operator,op));

        for (int i = 1; i < size; i++) {
            visit(ctx.get(i));
            value2= this.evaluationStack.pop();
            value1= this.evaluationStack.pop();
            this.evaluationStack.push(this.makeOperation(value1,value2,operator,op));
        }


    }

    @Override
    public Object visitAddFactSum_Mky(MonkeyParser.AddFactSum_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            this.addSumSub(ctx.multiplicationExpression(), "+",ctx.SUM(0).getSymbol());
        }
        return null;
    }

    @Override
    public Object visitAddFactSub_Mky(MonkeyParser.AddFactSub_MkyContext ctx) {
        if (ctx.multiplicationExpression().size()>0){
            this.addSumSub(ctx.multiplicationExpression(), "-", ctx.SUB(0).getSymbol());
        }

        return null;
    }

    @Override
    public Object visitMultExpr_Mky(MonkeyParser.MultExpr_MkyContext ctx) {

        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());
        return null;
    }

    public void mutlFactMulDiv(List<MonkeyParser.ElementExpressionContext>ctx,String operator,Token op) {

        ProgramStackElement value1;
        ProgramStackElement value2;

        int size= ctx.size();

        visit(ctx.get(0));
        value2=this.evaluationStack.pop();
        value1= this.evaluationStack.pop();
        this.evaluationStack.push(this.makeOperation(value1,value2,operator, op));

        for (int i = 1; i < size; i++) {
            visit(ctx.get(i));
            value2=this.evaluationStack.pop();
            value1= this.evaluationStack.pop();
            this.evaluationStack.push(this.makeOperation(value1,value2,operator, op));
        }
    }

    @Override
    public Object visitMultFactMul_Mky(MonkeyParser.MultFactMul_MkyContext ctx) {
        if (ctx.elementExpression().size()>0){
            this.mutlFactMulDiv(ctx.elementExpression(),"*",ctx.MUL(0).getSymbol());
        }
        return null;
    }

    @Override
    public Object visitMultFactDiv_Mky(MonkeyParser.MultFactDiv_MkyContext ctx) {
        if (ctx.elementExpression().size()>0){
            this.mutlFactMulDiv(ctx.elementExpression(),"/", ctx.DIV(0).getSymbol());
        }
        return null;
    }

    @Override
    public Object visitElemExprElemAccess_Mky(MonkeyParser.ElemExprElemAccess_MkyContext ctx) {

        //visit the name of the array or hash literal to get the array value or hash value
        visit(ctx.primitiveExpression());




        ProgramStackElement element= this.evaluationStack.pop();

        if ( (element.getType() != this.ARRAY_LITERAL && element.getType()!= this.HASH_LITERAL )){ //if it's not a neutral type, or array or hash literal

            throw new InterpreterException("Indexing process can only be applied to HASH OR ARRAY Literal. "+
                    "in line: "+ ctx.primitiveExpression().getStart().getLine()+" column: "+ ctx.primitiveExpression().getStart().getCharPositionInLine());
        }

        visit(ctx.elementAccess());

        ProgramStackElement index = this.evaluationStack.pop();

        /**
         * If there are error types in hash literal
         */
        if ( index.getType() ==this.TYPE_ERROR){
            throw new InterpreterException("Error in index value. "+
                    "in line: "+ ctx.elementAccess().getStart().getLine()+" column: "+ ctx.elementAccess().getStart().getCharPositionInLine());
        }

        //if primitive expression is an array it can't be indexed through an string index
        if (element.getType() == this.ARRAY_LITERAL && index.getType() ==this.STRING){
            return this.TYPE_ERROR;
        }

        /**
         * IF WE ARE INDEXING A HASH LITERAL, the index must to be a key in string
         */
        if (element.getType()== this.HASH_LITERAL){
            if(index.getType()== this.STRING | index.getType() == this.INTEGER){
                //indexing hash literal in a simple way


                ProgramStackElement elemento = new ProgramStackElement(((Hashtable) element.getValue()).get(index.getValue()),this.getTypeOfElement(element.getValue()));


                this.evaluationStack.push(elemento);
            }
            else{
                throw new InterpreterException("HASH literal can be indexing only through String or int index. "+
                        "in line: "+ ctx.elementAccess().getStart().getLine()+" column: "+ ctx.elementAccess().getStart().getCharPositionInLine());
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

        ProgramStackElement element= this.evaluationStack.pop();

        //if the element is not  a funtion
        if (element.getType() != this.FUNCTION){
            throw new InterpreterException("The identifier "+ctx.primitiveExpression().getText()+ " is not a function."+
                    " in line: "+ ctx.primitiveExpression().getStart().getLine()+" column: "+ ctx.primitiveExpression().getStart().getCharPositionInLine());
        }
        else {
            visit(ctx.callExpression());
            //check if the stack have at least the amount of elements required for the funtion

            MonkeyParser.FuncLit_MkyContext func= (MonkeyParser.FuncLit_MkyContext)(element.getValue());

            int paramsAmount;
            //get function params

            MonkeyParser.MoreIdentifiersContext identifiers= ((MonkeyParser.FuncParams_MkyContext )func.functionParameters()).moreIdentifiers();
            paramsAmount= (identifiers.getChildCount()/2)+1;

            //check if the stack size is not the necessary to call the function
            if (this.evaluationStack.size() < paramsAmount){
                throw new InterpreterException("The number of parameters are not the same that function "+
                        ctx.primitiveExpression().getText()+" require, function was expected" +
                        paramsAmount+
                        " . in line: "+ ctx.getStart().getLine()+" column: "+ ctx.getStart().getCharPositionInLine());
            }
            else{
                this.elementsCount++;
                dataStorage temp;


                // for normal functions
                Frame newFrame=new Frame(this.elementsCount,ctx.primitiveExpression().getText());

                //create new let Statement context
                MonkeyParser.LetStatementContext letContex;

                temp=newFrame.getStorage();



                paramsAmount= ((paramsAmount-1)*2)-1;

                for (int i = paramsAmount; i >= 0; i=i-2) {
                    element=this.evaluationStack.pop();
                    letContex =new MonkeyParser.LetStatementContext(null,0);
                    letContex.storageIndex= temp.getCurrentIndex();

                    temp.addData(identifiers.getChild(i).getText(),element.getValue(),
                            temp.getCurrentIndex(),this.getTypeOfElement(element.getValue()),letContex);

                }

                element=this.evaluationStack.pop();
                letContex =new MonkeyParser.LetStatementContext(null,0);
                letContex.storageIndex= temp.getCurrentIndex();

                temp.addData(func.functionParameters().getChild(0).getText(),element.getValue(),
                        temp.getCurrentIndex(),this.getTypeOfElement(element.getValue()),letContex);

                System.out.println("*************************************");

                //add the frame to the list
                boolean res =this.programFrames.insertFrame(newFrame,
                        (MonkeyParser.Id_MkyContext)(((MonkeyParser.PExprID_MkyContext) ctx.primitiveExpression()).identifier()),
                        this.FUNCTION);
                if (res==false){
                    throw new InterpreterException("Error in function call."+
                            " in line: "+ ctx.primitiveExpression().getStart().getLine()+" column: "+
                            ctx.primitiveExpression().getStart().getCharPositionInLine());
                }

                //set stack and data storage globals vars
                this.evaluationStack= newFrame.getStack();
                this.DataStorage= newFrame.getStorage();




                //backup control of return statement
                boolean haveTReturn= this.haveToReturn;
                //go to execute the function
                visit(func.blockStatement());
                //important to allow that program search in global frame when the recursion end in console call

                this.haveToReturn= haveTReturn;

                //then remove the frame after get the value in the stack
                this.programFrames.deleteCurrentFrame();

                this.evaluationStack= this.programFrames.getCurrentProgramFrame().getStack();
                this.DataStorage= this.programFrames.getCurrentProgramFrame().getStorage();




            }

        }

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
    public Object visitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx) {


        /**
         ProgramStackElement index= this.evaluationStack.pop();
         ProgramStackElement element= this.evaluationStack.pop();
         ArrayList temp= ( ArrayList ) element.getValue();
         int ind= (Integer) index.getValue();


         * check if the index is inside the array

        if (ind < temp.size()){
            //obtener tipo de dato del elemento en el array, ahorita solo va a servir para números enteros
            this.evaluationStack.push(new ProgramStackElement(temp.get(ind),this.getTypeOfElement(temp.get(ind))));
        }
        /* INDEX OUT OF BOUNDS throw exception to avoid null pointer exception in program
        else{
            return this.TYPE_ERROR;
        }

        //load params for the function
        visit (ctx.expressionList());

        MonkeyParser.FuncLit_MkyContext func= (MonkeyParser.FuncLit_MkyContext)(element.getValue());

        int paramsAmount;
        //get function params

        MonkeyParser.MoreIdentifiersContext identifiers= ((MonkeyParser.FuncParams_MkyContext )func.functionParameters()).moreIdentifiers();
        paramsAmount= (identifiers.getChildCount()/2)+1;

        //check if the stack size is not the necessary to call the function
        if (this.evaluationStack.size() < paramsAmount){
            System.out.println("******************************");
            System.out.println("ERROR IN FUNCTION PARAMS");
            System.out.println("******************************");
        }
        else{
            this.elementsCount++;

            dataStorage temporal;


            // for special function require identifier
            Frame newFrame=new Frame(this.elementsCount,ctx.primitiveExpression().getText());

            //create new let Statement context
            MonkeyParser.LetStatementContext letContex;

            temp=newFrame.getStorage();



            paramsAmount= ((paramsAmount-1)*2)-1;

            for (int i = paramsAmount; i >= 0; i=i-2) {
                element=this.evaluationStack.pop();
                letContex =new MonkeyParser.LetStatementContext(null,0);
                letContex.storageIndex= temp.getCurrentIndex();

                temp.addData(identifiers.getChild(i).getText(),element.getValue(),
                        temp.getCurrentIndex(),this.getTypeOfElement(element.getValue()),letContex);

            }

            element=this.evaluationStack.pop();
            letContex =new MonkeyParser.LetStatementContext(null,0);
            letContex.storageIndex= temp.getCurrentIndex();

            temp.addData(func.functionParameters().getChild(0).getText(),element.getValue(),
                    temp.getCurrentIndex(),this.getTypeOfElement(element.getValue()),letContex);

            System.out.println("*************************************");

            //add the frame to the list
            boolean res =this.programFrames.insertFrame(newFrame,
                    (MonkeyParser.Id_MkyContext)(((MonkeyParser.PExprID_MkyContext) ctx.primitiveExpression()).identifier()),
                    this.FUNCTION);
            if (res==false){
                System.out.println("******************************");
                System.out.println("ERROR INSERTING FRAMES");
                System.out.println("******************************");
            }

            //set stack and data storage globals vars
            this.evaluationStack= newFrame.getStack();
            this.DataStorage= newFrame.getStorage();




            //backup control of return statement
            boolean haveTReturn= this.haveToReturn;
            //go to execute the function
            visit(func.blockStatement());
            //important to allow that program search in global frame when the recursion end in console call

            this.haveToReturn= haveTReturn;

            //then remove the frame after get the value in the stack
            this.programFrames.deleteCurrentFrame();

            this.evaluationStack= this.programFrames.getCurrentProgramFrame().getStack();
            this.DataStorage= this.programFrames.getCurrentProgramFrame().getStorage();




        }

        */

        visit (ctx.expressionList());

        return null;
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
            this.evaluationStack.push(new ProgramStackElement(temp.get(ind),this.getTypeOfElement(temp.get(ind))));
        }
        /* INDEX OUT OF BOUNDS throw exception to avoid null pointer exception in program*/
        else{
            throw new InterpreterException("Index out of range."+
                    " in line: "+ ctx.getStart().getLine()+" column: "+ ctx.getStart().getCharPositionInLine());
        }

        return null; // here it always goes to return a valid value.
    }

    @Override
    public Object visitCallExpr_Mky(MonkeyParser.CallExpr_MkyContext ctx) {
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPExprInt_Mky(MonkeyParser.PExprInt_MkyContext ctx) {

        this.evaluationStack.push( new ProgramStackElement(Integer.parseInt(ctx.getText()),this.INTEGER) );
        return null;
    }

    @Override
    public Object visitPExprStrMky(MonkeyParser.PExprStrMkyContext ctx)
    {
        this.evaluationStack.push(new ProgramStackElement(String.valueOf(ctx.getText().replace("\"","")),this.STRING));
        return null;
    }

    @Override
    public Object visitPExprID_Mky(MonkeyParser.PExprID_MkyContext ctx) {

        dataStorageItem element;

        // if execution is not trough console
        if(this.flagConsole == 0) {
            element= this.programFrames.searchElement( (MonkeyParser.Id_MkyContext)ctx.identifier());
        }

        else
            {

            if (ctx.identifier().decl!=null){
                //call for recursion
                element= this.programFrames.searchElement( (MonkeyParser.Id_MkyContext)ctx.identifier());

            }
            else{
                // first call through console
                element =this.DataStorage.findElement(ctx.identifier().getText());
                if (element== null) {
                    throw new InterpreterException("Identifier "+ ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getText()+ " doesn't exist."+
                            " in line: "+ ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getSymbol().getLine()+" column: "+
                            ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getSymbol().getCharPositionInLine());
                }

                //building letstatementcontext for function call type by console and for any var
                MonkeyParser.LetStatementContext letContex =new MonkeyParser.LetStatementContext(null,0);
                letContex.storageIndex=element.getIndex();
                ctx.identifier().decl=letContex;


            }

        }

        if (element== null) {
            throw new InterpreterException("Identifier "+ ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getText()+ " doesn't exist."+
                    " in line: "+ ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getSymbol().getLine()+" column: "+
                    ((MonkeyParser.Id_MkyContext) ctx.identifier()).ID().getSymbol().getCharPositionInLine());
        }


        this.evaluationStack.push(new ProgramStackElement(element.getValue(),element.getType()));

        return null;
    }

    @Override
    public Object visitPExprTRUE_Mky(MonkeyParser.PExprTRUE_MkyContext ctx) {

        this.evaluationStack.push(new ProgramStackElement(Boolean.parseBoolean(ctx.getText()),this.BOOLEAN));
        return null;
    }

    @Override
    public Object visitPExprFALSE_Mky(MonkeyParser.PExprFALSE_MkyContext ctx) {
        this.evaluationStack.push(new ProgramStackElement(Boolean.parseBoolean(ctx.getText()),this.BOOLEAN));
        return null;
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

    public void lenEvaluation(Token t){
        ProgramStackElement valor;
        ProgramStackElement nuevoElemento = new ProgramStackElement("",0);

        valor =this.evaluationStack.pop();

        if(valor.getType() == this.STRING){
            nuevoElemento.setType(this.INTEGER);
            nuevoElemento.setValue(valor.getValue().toString().length());
        }
        else if(valor.getType() == this.ARRAY_LITERAL){
            nuevoElemento.setType(this.INTEGER);
            nuevoElemento.setValue(((ArrayList) valor.getValue()).size());
        }
        else{
            throw new InterpreterException("Len function is used only with Array literal and Strings. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());

        }
        this.evaluationStack.push(nuevoElemento);
    }

    public void firstEvaluation(Token t){
        ProgramStackElement id = new ProgramStackElement("",0);
        ProgramStackElement nuevoElemento = new ProgramStackElement("",0);

        id =this.evaluationStack.pop();

        if(id.getType() == this.ARRAY_LITERAL){
            if(((ArrayList)id.getValue()).size() > 0){
                nuevoElemento.setValue(((ArrayList)id.getValue()).get(0));
                nuevoElemento.setType(this.getTypeOfElement(nuevoElemento.getValue()));
            }
            else{
                nuevoElemento.setValue(new ArrayList<>());
                nuevoElemento.setType(this.ARRAY_LITERAL);
            }
        }
        else{
            throw new InterpreterException( "First function is used only with Array Literal types. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());
        }
        this.evaluationStack.push(nuevoElemento);
    }

    public void lastEvaluation(Token t){
        ProgramStackElement id = new ProgramStackElement("",0);
        ProgramStackElement nuevoElemento = new ProgramStackElement("",0);

        id =this.evaluationStack.pop();

        if(id.getType() == this.ARRAY_LITERAL){
            if(((ArrayList)id.getValue()).size() > 0){
                nuevoElemento.setValue(((ArrayList)id.getValue()).get(((ArrayList)id.getValue()).size() - 1));
                nuevoElemento.setType(this.getTypeOfElement(nuevoElemento.getValue()));
            }
            else{
                nuevoElemento.setValue(new ArrayList<>());
                nuevoElemento.setType(this.ARRAY_LITERAL);
            }
        }
        else{
            throw new InterpreterException("The function LAST only use Array Literal elements. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());
        }
        this.evaluationStack.push(nuevoElemento);
    }

    public void restEvaluation(Token t){
        ProgramStackElement id = new ProgramStackElement("",0);
        ProgramStackElement nuevoElemento = new ProgramStackElement("",0);

        id =this.evaluationStack.pop();

        if(id.getType() == this.ARRAY_LITERAL){
            if(((ArrayList)id.getValue()).size() > 1){
                ArrayList array = (ArrayList)id.getValue();
                ArrayList nuevoArray = new ArrayList();

                for (int i = 1; i < array.size(); i++) {
                    nuevoArray.add(array.get(i));
                }

                nuevoElemento.setValue(nuevoArray);
                nuevoElemento.setType(this.getTypeOfElement(nuevoElemento.getValue()));
            }
            else {
                nuevoElemento.setValue(new ArrayList<>());
                nuevoElemento.setType(this.getTypeOfElement(nuevoElemento.getValue()));
            }

        }
        else{
            throw new InterpreterException("rest function is used only with Array Literal. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());
        }
        this.evaluationStack.push(nuevoElemento);
    }

    public void pushEvaluation(Token t){
        ProgramStackElement valor;
        ProgramStackElement id ;
        ProgramStackElement nuevoElemento = new ProgramStackElement("",0);

        if(this.evaluationStack.size() > 1) {
            valor = this.evaluationStack.pop();
            id = this.evaluationStack.pop();
        }
        else{
            throw new InterpreterException("push function require two param. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());
        }

        if(id.getType() == this.ARRAY_LITERAL){
            //agregar el elemento al arreglo
            ((ArrayList)id.getValue()).add(valor.getValue());
            nuevoElemento.setValue(id.getValue());
            nuevoElemento.setType(this.getTypeOfElement(nuevoElemento.getValue()));
        }
        else{

            throw new InterpreterException("push function is used only with Array Literal. "+
                    "In line: "+ t.getLine()+" column: "+t.getCharPositionInLine());
        }
        this.evaluationStack.push(nuevoElemento);
    }

    @Override
    public Object visitPExprArrayFunc_Mky(MonkeyParser.PExprArrayFunc_MkyContext ctx) {
        java.lang.String arrayFunction = (java.lang.String) visit(ctx.arrayFunctions());
        visit(ctx.expressionList());

        //if the stack is empty
        if (this.evaluationStack.size()==0){
            throw new InterpreterException("Default functions like len,first,last and rest requiere one param " +
                    "push default function require two params. "+
                    "In line: "+ ctx.arrayFunctions().getStart().getLine()+" column: "+ctx.arrayFunctions().getStart().getCharPositionInLine());
        }
        switch (arrayFunction){
            case "len":
                lenEvaluation(ctx.arrayFunctions().getStart());
                break;
            case "first":
                firstEvaluation(ctx.arrayFunctions().getStart());
                break;
            case "last":
                lastEvaluation(ctx.arrayFunctions().getStart());
                break;
            case "rest":
                restEvaluation(ctx.arrayFunctions().getStart());
                break;
            case "push":
                pushEvaluation(ctx.arrayFunctions().getStart());
                break;
            default:
                throw new InterpreterException("Error using default functions "+
                        "In line: "+ ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
        }
        return null;
    }

    @Override
    public Object visitPExprFuncDecl_Mky(MonkeyParser.PExprFuncDecl_MkyContext ctx) {
        return visit(ctx.functionLiteral());

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
        return ctx.LEN().getText();//CAMBIAR
    }

    @Override
    public Object visitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx) {
        return ctx.FIRST().getText();//CAMBIAR
    }

    @Override
    public Object visitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx) {
        return ctx.LAST().getText();//CAMBIAR
    }

    @Override
    public Object visitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx) {
        return ctx.REST().getText();//CAMBIAR
    }

    @Override
    public Object visitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx) {
        return ctx.PUSH().getText();//CAMBIAR
    }

    //array declaration
    @Override
    public Object visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx) {

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

        return null;//CAMBIAR
    }


    @Override
    public Object visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx) {
        /**
         * Only working for normal functions
         */

        //it´s only a declaration, so only insert to the stack for make th assigment then in let statement
        //visit(ctx.functionParameters());
        this.evaluationStack.push(new ProgramStackElement(ctx,this.FUNCTION));

        //visit(ctx.blockStatement());

        return null;//CAMBIAR

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
        IdentifierElement elem;
        for (int i = 0; i <size; i++){

        }
        return null;
    }

    @Override
    public Object visitHashLit_Mky(MonkeyParser.HashLit_MkyContext ctx) {
        Hashtable<Object, Object> hashElement = new Hashtable<Object, Object>();
        Object key = null, value = null;

        visit(ctx.hashContent());
        visit(ctx.moreHashContent());

        for (int i = 0; i < (ctx.moreHashContent().getChildCount()/2 + 1); i++) {
            value = evaluationStack.pop().getValue();
            key = evaluationStack.pop().getValue();
            hashElement.put(key,value);
        }

        ProgramStackElement element = new ProgramStackElement(hashElement,this.HASH_LITERAL);
        evaluationStack.push(element);

        return null;
    }

    @Override
    public Object visitHashCont_Mky(MonkeyParser.HashCont_MkyContext ctx) {
        visit(ctx.expression(0));
        ProgramStackElement el = this.evaluationStack.pop();
        //if the key it's an integer or string
        if(el.getType() == this.INTEGER || el.getType() == this.STRING){
            this.evaluationStack.push(el);
            visit(ctx.expression(1));
        }
        else{
            throw new InterpreterException("Hash key must be String or int type. "+
                    "In line: "+ ctx.expression(0).getStart().getLine()+" column: "+
                    ctx.expression(0).getStart().getCharPositionInLine());
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
        return null;
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

        visit(ctx.expression());
        ProgramStackElement elemento = this.evaluationStack.pop();
        if (this.flagConsole==1){
            this.printInConsole(elemento.getValue().toString());
        }

        return null;
    }

    @Override
    public Object visitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx) {
        visit(ctx.expression());

        ProgramStackElement element = this.evaluationStack.pop();

        //execute the if block statement
        if ( Boolean.parseBoolean(element.getValue().toString()) ==true ){

            this.DataStorage.openScope();
            visit(ctx.blockStatement(0));
            this.DataStorage.closeScope();
        }

        // else block statement
        else{
            // check if there is an else statement
            if (ctx.blockStatement().size()> 1){
                this.DataStorage.openScope();
                visit(ctx.blockStatement(1));
                this.DataStorage.closeScope();
            }
        }

        return null;
    }

    @Override
    public Object visitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx) {

        for(MonkeyParser.StatementContext stm : ctx.statement()){
            visit(stm);
            if (this.haveToReturn==true){
                return null;
            }
        }

        return null;
    }
}
