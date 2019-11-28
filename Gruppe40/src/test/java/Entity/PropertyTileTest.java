package Entity;

import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;


public class PropertyTileTest {

    String testName = "lars";
    String testText = "Hvad laver du lars?";
    int testCost = 2;
    String testColor = "pink";
    PropertyTile tile = new PropertyTile(testName,testText,testCost,testColor);
    @Test
    public void getOwnedBy() {
        assertEquals(0,tile.getOwnedBy());
    }

    @Test
    public void setOwnedBy() {
        int owner = 4;
        tile.setOwnedBy(owner);
        assertEquals(owner,tile.getOwnedBy());
    }

    @Test
    public void landOn() {

        GUI gui = new GUI();
        DiceGame game = new DiceGame(gui,5);
        Board board = game.getBoard();
        int testTilePos =4 ;

        PropertyTile tile1 = (PropertyTile)board.getTile(testTilePos);
        PropertyTile tile2 = (PropertyTile)board.getTile(testTilePos+1);

        Player[] players  = game.getPlayers();
        Player pl1 =players[0];
        Player pl2 = players[1];
        int testStartM = 100;
        pl1.setMoney(testStartM);
        pl2.setMoney(testStartM);


        //Hvis feltet er ejer af en anden spiller
        tile1.setOwnedBy(pl2.getID());
        assertEquals(pl2.getID(), tile1.getOwnedBy());
        tile1.landOn(pl1,gui,board,game);

        assertEquals(testStartM - tile1.getCost(),pl1.getMoney());
        assertEquals(testStartM + tile1.getCost(),pl2.getMoney());

        //Hvis feltet ikke er ejet af nogle
        pl1.setMoney(testStartM);
        pl2.setMoney(testStartM);

        tile2.landOn(pl1,gui,board,game);
        System.out.println(tile2.getCost());
        assertEquals(testStartM - tile2.getCost(),pl1.getMoney());
        int[] expectedOwned = {board.getTilePos(tile2)};
        assertArrayEquals(expectedOwned,pl1.getOwnedProperties());



    }

    @Test
    public void getCost() {
        assertEquals(testCost,tile.getCost());
    }

    @Test
    public void setCost() {
        int newcost =1;
        tile.setCost(newcost);
        assertEquals(newcost,tile.getCost());
    }
}