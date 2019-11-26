package Entity;

import Control.DiceGame;
import gui_main.GUI;

public class JailTile extends Tile {


    private int sendTo = 0;

    public JailTile(String name, String text, String colour, int sendTo) {
        super(name, text, colour);
        this.sendTo = sendTo;
    }

    public void landOn(Player player, GUI gui, Board board, DiceGame game) {
        if(sendTo!= 0){
            player.setCurrentTile(sendTo);
            player.setInJail(true);
            //boardet skal updaters efter i diceGame Klassen
        }
    }
}
