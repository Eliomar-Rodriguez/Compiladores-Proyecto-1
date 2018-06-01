package clases.Interpreter;


/*
IT can't extends from Exception because there are problems with generated classes
 */
public class InterpreterException extends NullPointerException{

    public InterpreterException(String s) {
        super(s);
    }
}
