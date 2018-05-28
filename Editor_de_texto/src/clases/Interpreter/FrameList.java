package clases.Interpreter;

import generated.MonkeyParser;

import java.util.ArrayList;

public class FrameList {

    private ArrayList<Frame> programFrames;
    private int currentFrame;

    public FrameList (){
        this.programFrames= new ArrayList<Frame>();
        currentFrame=0;
        Frame frame= new Frame(0,"none");
        frame.setPreviousFrame(null);
        this.programFrames.add(frame);
    }

    public Frame getCurrentProgramFrame(){
        return this.programFrames.get(currentFrame);
    }

    //type is for receive function type
    public boolean insertFrame(Frame newFrame, MonkeyParser.Id_MkyContext id,int type){

        Frame tempFrame= this.programFrames.get(this.currentFrame);
        dataStorage tempStorage;
        int storageIndex;
        while (tempFrame!=null){
            tempStorage= tempFrame.getStorage();
            storageIndex= ((MonkeyParser.LetStatementContext) id.decl).storageIndex;

            /**
             * check if the datastorage contain the id position, ann then check if the frame's datastorage
             * contains at the storage index position a function with the same name that the specified by id token
             * only evaluate for frames that have a level's value of one less or more than the new frame
             */
            if (tempFrame.getLevel() < newFrame.getLevel()) {
                if ((tempStorage.getProgramData().size() > storageIndex)
                        &&
                        ( (tempStorage.getProgramData().get(storageIndex).getType()== type)&&
                                (tempStorage.getProgramData().get(storageIndex).getName().equals(id.getText()) == true)))
                {
                    newFrame.setPreviousFrame(tempFrame);
                    this.programFrames.add(newFrame); //add frame to the list
                    this.currentFrame++; // set the new frame as current frame
                    return true;
                }
            }
            //continue looking for the parent frame
            tempFrame= tempFrame.previousFrame;


        }

        return false;
    }

    public dataStorageItem searchElement(MonkeyParser.Id_MkyContext id){
        Frame tempFrame= programFrames.get(this.currentFrame);
        dataStorage tempStorage;
        int storageIndex;
        while (tempFrame!=null){
            tempStorage= tempFrame.getStorage();
            //if it's a var inside a function
            if (tempFrame.getPreviousFrame() != null)
            {
                   dataStorageItem element= tempStorage.deepSearch(id.getText());
                   if (element!=null){
                       return element;
                   }
            }
            else {


                //fail for search a function params value
                storageIndex = ((MonkeyParser.LetStatementContext) id.decl).storageIndex;

                /**
                 * check if the datastorage contain the id position, ann then check if the frame's datastorage
                 * contains at the storage index position a var with the same name that the specified by id token
                 */
                if ((tempStorage.getProgramData().size() > storageIndex)
                        &&
                        (tempStorage.getProgramData().get(storageIndex).getName().equals(id.getText()) == true))

                {
                    return tempStorage.getData(storageIndex);

                }
            }
            //continue looking for the parent frame
            tempFrame= tempFrame.previousFrame;


        }
        return null;
    }

    public void deleteCurrentFrame(){

        Frame temp= this.programFrames.get(this.currentFrame);
        //if the function return something
        if (temp.getStack().size() > 0){

            // if it's not the main frame
            //there is at least more than one frame
            if (this.currentFrame> 0){
                // add the element to the previous frame stack
                this.programFrames.get(this.currentFrame-1).getStack().push(temp.getStack().pop());
            }

        }
        this.programFrames.remove(this.currentFrame);
        //set to the previous frame
        this.currentFrame--;

    }

}
