package clases.Interpreter;

import java.util.LinkedList;

public class dataStorage {
    private LinkedList<dataStorageItem> programData;
    private int currentIndex;


    public dataStorage(){
        this.currentIndex=0;
        this.programData= new LinkedList<dataStorageItem>();
    }

    public void addData(String name, Object value,int index,int type,int level){
        dataStorageItem newItem= new dataStorageItem(name,value,index,type,level);
        this.programData.add(newItem);

    }

    public dataStorageItem getData(int index){
        return this.programData.ge
    }


}


    public Value getData(int index) {
        return this.data.get(index);
    }

    public Value getData(String name) {
        //se debe buscar en la tabla pero por nombre
        return null;
    }

    public void cleanData(){
        while (this.data.size()!=0)
            this.data.pop();
        this.actualIndex=0;
    }

    public String toString() {
        String message = new String("");
        message += "****** ESTADO DE DATA STORAGE ******\n";
        if (!this.data.isEmpty()) {
            for (Value i : this.data) {
                message += i.name + " --> " + i.value.toString() + "\n";
            }
            message += "------------------------------------------";
        } else
            message += "Data Storage is Empty!!";
        return message;
    }

    public int getActualStorageIndex(){
        return this.actualIndex;
    }
    public LinkedList<Value> getData() {
        return this.data;
    }

}
