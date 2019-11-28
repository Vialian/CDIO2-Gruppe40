package Entity;

import gui_main.GUI;

public class SendToCard extends ChanceCard {
    private int sendTo;

    public void setSendTo(int sendTo) {
        this.sendTo = sendTo;
    }

    public int getSendTo() {
        return sendTo;
    }

    public SendToCard(/*int sendTo*/) {
        //setSendTo(sendTo);
    }

    public void onDraw(Player pl, GUI gui,Board br, DiceGame game, boolean fieldFree) {

        pl.setCurrentTile(sendTo);


        Tile tl = br.getTile(pl.getCurrentTile());
        if(tl instanceof PropertyTile){
            PropertyTile tile = (PropertyTile) tl;
            if (tile.getOwnedBy() == 0) {
                if(fieldFree) {
                    pl.addMoney(tile.getCost());
                }
            }
            tile.landOn(pl, gui, br, game);
        }else{
            tl.landOn(pl, gui, br, game);
        }
        


    }
    @Override
    public String toString() {
        return "____________";
    }
}

