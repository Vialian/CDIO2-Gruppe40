package Entity;

 public class propetyTile extends Tile {

     private int cost;
     private int rent;

     public String getOwnedBy() {
         return ownedBy;
     }

     public void setOwnedBy(String ownedBy) {
         this.ownedBy = ownedBy;
     }

     private String ownedBy ="None";
     private String type = "propety";

     public propetyTile(String name, String text, int cost, int rent) {
         super(name, text);
         this.cost = cost;
         this.rent = rent;

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
 }
