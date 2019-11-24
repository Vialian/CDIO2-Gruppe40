package Entity;

public abstract class Tile {
    private String name;
    private String text;
    private String type;
    private String colour;
    private final boolean meIsProperty = false;

    public Tile(String name, String text, String colour)
    {
        this.name = name;
        this.text = text;
        this.colour = colour;

    }
    public void landOn(Player pl){

    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public String getColour() {
        return this.colour;
    }

    public Boolean isProperty() {
        return this.meIsProperty;
    }
}
