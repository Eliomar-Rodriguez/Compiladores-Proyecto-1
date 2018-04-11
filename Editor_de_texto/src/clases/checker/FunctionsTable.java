package clases.checker;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class FunctionsTable {
    private LinkedList<FuncTableElement> table;
    private int currentLevel;

    /************** 0 representa tipo neutro **********/

    public FunctionsTable()
    {
        this.currentLevel = -1;
        this.table = new LinkedList<FuncTableElement>();
        this.setDefaultFunctions();
    }

    public void setDefaultFunctions(){
        this.table.push(new FuncTableElement(0, new CommonToken(0,"len"),1,0,null));
        this.table.push(new FuncTableElement(0, new CommonToken(0,"first"),1,0,null));
        this.table.push(new FuncTableElement(0, new CommonToken(0,"last"),1,0,null));
        this.table.push(new FuncTableElement(0, new CommonToken(0,"rest"),1,0,null));
        this.table.push(new FuncTableElement(0, new CommonToken(0,"push"),1,0,null));
        this.table.push(new FuncTableElement(0, new CommonToken(0,"puts"),1,0,null));
    }

    public FuncTableElement insert(Token token, int paramsNumber, int returnType, ParserRuleContext declaracion)
    {
        FuncTableElement elem = new FuncTableElement(this.currentLevel,token,paramsNumber,returnType,declaracion);
        int j = 0;
        while (j < this.table.size() && this.table.get(j).getLevel() == this.currentLevel) {
            if (this.table.get(j).getToken().getText().equals(token.getText())) {
                System.out.println("El identificador \"" + token.getText() + "\" ya ha sido declarado. Line " + token.getLine() + ":" + token.getCharPositionInLine());
                return null;
            }
            j++;
        }
        table.push(elem); //tabla estilo pila
        return this.table.get(0); //retornar el elemento recién insertado

    }

    public void openScope(){
        this.currentLevel++;
    }

    public void closeScope(){
        FuncTableElement element = this.table.get(0);
        while (element != null && element.getLevel() == this.currentLevel){
            table.pop();
            if(!this.table.isEmpty())
                element = this.table.get(0);
            else
                element= null;
        }
        this.currentLevel--;
    }

    /**
     *
     * @param nombre: nombre del identifcador de la función
     * @return
     */
    public FuncTableElement buscar(String nombre)
    {
        FuncTableElement temp=null;
        for(FuncTableElement elem : this.table)
            if (elem.getToken().getText().equals(nombre)) {
                temp = elem;
                break;
            }
        return temp;
    }

}
