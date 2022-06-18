import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail AntonLeslie
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); 				// new CircuitTracer that passes in args 
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		System.out.println("Invalid or missing arguments. Please try again. \n"); 
		System.out.println("Usage: java CircuitTracer [-s | -q] [-c | -g] <filename> \n"); 
		System.out.println("First argument: -s (stack) or -q (queue) \n");
		System.out.println("Second argument: -c (console output) or -g (gui output) \n");
		System.out.println("Third argument: <filename> (full name of the file) \n");
		System.out.println("-g (A GUI output was not implemented and is currently not available at this time. Use -c for all console outputs).");
	}

	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		if(!args[0].equals("-s") && !args[0].equals("-q")) {
			printUsage();
			return;
		}
		if(args[1].equals("-g")) {
			System.out.println("A -g (gui output) is not currently implemented. Please use -c for console output.\n");
			return;
		} else if(!args[1].equals("-c")) {
			printUsage();
			return;
		}

		// brute-force algorithm implemented using Pseudo-code
		Storage<TraceState> stateStore;
		if(args[0].equals("-s")) {
			stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
		} else {
			stateStore = new Storage<TraceState>(Storage.DataStructure.queue);
		}
		try {
			CircuitBoard baseBoard = new CircuitBoard(args[2]);
			List<TraceState> bestPaths = new ArrayList<TraceState>();
			Point startingPoint = baseBoard.getStartingPoint();
			if(baseBoard.isOpen(startingPoint.x, startingPoint.y+1)) {
				TraceState newTrace = new TraceState(baseBoard, startingPoint.x, startingPoint.y+1);
				stateStore.store(newTrace);
			}
			if(baseBoard.isOpen(startingPoint.x, startingPoint.y-1)) {
				TraceState newTrace = new TraceState(baseBoard, startingPoint.x, startingPoint.y-1);
				stateStore.store(newTrace);
			} 
			if(baseBoard.isOpen(startingPoint.x+1, startingPoint.y)) {
				TraceState newTrace = new TraceState(baseBoard, startingPoint.x+1, startingPoint.y);
				stateStore.store(newTrace);
			}
			if(baseBoard.isOpen(startingPoint.x-1, startingPoint.y)) {
				TraceState newTrace = new TraceState(baseBoard, startingPoint.x-1, startingPoint.y);
				stateStore.store(newTrace);
			}
			while(!stateStore.isEmpty())
			{
				TraceState currVal = stateStore.retrieve();
				if(currVal.isComplete())
				{
					if(bestPaths.isEmpty())
					{
						bestPaths.add(currVal);
					} else {
						if(bestPaths.get(0).pathLength() > currVal.pathLength())
						{
							bestPaths.clear();
							bestPaths.add(currVal);
						} else if(bestPaths.get(0).pathLength() == currVal.pathLength()) {
							bestPaths.add(currVal);
						}
					}
				} else {
					if(currVal.isOpen(currVal.getRow(), currVal.getCol()+1))
					{
						TraceState newTrace = new TraceState(currVal, currVal.getRow(), currVal.getCol()+1);
						stateStore.store(newTrace);
					}
					if(currVal.isOpen(currVal.getRow(), currVal.getCol()-1))
					{
						TraceState newTrace = new TraceState(currVal, currVal.getRow(), currVal.getCol()-1);
						stateStore.store(newTrace);
					}
					if(currVal.isOpen(currVal.getRow()+1, currVal.getCol()))
					{
						TraceState newTrace = new TraceState(currVal, currVal.getRow()+1, currVal.getCol());
						stateStore.store(newTrace);
					}
					if(currVal.isOpen(currVal.getRow()-1, currVal.getCol()))
					{
						TraceState newTrace = new TraceState(currVal, currVal.getRow()-1, currVal.getCol());
						stateStore.store(newTrace);
					}
				}
			}
			if(!bestPaths.isEmpty()) {
				for(TraceState paths: bestPaths) {
					System.out.println(paths.toString());
				}
			}
		}
		catch(FileNotFoundException fnfe) {
			System.out.println(fnfe.toString());
			
		}
		catch(InvalidFileFormatException ife) {
			System.out.println(ife.toString());
			
		}
	}
}

 // class CircuitTracer
