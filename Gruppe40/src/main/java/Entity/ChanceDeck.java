package Entity;

import Control.DiceGame;
import gui_main.GUI;

public class ChanceDeck {

    private int index;
    private int[] deck;
    private FreeRealEstateCard freeRealEstateCard = new FreeRealEstateCard();
    private GetOutOfJailFreeCard getOutOfJailFreeCard = new GetOutOfJailFreeCard();
    private MoneyCard moneyCard = new MoneyCard();
    private MoveForwardCard moveForwardCard = new MoveForwardCard();
    private SendToCard sendToCard = new SendToCard();

    public ChanceDeck() {
        deck = new int[20];
        mixDeck();
    }

    public void mixDeck() {
        int[] initDeck = new int[20];
        for (int i = 0; i < 20; i++)
            initDeck[i] = 1;
        
        int mixCount = 0;
        while (mixCount < 20) {
            int i;
            do {
                i = (int)(Math.random() * 20);
            } while (initDeck[i] == 0);
            deck[mixCount++] = i;
            initDeck[i] = 0;
        }
        index = 0;
    }

    public void drawCard(Player player, GUI gui, Board board, DiceGame game) {
        index++;
        index = index%20;
        performAction(deck[index], player, gui, board, game);
    }
    public void drawCard() {
        index++;
        index = index%20;
//        performAction(deck[index], player, gui, board, game);
    }
    
