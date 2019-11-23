package Entity;

public abstract class Tile {
    private String name;
    private String text; // vil vi have text til felterne ?
    private String type;

    public Tile(String name, String text)
    {
        this.name = name;
        this.text = text;

    }
    public  String landOn(){
        return type;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
