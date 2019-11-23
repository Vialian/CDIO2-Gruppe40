package Entity;

public class MoveForwardCard extends ChanceCard{
    private int move;

    public void setMove(int move){
        this.move=move;
    }
    public int getMove(){
        return move;
    }
    public MoveForwardCard(int move){
        setMove(move);
    }

    public void onDraw(Player player) {
        //TODO: INSERT METHOD FROM controller and add needed arguments;
    }
    @Override
    public String toString() {
        return "____________";
    }
}
