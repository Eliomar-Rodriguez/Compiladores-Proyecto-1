package clases.checker;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class FnSpecialTable {
    private LinkedList<FnSpecialElement> FnSpecialTable;
    private int currentLevel;

    public FnSpecialTable() {
        this.FnSpecialTable = new LinkedList<FnSpecialElement>();
        this.currentLevel = 0;
    }

    public FnSpecialElement insert(int paramsNumber, String arrayName, int index) {
        FnSpecialElement elem = new FnSpecialElement(paramsNumber,arrayName,index, currentLevel);

        int j = 0;
        while (j < this.FnSpecialTable.size() && this.FnSpecialTable.get(j).getLevel() == this.currentLevel) {
            if (this.FnSpecialTable.get(j).getArrayName().equals(arrayName)) {
                return null;
            }
            j++;
        }

        FnSpecialTable.push(elem); //tabla estilo pila
        return this.FnSpecialTable.get(0); //retornar el elemento recién insertado
    }

    public FnSpecialElement buscar(int index, String arrayName){
        FnSpecialElement element = null;

        for (int i = 0; i < this.FnSpecialTable.size(); i++){
            if (this.FnSpecialTable.get(i).getArrayName().equals(arrayName) & this.FnSpecialTable.get(i).getIndex() == index)
                element = this.FnSpecialTable.get(i);
        }
        return element;
    }

    public void openScope(){
        this.currentLevel++;
    }

    public void closeScope(){
        if (this.FnSpecialTable.size()==0){
            return;
        }
        FnSpecialElement element = this.FnSpecialTable.get(0);
        while (element != null && element.getLevel() == this.currentLevel){
            FnSpecialTable.pop();
            if(!this.FnSpecialTable.isEmpty())
                element = this.FnSpecialTable.get(0);
            else
                element= null;
        }
        this.currentLevel--;
    }

    //return if an array have a function with the same number of params
    public boolean arrayHaveFunctions(int params, String arrayName){

        for (int i = 0; i < this.FnSpecialTable.size(); i++){
            if (this.FnSpecialTable.get(i).getArrayName().equals(arrayName) & this.FnSpecialTable.get(i).getParamsNumber()==params){
                return true;
            }

        }
        return false;
    }

    //it might be neccesary to have a function that can identify if an array have functions related with the array

    public void imprimir() {
        System.out.println("****** ESTADO DE TABLA DE SÍMBOLOS PARA FUNCIONES ESPECIALES ******");
        if (!this.FnSpecialTable.isEmpty()) {
            for (FnSpecialElement i : this.FnSpecialTable) {
                System.out.println("Array Name: " + i.getArrayName() + " - Array position: " + i.getIndex()+ " - Parameters number: "+i.getParamsNumber() +" - Level: " +i.getLevel());
            }
            System.out.println("------------------------------------------");
        }
        else
            System.out.println("Tabla vacía");
    }

    public boolean deleteElement(String name){
        for (int i = 0; i < this.FnSpecialTable.size(); i++) {
            if(this.FnSpecialTable.get(i).getArrayName().equals(name)){
                this.FnSpecialTable.remove(i);
                return true;
            }
        }
        return false;
    }
}