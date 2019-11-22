package Entity;

public class GetOutOfJailFreeCard extends ChanceCard{
    public void onDraw(Player player) {
        player.incrementReleaseCards();
    }
    @Override
    public String toString() {
        return "____________";
    }
}
