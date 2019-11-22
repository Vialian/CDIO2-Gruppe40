package Entity;

public abstract class Tile {
    String name;
    private String text; 
    private String type;

    public Tile(String name, String text)
    {
        this.name = name;
        this.text = text;

    }
    public void landOn(Player pl){

    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }



}
