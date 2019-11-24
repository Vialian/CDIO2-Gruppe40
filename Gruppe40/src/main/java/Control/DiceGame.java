package Control;

import Entity.*;
import gui_codebehind.GUI_Center;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;



import java.awt.*;

public class DiceGame {

    private Boolean gameHasEnded;//Den her skal fjernes man kan bare returne når nogen vinder
    private Player[] players;

    private int MAX_PLAYERS = 0;
    private GUI_Player[] guiPlayers;
    private Board board;
    private int TILES_COUNT = 24;
    private GUI_Field[] guiFields;
    private GUI gui;
    private int youngest = 0;
    private int passStart = 2;

    public Player findPlayer(String name) {
        for (Player player: players) {
            if(player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    private void MakePropertyTile(PropertyTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), "" + tile.getCost(), Color.WHITE, Color.BLACK);
    }
    private void MakeChanceTile(ChanceTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), "" + "", Color.WHITE, Color.BLACK);
    }
    private void MakeJailTile(JailTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), "", Color.WHITE, Color.BLACK);
    }
    private void MakeUseless(UselessTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), "", Color.WHITE, Color.BLACK);
    }

    public DiceGame() {
        Board board = new Board();

        guiFields = new GUI_Field[TILES_COUNT];
        for (int i = 0; i < TILES_COUNT; i++) {
            Tile tile = board.getTile(i);
            if(tile instanceof PropertyTile)
                MakePropertyTile((PropertyTile) tile,i);
            if(tile instanceof ChanceTile)
                MakeChanceTile((ChanceTile) tile,i);
            if(tile instanceof JailTile)
                MakeJailTile((JailTile) tile,i);
            if(tile instanceof UselessTile)
                MakeUseless((UselessTile) tile,i);
        }
        GUI_Center guic = GUI_Center.getInstance();


        gui = new GUI(guiFields);

        guic.setBGColor(Color.WHITE);
        guic.setChanceCard("Welcome");

        gui.getUserString("welcome to Hyperdice");
        while (MAX_PLAYERS < 2 && MAX_PLAYERS > 4) {
            try {
                MAX_PLAYERS = Integer.parseInt(gui.getUserString("How many players?"));
            } catch (Exception e) {
                gui.getUserString("fejl, indtast et tal 2-4");
            }

        }


        players = new Player[MAX_PLAYERS];
        guiPlayers = new GUI_Player[MAX_PLAYERS];

        int youngestTemp = 200;
        for (int i = 1; MAX_PLAYERS + 1 >= i; i++) {
            int s = i - 1;
            String playerName = gui.getUserString("Player " + i + ": What is your name?");
            playerName += i;
            players[s] = new Player(playerName + i, 1000, i);
            gui.addPlayer(guiPlayers[s] = new GUI_Player(playerName + i, 1000));
            int playerAge = 0;
            try {
                playerAge = Integer.parseInt(gui.getUserString("Player " + i + ": What is your age?"));
            } catch (Exception e) {
                gui.getUserString("fejl, indtast alder");
            }

            if (youngestTemp > playerAge) {
                youngest = s;
                youngestTemp = playerAge;
            }

        }
        gui.showMessage("Alright, let's get started..., the youngest one " + players[youngest].getName() + " will start");

    }


    public void playDiceGame() {
        gameHasEnded = false;

        while (!gameHasEnded) {


            for (int currentPlayer = youngest; currentPlayer < MAX_PLAYERS && !gameHasEnded; currentPlayer++) {
                Boolean nextPlayer = false;
                while (!nextPlayer) {
                    gui.getUserString(players[currentPlayer].getName() + ": Will you roll your dice?...");

                    int roll = players[currentPlayer].rollDie();

                    int currentPosition = players[currentPlayer].getCurrentTile();


                    //Tile tile = board.getTile(roll + currentPosition % board.getBoard().length);

                    if(roll + currentPosition > board.getBoard().length)
                    {
                        PassingStart(currentPlayer);
                    }


                    updateGui(currentPlayer);




                    showTileMessage(currentPosition, currentPlayer);

                    nextPlayer = doPlayerConditions(players[currentPlayer]);
                }
            }
        }
    }

    private void PassingStart(int player)
    {
        players[player].addMoney(passStart);
    }


    private Boolean doPlayerConditions(Player player) {
        if (player.hasLost()) {
            if (player.getOwnedProperties().length > 0 && player.getMoney() < 0) {
                int[][] pl = new int[8][2];
                int num = 0;
                int amount = 1;
                for (int i = 0; i <= player.getOwnedProperties().length; i++) {
                    for (int s : player.getOwnedProperties()) {
                        if (s == i)
                        {
                            pl[i][num] = amount;
                            num++;
                            amount++;
                            if (amount >= 2)
                                amount = 1;
                        }
                    }

                }
//                int toSale = 0, toSale2 = 3;
//                int value = 0;
//                for (int i = 0; i <= player.getOwnedProperties().length; i++) {
//                    if (pl[i].length <= toSale) {
//                        if (player.getOwnedProperty[i][0].value >= player.getProperty[toSale][0].value) {
//                            toSale = i;
//                            if (player.getProperty[toSale][0].value >= player.getProperty[toSale][1].value && pl[toSale].length >= 2) {
//                                toSale2 = 0;
//                            } else
//                                toSale2 = 1;
//
//                        }
//                    }
//                }
                //player.sellTile(toSale, toSale2);
                sellPropety(player);
            }
            if (player.getMoney() >= 0) {
                hasLost = false;
                return false;
            }

            gui.showMessage(player.getName() + " has lost the game!");
            gameHasEnded = true;
            return true;
        }
        return true;
    }

    private void sellPropety(Player pl){
//        String res = gui.getUserString("Indtast nr på den grund du vil sælge: ");
//        if(res != "nej"){
//            int propety =  Integer.parseInt(res);
//            for (int x : pl.getOwnedProperties()){
//                if(propety == x){
//                    String res2 = gui.getUserSelection("hvem vil du sælge til, Spiller, Banken");
//                    if(res2 == "Spiller"){
//                        String sellTo = gui.getUserString("Skriv navnet på spilleren du vil sælge til");
//                        //sellToPlayer(propety,sellTo, pl);
//                    } else if(res2 == "Banken"){
//                        //pl.addMoney(board.getTileCost(propety));
//                        pl.removeProperty(propety);
//
//                        //postion burde være id;
//                    }
//                }
//            }
        }


        private void updateGui(int currentPlayer) {
        //update all cars
        for (int f = 0; f < TILES_COUNT; f++) {
            guiFields[f].removeAllCars();
        }

        for (int p = 0; p < MAX_PLAYERS; p++) {
            guiFields[players[p].getCurrentTile()].setCar(guiPlayers[p], true);
        }

        guiPlayers[currentPlayer].setBalance(players[currentPlayer].getMoney());
        gui.setDice(players[currentPlayer].getDie().getFaceValue());
    }


    private void showTileMessage(int pos, int currentPlayer) {//Spørg om det er ok at overfører argumenter som man ikke har “strictly” brug for til metoder.
        String message = "You have landed on " + board.getTile(pos).getName() + ": " + board.getTile(pos).getText() + ". ";
        Tile tile=board.getTile(pos);
        if (tile instanceof ChanceTile) {
            message = board.getTile(pos).toString();
        }
        else if (tile instanceof PropertyTile)
        {
            PropertyTile property = (PropertyTile) tile;
            boolean ownedByThemselves = players[currentPlayer].getID() == property.getOwnedBy(); // get player?

            if (ownedByThemselves)
            {
                message = "You own it yourself";
                ownedByThemselves = false;
            }

            if (property.getOwnedBy() == 0)
            {
                message = "you can buy this property";
                //or
                //message = title.toString()}
            }
			else {
                    message = "you owe " + property.getOwnedBy() + " money, pay : " + property.getCost();
                }
        }
        else if (tile instanceof JailTile) {
            message = "go to prusin";
        }
        else{
            if (tile.getName().equals("Jailvisit"))
                message = "you are visiting the jail";
            else if (tile.getName().equals("Parkinglot"))
                message = "you are visiting the jail";
            else
                message = "Nothing";
        }


        gui.showMessage(message);


    }

    public boolean colourPair(int position) {
        // Position på tile på board
        Tile tile = board.getTile(position);
        if (tile instanceof PropertyTile) {
            // Tile colour
            PropertyTile property = (PropertyTile) tile;
            int colour = property.getColour();

            // Ved nedenstående fås spilleren som ejer den pågældende tile
            int player = property.getOwnedBy();


            // while loop til kontrol af tile og colour i den éne retning
            int currentPosition = position;
            while (true) {

                // Ved at sige ++, så inkrenmenteres currentPosition med én vi sørger også for at den aldrig når over 23
                currentPosition= (currentPosition+1)%TILES_COUNT;
                //Vi skal tjekke om vi nogensinde når position I dette loop. Vi behøver ikke at tjekke for det næste da det er umuligt.
                if(currentPosition==position){
                    //Hvis vi når den samme postion betyder det vi aldrig så andre farver, eller properties af samme farve som var ejet af en anden person. Vi returner derfor true.
                    return true;
                }
                Tile nextProperty = board.getTile(currentPosition);

                // Kontrol om næste tile er propertyTile eller ej
                // Hvis tile ikke er en instans af propertyTile, så forsættes eksekvering af koden
                if (!(nextProperty instanceof PropertyTile)) {
                    continue;
                }

                // Kontrol om currentTile har samme farve som næste tile
                // Hvis det er sandt, så break'er if-sætningen
                if (((PropertyTile) nextProperty).getColour() != colour) {
                    break;
                }

                // Nu VIDES det at det at den næste tile er én propertyTile og currentTile og næste tile har samme farve
                // Hvis player er forskellig fra spilleren som ejer tilen, så returneres false
                if (player != ((PropertyTile) nextProperty).getOwnedBy()) {
                    return false;
                }
            }


            // while loop til kontrol af tile og colour i den anden retning
            currentPosition = position;
            while (true) {

                // Ved at sige --, så deinkrementeres currentPosition med én Vi sørger også for at den aldrig når under 0
                currentPosition= (currentPosition-1)%TILES_COUNT;
                Tile previousProperty = board.getTile(currentPosition);

                // Kontrol om næste tile er propertyTile eller ej
                // Hvis tile ikke er en instans af propertyTile, så forsættes eksekvering af koden
                if (!(tile instanceof PropertyTile)) {
                    continue;
                }

                // Kontrol om currentTile har samme farve som næste tile
                // Hvis det er sandt, så break'er if-sætningen / Nu vides  at det er IKKE samme farve på tilen
                if (((PropertyTile) previousProperty).getColour() != colour) {
                    break;
                }

                // Nu VIDES det at det at den næste tile er én propertyTile og currentTile og næste tile har samme farve
                // Hvis player er forskellig fra spilleren som ejer tilen, så returneres false
                if (player != ((PropertyTile) previousProperty).getOwnedBy()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
