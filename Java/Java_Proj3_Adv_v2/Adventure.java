/*
 * Class: Adventure
 * Purpose: Run program, call other classes.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Adventure
{
	/*
     * Method: main
     * Input: command line parameter including a file name
     * Output: Void
     * Purpose: process user selections. Selection options include (Go, North, South, East, West, Inventory, Quit)
     *			use only the first letter to recognize the commands.
     */
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
		Scanner fin = null;
		String input;
		char north = 'n';
		char south = 's';
		char east = 'e';
		char west = 'w';
		int row = 0;
		int col = 0;
		int rowMax;
		int colMax;
		int height = 0;
		int length = 1;
		Boolean cont = true;
    	// Test file to see if found
        //try
    	//{
        //	fin = new Scanner(new File(args[0]));
        //}
        //catch (FileNotFoundException x)
        //{
        //	System.out.println("Error: " + x);
        //	System.exit(0);
        //}
        //GameChar game = new GameChar(fin);
        GameChar game = new GameChar(args[0]);


		System.out.println(String.format("Project3$ java Adventure_v2.0"));
		do
		{
			System.out.print("The scouts are back sir, what shall we do?\r\n> ");
			input = in.nextLine();
			switch (Character.toUpperCase(input.charAt(0)))
			{
				case 'G': // "Go"
				{
					//Split string and read the direction
					String[] parts = (input.split(" "));
					switch (Character.toUpperCase(parts[1].charAt(0)))
					{
						case 'N':
							if(game.Go(north) == 0){
								System.out.println("By the gods, we've hit the edge of the world!");
							}
							break;
						case 'S':
							if(game.Go(south) == 0){
								System.out.println("By the gods, we've hit the edge of the world!");
							}
							break;
						case 'W':
							if(game.Go(west) == 0){
								System.out.println("By the gods, we've hit the edge of the world!");
							}
							break;
						case 'E':
							if(game.Go(east) == 0){
								System.out.println("By the gods, we've hit the edge of the world!");
							}
							break;
						default:
							System.out.println("I don't follow Cap'n");
							break;
					}
					break;
				}
				case 'I': // Inventory
					game.PrintInventory();
					break;

				case 'Q': // Quit
				{
					cont = false;
					System.out.println("Aye, it was a good run at that, Cap'n. Let Davey Jones take her!");
					break;
				}
				default: // Invalid selection
				{
					System.out.println("I don't follow Cap'n");
					break;
				}

			}
		}while(cont == true);
	} 
}