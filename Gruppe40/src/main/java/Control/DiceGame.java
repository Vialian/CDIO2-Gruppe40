package Control;

import Entity.Board;
import Entity.Player;
import Entity.Tile;
import Entity.PropertyTile;
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


    private void MakePropertyTile(propetyTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), "" + tile.getCost(), Color.WHITE, Color.BLACK);
    }
    private void MakeChanceTile(propetyTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), Color.WHITE, Color.BLACK);
    }
    private void MakeJailTile(propetyTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), Color.WHITE, Color.BLACK);
    }
    private void MakeUseless(propetyTile tile, int pos)
    {
        guiFields[pos] = new GUI_Street(tile.getName(), "", tile.getText(), Color.WHITE, Color.BLACK);
    }

    public DiceGame() {
        Board board = new Board();

        guiFields = new GUI_Field[TILES_COUNT];
        for (int i = 0; i < TILES_COUNT; i++) {
            Tile tile = board.getTile(i);
            if(tile instanceof propetyTile)
                MakePropertyTile((propetyTile) tile,i);
            if(tile instanceof chanceTile)
                MakeChanceTile((chanceTile) tile,i);
            if(tile instanceof jailTile)
                MakeJailTile((jailTile) tile,i);
            if(tile instanceof useLessTile)
                MakeUseless((uselessTile) tile,i);
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
            players[s] = new Player(playerName + i, 1000);
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


                    Tile tile = board.getTile(roll + currentPosition % board.getBoard().length);
                    if (currentPosition + roll >= board.getBoard().length)
                        PassingStart(currentPlayer);

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
                int toSale = 0, toSale2 = 3;
                int value = 0;
                for (int i = 0; i <= player.getOwnedProperties().length; i++) {
                    if (pl[i].length <= toSale) {
                        if (player.getOwnedProperty[i][0].value >= player.getProperty[toSale][0].value) {
                            toSale = i;
                            if (player.getProperty[toSale][0].value >= player.getProperty[toSale][1].value && pl[toSale].length >= 2) {
                                toSale2 = 0;
                            } else
                                toSale2 = 1;

                        }
                    }
                }
                player.sellTile(toSale, toSale2);
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

        if (board.getTile(pos) instanceof ChanceTile) {
            message = board.getTile(pos).toString();
        }
        else if (board.getTile(pos) instanceof PropertyTile)
        {
            boolean ownedByThemselves = players[currentPlayer].equals(getPlayer(board.getTile(pos).getOwnedBy()));

            if (ownedByThemselves)
            {
                message = "You own it yourself";
                ownedByThemselves = false;
            }

            if (board.getTile(pos).getOwnedBy() == "None")
            {
                message = "you can buy this property";
                //or
                //message = title.toString()}
            }
			else {
                    message = "you owe " + board.getTile(pos).getOwnedBy() + " money, pay : " + board.getTile(pos).getCost();
                }
        }
        else if (board.getTile(pos) instanceof jailTile) {
            message = "go to prusin";
        }
        else{
            if (board.getTile(pos).getName() == "Jailvisit")
                message = "you are visiting the jail";
            else if (board.getTile(pos).getName() == "Parkinglot")
                message = "you are visiting the jail";
            else
                message = "Nothing";
        }


        gui.showMessage(message);


    }
}
