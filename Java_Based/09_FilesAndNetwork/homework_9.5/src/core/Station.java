package core;

public class Station {
    private String lineNumber;
    private String name;

    public Station(String lineNumber, String name){
        this.lineNumber = lineNumber;
        this.name = name;
    }


    public String getLine() {
        return lineNumber;
    }

    public String getName() {
        return name;
    }
}
