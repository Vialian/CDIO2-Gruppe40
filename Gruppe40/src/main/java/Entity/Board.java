package Entity;

public class Board {
    private Tile[] boardTiles;

    public Tile[] getBoard(){
        return boardTiles;
    }

    public void addUseless(int position, String name, String description){
        boardTiles[position] = new UselessTile(name, description);
    }

    public void addImprisonment(int position, String name, String description){
        boardTiles[position] = new JailTile(name, description);
    }

    public void addProperty(int position, String name, String description, int price, String color){
        boardTiles[position] = new PropertyTile(name, description, price, color);
    }

    public void addChance(int[] positions, String name){
        ChanceTile tile = new ChanceTile(name, "Prøv lykken");
        for (int position : positions){
            boardTiles[position] = 	tile;
        }
    }
    public Tile getTile(int position) {
        return boardTiles[position];
    }

    public Board(){//spørg hvor det bør ligge, bør controller gøre det her.
        boardTiles = new Tile[24];
        addUseless(0,"Go","When you pass Go you gain 1 Dollar.");
        addUseless(6,"Jail", "This is where the hardest criminals are kept");
        addUseless(12,"Free Parking", "Just take a break.");
        addImprisonment(18,"Go To Jail", "Stop! You violated the law. Pay the court a fine or serve your sentence. Your stolen goods are now forfeit.");

        addProperty(1,"The Burgerbar","This is where you get your burgers", 1, "GRØN");
        addProperty(2,"The Pizzeria","This is where you get pizza pies", 1, "GRØN");
        addProperty(4,"The Candyshop","This is where you get your candy", 1, "LYSEBLÅ");
        addProperty(5,"The Ice Cream Shop", "This is where you get ice cream", 1, "LYSEBLÅ");

        addProperty(7,"The Museum","This is where you look at old stuff", 2, "PINK");
        addProperty(8,"The Library","This is where you get your knowledge", 2, "PINK");
        addProperty(10,"The Skatepark","This is where you get your grind on", 2, "GUL");
        addProperty(11,"The Swimmingpool","This is where you get to swim", 2, "GUL");

        addProperty(13,"The Casino","This is where you lose your money", 3, "RØD");
        addProperty(14,"The Cinema","This is where you watch movies", 3, "RØD");
        addProperty(16,"The Toy Store","This is where you get your toys", 3, "BRUN");
        addProperty(17,"The Pet Store","This is where you get your pets", 3, "BRUN");

        addProperty(19,"The Bowling Alley","This is where you bowl", 4, "GRØN");
        addProperty(20,"The Zoo","This is where you watch animals", 4, "GRØN");
        addProperty(22,"The Waterpark","This is where you try fun rides", 4, "BLÅ");
        addProperty(23,"The Beach","This is where you get tanned", 4, "BLÅ");

        addChance(new int[]{3, 9, 15, 21},"Chance");
    }
// kan bruges til at udvide bordet i fremtiden,
//hvor den tager en int, hvor bordet vil have size tiles
    public Board(int size){
        boardTiles = new Tile[size];
    }

    public String toString(){//Find ud af hvad der skal udskrives
        String result = "tiles: {";
        for (Tile tile: boardTiles) {
            result += "," + tile.toString();
        }
        result += "};";
        return result;
    }

    public int getNextTileColor(String color) {
        int index = 0;
        for (Tile tile: boardTiles) {
            if (tile.getColor().equals(color)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    public int getNextTileColor2(String color1, String color2) {
        int index = 0;
        for (Tile tile: boardTiles) {
            if (tile.getColor().equals(color1) || tile.getName().equals(color2)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    public int getNextTileName(String name) {
        int index = 0;
        for (Tile tile: boardTiles) {
            if (tile.getName().equals(name)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    public Boolean isVacant(int index) {
        Tile tile = getTile(index);
        if (tile.isProperty()) {
            PropertyTile pTile = (PropertyTile) tile;
            return pTile.getOwnedBy().equals("None");
        }
        return false;
    }
}