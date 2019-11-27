package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetOutOfJailFreeCardTest {

    @Test
    public void onDraw() {
        GetOutOfJailFreeCard card = new GetOutOfJailFreeCard();
        Player player = new Player("Laurits",20,1);
        assertTrue(!player.getPromisedRealEstate());
        card.onDraw(player);
        assertTrue(player.getPromisedRealEstate());
        card.onDraw(player);
        assertTrue(player.getPromisedRealEstate());

    }
}