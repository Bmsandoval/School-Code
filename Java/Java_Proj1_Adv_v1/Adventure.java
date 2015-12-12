import java.util.*;
import java.lang.*;

public class Adventure
{
	public static void main(String[] args)
	{
		String input;
		Scanner in = new Scanner(System.in);
		Boolean cont = true;
		int row = 0;
		int col = 0;
		System.out.println(String.format("Project1$ java Adventure\r\nCap'n, the ship was lost in the storm.\r\nThe fog's got us all muddled up, but I think we are at %d,%d", row, col));
		do
		{
			System.out.println("What do you want to do Cap'n?");
			input = in.nextLine();
			switch (Character.toUpperCase(input.charAt(0)))
			{
				case 'G':
				{
					//Split string and read the direction
					String[] parts = (input.split(" "));
					switch (Character.toUpperCase(parts[1].charAt(0)))
					{
						case 'N':
							System.out.println("Aye Cap'n, North it is!");
							if(row==0)
							{
								System.out.println("Rocks ahead Cap'n, we can't go that far North.");
								break;
							}
							else
								row -= 1;
							//decrement row by one. if row is already 0, print error
							break;
						case 'S':
							System.out.println("Aye Cap'n, South it is!");
							if(row==4)
							{
								System.out.println("Rocks ahead Cap'n, we can't go that far South.");
								break;
							}
							else
								row += 1;
							//increment row by one. if row is already 4, print error
							break;
						case 'W':
							System.out.println("Aye Cap'n, West it is!");
							if(col==0)
							{
								System.out.println("Rocks ahead Cap'n, we can't go that far West.");
								break;
							}
							else
								col -= 1;
							//decrement col by one. if col is already 0, print error
							break;
						case 'E':
							System.out.println("Aye Cap'n, East it is!");
							if(col==4)
							{
								System.out.println("Rocks ahead Cap'n, we can't go that far East.");
								break;
							}
							else
								col += 1;
							//increment col by one. if col is already 4, print error
							break;
						default:
						{
							System.out.println("I don't follow Cap'n");
							break;
						}

					}
					break;
				}
				case 'I':
				// print out inventory. not much needed for this time, but since we are going to be building upon
				// this later, it may be a good idea to make an array where I can change the contents.
					System.out.println("Here's what we have left aboard Cap'n, much was lost in the storm:\r\nbrass lantern\r\nrope\r\nrations\r\nstaff");
					break;

				case 'Q':
				{
					cont = false;
					System.out.println("Aye, it was a good run at that, Cap'n. Let Davey Jones take her!");
				// exit program
					break;
				}
				default:
				{
					System.out.println("I don't follow Cap'n");
					break;
				}

			}
			System.out.println(String.format("We should be at %d,%d\r\n", row, col));
		}while(cont == true);
	} 
}