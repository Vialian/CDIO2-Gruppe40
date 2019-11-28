package Entity;

import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceDeckTest {
    GUI gui = new GUI();
    DiceGame diceGame = new DiceGame(gui);
    Board board = new Board();
    Player player = new Player("Ben",20,1);
    ChanceDeck chanceDeck = new ChanceDeck();

    @Test
    public void drawCard() {
        ChanceDeck chanceDeck = new ChanceDeck();

        for (int i = 0; i < 20; i++) {
            assertEquals(i, chanceDeck.getIndex());
            chanceDeck.drawCard();

        }


    }
    @Test
    public void mixDeck() {

        chanceDeck.mixDeck();
        int[] mix = new int[chanceDeck.getDeck().length];
        int[] mix2 = new int[chanceDeck.getDeck().length];

        chanceDeck.mixDeck();
                int i = 0, j = 0;
                    for (int mixing: chanceDeck.getDeck()) {
                        mix[i] = mixing;
//                        System.out.println(mix[i]);
                        i++;
                    }
                for (int k = 0; k < 1; k++) {
                    boolean passed = false;

                    chanceDeck.mixDeck();
                    for (int mixing: chanceDeck.getDeck()) {
                        mix[j] = mixing;
//                        System.out.println("test " + mix[j]);
                        if(mix[j] != mix2[j])
                        {
                            passed = true;
                        }
                        assertTrue(passed);
                        j++;
                    }
                }
    }

//    @Test
//    public void moveBuyTile() {
//    int pos = 5;
//    chanceDeck.moveBuyTile(pos,player,board, gui, diceGame);
//        System.out.println("test " + player.getCurrentTile() + " or " + pos);
//    assertEquals(pos, player.getCurrentTile());
//    }
//
//    @Test
//    public void performAction() {
//        chanceDeck.performAction(5,player,gui,board,diceGame);
//
//    }

    @Test
    public void getDeck() {
        int[] initDeck = new int[10];
        for (int i = 0; i < initDeck.length ; i++) {
            initDeck[i] = i;
        }
        chanceDeck.setDeck(initDeck);
        for (int i = 0; i < initDeck.length; i++) {
            assertEquals(chanceDeck.getDeck()[i], initDeck[i]);

        }
    }
    @Test
    public void setDeck() {
        int[] initDeck = new int[10];
        for (int i = 0; i < initDeck.length ; i++) {
            initDeck[i] = i;
        }
        chanceDeck.setDeck(initDeck);
        for (int i = 0; i < initDeck.length; i++) {
            assertEquals(chanceDeck.getDeck()[i], initDeck[i]);

        }

    }

    @Test
    public void getIndex() {
        int setint = 5;
        chanceDeck.setIndex(setint);
        assertEquals(chanceDeck.getIndex(), setint);
    }
    @Test
    public void setIndex() {
        int setint = 5;
        chanceDeck.setIndex(setint);
        assertEquals(chanceDeck.getIndex(), setint);
    }

}