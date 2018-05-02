package clases.checker;

import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checker extends MonkeyParserBaseVisitor {

    private FunctionsTable functionsTable;
    private IdentifiersTable identifierTable;
    private FnSpecialTable fnSpecialTable;
    private int haveReturn=0;
    private int finalCounterParams;
    private int globalCounterReturn = 0;
    private int globalCounterParams = 0;
    private int globalCounterIdentifiers = 0;
    private boolean returnInFunction; //para controlar si la sentencia return se encuentra dentro de una función
    private String arrayName;
    private boolean isInLet = false;
    private boolean allowChangeInLet; //to control when isInLet var can change of value
    private int specialIndex;
    private String specialArrayName;
    private RecursivityObject temporalObject;

    /*
    /*it's going to store the name of the function, it helps with visit function Literal
      the vaue is none when the function literal visti is not due to a let statement

    private Token functionName;

    /*
    for backup the context declaration of function

    private ParserRuleContext functionRuleContext;
    */
    /*
    when the program goes to a function declaration, this vars increase in one number,
    if functionInsideOther's value is 1 or more it means that that there are functions inside
    a function
    */
    private int functionInsideOther;


    /*
     * tipo neutro se va a representar con un 0, -1 representa que no existe tipo para el identificador
     * variable tipo arreglo= 4 , variable tipo diccionario = 5, variable normal -1
     * -2 : algunas expresiones (como el if) retornarán -2  para validar que no se puedan usar con ciertos operadores
     */

    /**
     * if specialIndex value is equal to -1, it's indicated that the index is neutral or a operation
     * in other case specialIndex is going to have the value;  -2 define that index is not usable
     */

    private ArrayList<String> errorsList;

    public Checker(){
        this.errorsList= new ArrayList<String>();
        this.functionsTable= new FunctionsTable();
        this.identifierTable= new IdentifiersTable();
        this.arrayName=null;
        this.fnSpecialTable = new FnSpecialTable();
        this.allowChangeInLet=false;
        this.specialIndex=-1;
        this.returnInFunction= false;
        this.specialArrayName="none";
        this.specialIndex=-2;
        this.functionInsideOther=-1;
        this.finalCounterParams=0;
        this.temporalObject = new RecursivityObject(null,-1);// -1 representa que es una funcion normal, no una funcion declarada dentro de un array
    }

    public ArrayList<String> getErrorsList() {
        return errorsList;
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


    /**
     *
     * @param array
     * @param index
     * @param paramsNumber
     * @return  0 if the function call is success, -1 if something fail in the function call
     */
    public int evaluateSpecialFunctionCall(String array,int index,int paramsNumber,MonkeyParser.ExpressionListContext ctx){
        int res=0;
            if (array.equals("none")){ //it's not possible to know nothing about the array, for that accept
            res = 0;
        }
        /*
        it's not possible to know the function position in the array, so if there is a function with the same
        params number in the array accept
         */
        else if (index==-2){
            if (this.fnSpecialTable.arrayHaveFunctions(paramsNumber,array) ==true){
                res = 0;
            }
            else{
                this.errorsList.add("Error: Array "+array+" doesn't contain a function with the same number of params" +
                        " that you sent. At line: "+ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
                res = -1;
            }
        }
        //it's possible to looking for a function in the array at the position of the index value
        else{
            FnSpecialElement elem= this.fnSpecialTable.buscar(index,array);
            if (elem==null){
                this.errorsList.add("Error: Array "+array+" doesn't contain a function at the position number " +
                        index  + " .At line: "+ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
                res = -1;
            }
            else{
                if (elem.getParamsNumber()!=paramsNumber) {
                    this.errorsList.add("Error: Array " + array + " function at the position " + index +
                            " was expected " + elem.getParamsNumber() + " params and you sent " + paramsNumber + " .At line: " +
                            ctx.getStart().getLine() + " column: " + ctx.getStart().getCharPositionInLine());
                    res = -1;
                }
            }
        }
        System.out.println(res);

        return res;
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
        this.temporalObject.setFnName(null);
        this.temporalObject.setIndex(-1);
        if (ctx.expression().toStringTree().contains("fn(") || ctx.expression().toStringTree().contains("fn (")){
            this.temporalObject.setFnName(ctx.ID().getSymbol());
        }

        this.specialIndex=-2;

        this.arrayName = ctx.ID().getText();


        //check if function or variable name is an special function
        if (this.isReservedWord(ctx.ID().getText().toLowerCase())==true){
            this.errorsList.add("Error: Assignment is not posible because the identifier  " + ctx.ID().getText() + " it's a Monkey language reserved word. At line: " +
                    ctx.ID().getSymbol().getLine() + " column: " + ctx.ID().getSymbol().getCharPositionInLine());
            return -1;
        }

        this.isInLet = true;
        this.allowChangeInLet=false;

        int type = (Integer) visit(ctx.expression());

        if (allowChangeInLet == true){
            this.isInLet=false;
        }

        if (type !=4 && type!= -1 && (ctx.expression().toStringTree().contains("fn(") || ctx.expression().toStringTree().contains("fn ("))){
            FuncTableElement element = this.functionsTable.buscar(ctx.ID().getText());
            element.setDeclaration(ctx.expression());
            element.setReturnType(this.haveReturn);
        }
        else{
            //error in the expresion
            if (type==-1){ // ir a fnSpecialTable a eliminar todas las funciones asociadas a la variable arrayName (global)
                return -1;
            }

            if (this.functionsTable.buscar(ctx.ID().getText().toLowerCase())!=null){
                this.errorsList.add("Error: The identifier " + ctx.ID().getText() + " it's already declared like a function and" +
                        " can't not be change to variable. At line: " +
                        ctx.getStart().getLine() + " column: " + ctx.getStart().getCharPositionInLine());
                return -1;
            }


            //neutro type
            this.identifierTable.insertar(ctx.ID().getSymbol(),type,ctx);
        }


        this.fnSpecialTable.imprimir();

        return -2;

    }

    @Override
    public Object visitReturnSt_Mky(MonkeyParser.ReturnSt_MkyContext ctx) {
        this.specialIndex=-2;
        if (this.returnInFunction==false){
            this.errorsList.add("Error: a return statement has to be inside a function. At line: " +
                    ctx.getStart().getLine()+" column: "+ctx.getStart().getCharPositionInLine());
            return -1;
        }

        this.globalCounterReturn++;

        int type= (Integer) visit(ctx.expression());

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
            if (type1==-2 || type1 > 3){ //un statement no es compatible con ningún operador, ni el array literal o hash literal
                this.errorsList.add("Error: "+this.getTypeName(type1)+" is not compatible with any operator.At line: " +
                        ctx.additionExpression().getStart().getLine()+" column: "+ctx.additionExpression().getStart().getCharPositionInLine());
            }
            type2= (Integer) visit(ctx.comparison());
        }
        if (this.checkTypesCompatibility(type1,type2)!=true){
            this.errorsList.add("Error: type error "+this.getTypeName(type1)+" is not compatible with type "+
                    this.getTypeName(type2)+".At line: " +
                    ctx.additionExpression().getStart().getLine()+" column: "+ctx.additionExpression().getStart().getCharPositionInLine());
            resType=-1;
        }
        else{
            resType= type1;
        }
        return resType;

    }

    public int checkRestrictions(int type1, int type2, String operator,ParserRuleContext ctx){
        if (this.isValidOperator(operator,type2)!=true){
            this.errorsList.add("Error: type "+ this.getTypeName(type1)+" is not compatible with the operator "+operator+".At line: " +
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

        if ( (type1 > 0 && type1 <4 )){ //if it's not a neutral type, or array or hash literal
            this.errorsList.add("Error: Can't index a value that is not an array or hash literal. At line:"+ ctx.getStart().getLine()+
                    " column: "+ ctx.getStart().getCharPositionInLine());
            return -1;
        }
        else{
            this.specialArrayName = this.setSpecialArrayName(ctx.primitiveExpression().getText());
        }

        type1= (Integer) visit(ctx.elementAccess());


        if (type1==-1){
            return -1;
        }
        else{  //evaluate special funcion call
            type1= (Integer) visit(ctx.specialCall());
            if (type1==-1){
                return -1;
            }
        }
        return 0; //tipo válido
    }

    //por el momento va a funcionar solo con funciones normales (no con la del arreglo)
    @Override
    public Object visitElemExprCallExpr_Mky(MonkeyParser.ElemExprCallExpr_MkyContext ctx) {
        this.globalCounterParams=0;
        int resType=0;

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

        int type = (Integer) visit(ctx.expression());

        if (type != 0 && type != 1) {  //solo se permiten neutros o enteros para indexar arreglos
            this.errorsList.add("Error: Array or Hash literal must be indexed through an Int or neutral value " +
                    ".At line: " + ctx.expression().getStart().getLine() + " column: " + ctx.expression().getStart().getLine());
            return -1; //error

        }
        //obtener indice y cantidad de parametros enviados a la funcion especial, considerar que una expresion como está
        // 1+2; se tomara como valida si existe alguna posición en el arreglo que tenga una funcion
        //lo mismo aplica para el tipo neutro
        this.specialIndex = this.setSpecialIndex(this.specialArrayName,ctx.expression().getText());

        return type;
    }


    @Override
    public Object visitSpecialCall_Mky(MonkeyParser.SpecialCall_MkyContext ctx) {
        int type;

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
        System.out.println("TYPE = " + type);
        return type;
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
        int resType = -1;
        IdentifierElement elem = this.identifierTable.buscar(ctx.ID().getText());
        FuncTableElement elem2= this.functionsTable.buscar(ctx.ID().getText());
        if (elem==null && elem2==null){
            this.errorsList.add("Error: Variable or function "+ ctx.ID().getText()+ " have not been declared. At line: "+
            ctx.ID().getSymbol().getLine()+" Column: "+ ctx.ID().getSymbol().getCharPositionInLine());
            return -1;
        }

        if (elem!= null) {

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

        //si se llama a la función ddentro del arreglo aqui cae; o entra
        int type= (Integer) visit(ctx.expression());
        // if a function can be called
        if (type==-1){
            this.errorsList.add("Error: Types error in the expression.At line: "+
                    ctx.getStart().getLine()+ " column: "+ ctx.getStart().getCharPositionInLine());
        }
        return type;

    }

    @Override
    public Object visitPExprArrayLit_Mky(MonkeyParser.PExprArrayLit_MkyContext ctx) {
        return (Integer) visit(ctx.arrayLiteral());
    }


    //retorna el valor de retorno de la función al evaluarlo
    public int manageDefaultFunctions(MonkeyParser.PExprArrayFunc_MkyContext ctx){
        int res=0;
        FuncTableElement elem= (FuncTableElement) visit(ctx.arrayFunctions());
        this.globalCounterParams=0; //contar los parametros
        res= (Integer) visit(ctx.expressionList());

        //verificar que se haya enviado la cantidad de parámetros que espera el método
        if (elem.getParamsNumber()!=this.globalCounterParams){
            this.errorsList.add("Error: function "+ elem.getToken().getText()+" was expected "+ elem.getParamsNumber()+
            " param and you sent "+ this.globalCounterParams+" params. At line: "+ ctx.arrayFunctions().getStart().getLine()+
                    "column: "+ ctx.getStart().getCharPositionInLine());
            return -1; //tipo invalido
        }

        // si no hubo problema en las expresiones que representan los parametros que las funciones aceptan
        //push podría ser la excepción, el len también admite strings

        if ((res==0 || res==4 || res==5) || (res==2  && elem.getToken().getText().equals("len"))){
            res= elem.getReturnType();
        }
        else{
            this.errorsList.add("Error: types error function "+ elem.getToken().getText()+" is not compatible" +
                    " with "+ this.getTypeName(res) +".At line: "+ ctx.arrayFunctions().getStart().getLine()+
                    "column: "+ ctx.getStart().getCharPositionInLine());
            res = -1; //tipo invalido
        }

        return res;
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
        return this.functionsTable.buscar(ctx.LEN().getSymbol().getText());
    }

    @Override
    public Object visitArrayFuncFirst_Mky(MonkeyParser.ArrayFuncFirst_MkyContext ctx) {
        return this.functionsTable.buscar(ctx.FIRST().getSymbol().getText());
    }

    @Override
    public Object visitArrayFuncLast_Mky(MonkeyParser.ArrayFuncLast_MkyContext ctx) {
        return this.functionsTable.buscar(ctx.LAST().getSymbol().getText());
    }

    @Override
    public Object visitArrayFuncRest_Mky(MonkeyParser.ArrayFuncRest_MkyContext ctx) {
        return this.functionsTable.buscar(ctx.REST().getSymbol().getText());
    }

    @Override
    public Object visitArrayFuncPush_Mky(MonkeyParser.ArrayFuncPush_MkyContext ctx) {
        return this.functionsTable.buscar(ctx.PUSH().getSymbol().getText());
    }

    //array declaration
    @Override
    public Object visitArrayLit_Mky(MonkeyParser.ArrayLit_MkyContext ctx) {

        int res= (Integer)visit(ctx.expressionList());
        if (res!=-1){
            return 4; //type array
        }
        return res;
    }

    @Override
    public Object visitFuncLit_Mky(MonkeyParser.FuncLit_MkyContext ctx) {
        RecursivityObject respaldo = new RecursivityObject(this.temporalObject.getFnName(),this.temporalObject.getIndex());
        int res=-1;
        int temp= this.globalCounterReturn;
        //update function inside other counter
        this.functionInsideOther++;

        this.globalCounterReturn=0; // reestablecer el contador

        this.identifierTable.OpenScope();



        res= (Integer) visit(ctx.functionParameters());
        // backup the value of global counter params
        int paramsTemp=this.globalCounterParams;

        if (res==-1){
            return res;
        }
        /*
        * insertar ya sea a la tabla de funciones especiales o a la de funciones normales
        * */
        if(this.temporalObject.getIndex() == -1){
            if (this.identifierTable.buscar(temporalObject.getFnName().getText())!=null){
                this.errorsList.add("Error: The identifier " + temporalObject.getFnName().getText() + " it's already declared like a varaible" +
                        " and can't be change to function. At line: " +
                        temporalObject.getFnName().getLine() + " column: " + temporalObject.getFnName().getCharPositionInLine());
                return -1;
            }
            FuncTableElement resp = this.functionsTable.insert(temporalObject.getFnName(),this.globalCounterParams,0,null);
            if(resp == null){
                this.errorsList.add("Error: The function " + this.temporalObject.getFnName().getText() + " it's already declared. At line: " +
                        ctx.getStart().getLine() + " column: " + ctx.getStart().getCharPositionInLine());
                return -1;
            }
        }
        else{

            this.identifierTable.setActLevel(this.identifierTable.getActLevel()-1);

            this.identifierTable.insertar(temporalObject.getFnName(),4,null);

            this.identifierTable.setActLevel(this.identifierTable.getActLevel()+1);

            this.fnSpecialTable.insert(this.globalCounterParams,this.temporalObject.getFnName().getText(),this.temporalObject.getIndex());
        }
        this.functionsTable.openScope();

        this.returnInFunction = true; //se permite dentro del blockstatement la sentencia return


        res= (Integer) visit(ctx.blockStatement());


        this.temporalObject=respaldo;
        if (res == -1) {
            if(this.temporalObject.getIndex() != -1){
                this.identifierTable.deleteElement(this.temporalObject.getFnName().getText());
                this.fnSpecialTable.deleteElement(this.temporalObject.getFnName().getText());

            }
            else{
                this.functionsTable.deleteElement(this.temporalObject.getFnName().getText());

            }

        }
        else{
            if (this.temporalObject.getIndex() != -1){
                this.identifierTable.deleteElement(this.temporalObject.getFnName().getText());
            }
        }

        if (this.functionInsideOther == 0){
            this.returnInFunction = false;  //to control that there are not return outside a function
        }

        //set the final counter params with the backup value
        this.finalCounterParams = paramsTemp;

        //reset the global counter params value
        this.globalCounterParams = paramsTemp;

        //reset value of function inside other counter
        this.functionInsideOther--;

        this.identifierTable.imprimir();
        this.functionsTable.imprimir();
        this.identifierTable.closeScope();
        this.functionsTable.closeScope();


        if (this.globalCounterReturn > 0){
            this.haveReturn = 0; //tipo neutro para la función
        }
        else{
            this.haveReturn = -1; //función sin tipo
        }

        this.globalCounterReturn = temp;
        return 0;

    }

    @Override
    public Object visitFuncParams_Mky(MonkeyParser.FuncParams_MkyContext ctx) {
        //habría que guardar esta id
        //ctx.ID()
        this.globalCounterParams=0;
        this.identifierTable.insertar(ctx.ID().getSymbol(),0,ctx);
        this.globalCounterParams++;
        return visit(ctx.moreIdentifiers());

    }

    @Override
    public Object visitMoreIdentifiers_Mky(MonkeyParser.MoreIdentifiers_MkyContext ctx) {
        int size=ctx.ID().size();
        IdentifierElement elem;
        for (int i = 0; i <size; i++){
            elem= this.identifierTable.insertar(ctx.ID().get(i).getSymbol(),0,ctx);
            if (elem!=null){
                this.globalCounterParams++;
            }
            else{
                this.errorsList.add("Error: Two or more params have the same name. At line: "+ctx.ID(i).getSymbol().getLine()+
                        " column: "+ ctx.ID(i).getSymbol().getCharPositionInLine());
                return -1;
            }
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
        if(type1 == 2){
            type2 = (Integer) visit(ctx.expression(1));
            if (type2 != -1){
                return 0;
            }
            else{
                this.errorsList.add("Error: Error type in the expression. At line: "+ctx.expression(1).getStart().getLine()+
                        " column: "+ ctx.expression(1).getStart().getCharPositionInLine());
                return -1;
            }
        }
        else{
            this.errorsList.add("Error: The key in a hash var must to be String. At line: "+ctx.expression(0).getStart().getLine()+
                    " column: "+ ctx.expression(0).getStart().getCharPositionInLine());
            return -1;
        }
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
        this.globalCounterParams++;
        int temp=this.globalCounterParams; //respaldo del valor que tenían los parametros antes de visitar expresion
        String nameTemp= this.arrayName;
        boolean insideLet= this.isInLet;
        // si contiene a una fucion y está en un let
        if(ctx.expression().toStringTree().contains("fn(") | ctx.expression().toStringTree().contains("fn (")){

            if (insideLet){
                //this.fnSpecialTable.insert(globalCounterParams,nameTemp,0);
                temporalObject.setIndex(0);
                this.globalCounterParams = 0;
            }
            else{
                this.errorsList.add("Error: . You can not declare a function in an array if it isn't in a let statement.At line: "+ctx.expression().getStart().getLine()+
                        " column: "+ ctx.expression().getStart().getCharPositionInLine());
            }

        }

        int type1= (Integer) visit(ctx.expression());

        if(type1 == -2){
            this.errorsList.add("Error: . You can not declare a statement in an array. At line: "+ctx.expression().getStart().getLine()+
                    " column: "+ ctx.expression().getStart().getCharPositionInLine());
            return -1;
        }


        this.arrayName=nameTemp; //restart value
        this.globalCounterParams=temp;

        int type2=0;
        if (ctx.moreExpressions().getChildCount()>0){
            this.isInLet= insideLet;
            type2=(Integer) visit(ctx.moreExpressions());
        }
        if(type2 == -2){
            this.errorsList.add("Error: . You can not declare a statement in an array.");
            return -1;
        }
        this.arrayName=null; //reset array name temp

        this.allowChangeInLet=true;

        if (type1==-1 || type2==-1){
            return -1;
        }

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
        boolean insideLet= this.isInLet;
        for(int i = 0; i < ctx.expression().size(); i++){
            this.globalCounterParams++;
            temp=this.globalCounterParams; //respaldo del valor que tenían los parametros antes de visitar expresion

            if(ctx.expression(i).toStringTree().contains("fn(") | ctx.expression(i).toStringTree().contains("fn (")){
                if (insideLet){
                    this.temporalObject.setIndex(i+1);
                    //this.globalCounterParams = 0;
                }
                else{
                    this.errorsList.add("Error: . You can not declare a function in an array if it isn't in a let statement.At line: "+ctx.expression(i).getStart().getLine()+
                            " column: "+ ctx.expression(i).getStart().getCharPositionInLine());
                }
            }
            else{
                if (type == -1){
                    return -1;
                }
            }

            type = (Integer) visit(ctx.expression(i));
            if(type == -2){
                this.errorsList.add("Error: . You can not declare a statement in an array. At line: "+ctx.expression().get(i).getStart().getLine()+
                        " column: "+ ctx.expression().get(i).getStart().getCharPositionInLine());
                return -1;
            }

            this.globalCounterParams=temp; //reestablecimiento del valor previo de global counter params

        }

        this.fnSpecialTable.imprimir();
        return type;
    }

    @Override
    public Object visitPrintExpr_Mky(MonkeyParser.PrintExpr_MkyContext ctx) {

        int type = (Integer) visit(ctx.expression());
        if(type < 0)
            this.errorsList.add("Error: Print error: "+ctx.expression().getStart().getLine()+
                    " column: "+ ctx.expression().getStart().getCharPositionInLine());

        return -2;
    }

    @Override
    public Object visitIfExpr_Mky(MonkeyParser.IfExpr_MkyContext ctx) {
        visit(ctx.expression());
        //requerido abrir un nivel aquí
        for(int i = 0; i < ctx.blockStatement().size(); i++) {
            this.identifierTable.OpenScope();
            this.functionsTable.openScope();
            visit(ctx.blockStatement(i));
            this.identifierTable.closeScope();
            this.functionsTable.closeScope();
            this.identifierTable.imprimir();
            this.functionsTable.imprimir();

        }
        return -2;
    }

    @Override
    public Object visitBlockSt_Mky(MonkeyParser.BlockSt_MkyContext ctx) {


        for(MonkeyParser.StatementContext stm : ctx.statement()){
            visit(stm);
        }
        this.identifierTable.imprimir();


        return -2;
    }
}
