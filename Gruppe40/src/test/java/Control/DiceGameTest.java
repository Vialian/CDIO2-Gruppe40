package Control;

import Entity.Player;
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
    public void playDiceGame() {
        Player player = game.findPlayer(1);
        player.setInJail(true);
        game.playDiceGameStump(0,2);
        assertTrue(!player.isInJail());
        assertEquals(19,player.getMoney() );
    }

    @Test
    public void colourPair() {
    }
}