package clases.checker;

public class FnSpecialElement {
    private int paramsNumber;
    private String arrayName;
    private int level;
    private int index;

    public FnSpecialElement(int paramsNumber, String arrayName, int index, int level) {
        this.paramsNumber = paramsNumber;
        this.arrayName = arrayName;
        this.index = index;
        this.level = level;
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
