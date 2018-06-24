
/**
 * Enumeration class CommandWord - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum CommandWord
{
    //A value for each command word
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"),
    EAT("eat"),  TAKE("take"), DROP("drop");
   

// The command string.
    private String commandString;
    
/**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
/**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}