package Entity;

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

    public void drawCard(Player player, GUI gui, Board board) {
        performAction(index++, player, gui, board);
        if (index == 20)
            index = 0;
    }
    
    private void performAction(int index, Player player, GUI gui, Board board) {
        switch(index) {
            case 0:
                drawCard(player, gui, board); //Tage et chancekort mere
                Player bil = board.findPlayer("BIL");
                bil.setPromisedRealEstate();
                //Giv kort til bilen
                //Til BIL:
                //På næste tur skal du flytte til ledigt felt og køb det
                //HVIS Ingen ledige: Køb af anden spiller
            case 5:
                drawCard(player, gui, board); //Tage et chancekort mere
                Player bil = board.findPlayer("SKIB");
                bil.setPromisedRealEstate();
                // Samme, men for SKIBET
            case 11:
                drawCard(player, gui board); //Tage et chancekort mere
                Player bil = board.findPlayer("KAT");
                bil.setPromisedRealEstate();
                // Som 5, men til KAT
            case 12:
                drawCard(player, gui board); //Tage et chancekort mere
                Player bil = board.findPlayer("HUND");
                bil.setPromisedRealEstate();
                // Som 5 men til HUND

            case 3:
                int tileIndex = board.getNextTile("ORANGE");
                moveBuyTile (index, player, board, gui);
                //Ryk frem til et orange felt
                //HVIS ledigt: Modtag felt gratis
                //ELLERS betal leje til ejeren
            case 7:
                tileIndex = board.getNextTile("ORANGE|GRØN");
                moveBuyTile (index, player, board, gui);
                //GRATIS FELT
                //Ryk frem til ORANGE eller GRØNT felt
                //HVIS ledigt: Modtag felt
                //ELLERS betal leje
            case 8:
                tileIndex = board.getNextTile("LYSEBLÅ");
                moveBuyTile (index, player, board, gui);
                // Samme, men for LYSEBLÅT felt
            case 14:
                tileIndex = board.getNextTile("PINK|LYSEBLÅ");
                moveBuyTile (index, player, board, gui);
                // som 7, men for pink eller Lyseblåt
            case 16:
                tileIndex = board.getNextTile("RØD");
                moveBuyTile (index, player, board, gui);
                // som 8, men for Rødt
            case 17:
                tileIndex = board.getNextTile("Skaterparken");
                moveBuyTile (index, player, board, gui);
                //Skaterparken: Ryk til Sk.
                // HVIS ledigt: Modtag gratis
                // ELLERS betal leje til ejer
            case 18:
                tileIndex = board.getNextTile("LYSEBLÅ|RØD");
                moveBuyTile (index, player, board, gui);
                // Som 8, men for Lyseblåt eller Rødt
            case 19:
                tileIndex = board.getNextTile("BRUN|GUL");
                moveBuyTile (index, player, board, gui);
                // Som 8, men for Brunt eller Gult

            case 1:
                sendToCard.setSendTo(0);
                sendToCard.onDraw(player);
                //Ryk frem til start
                //Modtag 2M
            case 2:
                int move = gui.getUserInteger("Hvor mange felter vil du flytte frem (1-5)?");
                moveForwardCard.setMove(move);
                moveForwardCard.onDraw(player);
                //Ryk op til 5 felter frem
            case 4:
                String vilDu = gui.getUserSelection("Vil du rykke 1 felt frem eller tage et chancekort mere?", "1 felt frem", "Chancekort");
                if (vilDu.equals("1 felt frem")) {
                    moveForwardCard.setMove(1);
                    moveForwardCard.onDraw(player);
                } else {
                    drawCard(player, gui, board);
                }
                //ENTEN ryk 1 felt frem
                //ELLER tag et chancekort mere
            case 6:
                moneyCard.setMoney(-2);
                moneyCard.onDraw(player);
                //Du har spist for meget slik
                //Betal 2M til banken

            case 9:
                player.incrementReleaseCards();
                // LØSLADELSESKORT
            case 10:
                tileIndex = board.getNextTile("Strandpromenaden");
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
                gui.showMessage("Du har lavet lektier. Modtag 2M.");
                moneyCard.setMoney(2);
                moneyCard.onDraw(player);
                // Lektier: Modtag 2M
        }
    }

    private void moveBuyTile (int index, Player player, Board board, GUI gui) {
        sendToCard.setSendTo(index);
        sendToCard.onDraw(player);
        Boolean isVacant = board.isVacant(index);
        if (isVacant) {
            String jaNej = gui.getUserSelection("Du er nu på et orange felt. Vil du købe det?", "ja","nej");
            if (jaNej.equals("ja")) {
                if(player.getPoints() >= tile.getCost()){
                    tile.setOwnedBy(pl.getName());
                    player.addPoints(-tile.getCost());
                    gui.showMessage("Du ejer nu det orange felt.");
                } else {
                    gui.showMessage("Du har desværre ikke penge nok til at købe feltet.");
                }
            }
        }
    }
}


