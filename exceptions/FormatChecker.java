import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/***
 * 
 * FormatChecker is a program that checks to see if the contents of specific files 
 * are valid or invalid. Files are valid if they are in the expected format according 
 * to each file and if a file is INVALID, we catch the exception, provide a informative 
 * message and then the program continues to run.
 * 
 * @author Anton Leslie
 * 
 */

public class FormatChecker {
	
	public FormatChecker() {
		int row;
		
		int col;
		
		String check;
		int count;
	}
	

	public static void main(String[] args) {
		
		if(args.length !=0 )					// Checks to confirm that there are args 
		{
			
			for(int i = 0; i < args.length; i++)				// loops over the whole elements of the args
			{
							// As long as args still has an index, create a file and run the checkFormat method below
				File file = new File(args[i]);
				System.out.println(checkFormat(file));
			}
		}
		else
		{
			System.out.println("There needs to be at least one file");
		}
	}
	
	/***
	 * 
	 * @param A file who's format needs to be checked
	 * @return Returns a string containing whether or not the file was valid.
	 */
	private static String checkFormat(File file)	// Private method that takes in a file																	
	{
		Scanner scan = null;
		
		int row = 0;
		
		int col = 0;
		
		String check = "INVALID" + "\n";
		
		try							// Testing to see if a file exists
		{
			scan = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			return check + " the file submitted cannot not be found " + e.toString();
		}


		if(scan.hasNextLine())
		{
										// Test to see if the rows and columns are formatted in the correct way
			String test = scan.nextLine();																	

			try 
			{
				test = test.replaceAll("\\s","");
				// Parsing the int, we are able to check/see if there is a NumberFormatException 	
				row = Integer.parseInt(test.substring(0,1));
				col = Integer.parseInt(test.substring(1,2));
			}
			catch(IndexOutOfBoundsException t)				// Catches a IndexOutOfBoundsException 
			{

				return check + " the row/column values are formatted improperly " + t.toString() ;
			}
			catch(NumberFormatException e)			// Catches a NumberFormatException
			{
				return check + " invalid value " + e.toString(); 
			}
			if(test.length() > 2 || test.length() <2)
			{
				return check + " the row/column values are formatted improperly";
			}
		}
		
		
		int increase = 0;
		
		String currentLine;

		while(scan.hasNextLine() && scan.hasNextDouble())			// Check to see if there is another line and double and columns specified in the file itself
		{

			int count = 0;
			
			currentLine= scan.nextLine();
			
			Scanner scanLine = new Scanner(currentLine);


			while(scanLine.hasNextDouble())
			{
				scanLine.nextDouble();
				
				count++;
			}

			if (count != col)					// Checking to see if the number of specified columns is correct
			{
				return check + "The specified number of columns do not match";
			}
			
			increase++;
		}
		
		if(increase != row)						// Checking to see if the number of specified rows is correct
		{
			return check + "The specified number of rows do not match";
		}
		
		return "VALID"; // If none of the tests to check if the files are INVALID then the method will return VALID
	}
}

