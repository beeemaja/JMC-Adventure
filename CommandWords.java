import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    //private static final String[] validCommands = {
    //    "go", "quit", "help", "look", "eat"
    private HashMap<String, CommandWord> validCommands;
 

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }
    
    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        for(CommandWord command : CommandWord.values())
        {
        if(command.toString().toLowerCase().equals(commandWord)) 
            return command;
        }
        return CommandWord.UNKNOWN;
    }
    
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
       
    }
    
    /**
     * Print out valid commands
     */
    public String getCommandList(){
        String returnList = " ";
        for(CommandWord command : CommandWord.values()){
        if(!command.toString().equals("?")){
            returnList += " " + command.toString().toLowerCase();
        }
    }
        return returnList;
    }
}
