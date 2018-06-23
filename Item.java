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

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
      this.name = name;
      this.description = description;
      this.weight = weight;
      
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
     * Return the full description of an item.
     */
    public String itemDescriptionLong(){
    return name + " - " + description + "Weight: " + weight;
    }
    
}