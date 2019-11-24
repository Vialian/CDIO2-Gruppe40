package Entity;

public class ChanceTile extends Tile  {
    public ChanceTile(String name, String text, String colour) {
        super(name, text, colour);
    }

    @Override
    public void landOn(Player pl) {
        ChanceDeck.drawCard(); // Vi skal fidne ud af hvordan vi vil hente kort.
    }
}
