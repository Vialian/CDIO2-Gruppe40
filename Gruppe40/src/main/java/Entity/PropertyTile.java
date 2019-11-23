package Entity;

import Control.DiceGame;
import gui_codebehind.GUI_BoardController;
import gui_main.GUI;

public class PropertyTile extends Tile {

     private int cost;
//     private int colour;
    private final Boolean meIsProperty = true;

     public String getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(String ownedBy) {
         this.ownedBy = ownedBy;
     }

     private String ownedBy = "None";
     private String type = "Property";

     public PropertyTile(String name, String text, int cost, String color) {
         super(name, text, color);
         this.cost = cost;
//         this.colour = colour;
     }
 /*    public void setColour(int colour){
         this.colour = colour;
     }
*//*
     public int getColour(){
         return colour;
     }
*/

     public void landOn(Player pl, GUI gui) {

         if(this.getOwnedBy() == "None"){
             String choose = gui.getUserSelection("Vil du købe ejendommen: " + getName() +"?", "ja","nej");
             if(choose == "ja" ){
                 if(pl.getMoney() >= this.cost){
                     this.setOwnedBy(pl.getName());
                     pl.addMoney(- this.cost);
                     gui.showMessage("Du er ejer nu: "+ getName());

                 }else{
                     gui.showMessage("Du har ikke nok penge");
                 }

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
