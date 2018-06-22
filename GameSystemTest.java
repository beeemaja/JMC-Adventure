
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a Game System Tests - it tests the
 * whole game and the endpoints between user input
 * and output to the console.
 *
 * @author  Barne Kleinen
 */
public class GameSystemTest
{
    private Game game;

    public GameSystemTest()
    {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        game = new Game();
    }
    
    @Test
    public void testGo(){
        CommandWords commandWords = new CommandWords();
        CommandWord actual = commandWords.getCommandWord("go");
        assertEquals("the word go", actual,CommandWord.GO);
        
    }

    @Test
    public void testQuit()
    {
       CommandWords commandWords = new CommandWords();
       CommandWord commandWord = commandWords.getCommandWord("quit");
       Command cmd = new Command(commandWord,null);
       boolean quit = game.processCommand(cmd);
       assertTrue("check if game will quit",quit);
    }

    @Test
    public void testHelp()
    {
       String help = game.printHelp();
        assertTrue("should print help message containing command words", help.contains("command words"));
        assertTrue("message contains command word go", help.contains("go"));
        assertTrue("message contains command word quit", help.contains("quit"));
        assertTrue("message contains command word help", help.contains("help"));
    }

    @Test
    public void testUnknownCommand(){
       /* // given arbitrary game
        // when entering unknown command
        String output = game.processCommand("asdf");
        // then an error message should be returned
        assertTrue("should output error message", output.contains("I don't know what you mean"));*/
    }
    @Test
    public void testGoSouth()
    {
       CommandWords commandWords = new CommandWords();
       CommandWord commandWord = commandWords.getCommandWord("go");
       Command cmd = new Command(commandWord,"south");
       boolean goSouth = game.processCommand(cmd);
       assertFalse("check can go south->false",goSouth);
       
    }

    @Test
    public void testGoNorth()
    {
       CommandWords commandWords = new CommandWords();
       CommandWord commandWord = commandWords.getCommandWord("go");
       Command cmd = new Command(commandWord,"north");
       boolean goNorth = game.processCommand(cmd);
       assertTrue("check can go north->true",goNorth);
    }
    
}

