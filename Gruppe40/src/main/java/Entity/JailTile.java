package Entity;

public class JailTile extends Tile {


    private int sendTo = 0;

    public JailTile(String name, String text, String colour, int sendTo) {
        super(name, text, colour);
        this.sendTo = sendTo;
    }
    public JailTile(String name, String text, String colour){
        super(name, text, colour);

    }

    public void landOn(Player pl) {
        if(sendTo!= 0){
            pl.setCurrentTile(sendTo);
            pl.setInJail(true);
            //boardet skal updaters efter i diceGame Klassen
        }
    }
}
