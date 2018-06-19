
import java.util.HashMap;
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
    //private Room northExit;
    //private Room southExit;
    //private Room eastExit;
    //private Room westExit;
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
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null) {
            exits.put ("north", north);
        }
        if(east != null) {
            exits.put ("east", east);
        }
        if(south != null) {
            exits.put ("south", south);
        }
        if(west != null) {
            exits.put ("west", west);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * Return the room that is reached if we go fro this room in diretion "direction".
     * If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
    return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits, 
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitsString(){
    String exists = "Exits: ";
    if(northExit != null) {
            exits += "north";
        }
    if(eastExit != null) {
            exits += "east";
        }
    if(southExit != null) {
          exits += "south";
        }
    if(westExit != null) {
            exits += "west";
        }
   }
    
   
    }


