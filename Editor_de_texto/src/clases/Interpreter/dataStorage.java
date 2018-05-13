package clases.Interpreter;

import generated.MonkeyParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;

public class dataStorage {
    private LinkedList<dataStorageItem> programData;
    private int currentIndex;
    private int currentLevel;


    public dataStorage(){
        this.currentIndex = 0;
        this.currentLevel = -1;
        this.programData= new LinkedList<dataStorageItem>();
    }

    public dataStorageItem addData(String name, Object value,int index,int type,ParserRuleContext ctx){
        dataStorageItem newItem= new dataStorageItem(name,value,index,type,this.currentLevel);
        int j=0;
        while( j <this.programData.size() && this.programData.get(j).getLevel()== this.currentLevel ){
            //if the var is already declared change the value and the type if it's necessary
            if (this.programData.get(j).getName().equals(name)){
                this.programData.get(j).setValue(value);
                this.programData.get(j).setType(type);
                return null;
            }
            j++;
        }

        ((MonkeyParser.LetStatementContext) ctx).storageIndex=this.currentIndex;

        this.programData.add(newItem);
        this.currentIndex++;
        return this.programData.get(0);

    }



    public void openScope(){
        this.currentLevel++;
    }

    public void closeScope(){
        if (this.programData.size()==0){
            return;
        }
        int i=0;
        int size= this.programData.size();
        dataStorageItem element;
        while( i < size){
            element= this.programData.get(i);
            if (element.getLevel()== this.currentLevel){
                this.programData.remove(i);
                i=i-1;
            }
            i++;

        }
        this.currentLevel--;

    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public dataStorageItem getData(int index){
        if (this.programData.get(index) == null)
            return null;

        return this.programData.get(index);
    }

    public void cleanData(){
        while(this.programData.size()!=0) {
            this.programData.pop();
        }
        this.currentIndex=0;
    }

    public int getCurrentStorageIndex(){
        return this.currentIndex;
    }
    public LinkedList<dataStorageItem> getProgramData() {
        return this.programData;
    }


    public String toString() {
        String message = new String("");
        message += "****** ESTADO DE DATA STORAGE ******\n";
        if (!this.programData.isEmpty()) {
            for (dataStorageItem i : this.programData) {
                message += i.getName() + " --> " + i.getValue()+" ,"+ i.getType() +" ," + i.getIndex() +" ," + i.getLevel() + "\n";
            }
            message += "------------------------------------------";
        } else
            message += "Data Storage is Empty!!";
        return message;
    }

}


