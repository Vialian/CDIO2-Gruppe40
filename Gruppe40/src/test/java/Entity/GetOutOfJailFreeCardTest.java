package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetOutOfJailFreeCardTest {

    @Test
    public void onDraw() {
        GetOutOfJailFreeCard card = new GetOutOfJailFreeCard();
        Player player = new Player("Laurits",20,1);
        assertEquals(0,player.getReleaseCards());
        card.onDraw(player);
        assertEquals(1,player.getReleaseCards());
        card.onDraw(player);
        assertEquals(2,player.getReleaseCards());
    }
}