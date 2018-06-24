/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game  
{
    private Parser parser;
    private Player player;
    private Item item;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, boss, amunition, secretRoom, rest, goal;
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        boss = new Room("in a boss room. Look out! There's monster sleeping");
        amunition = new Room("in the amunition room. Find a sword!");
        secretRoom = new Room("in a secretRoom. Find a passsword!");
        rest = new Room("in the rest room.");
        goal = new Room("in the goal room. Here's your holy grail");
        //office = new Room("in the computing admin office");
        //cellar = new Room ("in the cellar");
        // initialise room exits
        //outside.setExits(null, theater, lab, pub);//n e s w
        outside.setExit("east", rest);
        outside.setExit("north", boss);
        outside.setExit("west", amunition);
       
        boss.setExit("north", goal);
        boss.setExit("south", outside);
        boss.addItem("Dragon", "'s sleeping now." + "\n" + 
        "Use your sword to defeat him", 200);
        boss.getItem("Dragon").setEatable(false);
        
        amunition.setExit("south", outside);
        amunition.setExit("west", secretRoom);
        amunition.addItem("Sword", "helps to defeat dragons", 25);
        boss.getItem("Sword").setEatable(false);
        
        secretRoom.setExit("east",amunition);        
        secretRoom.addItem("password", "the key to the goal room", 25);
        
        goal.setExit("south", boss);
        goal.addItem("Holy grail", "You'll save your beloved one", 25);
        boss.getItem("Holy grail").setEatable(true);
        
        rest.setExit("west", outside);
        rest.addItem("magic cookie", "boost your energy", 25);
        boss.getItem("magic cookie").setEatable(true);
   
       player = new Player(outside);  // start game outside
    }

 /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

 /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord){
        case UNKNOWN:
            System.out.println("I don't know what you mean..."); 
            break;
        
        case HELP:
            printHelp();
            break;
        
        case GO:
            goRoom(command);
            break;
        
        case LOOK:
            look();
            break;
        
        case EAT:
            eat();
            break;
            
        case TAKE:
             take();
             break;
             
        case DROP:
             drop();
             break;
        
        case QUIT:
            wantToQuit = quit(command);
            break;
        
    }
    return wantToQuit;
 }

    // implementations of user commands:
 /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println ( "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:");
        parser.showCommandList();
    }

 /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            player.movePlayer(nextRoom);
            printLocationInfo();    
        }
        return result + "\n";
    }
  /**
   * Let the player eat the item if it is eatable.
   */  
 private void eat(Command command) {
    //Checks if there is a second word or not!
   if(!command.hasSecondWord()) {
	System.out.println("Eat what?");
	return;
		}
		String item = command.getSecondWord();
		//Check if the player has this item
   if (!player.isInBag(item)) {
	System.out.println("You don?t have this item!");
	return;
		}
   //Check if the player is allowed to eat this item
  if (!player.getItem(item).isEatable()){
	System.out.println("You cant eat this item!");
	return;
		}
  if (player.getItem(item).equals("cookie"))
			{
	
	System.out.println("You eat a magic cookie and feel much stronger!");

	return;
			}
    }
/**
 * Take the item in the current room.
 */
  private void take(Command command){
if(!command.hasSecondWord()) {
			System.out.println("Take what?");
			return;
		}
 String item = command.getSecondWord();
 Room currentRoom = player.getCurrentRoom();
 Item itemToBePicked = currentRoom.getItem(item);
		//Check if the item exists
if (itemToBePicked==null) {
	System.out.println("This item doesn?t exist in this room");
			return;
		}
		//Check if items is allowd to be looted
if (!currentRoom.isItemCouldPicked(itemToBePicked)) {
	System.out.println("You are not allowed to take this item");
			return;
		}
if (!player.isAbleToCarry(currentRoom.getItem(item)))
		{
	System.out.println("This item is to heavey for you to pick up!");
			return;
		}
 player.addItemtoBag(itemToBePicked);
 System.out.println("You successfully took " + item);
}
/**
 * Drop the items if the bag is too heavy.
 */		
private void drop(Command command){
if(!command.hasSecondWord()) {
			System.out.println("Drop what?");
			return;
		} 
		String item = command.getSecondWord();
		Room currentRoom = player.getCurrentRoom();
		//Check if the item exists
if (!player.isInBag(item)) {
			System.out.println("You dont have this item!");
			return;
		}
		currentRoom.addItem(player.getItem(item));
		
		System.out.println("You successfully dropped " + item);

	}
	
/**
     * Print the current location information.
     */
    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
/**
 * Display the items the player is carrying.
 */
private void showItems(){
System.out.println(player.getDescriptionOfItems());
}

/** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println ("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
/**
     * Print the description of the room and available exits.
     */
    private void look(){
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
/**
 * Print the result if you eat the cookie.
 */   
private void eat(){
    System.out.println("You have eaten now and you are not hungry any more.");
    }
    
/**
 * If you are able to pick the item, print the result.
 */
private void take(){
    System.out.println("The item is taken");
}

private void drop(){
    System.out.println("The item is dropped");
}
}
