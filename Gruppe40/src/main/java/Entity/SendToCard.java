package Entity;

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

    public void onDraw(Player player) {
        //TODO: INSERT METHOD FROM IMPRISONMENTTILE;
    }

    @Override
    public String toString() {
        return "____________";
    }
}

