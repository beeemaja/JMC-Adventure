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
    private int hungry = 0;
    private boolean foodFound = false;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(){
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east",theater);
        outside.setExit("south",lab);
        outside.setExit("west",pub);
        theater.setExit("west",outside);
        pub.setExit("east",outside);
        lab.setExit("north",outside);
        lab.setExit("east",office);
        office.setExit("west",lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play(){            
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
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type"+CommandWord.HELP+ "if you need help.");
        System.out.println();
        System.out.println(lookAround());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command){
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        
        switch(commandWord){
            case HELP:
                System.out.println(printHelp());
                break;
            case UNKNOWN:
                System.out.println("Dunno what you mean...");
                break;
            case GEHEN:
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quitGame(command);
                break;
            case LOOK:
                System.out.println(lookAround());
                break;
            case EAT:
                System.out.println(eat());
                break;
        }
        
        hungryStatus();
        return wantToQuit ;
    }

    // implementations of user commands:
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quitGame(Command command){
        if(command.hasSecondWord()) {
            System.out.println("What you want to quit?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private String eat(){
        if(!foodFound){
            return "Find something to eat!";
        }else{
            hungry = 0;
            return "You have eaten and are not hungry anymore";
        }
    }
    
    private void hungryStatus(){
        if(hungry>=5){
            System.out.println("!!!you are hungry...find something to eat!!!");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String lookAround(){
        return currentRoom.getFullDescription();
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public String printHelp(){
        return "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:"
        +"\n"
        + parser.getAllCommands();

    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getFullDescription());
            hungry++;
            if(currentRoom.getDescription().contains("pub")){
                foodFound = true;
                System.out.println("YAY you found some food! now find out how to eat ;)");
            }
        }
    }
}
