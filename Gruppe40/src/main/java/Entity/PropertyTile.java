package Entity;

import Control.DiceGame;
import gui_codebehind.GUI_BoardController;
import gui_main.GUI;

public class PropertyTile extends Tile {

     private int cost;
     private int color;

     public String getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(String ownedBy) {
         this.ownedBy = ownedBy;
     }

     private String ownedBy ="None";
     private String type = "propety";

     public PropertyTile(String name, String text, int cost, int color) {
         super(name, text);
         this.cost = cost;
         this.color = color;

     }


     public void landOn(Player pl, GUI gui) {

         if(this.getOwnedBy() == "None"){
             String choose = gui.getUserSelection("Vil du købe ejendommen: " + this.name +"?", "ja","nej");
             if(choose == "ja" ){
                 if(pl.getMoney() >= this.cost){
                     this.setOwnedBy(pl.getName());
                     pl.addMoney(- this.cost);
                     gui.showMessage("Du er ejer nu: "+ this.name);

                 }else{
                     gui.showMessage("Du har ikke nok penge");
                 }

             }
         }


     }

     public int getCost() {
         return cost;
     }

 }
