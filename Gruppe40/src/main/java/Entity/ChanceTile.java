package Entity;

public class ChanceTile extends Tile  {
    public ChanceTile(String name, String text) {
        super(name, text);
    }

    @Override
    public void landOn(Player pl) {
        ChanceDeck.drawCard(); // Vi skal fidne ud af hvordan vi vil hente kort.
    }
}
