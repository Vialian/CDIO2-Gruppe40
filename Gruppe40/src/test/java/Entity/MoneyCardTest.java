package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyCardTest {

    @Test
    public void setGetMoney() {
        int[] moneys = {3,6,-2,-30103,43,34435,-1,-4,0,5};
        MoneyCard card = new MoneyCard();
        for(int money : moneys) {
            card.setMoney(money);
            assertEquals(money, card.getMoney());
        }
    }

    @Test
    public void onDraw() {
        int totalMoney = 20;
        Player player = new Player("Jens", totalMoney, 1);
        int[] moneys = {3,6,-2,-30103,43,34435,-1,-4,0,5};
        MoneyCard card = new MoneyCard();

        for(int money : moneys) {
            totalMoney += money;
            card.setMoney(money);
            card.onDraw(player);
            assertEquals(totalMoney, player.getMoney());
        }
    }
}