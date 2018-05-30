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
    public int size(){
        return this.Stack.size();
    }


    public String  toString (){
        String message = new String("");
        message += "****** ESTADO DE Stack ******\n";
        if (!this.Stack.isEmpty()) {
            for (ProgramStackElement i : this.Stack) {
                message += i.getValue() + " --> " + i.getType() + "\n";
            }
            message += "------------------------------------------";
        } else
            message += "Data Storage is Empty!!";
        return message;
    }
}
