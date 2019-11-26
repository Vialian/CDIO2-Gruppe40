package Entity;

import Control.DiceGame;
import gui_main.GUI;

public class ChanceDeck {
    int index;
    int[] deck;
    FreeRealEstateCard freeRealEstateCard = new FreeRealEstateCard();
    GetOutOfJailFreeCard getOutOfJailFreeCard = new GetOutOfJailFreeCard();
    MoneyCard moneyCard = new MoneyCard();
    MoveForwardCard moveForwardCard = new MoveForwardCard();
    SendToCard sendToCard = new SendToCard();

    public ChanceDeck() {
        deck = new int[20];
        mixDeck();
    }

    private void mixDeck() {
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
        performAction(index++, player, gui, board, game);
        index = index%20;
    }
    
    private void performAction(int index, Player player, GUI gui, Board board, DiceGame game) {
        switch(index) {
            case 0:
                gui.displayChanceCard("PROMISED LAND CARD: Car");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player bil = game.findPlayer(1);
                bil.setPromisedRealEstate(true);
                //Giv kort til bilen
                //Til BIL:
                //På næste tur skal du flytte til ledigt felt og køb det
                //HVIS Ingen ledige: Køb af anden spiller
            case 5:
                gui.displayChanceCard("PROMISED LAND CARD: Ship");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player skib = game.findPlayer(2);
                skib.setPromisedRealEstate(true);
                // Samme, men for SKIBET
            case 11:
                gui.displayChanceCard("PROMISED LAND CARD: Cat");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player kat = game.findPlayer(3);
                kat.setPromisedRealEstate(true);
                // Som 5, men til KAT
            case 12:
                gui.displayChanceCard("PROMISED LAND CARD: Dog");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player hund = game.findPlayer(4);
                hund.setPromisedRealEstate(true);
                // Som 5 men til HUND

            case 3:
                gui.displayChanceCard("FREE PROPERTY CARD: ORANGE");
                int tileIndex = board.getNextTileColor("ORANGE");
                moveBuyTile (index, player, board, gui, game);
                //Ryk frem til et orange felt
                //HVIS ledigt: Modtag felt gratis
                //ELLERS betal leje til ejeren
            case 7:
                gui.displayChanceCard("FREE PROPERTY CARD: ORANGE/GREEN");
                tileIndex = board.getNextTileColor2("ORANGE","GREEN");
                moveBuyTile (index, player, board, gui, game);
                //GRATIS FELT
                //Ryk frem til ORANGE eller GRØNT felt
                //HVIS ledigt: Modtag felt
                //ELLERS betal leje
            case 8:
                gui.displayChanceCard("FREE PROPERTY CARD: LIGHT BLUE");
                tileIndex = board.getNextTileColor("LYSEBLÅ");
                moveBuyTile (index, player, board, gui, game);
                // Samme, men for LYSEBLÅT felt
            case 14:
                gui.displayChanceCard("FREE PROPERTY CARD: PINK/LIGHT BLUE");
                tileIndex = board.getNextTileColor2("PINK", "LIGHT BLUE");
                moveBuyTile (index, player, board, gui, game);
                // som 7, men for pink eller Lyseblåt
            case 16:
                gui.displayChanceCard("FREE PROPERTY CARD: RED");
                tileIndex = board.getNextTileColor("RED");
                moveBuyTile (index, player, board, gui, game);
                // som 8, men for Rødt
            case 17:
                gui.displayChanceCard("FREE PROPERTY CARD: Skaterparken");
                tileIndex = board.getNextTileName("Skaterparken");
                moveBuyTile (index, player, board, gui, game);
                //Skaterparken: Ryk til Sk.
                // HVIS ledigt: Modtag gratis
                // ELLERS betal leje til ejer
            case 18:
                gui.displayChanceCard("FREE PROPERTY CARD: RED");
                tileIndex = board.getNextTileColor2("LYSEBLÅ", "RED");
                moveBuyTile (index, player, board, gui, game);
                // Som 8, men for Lyseblåt eller Rødt
            case 19:
                gui.displayChanceCard("FREE PROPERTY CARD: BROWN/YELLOW");
                tileIndex = board.getNextTileColor2("BROWN", "YELLOW");
                moveBuyTile (index, player, board, gui, game);
                // Som 8, men for Brunt eller Gult

            case 1:
                gui.displayChanceCard("GO TO START CARD");
                sendToCard.setSendTo(0);
                sendToCard.onDraw(player);
                //Ryk frem til start
                //Modtag 2M
            case 2:
                gui.displayChanceCard("MOVE FORWARD CARD");
                int move = gui.getUserInteger("How many fields do you want to move forward (1-5)?");
                moveForwardCard.setMove(move);
                moveForwardCard.onDraw(player);

                //Ryk op til 5 felter frem
            case 4:
                gui.displayChanceCard("THE CHOICE IS YOURS CARD");
                String vilDu = gui.getUserSelection("Do you want to move 1 field forward or take a change card more?", "1 field forward", "Chance card");
                if (vilDu.equals("1 field forward")) {
                    moveForwardCard.setMove(1);
                    moveForwardCard.onDraw(player);
                } else {
                    drawCard(player, gui, board, game);
                }
                //ENTEN ryk 1 felt frem
                //ELLER tag et chancekort mere
            case 6:
                gui.displayChanceCard("TOO MUCH CANDY CARD");
                gui.showMessage("You have eaten to much candy. Minus 2M :(");
                moneyCard.setMoney(-2);
                moneyCard.onDraw(player);
                //Du har spist for meget slik
                //Betal 2M til banken

            case 9:
                gui.displayChanceCard("GET OUT OF JAIL FREE CARD");
                player.incrementReleaseCards();
                // LØSLADELSESKORT
            case 10:
                gui.displayChanceCard("Strandpromenaden CARD");
                tileIndex = board.getNextTileName("Strandpromenaden");
                sendToCard.setSendTo(index);
                sendToCard.onDraw(player);
                String jaNej = gui.getUserSelection("Do you have birthday today?", "Yes", "No");
                if(jaNej.equals("Yes")) {
                    moneyCard.setMoney(1);
                    moneyCard.onDraw(player);
                }
                // Ryk frem til Strandpromenaden
                // Fødselsdag: Modtag 1 M
            case 15:
                gui.displayChanceCard("GOOD JOB HOMEWORK CARD");
                gui.showMessage("You have done your homework. Receive 2M");
                moneyCard.setMoney(2);
                moneyCard.onDraw(player);
                // Lektier: Modtag 2M
        }
    }

    private void moveBuyTile(int pos, Player pl, Board br, GUI gui, DiceGame game){
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
            String jaNej = gui.getUserSelection("You are now on a orange field. Do you want to buy it?", "Yes","No");
            if (jaNej.equals("Yes")) {
                Tile vTile = board.getTile(index);
                PropertyTile pTile = (PropertyTile)vTile;
                if(player.getMoney() >= pTile.getCost()){
                    pTile.setOwnedBy(player.getID());
                    player.addMoney(-pTile.getCost());
                    gui.showMessage("You now own the orange field.");
                } else {
                    gui.showMessage("Unfortunately, you don't have enough money to buy the field.");
                }
            }
        } else {
            gui.showMessage("You must pay rent to the owner.");
            Tile ownedTile = board.getTile(index);
            if (ownedTile.isProperty()) {
                PropertyTile pTile = (PropertyTile) ownedTile;
                Player owner = game.findPlayer(pTile.getOwnedBy());
                player.addMoney(-pTile.getCost());
                owner.addMoney(pTile.getCost());
            }
        }
    }
}
