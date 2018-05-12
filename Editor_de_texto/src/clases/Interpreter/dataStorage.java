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


