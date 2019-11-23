package Entity;

 public class PropertyTile extends Tile {

     private int cost;
     private int colour;


     public String getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(String ownedBy) {
         this.ownedBy = ownedBy;
     }

     private String ownedBy = "None";
     private String type = "Property";

     public PropertyTile(String name, String text, int cost, int colour) {
         super(name, text);
         this.cost = cost;
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
     public void setCost(int cost){
         this.cost=cost;
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
