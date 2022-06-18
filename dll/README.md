****************
* IUDoubleLinkedList
* CS221-1
* 04/02/22
* Anton Leslie
**************** 

OVERVIEW:

 	Within the project IUDoubleLinkedList, we aimed to create a Double-linked node implementation class that we used to test different test scenarios within our ListTester class.

INCLUDED FILES:
 
	Node.java - source file
 
	IUDoubleLinkedList - source file
 
	ListTester.java - source file, contains main class
 
	IndexedUnsortedList.java - source file
 
	README - current file 
 
COMPILING AND RUNNING: 
 
 From the directory containing all source files, compile the test
 
 class (and all dependent classes) with the command:
 
 $ javac ListTester.java
 

 
 Run the compiled ListTester class with the command:
 
 $ java ListTester
 

 
 Console output will give the results of each of the test scenarios and at the end of the console output, it will display the overall tests ran, tests passed, and tests failed, after the program finishes. 
 
PROGRAM DESIGN AND IMPORTANT CONCEPTS: 

In the project IUDoubleLinkedList, IUDoubleLinkedList implements the IndexedUnsortedList Interface, which creates different key methods that are used within IUDoubleLinkedList to set up and to run different scenarios/tests. The indexedUnsortedList also includes key descriptions on what each method is supposed to perform. IUDoubleLinkedList uses Node implementations to store different elements and values. Like our implementation of IUSingleLinkedList, we also include Iterator tests. However, with the IUDoubleLinkedList, we implement references in the interface for ListTester. Within the ListTester interface, we gain access to different add(), remove(), and set() methods that we use to test different scenarios within our ListTester. 
 

 
 In the Node.java class, I designed different method implementations of nodes. Similar to the
 IUSingleLinkedList, we had methods hasNext(), next(), and remove(). However, with the new implementation of our ListIterator, we gained access to multiple different methods that were helpful to use within our ListTester class. One key difference IUDoubleLinkedList had that IUSingleLinkedList did not, was the access to going both ways in the list, so in the Node class for the IUDoubleLinkedTest, we gained access to methods, hasPrevious() and Previous(). Which gives us the ability to go back and forth in the list without completely restarting.
 
 
 
 Within the ListTester class, The goal was to implement and add multiple different test scenarios 
 that included listIterator tests, plus the test scenarios we wrote and tested for 
 IUSingleLinkedList. However, instead of adding iterator test scenarios, we implemented listIteratorstests, so that we can test the functionally of our listIterator methods in IUDoubleLinkedList. Using the IUDoubleLinkedList and the ListTester, we fully tested all of the test scenarios that I designed for ListIterator's.  
 
TESTING: 
 
To test my program to confirm that it was functional and meet all the requirements,I created a test class which also included the main class, ListTester, where I wrote different test scenarios to test using my IUDoubleLinkedList methods. To check the test scenarios to see if they are passing I first needed to start at the top of the console output and go down the list of errors that I was getting, and attempt to debug the errors to find the root cause of why each individual test would not be passing. After looking at the console output, and checking to make sure that none of the methods I implemented were failing, I could confidently say that my program was working properly and met all the required steps/requirements.  
 
 
 
 Within my implementation of IUDoubleLinkedTest and ListTester there are no known issues or bugs that remain within my project. This can be shown through the console where we check to see if any of the scenarios are failing tests and in my final product for IUDoubleLinkedList, I can confirm that there are no bugs or issues present because all the test are currently passing 100%. 
 
 
 
 
DISCUSSION: 
  
 While working within this project, I encountered a lot of strengths and struggles. One of the main areas that I was struggling with at the beginning was getting my listIterator methods to run properly. On add() and remove(), I began struggling to decide if I just wanted to create complete methods with out using the lit iterator or choosing to use the list iterator instead. I missed some of the special case scenarios. However, after working through each line of code and applying it to each case, (single element, two element, three element).This helped me visualize what was happening internally within each method and drawing pictures so I can effectively design code that prevents any bugs or errors. Another issue thatI experienced while working through IUDoubleLinkedList, was Null Pointer Exceptions and I found this was because I had some internal looping within some of the methods that I designed. Also, on my own, I had to go back into the listIterator interface to check what methods were supposed to be doing what so I knew exactly the goal that I neededto work towards. Last of all, I also struggled with getting the right amount of calls for each of the testing scenarios. I believe this was a result of me completing test and not checking them directly after. 
 
 
 
 Some of the things I improved on was, during the process of writing IUDoubleLinkedList I ultimately was able to work through issues that developed as I continued to write methods and add tests. I handled these issues by resorting to a whiteboard to write understand the internal processes that the methods were going through.. Another strength that I believe I improved within this project, was creating scenarios and listIterator tests. As I aimed to implement different test scenarios, I confirmed that the first scenario I wrote ran properly and then I expanded the amount of test scenarios.
 
 Overall, I really enjoyed working through the process of creating test scenarios and an IUDoubleLinkedTest that included methods that we used to test those scenarios. I am sure that I will be seeing this topic of node type implementations in the future and I will most likely use this current IUDoubleLinkedTest for other projects that need implementations of node based code.  
  
EXTRA CREDIT: 
 
 If the project had opportunities for extra credit that you attempted, 
 be sure to call it out so the grader does not overlook it. 
 
 
---------------------------------------------------------------------------- 

