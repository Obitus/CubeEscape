/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeescape;

/*
 * This parser reads user's input and tries to interpret it as a command
 * of the game. Every time its method "getCommand()" is called, it reads
 * a line from the terminal and tries to interpret it as a two words command.
 * It returns the command as an object of class Command. (See constraints
 * about the creation of a Command object in the definition of the class.)
 * Remarks:
 * - the second word is not checked at the moment.
 * - if the user entered more than two words, the rest is ignored.
 *
 * The parser has a set of known (valid) command words.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {
    
    /**
     * a constant HashMap that holds all valid command words
     * the connected integer signs how many parameter the command expected
     */
    private static final HashMap<String, Integer> validCommands = new HashMap<String, Integer>() {
        {
            put("go", 0);
            put("quit", 0);
            put("help", 0);
            put("tst", 3);
        }
    };
    
    BufferedReader reader;

    public Parser() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Command getCommand() {
        String inputLine = "";	// holds the full input line
        String command = null;		// holds the first word of the input line, if any
        String[] parameter = null;		// holds the second word of the input line, if any

        // print out the prompt
        System.out.print("> ");

        // read the input line
        try {
            inputLine = reader.readLine();
        } catch (java.io.IOException e) {
            System.out.println("There was an error during reading: "
                    + e.getMessage());
        }

        //extract words from the input line
        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        
        // get first word
        if (tokenizer.hasMoreTokens()) {
            command = tokenizer.nextToken();
        } 

                
        //get all other words
        String[] tmp;
        while(tokenizer.hasMoreTokens()){
            //start condition
            if(parameter == null){
                parameter = new String[1];
                parameter[0] = tokenizer.nextToken();
                
                //every turn after the first
            }else{
                tmp = parameter;
                parameter = new String[tmp.length + 1];                
                System.arraycopy(tmp, 0, parameter, 0, tmp.length);
                parameter[tmp.length] = tokenizer.nextToken();
                
            }
        }
       
        

        // check whether the first word is known as a valid command
        // if so, create a command with it
        // if not, create a "null" command (for unknown command)
        if (isValidCommand(command, parameter)) {
            return new Command(command, parameter);
        } else {
            return new Command(null, null);
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     *
     */
    public boolean isValidCommand(String command, String[] parameter) {
        if(validCommands.containsKey(command)){
            int parameter_count = (parameter == null)?0:parameter.length;
            
            if(validCommands.get(command) == parameter_count){
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print out a list of known command words.
     */
    public void showCommands() {
        for(String command: validCommands.keySet()){
            System.out.print(command + " ");
        }        
        System.out.println();
    }
}
