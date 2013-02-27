/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeescape;

/**
 * This class is the main class of the game application. This is a very simple,
 * text based adventure game. Users can walk around some scenery. That's all. It
 * should really be extended to make it more interesting!
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser to read and "understand" the user's commands and starts
 * the game. It also evaluates the user's commands that the parser returns,
 * until a "quit" command is thrown.
 */
public class CubeEscape {

    private Parser parser;		// to read and "understand" the user's commands
    private Room currentRoom;		// where the user currently is in the game field
    private boolean finished;		// equals true when the game is over

    /**
     * Create the game, initialise its internal map and a commands parser
     */
    public CubeEscape() {
        currentRoom = MapGenerator.generateRandomMap(MapGenerator.CUBE_LENGTH_HARD);
        parser = new Parser();
        finished = false;
        System.exit(0);
    }

    

    /**
     * Main game routine. Loops until end of play.
     */
    public void playGame() {
        Command command;		// the last command entered by the user

        // some hello words...
        System.out.println();
        printWelcome();
        // repeatedly read commands and execute them until the game is over
        while (!finished) {
            System.out.println();
            System.out.println(currentRoom.longDescription());
            System.out.println();
            command = parser.getCommand();
            processCommand(command);
        }
        // some bye bye words...
        printGoodbye();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("Welcome to the BINFO adventure game!");
        System.out.println("Type 'help' if you need help.");
    }

    /**
     * Print out the good bye message for the player.
     */
    private void printGoodbye() {
        System.out.println();
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Given a command, process it, if possible.
     */
    private void processCommand(Command command) {
        if (command.commandIsUnknown()) {
            System.out.println("I don't know what you mean...");
        } else {
            String commandWord = command.getCommandWord();
            // process all possible commands
            if (commandWord.equals("help")) {
                printHelp();
            } else if (commandWord.equals("go")) {
                if (!command.hasSecondWord()) {
                    System.out.println("Go where?");
                } else {
                    goDirection(command);
                }
            } else if (commandWord.equals("quit")) {
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                } else {
                    finished = true;
                }
            }
        }
    }

    /**
     * Print out some help information.
     */
    private void printHelp() {
        System.out.println("Your available command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to a given direction from the current room. If there is an exit
     * to that direction, enter the new room. Otherwise print an error message.
     */
    private void goDirection(Command command) {
        String direction = command.getFirstParameter();
        Room nextRoom = currentRoom.nextRoom(direction);

        // try to leave current room
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
        }
    }
    
    
    
    public static void main(String[] args){
        CubeEscape ce = new CubeEscape();
        ce.playGame();
    }
}
