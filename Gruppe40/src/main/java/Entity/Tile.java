package Entity;

public abstract class Tile {
    private String name;
    private String text;
    private String type;
    private int colour;
    private final Boolean meIsProperty = false;

    public Tile(String name, String text, int colour)
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

    public int getColor() {
        return this.colour;
    }

    public Boolean isProperty() {
        return this.meIsProperty;
    }
}
 