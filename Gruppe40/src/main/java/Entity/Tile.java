package Entity;

public abstract class Tile {
    private String name;
    private String text;
    private String type;
    private String color;
    private Boolean meIsProperty = false;

    public Tile(String name, String text, String color)
    {
        this.name = name;
        this.text = text;
        this.color = color;

    }
    public void landOn(Player pl){

    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public String getColor() {
        return this.color;
    }

    public Boolean isProperty() {
        return this.meIsProperty;
    }
}
