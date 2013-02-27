/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeescape;

/**
 *
 * @author Patrick
 */
public class MapGenerator {

    /*
     * Constants for the difficulty level.  
     */
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    /*
     * Constants for the cube length depending on difficulty
     * 
     */
    public static final int CUBE_LENGTH_EASY = 9;
    public static final int CUBE_LENGTH_MEDIUM = 16;
    public static final int CUBE_LENGTH_HARD = 25;

    public static Room returnFixedMap() {
        /**
         * Create all the rooms and link their exits together.
         */
        Room roomA, roomB, roomC, roomD, roomE, roomF, roomG;	// the five rooms of the sample map

        // create the rooms
        roomA = new Room("Room A");
        roomB = new Room("Room B");
        roomC = new Room("Room C");
        roomD = new Room("Room D");
        roomE = new Room("Room E");
        roomF = new Room("Room E");
        roomG = new Room("Room E");

        // initialise room exits: north, east, south, west
        roomA.setExits(roomB, roomC, roomD, roomE, roomF, roomG);
        roomB.setExits(null, null, roomA, null, null, null);
        roomC.setExits(null, null, null, roomA, null, null);
        roomD.setExits(roomA, null, null, null, null, null);
        roomE.setExits(null, roomA, null, null, null, null);
        roomF.setExits(null, null, null, null, null, roomA);
        roomG.setExits(null, null, null, null, roomA, null);

        // start the game in Room A
        return roomA;
    }

    
    /**
     * Generates a random world. Size depending depending on the difficulity
     * level
     *
     * @param difficulty difficulity level of the game
     * @return start room of the world
     */
    public static Room generateRandomMap(int difficulty) {
        int cube_length = getCubeLength(difficulty);
        Room[][][] cube = generateRoomArray(cube_length);
        setStartAndExitRoom(cube);
        setRoomConnections(cube);
        
        return returnFixedMap();
    }

    
    /**
     * Returns the side length of the cube we want to generate
     *
     * @param difficulty difficulty level of the game
     * @return side length of the cube
     */
    private static int getCubeLength(int difficulty) {
        switch (difficulty) {
            case EASY:
                return CUBE_LENGTH_EASY;
            case MEDIUM:
                return CUBE_LENGTH_MEDIUM;
            case HARD:
                return CUBE_LENGTH_HARD;
        }
        return CUBE_LENGTH_EASY;
    }

    /**
     * Creates an three demensional array of rooms.      
     * @param cube_length length of the side of the cube
     * @return room array
     */
    private static Room[][][] generateRoomArray(int cube_length) {
        Room[][][] cube = new Room[cube_length][cube_length][cube_length];

        for (int i = 0; i < cube_length; i++) {
            for (int j = 0; j < cube_length; j++) {
                for (int k = 0; k < cube_length; k++) {
                    cube[i][j][k] = new Room("[" + i + "][" + j + "][" + k + "]");
                }
            }
        }
      
        return cube;
    }

    public static void newReallyImportantTestMethodeWithNoUse(){
        
        
        
        /*
         * 
         * 
         * 
         */
    }
    
    /**
     * Set one room of the cube as start room and one as end room
     * @param cube cube which will be modified
     */
    private static void setStartAndExitRoom(Room[][][] cube) {
        //generate coordinates for start
        int x = (int) Math.random()*cube.length;
        int y = (int) Math.random()*cube.length;
        int z = (int) Math.random()*cube.length;
        
        cube[x][y][z].isStart = true;
        
        //generate as long coordinates for exit till they are not equal to the 
        //start coordinates
        do{
        x = (int) Math.random()*cube.length;
        y = (int) Math.random()*cube.length;
        z = (int) Math.random()*cube.length;
        }while(!cube[x][y][z].isStart);
        
        cube[x][y][z].isExit = true;
    }
    
    
    /**
     * Creates the connections between the rooms
     * @param cube 
     */
    private static void setRoomConnections(Room[][][] cube) {
        
    }
    
    
    /**
     * Make some changes in the world, like switching the place of two rooms
     *
     * @param current room current room of the player
     * @return current room of the player
     */
    public static Room generateRandomMapChanges(Room currentroom) {
        return currentroom;
    }

    
}
