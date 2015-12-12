import java.util.*;
import java.lang.*;
import java.io.*;

// Open Program

public class wSearch
{
	public static void main(String[] args)
	{
	// Get file name
	String input;
	Scanner in = new Scanner(System.in);
	do
	{
		boolean test = false;
		System.out.print("Please enter a filename to use: ")
		input = in.nextLine();
		try
		{
	    	fin = new Scanner(new File(input));
	    }
	    catch (FileNotFoundException x)
	    {
	    	System.out.println("File open failed. Please try again.");
	    	test = true;
	    }
	}while(test == true);

    String line;
    line = fin.nextLine();
    Char [][] square; //creates a multi dimensional array.
    int size = line.size();
	square = new double[size][size];
	square[0][] = line.split("");
    while (fin.hasNextLine())
    {
    	if(line == Null)
    		break;
    	int temp = 1;
    	line = fin.nextLine();
    	System.out.println(line);
    	square[temp][] = line.split("");
    	temp++;
    }
    ArrayList<string> listArray = new ArrayList<string>();
    while (fin.hasNextLine)
    {
    	listArray.add(fin.nextLine());
    }
    for(int i = 0; i < size; i++)
    {
    	for(int j = 0; j < size; j++)
    	{
    		for(int t = 0; t < listArray.size(); t++)
    		{
    			if(square[i][j]
    		}
    	}
    }
	// call wSearch(filename);
	// double 'if' sort to traverse array from top to bottom
		// test each letter to the first letter of any words on the list. Make an arrah holding the location of the words that are found
			// if match found test all 8 directions for next letter
				// if still successful, keep testing that direction until end of word. return 1 for successful word, 0 for unsuccessful.
			// if return type 1
				//Save an array holding the word, the starting point, and ending point for the word.
				// Delete word from wSearch's sortArray.
					// if this leaves sortArray empty, close program.
			// test all letters. perhaps a temp array for succesful words to make sure we are not double testing in a direction we already found a complete word



// wSearch
	// Pull Search
		// Save in searchArray
	// Pull List
		// Save in listArray
		// Save a sorted version into sortArray
	// Method to delete word out of sortArray



static accessible by accessing the class.