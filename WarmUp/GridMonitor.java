
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Grid Monitor
 * 
 * This sets up a monitor class that takes input from a file that stores it into a 2d array.
 *  then creates a grid that takes that data and uses in multiple ways first using 
 *  a sum method that takes the surrounding cells such as up right down left and averages it in
 *  the cell its positioned
 *  second method takes that sum and divides it by 4.0 and gets the average 
 *  then next method finds the delta with all the information it has stored 
 *  and creates a danger method using that delta data by creating upper and lower bounds that 
 *  determines if a spot will fall in danger or its safe and the toString displays all that data as well as providing the 
 *  danger points
 *
 * @author Anton Leslie
 * @version  CS221 Fall 2022
 */
public class GridMonitor implements GridMonitorInterface
{
    protected double[][] grid;
    protected Scanner file;
    protected int rows;
    protected int columns;
    protected int i = 0;
    protected double sum;
    protected double[][] sumOfGrid;
    protected double[][] gridAvg;
    protected double[][] deltaGrid;
    protected boolean[][] dangerGrid;
    protected double avg;
    protected double delt;
    protected boolean danger;
    protected double upperBound;
    protected double lowerBound;
    protected boolean [][]saveSpot;
        /* Constructor */
/*
 * setting up file reader and not catching them when scanner throws it 
 */
        public GridMonitor(String filename) throws FileNotFoundException{
           File fileScan = new File(filename);

            Scanner file = new Scanner (fileScan);

            

            String firstSpot = file.nextLine();

            Scanner lines = new Scanner(firstSpot);

            rows = Integer.parseInt(lines.next());

            columns = Integer.parseInt(lines.next());

            grid = new double[rows][columns];

            while(file.hasNextLine())
            {
                for(int i = 0; i < grid.length; i++ )
                {
                    firstSpot = file.nextLine();

                    lines = new Scanner(firstSpot);

                    for (int j = 0; j < grid[i].length; j++)
                    {
                        grid[i][j] = Double.parseDouble(lines.next());
                    }

                }
            }
                
                file.close();
                
            }
        

    @Override
    public double[][] getBaseGrid() {
        
        return grid;
    }

    @Override
    public double[][] getSurroundingSumGrid() {
        
        sumOfGrid = new double[rows][columns];
        
        /**
         * For loop to cycle through each instance 
         */
        for(int i = 0; i < grid.length; i++ )
        {
            for(int j= 0; j<grid[i].length; j++)
            {
                    // Checking if grid is a one by one
                if (grid.length > 1 && (grid[i].length > 1))
                {
                    if (i == 0 && j== 0) // Top Left
                    {
                        sum = grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j+1];
                        sumOfGrid [i][j] = sum;
                    }
                   
                    else if (i == 0 && j == grid[i].length - 1) // Top Right 
                    {
                        sum = grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j-1];
                        sumOfGrid [i][j] = sum;
                    }
                     
                    /**
                     * Bottom Section of grid 
                     */
                    // Bottom Left
                    else if (i == grid.length - 1 && j == 0) 
                    {
                        sum = grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j+1];
                        sumOfGrid [i][j] = sum;
                    }
                   
                   
                    // Bottom Right 
                    else if(i == grid.length - 1 && j == grid[i].length -1)
                    {
                        sum = grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j-1];
                        sumOfGrid [i][j] = sum;
                    }
                     // Bottom mid
                     else if (i == grid.length - 1) 
                    {
                        sum = grid[i][j] + grid[i - 1][j]+ grid[i][j-1]+ grid[i][j+1];
                        sumOfGrid [i][j] = sum;
                    }
                    /**
                     * Middle left and right 
                     */
                    
                    // Middle left

