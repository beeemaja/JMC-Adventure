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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2016.02.29
 */

public class Game  
{
    private Parser parser;
    private Player player;
    
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
        Room outside, boss, ammunition, secretRoom, rest, goal;
        // create the rooms
        outside = new Room("outside the main entrance of the temple");
        boss = new Room("in a boss room. Look out! There's monster sleeping");
        ammunition = new Room("in the amunition room. Find a sword!");
        secretRoom = new Room("in a secretRoom. Find a password!");
        rest = new Room("in the rest room.");
        goal = new Room("in the goal room. Here's your holy grail");
        
        //set the neighbouring rooms
        outside.setExit("east", rest);
        outside.setExit("north", boss);
        outside.setExit("west", ammunition);
       
        boss.setExit("north", goal);
        boss.setExit("south", outside);
        boss.addItem("dragon", "is going to crash you on the way to goal!" + "\n" + 
        "Use your sword to defeat him", 200);
        boss.getItem("dragon").setEatable(false);
        boss.getItem("dragon").setPickUpAbility(false);
        
        ammunition.setExit("south", outside);
        ammunition.setExit("west", secretRoom);
        ammunition.addItem("sword", "helps to defeat dragons", 25);
        ammunition.getItem("sword").setEatable(false);
        ammunition.getItem("sword").setPickUpAbility(true);
        ammunition.addItem("body armour", "protect you from injuries", 25);
        ammunition.getItem("body armour").setEatable(false);
        ammunition.getItem("body armour").setPickUpAbility(true);
        ammunition.addItem("shield", "protect you from dragon's fire", 25);
        ammunition.getItem("shield").setEatable(false);
        ammunition.getItem("shield").setPickUpAbility(true);
        ammunition.addItem("helmet", "protect you from dragon's fire", 25);
        ammunition.getItem("helmet").setEatable(false);
        ammunition.getItem("helmet").setPickUpAbility(true);
        
        
        secretRoom.setExit("east",ammunition);        
        secretRoom.addItem("black box", "the key to the goal room in it", 25);
        secretRoom.getItem("black box").setEatable(false);
        secretRoom.getItem("black box").setPickUpAbility(true);
        
        goal.setExit("south", boss);
        goal.addItem("Holy grail", "You'll save your beloved one", 25);
        goal.getItem("Holy grail").setEatable(true);
        goal.getItem("Holy grail").setPickUpAbility(true);
        
        rest.setExit("west", outside);
        rest.addItem("cookie", " boost your energy", 10);
        rest.getItem("cookie").setEatable(true);
        rest.getItem("cookie").setPickUpAbility(true);
        
        rest.addItem("drink", " drink me! ", 10);
        rest.getItem("drink").setEatable(true);
        rest.getItem("drink").setPickUpAbility(true);
        
       // the location of player
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
        System.out.println("Type" + CommandWord.HELP+ " if you need help.");
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
      
    // implementations of user commands:  
        switch(commandWord){
        case UNKNOWN:
            System.out.println("I don't know what you mean..."); 
            break;
        
        case HELP:
        case HILFE:
            printHelp();
            break;
        
        case GO:
        case GEHEN:
            goRoom(command);
            break;
        
        case LOOK:
        case SCHAUEN:
            look();
            break;
        
        case EAT:
        case ESSEN:
            eat(command);
            break;
            
        case TAKE:
        case NEHMEN:
             take(command);
             break;
             
        case DROP:
        case ABNEHMEN:
             drop(command);
             break;
        
        case QUIT:
        case BEENDEN:
            wantToQuit = quit(command);
            break;
            
        case BACK:
        case ZURÜCK:
            goBack(command);
            break;
    }
    return wantToQuit;
 }

 /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println ( "You are lost. You are alone. You wander"
        +"\n"
        + "around at the temple."
        +"\n"
        +"\n"
        +"Your command words are: "
        + parser.showCommandList() );
    }

 /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        // Try to leave current room.
        
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        String result = "";
        if (nextRoom == null) {
            System.out.println("There is no door!");
            look();
        }
        else {
            player.movePlayer(nextRoom);
        }  
    }
    
 /**
  * Let the player return to the previous room.
  */ 
 private void goBack(Command command){
     Room prevRoom = player.getPreviousRoom();
     player.movePlayer(prevRoom);
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
    System.out.println("You don't have this item!");
    return;
        }
   //Check if the player is allowed to eat this item
  if (!player.getItem(item).isEatable()){
    System.out.println("You cant eat this item!");
    return;
        }
        System.out.println("You eat a magic cookie and feel much stronger!");
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
        //Check if the item exists in the current room
if (itemToBePicked==null) {
    System.out.println("This item doesn't exist in this room");
            return;
        }
        //Check if items is allowed to be taken
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
  

//}
}
