package clases.Interpreter;

public class Frame {
    private dataStorage storage;
    private ProgramStack stack;
    private int level;
    Frame previousFrame;

    public Frame(int frameLevel){
        this.storage=new dataStorage();
        this.stack=new ProgramStack();
        this.level=  frameLevel;
        this.previousFrame=null;
    }

    public Frame getPreviousFrame() {
        return previousFrame;
    }

    public void setPreviousFrame(Frame previousFrame) {
        this.previousFrame = previousFrame;
    }

    public dataStorage getStorage() {
        return storage;
    }

    public void setStorage(dataStorage storage) {
        this.storage = storage;
    }

    public ProgramStack getStack() {
        return stack;
    }

    public void setStack(ProgramStack stack) {
        this.stack = stack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