                    else if( j == 0)
                    {
                        sum = grid[i][j] + grid[i-1][j]+ grid[i+1][j]+ grid[i][j+1];
                        sumOfGrid [i][j] = sum;
                    }
                    //middle right 
                    else if( j == grid[i].length - 1)
                    {
                        sum = grid[i-1][j] + grid[i][j-1]+ grid[i+1][j]+ grid[i][j];
                        sumOfGrid [i][j] = sum;
                    }
                    else if (i == 0 ) // Top Mid
                    {
                        sum = grid[i][j] + grid[i+1][j]+ grid[i][j+1]+ grid[i][j-1];
                        sumOfGrid [i][j] = sum;
                    }
                     // Middle 
                    else
                    {
                        sum = grid[i+1][j] + grid[i-1][j] + grid[i][j-1] +grid[i][j+1] ;
                        sumOfGrid [i][j] = sum;
                    }
                    
                    
                }
                else {
                    sum = grid[i][j] *4;
                    sumOfGrid [i][j] = sum;
               
                }
            }
        }
        return sumOfGrid;
    }

    @Override
    public double[][] getSurroundingAvgGrid() {
    	/*
    	 * takes surrounding sums and adds them up using all sides and corners
    	 */
    	gridAvg = new double[rows][columns];
    	
        for(int i = 0; i < grid.length; i++ )
        {
            for(int j= 0; j<grid[i].length; j++)
            {
            	if (grid.length > 1 && (grid[i].length > 1))
                {
                    if (i == 0 && j== 0) // Top Left
                    {
                        avg = (grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j+1]) / 4.0;
                        gridAvg [i][j] = avg;
                    }
                   
                    else if (i == 0 && j == grid[i].length - 1) // Top Right 
                    {
                        avg = (grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j-1]) / 4.0;
                        gridAvg [i][j] = avg;
                    }
                     
                    /**
                     * Bottom Section of grid 
                     */
                    // Bottom Left
                    else if (i == grid.length - 1 && j == 0) 
                    {
                        avg = (grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j+1]) / 4.0;
                        gridAvg [i][j] = avg;
                    }
                   
                   
                    // Bottom Right 
                    else if(i == grid.length - 1 && j == grid[i].length -1)
                    {
                        avg = (grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j-1])/ 4.0;
                        gridAvg [i][j] = avg;
                    }
                     // Bottom mid
                     else if (i == grid.length - 1) 
                    {
                        avg = (grid[i][j] + grid[i - 1][j]+ grid[i][j-1]+ grid[i][j+1])/ 4.0;
                        gridAvg [i][j] = avg;
                    }
                    /**
                     * Middle left and right 
                     */
                    
                    // Middle left

                    else if( j == 0)
                    {
                        avg = (grid[i][j] + grid[i-1][j]+ grid[i+1][j]+ grid[i][j+1])/ 4.0;
                        gridAvg [i][j] = avg;
                    }
                    //middle right 
                    else if( j == grid[i].length - 1)
                    {
                        avg = (grid[i-1][j] + grid[i][j-1]+ grid[i+1][j]+ grid[i][j])/ 4.0;
                        gridAvg [i][j] = avg;
                    }
                    else if (i == 0 ) // Top Mid
                    {
                        avg = (grid[i][j] + grid[i+1][j]+ grid[i][j+1]+ grid[i][j-1])/ 4.0;
                        gridAvg [i][j] = avg;
                    }
                     // Middle 
                    else
                    {
                        avg = (grid[i+1][j] + grid[i-1][j] + grid[i][j-1] +grid[i][j+1]) / 4.0;
                        gridAvg [i][j] = avg;
                    }
                    
                    
                }
                else {
                    avg = grid[i][j] ;
                    gridAvg [i][j] = avg;
               
                }
            }
        }
        return gridAvg;
    }

    @Override
    public double[][] getDeltaGrid() {
		    		/*
		    		 * takes the average divides to and gets delta setup
		    		 */
    	double [][] deltaGrid = new double[rows][columns];
        for(int i = 0; i < grid.length; i++ )
        {
            for(int j= 0; j<grid[i].length; j++)
            {
            	if (grid.length > 1 && (grid[i].length > 1))
                {
                    if (i == 0 && j== 0) // Top Left
                    {
                        double delt = ((grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j+1]) / 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                   
                    else if (i == 0 && j == grid[i].length - 1) // Top Right 
                    {
                        double delt = ((grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j-1]) / 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                     
                    /**
                     * Bottom Section of grid 
                     */
                    // Bottom Left
                    else if (i == grid.length - 1 && j == 0) 
                    {
                       delt = ((grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j+1]) / 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                   
                   
                    // Bottom Right 
                    else if(i == grid.length - 1 && j == grid[i].length -1)
                    {
                        delt = ((grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j-1])/ 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                     // Bottom mid
                     else if (i == grid.length - 1) 
                    {
                        delt = ((grid[i][j] + grid[i - 1][j]+ grid[i][j-1]+ grid[i][j+1])/ 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                    /**
                     * Middle left and right 
                     */
                    
                    // Middle left

                    else if( j == 0)
                    {
                        delt = ((grid[i][j] + grid[i-1][j]+ grid[i+1][j]+ grid[i][j+1])/ 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                    //middle right 
                    else if( j == grid[i].length - 1)
                    {
                        delt = ((grid[i-1][j] + grid[i][j-1]+ grid[i+1][j]+ grid[i][j])/ 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                    else if (i == 0 ) // Top Mid
                    {
                        delt = ((grid[i][j] + grid[i+1][j]+ grid[i][j+1]+ grid[i][j-1])/ 4.0)/2.0;
                        deltaGrid[i][j] =  Math.abs(delt);
                    }
                     // Middle 
                    else
                    {
                        delt = ((grid[i+1][j] + grid[i-1][j] + grid[i][j-1] +grid[i][j+1]) / 4.0)/2.0;
                        deltaGrid [i][j] =  Math.abs(delt);
                    }
                    
                    
                }
                else {
                    delt = (grid[i][j]) / 2.0 ;
                    deltaGrid [i][j] = Math.abs(delt);
               
                }
        
            }
        }
		return deltaGrid;
    }

    @Override
    public boolean[][] getDangerGrid() {
		/*
		 * this section is taking the delta and creating upper and lower bounds and setting up the danger zones
		 * */
boolean[][] dangerGrid = new boolean[rows][columns];
for(int i = 0; i < grid.length; i++ )
{
    for(int j= 0; j<grid[i].length; j++)
    {
    	if (grid.length > 1 && (grid[i].length > 1))
        {
            if (i == 0 && j== 0) // Top Left
            {
                double average = ((grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j+1]) / 4.0);
                delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
           
            else if (i == 0 && j == grid[i].length - 1) // Top Right 
            {
                double average = (grid[i][j] + grid[i][j]+ grid[i+1][j]+ grid[i][j-1]) / 4.0;
                delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
             
            /**
             * Bottom Section of grid 
             */
            // Bottom Left
            else if (i == grid.length - 1 && j == 0) 
            {
               double average= (grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j+1]) / 4.0;
               delt = average / 2.0;
               upperBound = average+ Math.abs(delt);
               lowerBound = average - Math.abs(delt);
               
               if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
               	
               	danger = false;
               }
               else {
               	danger = true;
               }
               dangerGrid[i][j] = danger;
            }
           
           
            // Bottom Right 
            else if(i == grid.length - 1 && j == grid[i].length -1)
            {
                double average = (grid[i][j] + grid[i][j]+ grid[i-1][j]+ grid[i][j-1])/ 4.0;
                
                upperBound = average + Math.abs(delt);
                lowerBound = average- Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
             // Bottom mid
             else if (i == grid.length - 1) 
            {
              double average = (grid[i][j] + grid[i - 1][j]+ grid[i][j-1]+ grid[i][j+1])/ 4.0;
               delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
            /**
             * Middle left and right 
             */
            
            // Middle left

            else if( j == 0)
            {
               double average = (grid[i][j] + grid[i-1][j]+ grid[i+1][j]+ grid[i][j+1])/ 4.0;
               delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
            // Middle right 
            else if( j == grid[i].length - 1)
            {
                double average = (grid[i-1][j] + grid[i][j-1]+ grid[i+1][j]+ grid[i][j])/ 4.0;
                delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
            else if (i == 0 ) // Top Mid
            {
                double average = (grid[i][j] + grid[i+1][j]+ grid[i][j+1]+ grid[i][j-1])/ 4.0;
                delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
             // Middle 
            else
            {
                double average = (grid[i+1][j] + grid[i-1][j] + grid[i][j-1] +grid[i][j+1]) / 4.0;
                delt = average / 2.0;
                upperBound = average + Math.abs(delt);
                lowerBound = average - Math.abs(delt);
                
                if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
                	
                	danger = false;
                }
                else {
                	danger = true;
                }
                dangerGrid[i][j] = danger;
            }
            
            
        }
        else {
            double average = (grid[i][j]);
            delt = average / 2.0;
            upperBound = average + Math.abs(delt);
            lowerBound = average - Math.abs(delt);
            
            if (grid[i][j] <= upperBound && grid[i][j]>= lowerBound) {
            	
            	danger = false;
            }
            else {
            	danger = true;
            }
            dangerGrid[i][j] = danger;
       
        }

    }
}
return dangerGrid;
}
    public String toString() {
    	boolean [][]saveSpot = new boolean[rows][columns];
    	
    	saveSpot = getDangerGrid();
    	String output = "Grid cells that are dangerous are here: ";
    	for (double[] value : grid) {
    		output += Arrays.toString(value);
    	}
    	for(int i = 0; i < grid.length; i++ )
    	{
    	    for(int j= 0; j<grid[i].length; j++)
    	    {
    	    	if (saveSpot[i][j] == true) {
    	    		output += "( " + i + ", " + j + ")";
    	    	}
    	    }
		
    	
    }
		return output;
    
    }
}
	

	
