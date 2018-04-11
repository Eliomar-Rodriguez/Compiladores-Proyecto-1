package clases.checker;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class FuncTableElement {
    private int level;
    private Token token;
    private int paramsNumber;
    private int returnType;
    private ParserRuleContext declaration;

    public FuncTableElement (int level, Token t, int paramsN, int returnT,ParserRuleContext ctx){
        this.level=level;
        this.token=t;
        this.paramsNumber= paramsN;
        this.returnType= returnT;
        this.declaration=ctx;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getParamsNumber() {
        return paramsNumber;
    }

    public void setParamsNumber(int paramsNumber) {
        this.paramsNumber = paramsNumber;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public ParserRuleContext getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ParserRuleContext declaration) {
        this.declaration = declaration;
    }
}
