package Entity;

import Control.DiceGame;
import gui_main.GUI;
import org.junit.Test;

import java.awt.*;

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
        Player player = new Player("Lars",100,1);
        GUI gui = new GUI();
        Board board = new Board();
        DiceGame game = new DiceGame();



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