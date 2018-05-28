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


    public dataStorageItem deepSearch(String name){
        int j=this.programData.size()-1;
        //search from the end to the begininn to find the var faster if it's in many levels
        while(j >= 0){
            if (this.programData.get(j).getName().equals(name)){
                return this.programData.get(j);
            }
            j--;
        }
        return null;
    }

    public dataStorageItem addData(String name, Object value,int index,int type,ParserRuleContext ctx){
        dataStorageItem newItem= new dataStorageItem(name,value,index,type,this.currentLevel);
       /*
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
        */

        ((MonkeyParser.LetStatementContext) ctx).storageIndex=this.currentIndex;

        this.programData.add(newItem);
        this.currentIndex++;
        return this.programData.get(0);
    }

    public void openScope(){
        this.currentLevel++;
    }

    public void closeScope() {
        if (this.programData.size() == 0) {
            return;
        }
        int i = 0;
        dataStorageItem element;

        while (i < this.programData.size())
        {
            element = this.programData.get(i);
            if (element.getLevel() == this.currentLevel) {
                this.programData.remove(i);
                i = i - 1;
            }
            i++;
        }
        this.currentLevel--;
    }

    public dataStorageItem getData(int index){
        if (this.programData.size() == 0)
            return null;

        return this.programData.get(index);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void cleanData(){
        while(this.programData.size()!=0) {
            this.programData.pop();
        }
        this.currentIndex=0;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
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

    public void printDataStorage(){
        System.out.println("****** ESTADO DE DATA STORAGE ******\n");
        for (int i = 0; i < this.programData.size(); i++) {
            dataStorageItem item = this.programData.get(i);
            System.out.println(
                    "Name: " + item.getName()+" - "+
                    "Value: " + item.getValue()+" - "+
                    "Type: " + item.getType()+" - "+
                    "Index: " + item.getIndex()+" - "+
                    "Level: " + item.getLevel()
            );
        }
    }

}


