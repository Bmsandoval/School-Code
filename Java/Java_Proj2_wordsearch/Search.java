/*
 * Name: Class Search
 * Input: File name containing an x by x grid, followed by a blank line, followed by a list of words
 * Output: Void
 * Purpose: Search for words withing the grid. If found print the word, starting location, and ending
 *        location, in the same order that they were found in file. If not found print an error
 *        for that word.
 */
import java.util.*;
import java.io.*;
/**
 *
 * @author Sandman
 */
public class Search {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        
        
    	Scanner fin = null;
    	// Test file to see if found
        try
    	{
            //*************************************************************************************fix input
        	fin = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException x)
        {
        	System.out.println("Error: " + x);
        	System.exit(0);
        }
        
        // Get first line of array to determine grid size, create 2d array and store grid
        String line;
        line = fin.nextLine();
        char [][] square;
        int size = line.length();
    	square = new char[size][size];
        System.out.println(line);
        for(int i = 0; i < line.length(); i++)
            square[0][i] = line.charAt(i);
        int tInt = 1;
        while(fin.hasNextLine())
        {
            line = fin.nextLine();
            if(line.equals(""))
                break;
            System.out.println(line);
            for(int i = 1; i < line.length(); i++)
            {
                square[tInt][i] = line.charAt(i);
            }
            tInt++;
        }
        
        // Get list of words
        ArrayList<String> listArray = new ArrayList();
        while(fin.hasNextLine())
        {
            line = fin.nextLine();
            listArray.add(line);
        }
        String loc;
        String[] locArray = new String[listArray.size()];
        // Begin searching square 
        System.out.println("");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {

                for(int t = 0; t < listArray.size(); t++) {
                    String temp = listArray.get(t);
                    // test first letter of each word to the selected array pt
                    if(square[i][j] == temp.charAt(0)) {
                        // once words discovered, test each direction.
                        // If matching letter is found, test all the way through that direction with the Find function.
                        // If not found, test more directions
                        
                        // Starts Top left corner of grid
                        if(i <= 0 && j <= 0)
                        {
                            // East
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + i + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            // South East
                            if(square[i+1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "se", square, i+1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            // South
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + j + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts bottom right corener of grid
                        else if(i >= size-1 && j >= size-1)
                        {
                                //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + j + " end: " + loc);
                                    break;
                                }
                            }
                            //north west
                            if(square[i-1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "nw", square, i-1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts top right corner of grid
                        else if(i <= 0 && j >= size-1)
                        {
                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south west
                            if(square[i+1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "sw", square, i+1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts bottom left corner of grid
                        else if(i >= size-1 && j <= 0)
                        {
                            //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north east
                            if(square[i-1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "ne", square, i-1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i-1) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //east
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts top of grid
                        else if(i <= 0)
                        {
                            //east
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south east
                            if(square[i+1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "se", square, i+1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south west
                            if(square[i+1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "sw", square, i+1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        
                        }
                        // Starts Botton of grid
                        else if(i >= size-1)
                        {

                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north west
                            if(square[i-1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "nw", square, i-1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north east
                            if(square[i-1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "ne", square, i-1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //east
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts Left side of grid
                        else if(j <= 0)
                        {
                            //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north east
                            if(square[i-1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "ne", square, i-1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //east
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south east
                            if(square[i+1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "se", square, i+1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Starts Right side of grid
                        else if(j >= size-1)
                        {
                            //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north west
                            if(square[i-1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "nw", square, i-1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south west
                            if(square[i+1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "sw", square, i+1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                        }
                        
                        // Anywhere else mid map
                        else
                        {
                            //north
                            if(square[i-1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "n", square, i-1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //north west
                            if(square[i-1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "nw", square, i-1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //west
                            if(square[i][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "w", square, i, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south west
                            if(square[i+1][j-1] == temp.charAt(1))
                            {
                                loc = Find(temp, "sw", square, i+1, j-1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            //south
                            if(square[i+1][j] == temp.charAt(1))
                            {
                                loc = Find(temp, "s", square, i+1, j, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }    
                            //south east
                            if(square[i+1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "se", square, i+1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            } 

                            // East
                            if(square[i][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "e", square, i, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            
                            // North east
                            if(square[i-1][j+1] == temp.charAt(1))
                            {
                                loc = Find(temp, "ne", square, i-1, j+1, size);
                                if(loc != null)
                                {
                                    locArray[Store(temp, listArray)] = (temp + " found at start: " + (i) + ", " + (j) + " end: " + loc);
                                    break;
                                }
                            }
                            
                        }
                    }
                }
            }
        }
        Print(locArray, listArray);
    };
    //*************************************************************************************************************
    // Name: Find
    // Input: Test word, direction to go, the puzzle array, starting row, starting column.
    // Output: String ending location if found || null if not found
    // Purpose: take word and given direction, continue to test that direction until end of word, end of line, or non match.
    public static String Find(String lWord, String direction, char puzzle[][], int row, int col, int max)
    {
        --max;
        lWord = lWord.substring(2);
    	while(lWord.length() > 0)
    	{
	    switch(direction)
            {
                case "n":
                {
                    if(row == 0)
                        return null;
                    row--;
                    break;
                }
                case "ne":
                {
                    if(row == 0 || col == max)
                        return null;
                    row--;
                    col++;
                    break;
                }
                case "e":
                {
                    if(col == max)
                        return null;
                    col++;
                    break;
                }
                case "se":
                {
                    if(col == max || row == max)
                        return null;
                    col++;
                    row++;
                    break;
                }
                case "s":
                {
                    if(row == max)
                        return null;
                    row++;
                    break;
                }
                case "sw":
                {
                    if(row == max || col == 0)
                        return null;
                    row++;
                    col--;
                    break;
                }
                case "w":
                {
                    if(col == max)
                        return null;
                    col--;
                    break;
                }
                case "nw":
                {
                    if(col == 0 || row == 0)
                        return null;
                    col--;
                    row--;
                    break;
                }
                default:
                {
                        break;
                }
            }
            if(lWord.charAt(0) == puzzle[row][col])
                lWord = lWord.substring(1);
            else
                return null;
	}
        // return a string which includes an ending position
        return (row + ", " + col);
    };

    //*************************************************************************************************************
    // Name: Store
    // Input: String word to find, ArrayList<String> list to find it in
    // Output: Int word's location in the array
    // Purpose: Take given word and return an int containing it's location withing the provided array
    public static int Store(String lWord, ArrayList<String> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(lWord.equals(list.get(i)))
            {
                return i;
            }
        }
        System.out.println("default return");
        return 0;
    };

    //*************************************************************************************************************
    // Name: Print
    // Input: String[] containing completed lines, ArrayList<String> containing word list
    // Output: void
    // Purpose: Print the output
    public static void Print(String[] compArr, ArrayList<String> wordArr)
    {
        for(int i = 0; i < compArr.length; i++)
        {
            if(compArr[i] != null)
                System.out.println(compArr[i]);
            else
                System.out.println(wordArr.get(i) + " not found");
        }
    }
}; // End Class Search