package Entity;

public class Board {
    private Tile[] boardTiles;

    public Tile[] getBoard(){
        return boardTiles;
    }

    public void addUseless(int position, String name, String description, String color){
        boardTiles[position] = new UselessTile(name, description, color);
    }

    public void addImprisonment(int position, String name, String description, String color, int sendTo){
        boardTiles[position] = new JailTile(name, description, color, sendTo);
    }

    public void addProperty(int position, String name, String description, int price, String color){
        boardTiles[position] = new PropertyTile(name, description, price, color);
    }

    public void addChance(int[] positions, String name, String color){
        ChanceTile tile = new ChanceTile(name, "Prøv lykken", color);
        for (int position : positions){
            boardTiles[position] = 	tile;
        }
    }
    public Tile getTile(int position) {
        return boardTiles[position];
    }

    public int getTilePos(Tile tile){
        String name = tile.getName();
        Tile br[] = getBoard();
        for(int i =0; i <= br.length; i++){
            if(name.equals(br[i].getName()))
                return i;
        }
        return 0;// nok ikke den bedste måde at gøre det på
    }

    public int getFieldCost (int pos){
        PropertyTile tile = (PropertyTile)getTile(pos);
        return tile.getCost();
    }

    public Board(){//spørg hvor det bør ligge, bør controller gøre det her.
        boardTiles = new Tile[24];
        addUseless(0,"Go","When you pass Go you gain 1 Dollar.", "WHITE");
        addUseless(6,"Jail", "This is where the hardest criminals are kept", "WHITE");
        addUseless(12,"Free Parking", "Just take a break.", "WHITE");
        addImprisonment(18,"Go To Jail", "Stop! You violated the law. Pay the court a fine or serve your sentence. Your stolen goods are now forfeit.", "WHITE", 6);

        addProperty(1,"The Burgerbar","This is where you get your burgers", 1, "GREEN");
        addProperty(2,"The Pizzeria","This is where you get pizza pies", 1, "GREEN");
        addProperty(4,"The Candyshop","This is where you get your candy", 1, "LIGHTBLUE");
        addProperty(5,"The Ice Cream Shop", "This is where you get ice cream", 1, "LIGHTBLUE");

        addProperty(7,"The Museum","This is where you look at old stuff", 2, "PINK");
        addProperty(8,"The Library","This is where you get your knowledge", 2, "PINK");
        addProperty(10,"The Skatepark","This is where you get your grind on", 2, "YELLOW");
        addProperty(11,"The Swimmingpool","This is where you get to swim", 2, "YELLOW");

        addProperty(13,"The Casino","This is where you lose your money", 3, "RED");
        addProperty(14,"The Cinema","This is where you watch movies", 3, "RED");
        addProperty(16,"The Toy Store","This is where you get your toys", 3, "BROWN");
        addProperty(17,"The Pet Store","This is where you get your pets", 3, "BROWN");

        addProperty(19,"The Bowling Alley","This is where you bowl", 4, "GREEN");
        addProperty(20,"The Zoo","This is where you watch animals", 4, "GREEN");
        addProperty(22,"The Waterpark","This is where you try fun rides", 4, "BLUE");
        addProperty(23,"The Beach","This is where you get tanned", 4, "BLUE");

        addChance(new int[]{3, 9, 15, 21},"Chance", "WHITE");
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
            if (tile.getColour().equals(color)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    public int getNextTileColor2(String color1, String color2) {
        int index = 0;
        for (Tile tile: boardTiles) {
            if (tile.getColour().equals(color1) || tile.getName().equals(color2)) {
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
            return pTile.getOwnedBy() == 0;
        }
        return false;
    }
}