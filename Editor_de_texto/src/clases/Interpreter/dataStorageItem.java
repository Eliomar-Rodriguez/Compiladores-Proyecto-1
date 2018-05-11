package clases.Interpreter;

public class dataStorageItem {
    private String name;
    private Object value;
    private int index;
    private int type;
    private int level;

    public dataStorageItem(String name, Object value, int index, int type, int level) {
        this.name = name;
        this.value = value;
        this.index = index;
        this.type = type;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
