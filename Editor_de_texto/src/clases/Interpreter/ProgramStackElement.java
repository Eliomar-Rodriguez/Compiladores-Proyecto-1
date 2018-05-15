package clases.Interpreter;

public class ProgramStackElement {

    private Object value;
    private int type;

    public ProgramStackElement(Object value, int type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString(){
        return this.value+ " "+ this.type;
    }
}
