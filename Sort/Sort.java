import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221 AntonLeslie
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		// Base case
		if(list.size() <= 1) {
			return;
		}
		// Dividing the original list into two halves
		int average = list.size()/2;
		int size = list.size();
		// Sort elements into left and right lists
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		for(int i = 0; i < average; i++) {
			left.add(list.removeFirst());
		}
		for(int i = average; i < size; i++) {
			right.add(list.removeFirst());
		}
		// Recursively sort left and right
		mergesort(left);
		mergesort(right);
		// Merging the sorted lists back into the original list
		while(left.size()!= 0 && right.size() != 0){
			T leftElem = left.removeFirst();
			T rightElem = right.removeFirst();
			if(leftElem.compareTo(rightElem) > 0) {
				list.add(rightElem);
				left.addToFront(leftElem);
			} else {
				list.add(leftElem);
				right.addToFront(rightElem);
			}
		}
		while(left.size() != 0) {
			list.add(left.removeFirst());
		}
		while(!right.isEmpty()) {
			list.add(right.removeFirst());
		}
	}
		
//	public static <T extends Comparable <T>> void quickSort(IndexedUnsortedList<T>list, Comparator<T>c) {
//		//base case
//		if(list.size( )< 2) {
//			return;
//			
//		}
//		
//		//choose a pivot element 
//		T pivot = list.removeFirst();
//		
//		
//		//partition remaining elements 
//		IndexedUnsortedList<T>left = newList();
//		IndexedUnsortedList<T>right = newList();
//		while(!list.isEmpty()) {
//			if(c.compare(list.first(),pivot) < 0) {
//				list.add(list.removeFirst());
//				
//			}
//			else {
//				right.add(list.removeFirst());
//			}
//			
//			//recursively sort left to right
//			quickSort(left);
//			quickSort(right);
//			
//			//reassemble final list
//			while(!left.isEmpty()) {
//				list.add(left.removeFirst());
//				
//			}
//			while (!right.isEmpty()) {
//				list.add(right.removeFirst());
//			}
			
	//	}
	///}
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// Base case
		if(list.size() <= 1) {
			return;
		}
		// Dividing the original list into two halves
		int average = list.size()/2;
		int size = list.size();
		// Sort elements into left and right lists
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		for(int i = 0; i < average; i++) {
			left.add(list.removeFirst());
		}
		for(int i = average; i < size; i++) {
			right.add(list.removeFirst());
		}
		// Recursively sort left and right
		mergesort(left, c);
		mergesort(right, c);
		// Merging the sorted lists back into the original list
		while(left.size()!= 0 && right.size() != 0){
			T leftElem = left.removeFirst();
			T rightElem = right.removeFirst();
			if(c.compare(leftElem, rightElem) > 0) {
				list.add(rightElem);
				left.addToFront(leftElem);
			} else {
				list.add(leftElem);
				right.addToFront(rightElem);
			}
		}
		while(left.size() != 0) {
			list.add(left.removeFirst());
		}

		while(!right.isEmpty()) {
			list.add(right.removeFirst());
		}
	}
	
}
