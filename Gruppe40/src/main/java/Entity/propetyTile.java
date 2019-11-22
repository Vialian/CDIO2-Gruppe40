package Entity;

 public class propetyTile extends Tile {

     private int cost;
     private int rent;
     private int colour;


     public String getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(String ownedBy) {
         this.ownedBy = ownedBy;
     }

     private String ownedBy ="None";
     private String type = "propety";

     public propetyTile(String name, String text, int cost, int rent, int colour) {
         super(name, text);
         this.cost = cost;
         this.rent = rent;
         this.colour = colour;

     }

     public void setColour(int colour){
         this.colour = colour;
     }

     public int getColour(){
         return colour;
     }

     @Override
     public String landOn() {
         return type;
     }

     public int getCost() {
         return cost;
     }
     public int getRent() {
         return rent;
     }
     public String colourString(int tilePosition){

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


     }
 }
