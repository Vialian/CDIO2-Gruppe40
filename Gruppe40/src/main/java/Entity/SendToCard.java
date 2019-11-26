package Entity;

import Control.DiceGame;
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
        PropertyTile tile = (PropertyTile) br.getTile(pl.getCurrentTile());

            if (tile.getOwnedBy() == 0) {
                if(fieldFree) {
                    pl.addMoney(tile.getCost());
                }
            }
            tile.landOn(pl, gui, br, game);

    }
    @Override
    public String toString() {
        return "____________";
    }
}

