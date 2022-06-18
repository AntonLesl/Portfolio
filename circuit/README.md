


****************
* Circuit Tracer
* 221-1
* 4/28/2022
* Anton Leslie
**************** 

OVERVIEW:

Within the project CircuitTracer, a program reads in data from an input 
file and uses either a stack or a queue as a search storage structure. 
CircuitTracer searches for the shortest path between a defined startingpoint -1
and endingpoint -2 then displays the output to the console, since a GUI
implementation was not made/created.

INCLUDED FILES:

List the files required for the project with a brief
explanation of why each is included.

* CircuitTracer.java - source file
* CircuitBoard.java - source file
* Storage.java - source file
* TraceState.java - source file
* InvalidFileFormatException.java - source file
* OccupiedPositionException.java - source file
* CircuitTracerPanel.java - source file
* README - this file

COMPILING AND RUNNING:

From the directory containing all source files, compile the test\
class (and all dependent classes) with the command:\

$ javac CircuitTracer.java

Run the compiled class file with the command:
$ java CircuitTracer -[ s , q ] -[ c , g ] <filename>

Note: -s will use a stack for storage, -q will use a queue for storage, 
-c will output the results to the console, and -g is not supported within
this program but is for guy if it had been implemented. Use -c for all console outputs.

If the file is not in the correct given format, an exception will be thrown and instructions will be available.

Example of file in correct format:

Valid Characters:
O - represents an open space
X - represents an occupied position 
1 - starting location, first of two components that need to be connected
2 - ending location, second of two components that need to be connected 
T - a location/position where a new trace connects the two components

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

In this program, we use the class CircuitTracer as our main driver class that everything is ran through. Within this class,command line arguments are passed through, allowing the user of the program to pick a storage configuration, stack or queue. In addition, in Circuit Tracer class there is a search algorithm that is created to find the quickest route from a starting position to the ending position, then outputs the final results.In the class CircuitBoard, a file is read in from the users selection and begins by checking to see if there are any formatting issues that occur within the specified file that is attempting to be scanned.If the file that is being open is not in the correct format than a InvalidFormatException will be thrown to the user.Within the class TraceState, a path is kept to track the amount of moves that have occurred and traces along the entire CircuitBoard until a shortestPath or fastest route is achieved by comparing each route that is found. 

The provided InvalidFileFormatException and OccupiedPositionException exceptions were both thrown
throughout the program. The InvalidFileFormatException is an exception that I threw whenever a specified file does not contain a CircuitBoard that contains all correct aspects of a valid CircuitBoard. The OccuipedPositionException is an exception I threw whenever a attempt was made to add a trace to anoccupied position in a specific CircuitBoard.

Analysis:

 Question 1:
How does the choice of Storage configuration (stack vs queue) affect the sequence in which paths are explored in the search algorithm? 

Answer:
The choice of Storage configuration dramatically changes the sequence in which paths are 
explored in the search algorithm.The reason that the choice of a stack or queue changes the 
sequence is because the way that each storage configuration adds and takes away objects of 
TraceState. Looking at a stack, stacks are LIFO's which means, objects are added and removed 
at the top of the stack call. This shows us that usually the most recent path that is trying 
to be explored will be at the top and it will explore that path to its completely done and there are no more options. On the flip side, a queue takes a FIFO's approach in which,
objects of TraceState are being taken off the front of the queue and added to the back of it. Therefore it does one for each option till everything is done . 

Question 2:
Is the total number of search states (possible paths) affected by the choice of stack or queue?\

Answer:
The total number of possible paths is not affected by the choice of stack or queue because in this program,we are using a brute force algorithm that already checks for all the possible solution paths.

Question 3:


 Does using either of the storage structures guarantee that the first solution found will be a shortest path?


Answer:


 Using one of the storage structures will guarantee that the first solution will find the shortest path and the structure that will achieve this will be the queue because it works level by level all the way through the queue. This proves a queue will guarantee the first solution because it will find the shortest path faster if the path is located at the top level. 

Question 4:
Does using one of the storage structures usually find a solution in fewer steps than the other? Always?

Answer:
A stack will usually find a solution faster than a queue, however that solution could also not always be the fastest one. This is because when a stack is used it only runs through one branch at a time.The special case would be if the path is the shortest one, but typically that is not the case within this program. On the other end, this means that a queue's first solution will always be the shortest one because like mentioned earlier, a queue checks sections one level at a time and then continues working through level section of the queue.

Question 5:
How is memory use (the maximum number of states in Storage at one time) affected by the choice of underlying structure?

 Answer:
The maximum number of states in Storage at one time is mostly affected by the choice of choosing
a stack because a stack only looks at one path. However, a queue looks at one section of paths 
and keeps working down until the last section of paths. With that being said, a stack will be a lot
more memory efficient than a queue because a queue has much more objects of TraceState to hold than a stack.

Question 6:
What is the Big-Oh runtime for the search algorithm? Does it reflect the maximum size of Storage? Does it reflect the number of board positions?
Does it reflect the number of paths explored? Does it reflect the maximum path length? Is it something else?

Answer:
The Big-Oh runtime for the search algorithm that we are using within this program will be O(2)
because when a search takes place and we are at already at some position, that position has up to three different possible moves that can be made and this will happen until a ending position is met or if the amountof valid moves is exceeded. 

TESTING:

The testing layout that I decided to follow was first running CircuitTracerTester to initially
get and understand all of the tests I am expected to be passing. Next, I found the first location 
where I encounter a bug or error and I started working and using the debugger to narrow down what could be wrong within my code.Within the CircuitBoard.java class, two different exceptions are being thrown to handle bad formatting.For bad formatting issues that involved errors like characters that were not included in ALLOW_CHARS, dimensions that did not contain the right size of the board, etc. I threw an InvalidFileFormatException with a message stating whythe exception arises. The last exception that I threw was a FileNotFoundException that handled if a file was not found.Within my program, there are not any known issues/bugs that still remain in my project / programs / methods. 

DISCUSSION:

Within this project, I did not experience a lot of issues along the way. For the most part,
I was able to work through all the required steps to achieve a functional CircuitTracer project
that indeed passed all of the tests being tested by the CircuitTracerTester. Although, I really
wanted to implement a GUI output because it would have been good practice and there was an 
extra reward (points) for doing so. However, I really wanted to get the jump on studying for my final since that is worth more points then the gui. 

I really enjoyed working on this project and I believe that I could have wrote a GUI in addition, but I just ended up feeling I would be running out of time to implement it and study for my final. 
----------------------------------------------------------------------------
}
