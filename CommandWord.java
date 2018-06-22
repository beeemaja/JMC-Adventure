
/**
 * Write a description of class CommandWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public enum CommandWord{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"),LOOK("look"), EAT("eat"),
    GEHEN("gehen"), HILFE("hilfe"), BEENDEN("beenden");
    //-> non valid command as value
    
    private String commandWord;
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandWord){
        this.commandWord = commandWord;
    }
        
        /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandWord;
    }
}
