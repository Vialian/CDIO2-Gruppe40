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
        if (index == 20)
            index = 0;
    }
    
    private void performAction(int index, Player player, GUI gui, Board board, DiceGame game) {
        switch(index) {
            case 0:
                gui.displayChanceCard("PROMISED LAND CARD: Bil");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player bil = game.findPlayer("BIL");
                bil.setPromisedRealEstate(true);
                //Giv kort til bilen
                //Til BIL:
                //På næste tur skal du flytte til ledigt felt og køb det
                //HVIS Ingen ledige: Køb af anden spiller
            case 5:
                gui.displayChanceCard("PROMISED LAND CARD: Skib");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player skib = game.findPlayer("SKIB");
                skib.setPromisedRealEstate(true);
                // Samme, men for SKIBET
            case 11:
                gui.displayChanceCard("PROMISED LAND CARD: Kat");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player kat = game.findPlayer("KAT");
                kat.setPromisedRealEstate(true);
                // Som 5, men til KAT
            case 12:
                gui.displayChanceCard("PROMISED LAND CARD: Hund");
                drawCard(player, gui, board, game); //Tage et chancekort mere
                Player hund = game.findPlayer("HUND");
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
                gui.displayChanceCard("FREE PROPERTY CARD: ORANGE/GRØN");
                tileIndex = board.getNextTileColor2("ORANGE","GRØN");
                moveBuyTile (index, player, board, gui, game);
                //GRATIS FELT
                //Ryk frem til ORANGE eller GRØNT felt
                //HVIS ledigt: Modtag felt
                //ELLERS betal leje
            case 8:
                gui.displayChanceCard("FREE PROPERTY CARD: LYSEBLÅ");
                tileIndex = board.getNextTileColor("LYSEBLÅ");
                moveBuyTile (index, player, board, gui, game);
                // Samme, men for LYSEBLÅT felt
            case 14:
                gui.displayChanceCard("FREE PROPERTY CARD: PINK/LYSEBLÅ");
                tileIndex = board.getNextTileColor2("PINK", "LYSEBLÅ");
                moveBuyTile (index, player, board, gui, game);
                // som 7, men for pink eller Lyseblåt
            case 16:
                gui.displayChanceCard("FREE PROPERTY CARD: RØD");
                tileIndex = board.getNextTileColor("RØD");
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
                gui.displayChanceCard("FREE PROPERTY CARD: RØD");
                tileIndex = board.getNextTileColor2("LYSEBLÅ", "RØD");
                moveBuyTile (index, player, board, gui, game);
                // Som 8, men for Lyseblåt eller Rødt
            case 19:
                gui.displayChanceCard("FREE PROPERTY CARD: BRUN/GUL");
                tileIndex = board.getNextTileColor2("BRUN", "GUL");
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
                int move = gui.getUserInteger("Hvor mange felter vil du flytte frem (1-5)?");
                moveForwardCard.setMove(move);
                moveForwardCard.onDraw(player);
                //Ryk op til 5 felter frem
            case 4:
                gui.displayChanceCard("THE CHOICE IS YOURS CARD");
                String vilDu = gui.getUserSelection("Vil du rykke 1 felt frem eller tage et chancekort mere?", "1 felt frem", "Chancekort");
                if (vilDu.equals("1 felt frem")) {
                    moveForwardCard.setMove(1);
                    moveForwardCard.onDraw(player);
                } else {
                    drawCard(player, gui, board, game);
                }
                //ENTEN ryk 1 felt frem
                //ELLER tag et chancekort mere
            case 6:
                gui.displayChanceCard("TOO MUCH CANDY CARD");
                gui.showMessage("Du har spist for meget slik. Minus 2M :(.");
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
                String jaNej = gui.getUserSelection("Har du fødselsdag i dag?", "Ja", "Nej");
                if(jaNej.equals("Ja")) {
                    moneyCard.setMoney(1);
                    moneyCard.onDraw(player);
                }
                // Ryk frem til Strandpromenaden
                // Fødselsdag: Modtag 1 M
            case 15:
                gui.displayChanceCard("GOOD JOB HOMEWORK CARD");
                gui.showMessage("Du har lavet lektier. Modtag 2M.");
                moneyCard.setMoney(2);
                moneyCard.onDraw(player);
                // Lektier: Modtag 2M
        }
    }

    private void moveBuyTile (int index, Player player, Board board, GUI gui, DiceGame game) {
        sendToCard.setSendTo(index);
        sendToCard.onDraw(player);
        Boolean isVacant = board.isVacant(index);
        if (isVacant) {
            String jaNej = gui.getUserSelection("Du er nu på et orange felt. Vil du købe det?", "ja","nej");
            if (jaNej.equals("ja")) {
                Tile vTile = board.getTile(index);
                PropertyTile pTile = (PropertyTile)vTile;
                if(player.getMoney() >= pTile.getCost()){
                    pTile.setOwnedBy(player.getName());
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
}
