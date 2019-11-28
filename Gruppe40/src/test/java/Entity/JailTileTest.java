package Entity;

import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class JailTileTest {
    private int sendTo = 0;
    JailTile jailTile;
    Board board;
    GUI gui;
    Player player;
    DiceGame diceGame;
//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//        diceGame = new DiceGame();
//        player = new Player("test", 2,1);
//        gui = new GUI();
//        board = new Board();
//        jailTile = new JailTile("Jail","You are in jail","GREY",6);
//
//    }

    @Test
    public void landOn() {
        GUI gui = new GUI();
        DiceGame game = new DiceGame(gui);
        Board board = new Board();

        int testSendTo = 5;
        JailTile tile = new JailTile("Jail","lars sendes i fængsel","pink",testSendTo);
        Player player = new Player("Player", 2,1);
        assertEquals(0,player.getCurrentTile());
        assertTrue(!player.isInJail());
      
        tile.landOn(player,gui,board,game);

        assertTrue(player.isInJail());
        assertEquals(testSendTo, player.getCurrentTile());
    }
}