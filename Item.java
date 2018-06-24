import java.util.ArrayList;
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private String name;
    private int weight;
    private boolean canBePickedUp;
    private boolean eatable;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
      this.name = name;
      this.description = description;
      this.weight = weight;
      canBePickedUp = true; 
     
    }
    
    /**
     * Set the item to eatable or not
     * @param condition True if the player should be able to eat the item, 
     * false if not.
     */
    public void setEatable(boolean condition) {
        eatable = condition;
    }
    
    /**
     * Set if the player should be able to pick up the item or not
     * @param can True if able to pick it up, false if not
     */
    public void setPickUpAbility(boolean can) {
        canBePickedUp = can;
    }
    
    /**
     * Return the description of an item
     */
    public String getDescription(){
    return description;
    }
    
    /**
     * Return the name.
     */
    public String getName(){
    return name;
    }
    
    /**
     * Return the weight of an item.
     */
    public int getWeight(){
    return weight;
    }
    
    /**
     * Return the availability of an item to be picked up.
     */
    public boolean pickUp(){
    return canBePickedUp;
    }
    
    /**
     * Returns if the player is able to eat this item or not
     * @return True if eatable, false if not
     */
    public boolean isEatable() {
        return eatable;
    }
    
    /**
     * Return the full description of an item.
     */
    public String itemDescriptionLong(){
    return name + " - " + description + "Weight: " + weight;
    }
    
}