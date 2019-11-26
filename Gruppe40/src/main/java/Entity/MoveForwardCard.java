package Entity;

import Control.DiceGame;
import gui_main.GUI;

public class MoveForwardCard extends ChanceCard{
    private int move;

    public void setMove(int move){
        this.move=move;
    }

    public int getMove(){
        return move;
    }
    public MoveForwardCard(/*int move*/){
        setMove(move);
    }

    public void onDraw(int numberOfMove, Player pl, Board br, GUI gui, DiceGame game) {

        pl.addToPos(numberOfMove,br.getBoard().length);
        Tile tile = br.getTile(pl.getCurrentTile());
        tile.landOn(pl,gui,br,game);

    }
    @Override
    public String toString() {
        return "____________";
    }
}
