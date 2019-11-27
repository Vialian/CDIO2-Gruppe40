package Entity;

import Control.DiceGame;
import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveForwardCardTest {

    int[] moves = {3,6,5,3,2,1,2,4,4,0};
    MoveForwardCard card = new MoveForwardCard();
    @Test
    public void setGetMove() {
        for(int move : moves) {
            card.setMove(move);
            assertEquals(move, card.getMove());
        }
    }

    @Test
    public void onDraw() {
        int totalmove = 0;
        GUI gui = new GUI();
        DiceGame game = new DiceGame(gui);
        Player player = game.findPlayer(1);
        for(int move : moves) {
            totalmove += move;
            totalmove = totalmove%24;
            card.setMove(move);
            card.onDraw(player,game.getBoard(),gui,game);

            assertEquals(totalmove, player.getCurrentTile());
        }
    }
}