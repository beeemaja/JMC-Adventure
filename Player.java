import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version 19.06.2018
 */
public class Player
{
     private Room currentRoom;
     private HashMap<String, Item> itemsYouHeld;
     final static int MAXWEIGHT = 75;
     private Room previousRoom;
     private int strength;
     private Room safeRoom=null;
     
/**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {
       this.currentRoom = currentRoom;
       itemsYouHeld = new HashMap<String, Item>();
       
       strength=30;
    }
/** 
* Set the Room for the beamer
* @param safeRoom The room which the beamer should return to
 */
public void setSafeRoom(Room safeRoom) {
	this.safeRoom = safeRoom;
} 

/**
* 
* @return The room which the beamer should return to
*/
public Room getSafeRoom() {
	return safeRoom;
}

 /**
  * Enters the specified room and prints the description.
  */
public void enterRoom(Room nextRoom){
    previousRoom = currentRoom;
    currentRoom = nextRoom;
    System.out.println(previousRoom.getLongDescription());
 }

 /**
 * Return the information about the player's previous location.
 */
public Room getPreviousRoom(){
return previousRoom;
}

/**
     * Add item to your inventory.
     * @param item The item to be added.
     */
    public void addItemtoInventory(Item item){
     itemsYouHeld.put (item.getName(), item);
    }
   
/**
     * Return the items in your bag.
     */
    public Item getItem(String item){
     return itemsYouHeld.get(item);
    }
 /**
	 * Removes and item from the players inventory
	 * @param item The item to be removed
	 */
	public void removeItemFromInventory(String item) {
		itemsYouHeld.remove(item);
	}
/**
* Return the string about the items that are
* stored in your inventory.
*/
    public String getDescriptionOfItems(){
    String itemsString = "The items in a bag: ";
    if(!itemsYouHeld.isEmpty()){
    Set<String> itemNames = itemsYouHeld.keySet();
    for(String itemName : itemNames) {
    itemsString += "\n"+ itemsYouHeld.get(itemName).itemDescriptionLong();
    }
    itemsString += "Carrying " + getTotalWeightInventory()
    + "of maximum " + 
    maxWeight;
    return itemsString;
    }
    else
    return itemsString += "Is empty";
    }
  
    /**
     * Returns the weight of the items carried.
     */
    
    public int getTotalWeightInventory(){
    int totalWeight = 0;
    if (!itemsYouHeld.isEmpty()){
    Set<String> itemNames = itemsYouHeld.keySet();
     for(String itemName : itemNames) {
        totalWeight += itemsYouHeld.get(itemName).getWeight();
     }
    }
    return totalWeight;
}
/**
* Increases the strength of the player
* @param increment The increment of the players strength
 */
	public void increaseStrength(double increment)
	{
		this.strength += increment;
	}
/**
 * Return the information about the player's location.
 */
public Room getCurrentRoom(){
return currentRoom;
}

/**
* Check if an item exits in the players inventory or not
* @param itemName The item that should be looked for
* @return True if the item exits, false if not
*/
public boolean isInBag(String itemName)
{
return itemsYouHeld.containsKey(itemName);
}
    
/**
* Calculates if the player can carry a given item or not
* @param item The item to be evaluated
* @return true if the player can carry the item, false if not
*/
  public boolean isAbleToCarry(Item item) {
 if (item.getWeight()+getTotalWeightInventory() <= MAXWEIGHT) {
    return true;
 }
  return false;
 }

/**
     * Drop the item if there are too many items you carry.
     */
    public boolean dropItem(Item item){
        boolean needToDrop = false;
    if (item.getWeight()<=50){
    return needToDrop = true;
    }
    return needToDrop;
    }
    
/**
     * Pick the item up if it is not heavy.
     */
    public boolean pickUpItem(Item item)
    {
        boolean ableToPick = true;
    if (item.getWeight()>200){
        return ableToPick = false;
    }
    return ableToPick;
    }
    
/**
     * Moves the player to another room
     * @param nextRoom The room to player should be moved to
     */
public void movePlayer(Room nextRoom) {   
    previousRoom = currentRoom;
    currentRoom = nextRoom;
    System.out.println(getCurrentRoom().getLongDescription());
}
}
