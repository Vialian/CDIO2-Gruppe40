package Control;

import Entity.DiceGame;
import Entity.Player;
import Entity.PropertyTile;
import Entity.Tile;
import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceGameTest {
    GUI gui = new GUI();
    DiceGame game = new DiceGame(gui);

    @Test
    public void runAll(){
        findPlayer();
        playDiceGame();
        colourPair();
    }

    @Test
    public void findPlayer() {

        assertEquals(game.getPlayers()[1], game.findPlayer(2));
        assertEquals(game.getPlayers()[0], game.findPlayer(1));
        assertNull(game.findPlayer(3));
    }

    @Test
    public void playDiceGame() {
        //test jail
        Player player = game.findPlayer(1);
        player.setInJail(true);
        game.playDiceGameStump(0,1);
        assertTrue(!player.isInJail());
        assertEquals(19,player.getMoney() );
        player.incrementReleaseCards();
        player.setInJail(true);
        game.playDiceGameStump(0,1);
        assertTrue(!player.isInJail());
        assertEquals(19,player.getMoney());
        assertEquals(0,player.getReleaseCards());
        //promised real estate test
        player.setPromisedRealEstate(true);
        game.playDiceGameStump(1,1);
        assertTrue(!player.getPromisedRealEstate());
        //passing go test
        player.setCurrentTile(23);
        game.playDiceGameStump(0,1);
        assertEquals(21,player.getMoney() );

    }

    @Test
    public void colourPair() {
        //first setup only one blue tile is owned by player 1
        game.getBoard().addProperty(0,"First","First property",0,"BLUE");
        game.getBoard().addProperty(1,"Second","Second property",0,"BLUE");
        Tile tile = game.getBoard().getTile(0);
        PropertyTile property = (PropertyTile) tile;
        property.setOwnedBy(1);
        assertTrue(!game.colourPair(0));
        //both tiles are owned by player 1
        tile = game.getBoard().getTile(1);
        property = (PropertyTile) tile;
        property.setOwnedBy(1);
        assertTrue(game.colourPair(0));
        //add a tile behind the beginning tile which is not owned by the same
        game.getBoard().addProperty(23,"Twentythird","Twentythird property",0,"BLUE");
        assertTrue(!game.colourPair(0));
        //add a tile which is not owned by 1 and has a few useless tiles between them
        game.getBoard().addProperty(20,"Third","Third property",0,"BLUE");
        assertTrue(!game.colourPair(0));
        //set the tile beside the others to owned by 1
        tile = game.getBoard().getTile(23);
        property = (PropertyTile) tile;
        property.setOwnedBy(1);
        assertTrue(!game.colourPair(0));
        //set the last propertytile to owned by the same player
        tile = game.getBoard().getTile(20);
        property = (PropertyTile) tile;
        property.setOwnedBy(1);
        assertTrue(game.colourPair(0));
        //encase a tile between two propertytiles with a diffenent color
        game.getBoard().addProperty(22,"Twentysecond","Twenty property",0,"RED");
        game.getBoard().addProperty(2,"Third","Third property",0,"RED");
        assertTrue(game.colourPair(0));
        //test if the owner of the tile between the differentcolored tiles matters
        tile = game.getBoard().getTile(20);
        property = (PropertyTile) tile;
        property.setOwnedBy(0);
        assertTrue(game.colourPair(0));
    }
}