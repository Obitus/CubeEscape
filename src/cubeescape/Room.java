/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeescape;

/*
 * This class represents one location (room) in the scenery (map) of the game.
 * It is connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or "null" if there is no exit in that direction.
 */
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

public class Room {

    public boolean isStart = false; //stores whether this room is the spawn room
    public boolean isExit = false;  //stores wheather this room is the exit of the game
    
    private String name;	// the name of the room (does not need to be an identifier)
    private HashMap exits;	// stores the exits of the room

    /**
     * Create a room described as "name". Initially, it has no exits.
     */
    public Room(String name) {
        this.name = name;
        exits = new HashMap();
    }

    /**
     * Define the exits of this room. Every direction either leads to another
     * room or is "null" (no exit there).
     *
     * @param north room north of this
     * @param east room east of this
     * @param south room south of this
     * @param west room west of this
     * @param top room above of this
     * @param bottom room under of this
     */
    public void setExits(Room north, Room east, Room south, Room west, Room top, Room bottom) {
        if (north != null) {
            exits.put("north", north);
        }
        if (east != null) {
            exits.put("east", east);
        }
        if (south != null) {
            exits.put("south", south);
        }
        if (west != null) {
            exits.put("west", west);
        }
        if (top != null) {
            exits.put("top", top);
        }
        if (bottom != null) {
            exits.put("bottom", bottom);
        }
    }

    /**
     * Return the name of this room.
     */
    public String shortDescription() {
        return name;
    }

    /**
     * Return a long description of this room, on the form: You are in the
     * kitchen. Exits: north, west
     */
    public String longDescription() {
        return "You are in " + name + ".\n" + exitsString();
    }

    /**
     * Return a string describing the possible exits of this room.
     */
    private String exitsString() {
        String returnString = "Exit(s):";
        Set keys = exits.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            returnString += " " + iter.next();
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return "null".
     */
    public Room nextRoom(String direction) {
        return (Room) exits.get(direction);
    }
}
