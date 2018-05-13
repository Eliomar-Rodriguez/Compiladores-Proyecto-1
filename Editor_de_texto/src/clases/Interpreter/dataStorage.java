package clases.Interpreter;

import java.util.LinkedList;

public class dataStorage {
    private LinkedList<dataStorageItem> programData;
    private int currentIndex;
    private int currentLevel;


    public dataStorage(){
        this.currentIndex = 0;
        this.currentLevel = 0;
        this.programData= new LinkedList<dataStorageItem>();
    }

    public boolean addData(String name, Object value,int index,int type,int level){
        dataStorageItem newItem= new dataStorageItem(name,value,index,type,level);
        if(this.programData.contains(newItem)){
            return false;
        }
        this.programData.add(newItem);
        return true;
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


