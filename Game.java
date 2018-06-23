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
    private Room currentRoom;
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
        Room outside, theater, pub, lab, office, cellar;
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room ("in the cellar");
        // initialise room exits
        //outside.setExits(null, theater, lab, pub);//n e s w
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
       
        theater.setExit("west", outside);
        
        pub.setExit("east", outside);
        pub.addItem("Dragon", "the enemy", 300);
        
        lab.setExit("north",outside);
        lab.setExit("east", office);
        lab.addItem("Sword", "will help you to fight the dragon", 10);
        
        office.setExit("west", lab);
        office.setExit("down", cellar); //set the down/up direction
        
        cellar.setExit("up", office);
        lab.addItem("magic cookie", "boost your energy", 5);
        
        
        
        //add some items to the rooms
        
        
        currentRoom = outside;  // start game outside
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
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param commandLine - the line entered as String
     * @return output of the command
     */
   
   //}

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
        System.out.println(currentRoom.getLongDescription());
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
        Room nextRoom = currentRoom.getExit(direction);
        
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();    
        }
        return result + "\n";
    }
    
    /**
     * Print the current location information.
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
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
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void eat(){
    System.out.println("You have eaten now and you are not hungry any more.");
    }
    
}
