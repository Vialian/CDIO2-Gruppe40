package Entity;

import Control.DiceGame;
import gui_main.GUI;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ChanceDeckTest {
    GUI gui = new GUI();
    DiceGame diceGame = new DiceGame(gui);
    Board board = new Board();
    Player player = new Player("Ben",20,1);

    @Test
    public void drawCard() {
        ChanceDeck chanceDeck = new ChanceDeck();

        for (int i = 0; i < 20; i++) {
            assertEquals(i, chanceDeck.getIndex());
            chanceDeck.drawCard(player, gui, board, diceGame);

        }


    }
    @Test
    public void mixDeck() {
        ChanceDeck chanceDeck = new ChanceDeck();
        chanceDeck.mixDeck();
        int[] mix = new int[chanceDeck.getDeck().length];
        int[] mix2 = new int[chanceDeck.getDeck().length];

        chanceDeck.mixDeck();
                int i = 0, j = 0;
                    for (int mixing: chanceDeck.getDeck()) {
                        mix[i] = mixing;
                        System.out.println(mix[i]);
                        i++;
                    }
                for (int k = 0; k < 1; k++) {
                    boolean passed = false;

                    chanceDeck.mixDeck();
                    for (int mixing: chanceDeck.getDeck()) {
                        mix[j] = mixing;
                        System.out.println("test " + mix[j]);
                        if(mix[j] != mix2[j])
                        {
                            passed = true;
                        }
                        assertTrue(passed);
                        j++;
                    }
                }
    }

    @Test
    public void moveBuyTile() {
    ChanceDeck chanceDeck = new ChanceDeck();
    int pos = 5;
    chanceDeck.moveBuyTile(pos,player,board, gui, diceGame);
        System.out.println("test " + player.getCurrentTile() + " or " + pos);
    assertEquals(pos, player.getCurrentTile());
    }
}