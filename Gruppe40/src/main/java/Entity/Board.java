package Entity;

public class Board {

    Tile tiles[];
    int tileCount = 0;

    public Board(int num)
    {
          this.tiles = new Tile[num];
    }

    public Board() {

    }

    public Tile getTile(int i) {
        if (tiles.length > i)
            return tiles[i];
        else
            return null;
    }

    public int getTileCost(int t){
        for(propetyTile x : tiles){
            if(t == x.getNr){
                return  x.getCost();
            }
        }
    }

    public void addTile(String _name, String _text, int _money)
    {
        if (tiles.length >= tileCount) {
            tiles[tileCount] = new Tile(_name, _text, _money);
            tileCount++;
        }
    }
}