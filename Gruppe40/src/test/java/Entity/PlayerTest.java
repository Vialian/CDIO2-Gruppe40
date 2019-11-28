package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

private String Testname = "TestName";
private int testStartMoney = 20;
private int testID = 1;
private Player pl = new Player(Testname,testStartMoney,testID);

    @Test
    public void addAndGetOwnedProperties() {

        int[] input = {1,2,6,7,9,8};
        for(int i=0; i< input.length; i++){
            pl.addProperty(input[i]);
        }
        assertArrayEquals(input, pl.getOwnedProperties());

    }

    @Test
    public void getMoney() {
        assertEquals(testStartMoney,pl.getMoney());
    }

    @Test
    public void setMoney() {
        int money = 30;
        pl.setMoney(money);
        assertEquals(money, pl.getMoney());
    }

    @Test
    public void addMoney() {
        int money = 30;
        pl.addMoney(money);
        assertEquals(money + testStartMoney, pl.getMoney());
    }

    @Test
    public void getName() {
        assertEquals(Testname, pl.getName());
    }

    @Test
    public void rollDie() {
        assertEquals(1,pl.rollDie(),6);
    }

    @Test
    public void getDieValue() {

    }

    @Test
    public void getDie() {
        Die die = new Die();
        assertSame(die,pl.getDie());
    }

    @Test
    public void setDie() {
        Die die = new Die();
        pl.setDie(die);
        assertSame(die, pl.getDie());
    }

    @Test
    public void setGetCurrentTile() {
        int tile = 23;
        pl.setCurrentTile(tile);

        assertEquals(tile,pl.getCurrentTile());
    }

    @Test
    public void addToPos() {
        int start = pl.getCurrentTile();
        int move = 4;
        int boardLenght = 20;
        pl.addToPos(move,boardLenght);
        assertEquals(start+move,pl.getCurrentTile());

        for(int i = 0; i<1000; i++){
            pl.addToPos(move,boardLenght);
            assertEquals(0,pl.getCurrentTile(), boardLenght-1);
        }
    }

    @Test
    public void propertyToSting() {
        int[] input = {1,2,6,7,9,8};
        for(int i=0; i< input.length; i++){
            pl.addProperty(input[i]);
        }
        String res = "Du ejer:";
        for(int pro : input){
            res = res + " "+ pro +",";
        }
        assertEquals(res,pl.propertyToSting());
    }


    @Test
    public void removeProperty() {
        int[] input = {1,2,6,7,9,8};
        for(int i=0; i< input.length; i++){
            pl.addProperty(input[i]);
        }
        int[] newInput = {1,2,7,9,8};
        pl.removeProperty(6);
        assertArrayEquals(newInput,pl.getProperties());
    }

    @Test
    public void incrementReleaseCards() {
        int expected = pl.getReleaseCards()+1;
        pl.incrementReleaseCards();
        assertEquals(expected,pl.getReleaseCards());
    }


    @Test
    public void useReleaseCards() {
        int start = pl.incrementReleaseCards();
        pl.useReleaseCards();
        assertEquals(start-1,pl.getReleaseCards());
    }


    @Test
    public void getSetPromisedRealEstate() {
        boolean x = true;
        pl.setPromisedRealEstate(x);
        assertEquals(x,pl.getPromisedRealEstate());
    }

    @Test
    public void hasLost() {
        pl.setMoney(-1);
        assertEquals(true, pl.hasLost());
        pl.setMoney(0);
        assertEquals(false, pl.hasLost());
        pl.setMoney(100);
        assertEquals(false, pl.hasLost());
    }

    @Test
    public void setGetID() {
        assertEquals(testID,pl.getID());
        int newID = 5;
        pl.setID(newID);
        assertEquals(newID,pl.getID());

    }


    @Test
    public void isInJail() {
        assertEquals(false,pl.isInJail());
    }

    @Test
    public void setInJail() {
        pl.setInJail(true);
        assertEquals(true,pl.isInJail());

        pl.setInJail(false);
        assertEquals(false,pl.isInJail());
    }
}