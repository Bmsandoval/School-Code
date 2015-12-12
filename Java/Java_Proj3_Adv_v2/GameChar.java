/*
 * Class: GameChar
 * Purpose: Move character and keep track of inventory
 */
import java.util.*;
import java.lang.*;
import java.io.*;

public class GameChar
{
	private int maxLength;
	private int maxHeight;
	private String[] inventory;
	private int invSize;
	private Map map;
	private int viewDistance;
	
	/*
     * Method: GameChar
     * Input: Scanner object
     * Output: Void
     * Purpose: Initialize class, build inventory, and call create the Map() object.
     */
	GameChar(String input){
		inventory = new String[4];
		viewDistance = 1;
		inventory[0] = "brass lantern";
		inventory[1] = "rope";
		inventory[2] = "rations";
		inventory[3] = "staff";
		invSize = 4;
		map = new Map(input);
		map.Print(viewDistance);
	}
    /*
     * Method: PrintInventory
     * Input: Void
     * Output: Void
     * Purpose: Print the current contents of the inventory array.
     */
	public void PrintInventory(){
		for(int i = 0; i < invSize; i++)
			System.out.println(inventory[i]);
		map.Print(viewDistance);
	}
    /*
     * Method: Go
     * Input: Char containing the lowercase lettered direction (e, w, n, s) that the user wants to go.
     * Output: 1 if move was successful, 0 if move was unsuccessful (user attempts to go out of bounds).
     * Purpose: Try and go the given direction. If it is possible, output the current surroundings. Otherwise output null for error.
     */
    public int Go(char dir) {
        int tRow = map.GetRow();
        int tCol = map.GetCol();
        switch(dir) {
            case 'n':
                tRow = map.GetRow() - 1;
                if(tRow < 0)
                	return 0;
                break;
            case 'e':
                tCol = map.GetCol() + 1;
                if(tCol > map.GetMaxC())
                	return 0;
                break;
            case 's':
                tRow = map.GetRow() + 1;
                if(tRow > map.GetMaxR())
                	return 0;
                break;
            case 'w':
                tCol = map.GetCol() - 1;
                if(tCol < 0)
                	return 0;
                break;
            default:
                return 0;
        }
        System.out.println(tRow + " " + tCol);
        map.Move(tRow, tCol);
        map.Print(viewDistance);
        return 1;
    }
}