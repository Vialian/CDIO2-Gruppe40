package Control;

import Entity.Board;
import Entity.Player;
import Entity.Tile;
import Entity.propetyTile;
import com.sun.deploy.panel.IProperty;
import gui_codebehind.GUI_Center;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;



import java.awt.*;

public class DiceGame {

    private Boolean gameHasEnded;
    private Player[] players;
    private final int MAX_PLAYERS = 2;
    private GUI_Player[] guiPlayers;
    private Board board;
    private final int TILES_COUNT = 12;
    private GUI_Field[] guiFields;
    private GUI gui;


    public DiceGame() {
        createBoard();

        guiFields = new GUI_Field[TILES_COUNT];
        for(int i = 0; i < TILES_COUNT; i++){
            Tile tile = board.getTile(i);
            guiFields[i] = new GUI_Street(tile.getTitle(),"", tile.getFlavourText(),"" + tile.getGoldValue(), Color.WHITE, Color.BLACK);
        }
        GUI_Center guic = GUI_Center.getInstance();



        gui = new GUI(guiFields);

        guic.setBGColor(Color.WHITE);
        guic.setChanceCard("Velkommen");

        gui.showMessage("Welcome to HyperDice, earthlings! :-D");

        players = new Player[MAX_PLAYERS];
        guiPlayers = new GUI_Player[MAX_PLAYERS];

        String playerName1 = gui.getUserString("Player " + (1) + ": What is your name?");
        players[0] = new Player(playerName1, 1000);
        gui.addPlayer(guiPlayers[0] = new GUI_Player(playerName1, 1000));

        String playerName2 = gui.getUserString("Player " + (2) + ": What is your name?");
        while(playerName1.equals(playerName2)) {
            playerName2 = gui.getUserString("Player " + (2) + ": Type a different name. Please.");
        }
        players[1] = new Player(playerName2, 1000);
        gui.addPlayer(guiPlayers[1] = new GUI_Player(playerName2, 1000));
        gui.showMessage("Alright, let's get started...");
    }

    public void createBoard(){
        board = new Board(12);

        board.addTile("theBlackHole", "Man kan ikke slå 1 med to terninger", 0);
        board.addTile("Tower", "The TwinTowers have been destroyed", 250);
        board.addTile("Crater", "The Crater smells bad, but looks great", -100);
        board.addTile("Palace gates", "The Palace gates looks good but it is not", 100);
        board.addTile("Cold Desert", "The Cold Desert is not cold it is warm", -20);
        board.addTile("Walled City", "Walled City is not very walled", 180);
        board.addTile("Monastery", "Monastery is full of bad boys", 0);
        board.addTile("Black Cave", "Black caves are good ;)", -70);
        board.addTile("Huts in the mountain", "Huts in the mountain sucks!", 60);
        board.addTile("The Warewall", "The werewolf-walls are full of monkeys", -80); //ekstratur
        board.addTile("The Pit", "Let's have beer!", -50);
        board.addTile("Goldmine", "No gold in the mine, sorry!", 650);

    }

    public void playDiceGame() {
        gameHasEnded = false;

        while (!gameHasEnded) {
            for(int currentPlayer = 0; currentPlayer < MAX_PLAYERS && !gameHasEnded; currentPlayer++) {
                Boolean nextPlayer = false;
                while (!nextPlayer) {
                    gui.getUserString(players[currentPlayer].getName() + ": Will you roll your dice?...");

                    int roll = players[currentPlayer].rollDice();
                    Tile tile = board.getTile(roll-1);
                    System.out.println(currentPlayer);
                    //players[currentPlayer].addPoints(tile.getGoldValue());
                    updateGui(currentPlayer);
                    showTileMessage(tile);
                    //lander på tile
                    processType(tile, players[currentPlayer]);
                    nextPlayer = doPlayerConditions(players[currentPlayer]);
                }
            }
        }
    }

    private <tile extends Tile> void processType( tile, Player pl){
        String type = tile.landOn();
        if(type == "propety")
            prossPropety(tile, pl);
        else if(type == "chance")
            prossChance(tile);
        else if(type == "jail")
            prossJail(tile);
    }

    private void prossChance(Tile tile) {

    }

    private void prossPropety(propetyTile tile, Player pl) {
        if(tile.getOwnedBy() == "None"){
            String choose = gui.getUserSelection("Vil du købe ejendommen: " + tile.getName()+"?", "ja","nej");
            if(choose == "ja" ){
                if(pl.getPoints() >= tile.getCost()){
                    tile.setOwnedBy(pl.getName());
                    pl.addPoints(- tile.getCost());
                    gui.showMessage("Du er ejer nu: "+ tile.getName());

                }else{
                    gui.showMessage("Du har ikke nok penge");
                }

            }
        }

    }

    private void prossJail(Tile tile) {

    }

    private Boolean doPlayerConditions(Player player) {
        if (player.hasWon()) {
            gui.showMessage(player.getName() + " has won the game!");
            gameHasEnded = true;
            return true;
        } else if (player.hasLost()){
            gui.showMessage(player.getName() + " has lost the game!");
            gameHasEnded = true;
            return true;
        } else if (player.getCurrentTile() == 9) {
            gui.showMessage(player.getName() + " has won an extra throw! :-D");
            return false;
        }
        return true;
    }

    private void sellPropety(Player pl){
        String res = gui.getUserString("Indtast nr på den grund du vil sælge: ");
        if(res != "nej"){
            int propety =  Integer.parseInt(res);
            for (int x : pl.getOwnedProperties()){
                if(propety == x){
                    String res2 = gui.getUserSelection("hvem vil du sælge til, Spiller, Banken");
                    if(res2 == "Spiller"){
                        String sellTo = gui.getUserString("Skriv navnet på spilleren du vil sælge til");
                        sellToPlayer(propety,sellTo, pl);
                    } else if(res2 == "Banken"){
                        pl.addMoney(board.getTileCost(propety));
                        pl.removeProperty(propety);

                        //postion burde være id;
                    }
                }
            }
        }
    }

    private void sellToPlayer(int propety, String sellTo, Player sellFrom) {
        String res2 = gui.getUserSelection(sellTo + " vil du købe?","ja","nej");
        if(res2 == "ja"){
            for(Player pl : players){
                int cost = board.getTileCost(propety);
                if(sellTo == pl.getName() && pl.getMoney() >= cost){
                    pl.addMoney(-cost);
                    sellFrom.addMoney(cost);
                    sellFrom.removeProperty(propety);
                    pl.addProperty(propety);
                }
            }
        }


    }

    private void updateGui(int currentPlayer) {
        //update all cars
        for(int f = 0; f < TILES_COUNT; f++) {
            guiFields[f].removeAllCars();
        }

        for (int p = 0; p < MAX_PLAYERS; p++) {
            guiFields[players[p].getCurrentTile()].setCar(guiPlayers[p], true);
        }

        guiPlayers[currentPlayer].setBalance(players[currentPlayer].getPoints());
        gui.setDice(players[currentPlayer].getDieValue1(), players[currentPlayer].getDieValue2());
    }

    private void showTileMessage(Tile tile) {
        String message = "You have hit " + tile.getTitle() + ": " + tile.getFlavourText() + ". ";
        int gold = tile.getGoldValue();
        if(gold > 0)
            message += "You get " + gold + " points! :-D";
        else if(gold < 0)
            message += "You loose " + gold + " points... :(";
        gui.showMessage(message);
    }
}
