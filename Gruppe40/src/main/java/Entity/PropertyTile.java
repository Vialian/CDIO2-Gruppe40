package Entity;

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

     @Override
     public String landOn() {
         return type;
     }

     public int getCost() {
         return cost;
     }
     public int getRent() {
         return color;
     }
 }
