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
     private String name;
     private HashMap<String, Item> itemsYouHeld;
     private int maxWeight;
     
/**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {
       name = "player";
       this.currentRoom = currentRoom;
       itemsYouHeld = new HashMap<String, Item>();
       maxWeight = 75;
    }

/**
     * Get the name of a player.
     * @return The name of a player
     */
    public String getName()
    {
        return name;
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
     * Add item to your bag.
     * @param item The item to be added.
     */
    public void addItemtoBag(Item item){
     itemsYouHeld.put (item.getName(), item);
    }
    
/**
     * Return the items in your bag.
     */
    public Item getItem(String item){
     return itemsYouHeld.get(item);
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
* Return the string about the items that are stored in your bag.
*/
    public String getDescriptionOfItems(){
    String itemsString = "The items in a bag: ";
    if(!itemsYouHeld.isEmpty()){
    Set<String> itemNames = itemsYouHeld.keySet();
    for(String itemName : itemNames) {
    itemsString += "\n"+ itemsYouHeld.get(itemName).itemDescriptionLong();
    }
    itemsString += "Carrying " + getTotalWeightItems() + "of maximum " + 
    maxWeight;
    return itemsString;
    }
    else
    return itemsString += "Is empty";
    }
    
    /**
     * Returns the weight of the items carried.
     */
    
    public int getTotalWeightItems(){
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
* Calculates if the player can carry a given item or not
* @param item The item to be evaluated
* @return true if the player can carry the item, false if not
*/
  public boolean isAbleToCarry(Item item) {
 if (item.getWeight()+getTotalWeightItems() <= maxWeight) {
	return true;
 }
  return false;
 }
 
/**
 * Return the information about the player's location.
 */
public Room getCurrentRoom(){
return currentRoom;
}

/**
	 * Moves the player to another room
	 * @param nextRoom The room to player should be moved to
	 */
public void movePlayer(Room nextRoom) {   

	currentRoom = nextRoom;
		
}
}
