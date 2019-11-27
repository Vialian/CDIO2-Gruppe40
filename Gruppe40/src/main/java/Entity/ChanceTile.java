package Entity;

import Control.DiceGame;
import gui_main.GUI;

public class ChanceTile extends Tile  {
    ChanceDeck chanceDeck;
    public ChanceTile(String name, String text, String colour) {
        super(name, text, colour);
        chanceDeck = new ChanceDeck();
    }

    public void landOn(Player player, GUI gui, Board board, DiceGame game ) {
        chanceDeck.drawCard(player, gui, board, game); // Vi skal fidne ud af hvordan vi vil hente kort.
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
