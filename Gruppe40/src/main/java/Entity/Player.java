package Entity;

import Entity.Die;

public class Player {

    private int money;
    private String name;
    private Die die;
    private int currentTile;
    private Boolean promisedRealEstate;
    private int releaseCards;

    public int[] getOwnedProperties() {
        return ownedProperties;
    }

    private int[] ownedProperties;
    private int ownedSize = 0;

    // Kun én terning
    // private Die die2;

    public Player(String name, int initialMoney) {
        die = new Die();
        //die2 = new Die();
        this.money = initialMoney;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int p) {
        this.money = p;
    }

    public void addMoney(int p) {
       setMoney(this.money + p);
    }

    public String getName() {
        return this.name;
    }

    /*public void setName(String name){
        setName(this.name);
    }*/

    public int rollDie() {
        int value1 = die.roll();
        //int value2 = die2.roll();
        return currentTile = value1;
    }
    public int getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(int position){
        this.currentTile = position;
    }

    public void addProperty (int position) {
        int[] newArray = new int[ownedSize+1];
        for( int i = 0; i < ownedSize; i++) {
            newArray[i] = ownedProperties[i];
        }
        newArray[ownedSize++] = position;
        ownedProperties = newArray;
    }

    public void removeProperty (int position) {
        int i;
        for (i = 0; i < ownedSize; i++) {
            if (position == ownedProperties[i])
                break;
        }
        if (i == ownedSize)
            return;
        int[] newArray = new int[ownedSize-1];
        for (int j = 0; j < i; j++) {
            newArray[j] = ownedProperties[j];
        }
        for (int j = i; j < ownedSize-1; j++) {
            newArray[j] = ownedProperties[j+1];
        }
        ownedProperties = newArray;
    }

    int[] getProperties(){
        return ownedProperties;
    }

    public int incrementReleaseCards () {
        return this.releaseCards++;
    }

    public int getReleaseCards() {
        return this.releaseCards;
    }

    public Boolean getPromisedRealEstate() {
        return this.promisedRealEstate;
    }

    public void setPromisedRealEstate(Boolean value) {
        this.promisedRealEstate = value;
    }

    /*public int getDieValue1() {
        return die1.getFaceValue();
    }

    public int getDieValue2() {
        return die2.getFaceValue();
    }

    /*public Boolean hasDoubles() {
        return die1.getFaceValue() == die2.getFaceValue();
    }

    public Boolean hasWon() {
        return points >= 3000 /*&& hasDoubleSix();
    }

    public Boolean hasLost(){
        return points < 0;
    }

    /*public Boolean hasDoubleOne() {
        return hasDoubles() && die1.getFaceValue() == 1;
    }

    public Boolean hasDoubleSix() {
        //didDouble6 = true;
        return hasDoubles() && die1.getFaceValue() == 6;
    }*/
}
