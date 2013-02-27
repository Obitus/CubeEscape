/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeescape;
/**
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take sword", then the two strings
 * obviously are "take" and "sword").
 * 
 * Commands must have valid command words when they are created, wrt. the
 * following rules:
 * - if the user entered an invalid command (a first word that is not known
 *   in the game commands list) then the command word is "null";
 * - if the command has only one word, then the second word is "null".
 */

public class Command
{
    private String commandWord;
    private String[] parameter;

    /**
     * Create a command object. First and second words must be supplied,
     * but either one or both can be null. The command word is null to
     * indicate that this was a command not recognised by the parser.
     */
    public Command(String commandWord, String[] parameter)
    {
        this.commandWord = commandWord;
        this.parameter = parameter;
    }

    /**
     * Return the command word of this command.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the second word of this command.
     */
    public String getFirstParameter()
    {
        if(parameter != null) {
            return parameter[0];
        }
        
        return null;
    }
    
    /**
     * Returns all parameters of the command
     * @return ordered string array of all parameters
     */
    public String[] getParameters(){
        return parameter;
    }

    /**
     * Return true if this command was not understood, false otherwise.
     */
    public boolean commandIsUnknown()
    {
        return (commandWord == null);
    }

    /**
     * Return true if the command has a second word, false otherwise.
     */
    public boolean hasSecondWord()
    {
        return (parameter != null);
    }
}
