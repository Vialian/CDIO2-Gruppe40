package Entity;

public class FreeRealEstateCard extends ChanceCard {
    private Player player;

    public void setPlayer(Player player){
        this.player=player;
    }
    public Player getPlayer(){
        return player;
    }
    public FreeRealEstateCard(Player player){
        setPlayer(player);
    }
    public void onDraw() {
        player.setPromisedRealEstate(True);
    }
    @Override
    public String toString() {
        return "____________";
    }
}
