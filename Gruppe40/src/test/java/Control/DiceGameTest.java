package Control;

import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceGameTest {
    GUI gui = new GUI();
    DiceGame game = new DiceGame(gui);
    @Test
    public void findPlayer() {

        assertEquals(game.getPlayers()[1], game.findPlayer(2));
        assertEquals(game.getPlayers()[0], game.findPlayer(1));
        assertNull(game.findPlayer(3));
    }

    @Test
    public void getBoard() {
    }

    @Test
    public void playDiceGame() {
    }

    @Test
    public void colourPair() {
    }
}