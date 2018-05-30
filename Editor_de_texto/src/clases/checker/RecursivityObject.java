package clases.checker;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class RecursivityObject {
    private Token fnName;
    private int index;
    private ParserRuleContext ctx;

    public RecursivityObject(Token fnName, int index,ParserRuleContext context) {
        this.fnName = fnName;
        this.index = index;
        this.ctx= context;
    }

    public Token getFnName() {
        return fnName;
    }

    public void setFnName(Token fnName) {
        this.fnName = fnName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        index = index;
    }

    public ParserRuleContext getCtx() {
        return ctx;
    }

    public void setCtx(ParserRuleContext ctx) {
        this.ctx = ctx;
    }
}
