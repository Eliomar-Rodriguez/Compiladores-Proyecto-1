package clases.checker;


import org.antlr.v4.runtime.Token;

public class RecursivityObject {
    private Token fnName;
    private int Index;

    public RecursivityObject(Token fnName, int index) {
        this.fnName = fnName;
        Index = index;
    }

    public Token getFnName() {
        return fnName;
    }

    public void setFnName(Token fnName) {
        this.fnName = fnName;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
}
