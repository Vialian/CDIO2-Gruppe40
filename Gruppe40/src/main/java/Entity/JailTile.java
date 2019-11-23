package Entity;

public class JailTile extends Tile {


    private int sendTo = 0;

    public JailTile(String name, String text, int sendTo) {
        super(name, text);
        this.sendTo = sendTo;
    }
    public JailTile(String name, String text){
        super(name, text);

    }

    public void landOn(Player pl) {
        if(sendTo!= 0){
            pl.setCurrentTile(sendTo);
            pl.setInJail(true);
            //boardet skal updaters efter i diceGame Klassen
        }
    }
}
