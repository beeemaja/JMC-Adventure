import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private HashMap<String, Item> items;
    private HashMap <String, Room> exits;
   
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }

   /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * 
     */
    public void setExit(String direction, Room neighbor)
    //(Room north, Room east, Room south, Room west) 
    {
        exits.put(direction, neighbor); //HashMap of direction 
        //that is associa. with a room,
    }
    
   /**
     * Return the room that is reached if we go from this room in diretion "direction".
     * If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
    return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits available, 
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString(){
    String availableExits = "Exits: ";
    Set<String> keys = exits.keySet();
    for(String exit: keys){
    availableExits += " "+ exit;
    }
        return availableExits;
   }
   
   /**
     * Add item to the room created.
     * 
     */
    public void addItem(String name, String description, 
    int weight)
    {
    items.put(name, new Item(name, description, weight));
    }
    
    /**
     * Add item to the room.
     */
    public void addItem(Item item){
    items.put(item.getName(), item);
    }
    
    /**
    * Remove item from the room
    * @param item The name of item to be removed
    */
   public void removeItem(String item){
    items.remove(item);
    }
    
     /**
     * Return the information about the items in the room.
     */
    public Item getItem(String itemName){
    return items.get(itemName);
    }
    
   /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The long description
     */
    public String getLongDescription(){
     String longDescrip;
     
     longDescrip = "You are " + description + " .";
    
     longDescrip += getItemString();
     longDescrip += "\n" +getExitString();
     return longDescrip;
    }
    
    /**
     * Return information about the items in the room.
     */
    public String getItemString(){
    String itemString = " Items: ";
    if (!items.isEmpty()){
    Set<String> itemNames = items.keySet();
    for(String itemName: itemNames){
        itemString += getItem(itemName).itemDescriptionLong();
    }
    return itemString;
    }
    else {
    return itemString + " None.";
    }
    }
    
    /**
	 * Checks if the player is allowed to loot a certaint item
	 * @param item The item to check
	 * @return true if the player is allowed, false if not
	 */
	public boolean isItemCouldPicked(Item item)
	{
		return item.pickUp();
	}


}