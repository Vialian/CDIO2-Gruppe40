package Entity;

import Control.DiceGame;
import gui_codebehind.GUI_BoardController;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class PropertyTile extends Tile {

     private int cost;
     private final Boolean meIsProperty = true;

     public int getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(int ownedBy) {
         this.ownedBy = ownedBy;
     }

     private int ownedBy = 0;
     private String type = "Property";

     public PropertyTile(String name, String text, int cost, String colour) {
         super(name, text, colour);
         this.cost = cost;
     }

     public void landOn(Player player, GUI gui, Board board, DiceGame game) {
         System.out.println("property landOn er kaldt");
         if(this.ownedBy == 0){
             if(player.getMoney() >= this.cost){
                 System.out.println("felt er købt");
                 this.setOwnedBy(player.getID());
                 player.addMoney(- this.cost);
                 player.addProperty(board.getTilePos(this));
                 gui.showMessage("Du er ejer nu: "+ this.getName());
                 GUI_Field fl[] = gui.getFields();
                 fl[board.getTilePos(this)].setTitle(getName() + player.getName()); //skal nok slettet, da anden løsning vil være mere optimal
                 fl[board.getTilePos(this)].setDescription(getText() + player.getName());

             }else{
                 gui.showMessage("Du har ikke nok penge");
                 }

             }
         }


     public int getCost() {
         return cost;
     }
     public void setCost(int cost){
         this.cost=cost;
     }

/*     public String colourString(int tilePosition){

         // Note til mig selv: der skal ikke være "break" i switch case,
         // fordi i dette tilfælde gør "return" det samme som "break"
         // når der returneres string, så "afbrydes" eksekveringen af koden i switch case statementet
         switch(this.colour)
         {
             case 1:
                 return "Brown";

             case 2:
                 return "Light blue";

             case 3:
                 return "Purple";

             case 4:
                 return "Orange";

             case 5:
                 return "Red";

             case 6:
                 return "Yellow";

             case 7:
                 return "Green";

             case 8:
                 return "Blue";

             default:
                 return "No colour";
         }
     }*/
 }
