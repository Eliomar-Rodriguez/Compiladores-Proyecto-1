package clases.Interpreter;

import java.util.LinkedList;

public class ProgramStack {
    private LinkedList<ProgramStackElement> Stack;

    public ProgramStack() {
        this.Stack = new LinkedList<ProgramStackElement>();
    }

    public ProgramStackElement pop(){
        return this.Stack.pop();
    }

    public void push(ProgramStackElement el){
        this.Stack.push(el);
    }
}
