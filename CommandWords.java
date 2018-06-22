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
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    public CommandWord getCommandWord(String aString){
        for(CommandWord cw : CommandWord.values()) { //.values() on the enum list to get all the values
            if(cw.toString().toLowerCase().equals(aString))
                return cw;
        }
        return CommandWord.UNKNOWN;
    }
    
    public String getCommands(){
        String words ="";
        for(CommandWord cw : CommandWord.values()) { //.values() on the enum list to get all the values
            if(!cw.toString().equals("?")){
                words +="\n"+ cw.toString().toLowerCase();  
            }
        }
        return words;
    }
}
