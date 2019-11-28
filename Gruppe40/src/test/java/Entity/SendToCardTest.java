package Entity;

import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class SendToCardTest {

    @Test
    public void setSendTo() {
    }

    @Test
    public void getSendTo() {
    }

    @Test
    public void onDraw() {
        SendToCard card = new SendToCard();

        int testSendTo = 4;
        //check for grund er gratis og ikke gratis
        card.setSendTo(testSendTo);
        GUI gui = new GUI();
        DiceGame game = new DiceGame(gui,5);
        Board board = game.getBoard();
        Player pl1 = game.findPlayer(1);
        int testStartM = pl1.getMoney();

        card.onDraw(pl1,gui,board,game, false);
        PropertyTile tile =(PropertyTile) board.getTile(testSendTo);
        int tileCost = tile.getCost();

        assertEquals(testStartM- tileCost, pl1.getMoney());
        int[] expected = {testSendTo};
        assertArrayEquals(expected,pl1.getOwnedProperties());

        //hvis det er gratis
        Player pl2 = game.findPlayer(2);
        testSendTo = 10;

        pl2.setMoney(testStartM);
        card.setSendTo(testSendTo);


        card.onDraw(pl2,gui,board,game, true);
        assertEquals(testStartM, pl2.getMoney());
        int[] expected2 = {testSendTo};
        assertArrayEquals(expected2, pl2.getOwnedProperties());


    }
}