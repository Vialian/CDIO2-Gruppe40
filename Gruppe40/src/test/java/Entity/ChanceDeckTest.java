package Entity;

import Control.DiceGame;
import gui_main.GUI;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ChanceDeckTest {

    @Test
    public void drawCard() {
        ChanceDeck chanceDeck = new ChanceDeck();
        Player player = new Player("Ben",20,1);
        GUI gui = new GUI();
        Board board = new Board();
        DiceGame diceGame = new DiceGame();

        assertEquals(0, chanceDeck.getIndex());

        chanceDeck.drawCard(player, gui, board, diceGame);
        assertEquals(1, chanceDeck.getIndex());
    }
    @Test
    public void mixDeck() {
        ChanceDeck chanceDeck = new ChanceDeck();
        int[] mix = chanceDeck.getDeck();
        int[] mix2 = chanceDeck.getDeck();

        chanceDeck.mixDeck();
        int i = 0, j = 0;
            for (int mixing: chanceDeck.getDeck()) {
                mix[i] = mixing;
//                System.out.println(mix[i]);
                i++;
            }
        for (int k = 0; k < 100; k++) {
            chanceDeck.mixDeck();
            for (int mixing: chanceDeck.getDeck()) {
                mix[j] = mixing;
//                System.out.println("test " + mix[j]);
                j++;
            }
            assertFalse(Arrays.equals(mix, mix2));
        }



    }
}