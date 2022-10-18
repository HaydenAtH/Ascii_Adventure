package PlayerService;

public class Attribute {
    protected String name;
    protected int value;

    public Attribute(String name, int value){
        this.name = name;
        this.value = value;
    }

    // Declarative/Modification Functions

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void modValue(int modVal){
        this.value += modVal;
    }

    // Query Functions

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
