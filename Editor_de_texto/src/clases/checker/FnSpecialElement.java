package clases.checker;

import org.antlr.v4.runtime.ParserRuleContext;

public class FnSpecialElement {
    private int paramsNumber;
    private String arrayName;
    private int level;
    private int index;
    private ParserRuleContext declaration;

    public FnSpecialElement(int paramsNumber, String arrayName, int index, int level,ParserRuleContext ctx) {
        this.paramsNumber = paramsNumber;
        this.arrayName = arrayName;
        this.index = index;
        this.level = level;
        this.declaration=ctx;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParamsNumber() {
        return paramsNumber;
    }

    public void setParamsNumber(int paramsNumber) {
        this.paramsNumber = paramsNumber;
    }

    public String getArrayName() {
        return arrayName;
    }

    public void setArrayName(String arrayName) {
        this.arrayName = arrayName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
