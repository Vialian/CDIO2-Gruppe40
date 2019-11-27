package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class FreeRealEstateCardTest {

    @Test
    public void onDraw() {
        FreeRealEstateCard card = new FreeRealEstateCard();
        Player player = new Player("Laurits",20,1);
        assertTrue(!player.getPromisedRealEstate());
        card.onDraw(player);
        assertTrue(player.getPromisedRealEstate());
        card.onDraw(player);
        assertTrue(player.getPromisedRealEstate());
    }
}