    public void performAction(int index, Player player, GUI gui, Board board, DiceGame game) {
        int position;
        String goTo;
        switch(index) {
            case 0:
                gui.displayChanceCard("PROMISED LAND CARD: Bil");
                Player bil = game.findPlayer(1);
                freeRealEstateCard.onDraw(bil);
                drawCard(player, gui, board, game);
                break;
                //Giv kort til bilen
                //Til BIL:
                //På næste tur skal du flytte til ledigt felt og køb det
                //HVIS Ingen ledige: Køb af anden spiller
            case 5:
                gui.displayChanceCard("PROMISED LAND CARD: Skib");
                Player skib = game.findPlayer(2);
                freeRealEstateCard.onDraw(skib);
                drawCard(player, gui, board, game);
                // Samme, men for SKIBET
                break;
            case 11:
                gui.displayChanceCard("PROMISED LAND CARD: Kat");
                Player kat = game.findPlayer(3);
                if(!(kat == null)) {
                    freeRealEstateCard.onDraw(kat);
                }
                drawCard(player, gui, board, game);
                break;
                // Som 5, men til KAT
            case 12:
                gui.displayChanceCard("PROMISED LAND CARD: Hund");
                Player hund = game.findPlayer(4);
                if(!(hund == null)) {
                    freeRealEstateCard.onDraw(hund);
                }
                drawCard(player, gui, board, game);
                break;
                // Som 5 men til HUND

            case 3:
                gui.displayChanceCard("FREE PROPERTY CARD: ORANGE");
                goTo = gui.getUserSelection("You can choose between landing on either orange tile.","The skatepark","The swimmingpool");
                position = 11;
                if(goTo.equals("The skatepark")){
                    position = 10;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                //Ryk frem til et orange felt
                //HVIS ledigt: Modtag felt gratis
                //ELLERS betal leje til ejeren
            case 7:
                gui.displayChanceCard("FREE PROPERTY CARD: ORANGE/GRØN");
                goTo = gui.getUserSelection("You can choose between landing on either orange or green tiles.","The skatepark","The swimmingpool","The bowling alley","The zoo");
                position = 11;
                if(goTo.equals("The skatepark")){
                    position = 10;
                }
                else if(goTo.equals("The bowling alley")){
                    position = 19;
                }
                else if(goTo.equals("The zoo")){
                    position = 20;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                //GRATIS FELT
                //Ryk frem til ORANGE eller GRØNT felt
                //HVIS ledigt: Modtag felt
                //ELLERS betal leje
            case 8:
                gui.displayChanceCard("FREE PROPERTY CARD: LYSEBLÅ");
                goTo = gui.getUserSelection("You can choose between landing on either orange tile.","The candyshop","The icre cream shop");
                position = 5;
                if(goTo.equals("The candyshop")){
                    position = 4;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                // Samme, men for LYSEBLÅT felt
            case 14:
                gui.displayChanceCard("FREE PROPERTY CARD: PINK/MØRKEBLÅ");
                goTo = gui.getUserSelection("You can choose between landing on either pink or blue tiles.","The museum","The library","The waterpark","The beach");
                position = 7;
                if(goTo.equals("The library")){
                    position = 8;
                }
                else if(goTo.equals("The waterpark")){
                    position = 22;
                }
                else if(goTo.equals("The beach")){
                    position = 23;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                // som 7, men for pink eller Lyseblåt
            case 16:
                gui.displayChanceCard("FREE PROPERTY CARD: RØD");
                goTo = gui.getUserSelection("You can choose between landing on either orange tile.","The casino","The cinema");
                position = 13;
                if(goTo.equals("The cinema")){
                    position = 14;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                // som 8, men for Rødt
            case 17:
                gui.displayChanceCard("FREE PROPERTY CARD: Skaterparken");

                sendToCard.setSendTo(10);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                //Skaterparken: Ryk til Sk.
                // HVIS ledigt: Modtag gratis
                // ELLERS betal leje til ejer
            case 18:
                gui.displayChanceCard("FREE PROPERTY CARD: LYSEBLÅ/RØD");
                goTo = gui.getUserSelection("You can choose between landing on either orange or green tiles.","The candyshop","The ice cream shop","The casino","The Cinema");
                position = 5;
                if(goTo.equals("The candyshop")){
                    position = 4;
                }
                else if(goTo.equals("The casino")){
                    position = 13;
                }
                else if(goTo.equals("The cinema")){
                    position = 14;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                // Som 8, men for Lyseblåt eller Rødt
            case 19:
                gui.displayChanceCard("FREE PROPERTY CARD: BRUN/GUL");
                goTo = gui.getUserSelection("You can choose between landing on either orange or green tiles.","The burgerbar","The pizzeria","The toy store","The pet store");
                position = 1;
                if(goTo.equals("The pizzeria")){
                    position = 2;
                }
                else if(goTo.equals("The toy store")){
                    position = 16;
                }
                else if(goTo.equals("The pet store")){
                    position = 17;
                }
                sendToCard.setSendTo(position);
                sendToCard.onDraw(player,gui,board,game,true);
                break;
                // Som 8, men for Brunt eller Gult

            case 1:
                gui.displayChanceCard("GO TO START CARD");
                sendToCard.setSendTo(0);
                sendToCard.onDraw(player,gui,board,game,false);
                moneyCard.setMoney(2);
                moneyCard.onDraw(player);
                break;
                //Ryk frem til start
                //Modtag 2M
            case 2:
                gui.displayChanceCard("MOVE FORWARD CARD");
                int move = 0;
                while(!(0< move && move<6)){
                    try {
                        move = gui.getUserInteger("Hvor mange felter vil du flytte frem (1-5)?");
                        break;
                    }
                    catch (Exception e){
                        gui.showMessage("indtast et tal mellem 1 og 5");
                    }
                }
                moveForwardCard.setMove(move);
                moveForwardCard.onDraw(player,board,gui,game);
                break;
                //Ryk op til 5 felter frem
            case 4:
                gui.displayChanceCard("THE CHOICE IS YOURS CARD");
                String vilDu = gui.getUserSelection("Vil du rykke 1 felt frem eller tage et chancekort mere?", "1 felt frem", "Chancekort");
                if (vilDu.equals("1 felt frem")) {
                    moveForwardCard.setMove(1);
                    moveForwardCard.onDraw(player,board,gui,game);
                } else {
                    drawCard(player, gui, board, game);
                }
                break;
                //ENTEN ryk 1 felt frem
                //ELLER tag et chancekort mere
            case 6:
                gui.displayChanceCard("TOO MUCH CANDY CARD");
                gui.showMessage("Du har spist for meget slik. Minus 2M :(.");
                moneyCard.setMoney(-2);
                moneyCard.onDraw(player);
                break;
                //Du har spist for meget slik
                //Betal 2M til banken

            case 9:
                gui.displayChanceCard("GET OUT OF JAIL FREE CARD");
                getOutOfJailFreeCard.onDraw(player);
                break;
                // LØSLADELSESKORT
            case 10:
                gui.displayChanceCard("Strandpromenaden CARD");
                sendToCard.setSendTo(23);
                sendToCard.onDraw(player, gui, board, game, false);
                break;
            case 13:
                gui.displayChanceCard("BIRTHDAY CARD");
                gui.showMessage("Det er din fødselsdag. Modtag 1M.");
                moneyCard.setMoney(1);
                moneyCard.onDraw(player);
                break;
                // Ryk frem til Strandpromenaden
                // Fødselsdag: Modtag 1 M
            case 15:
                gui.displayChanceCard("GOOD JOB HOMEWORK CARD");
                gui.showMessage("Du har lavet lektier. Modtag 2M.");
                moneyCard.setMoney(2);
                moneyCard.onDraw(player);
                break;
                // Lektier: Modtag 2M
        }
    }

    public void moveBuyTile(int pos, Player pl, Board br, GUI gui, DiceGame game){
        pl.setCurrentTile(pos);
        PropertyTile tile = (PropertyTile) br.getTile(pl.getCurrentTile());

        if(tile.getOwnedBy() == 0){
            pl.addMoney(tile.getCost());
            tile.landOn(pl, gui, br, game);
        }else{
            tile.landOn(pl, gui, br, game);

        }

    }



    private void MoveBuyTile (int index, Player player, Board board, GUI gui, DiceGame game) {
        sendToCard.setSendTo(index);
        sendToCard.onDraw(player);
        Boolean isVacant = board.isVacant(index);
        if (isVacant) {
            String jaNej = gui.getUserSelection("Du er nu på et orange felt. Vil du købe det?", "ja","nej");
            if (jaNej.equals("ja")) {
                Tile vTile = board.getTile(index);
                PropertyTile pTile = (PropertyTile)vTile;
                if(player.getMoney() >= pTile.getCost()){
                    pTile.setOwnedBy(player.getID());
                    player.addMoney(-pTile.getCost());
                    gui.showMessage("Du ejer nu det orange felt.");
                } else {
                    gui.showMessage("Du har desværre ikke penge nok til at købe feltet.");
                }
            }
        } else {
            gui.showMessage("Du skal betale leje til ejeren.");
            Tile ownedTile = board.getTile(index);
            if (ownedTile.isProperty()) {
                PropertyTile pTile = (PropertyTile) ownedTile;
                Player owner = game.findPlayer(pTile.getOwnedBy());
                player.addMoney(-pTile.getCost());
                owner.addMoney(pTile.getCost());
            }
        }
    }

    // Getter for deck
    public int[] getDeck() {
        return this.deck;
    }

    // Setter for deck
    public void setDeck(int[] newDeck) {
        this.deck = newDeck;
    }


    // Getter for index
    public int getIndex() {
        return this.index;
    }

    // Setter for index
    public void setIndex(int newIndex) {
        this.index = newIndex;
    }

    // Getter for moveForwardCard
    public MoveForwardCard getMoveForwardCard(){
        return this.moveForwardCard;
    }

    // Setter for moveForwardCard
    public void setMoveForwardCard(MoveForwardCard newMoreForwardCard){
        this.moveForwardCard = newMoreForwardCard;
    }

    // Getter for sendToCard
    public SendToCard getSendToCard() {
        return this.sendToCard;
    }

    // Setter for sendToCard
    public void setSendToCard(SendToCard newSendToCard) {
        this.sendToCard = newSendToCard;
    }

    //Getter for moneyCard
    public MoneyCard getMoneyCard(){
        return this.moneyCard;
    }

    // Sette for moneyCard
    public void setMoneyCard(MoneyCard newMoneyCard){
        this.moneyCard = newMoneyCard;
    }

    // Getter for getOutOfJailFreeCard
    public GetOutOfJailFreeCard getGetOutOfJailFreeCard(){
        return this.getGetOutOfJailFreeCard();
    }

    // Setter for getOutOfJailFreeCard
    public void setGetOutOfJailFreeCard(GetOutOfJailFreeCard newGetOutOfJailFreeCard){
        this.getOutOfJailFreeCard = newGetOutOfJailFreeCard;
    }

    // Getter for freeRealEstateCard
    public FreeRealEstateCard getFreeRealEstateCard(){
        return this.getFreeRealEstateCard();
    }

    // Setter for freeRealEstateCard
    public void setFreeRealEstateCard(FreeRealEstateCard newFreeRealEstateCard){
        this.freeRealEstateCard = newFreeRealEstateCard;
    }
}
