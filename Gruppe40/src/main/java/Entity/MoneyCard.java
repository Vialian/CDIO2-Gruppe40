package Entity;

public class MoneyCard extends ChanceCard{
    private int money;

    public void setMoney(int money){
        this.money=money;
    }
    public int getMoney(){
        return money;
    }
    public MoneyCard(/*int money*/){
        setMoney(money);
    }
    public void onDraw(Player player) {
        player.addMoney(money);
    }
    @Override
    public String toString() {
        return "____________";
    }
}
