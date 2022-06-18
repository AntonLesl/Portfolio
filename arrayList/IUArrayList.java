import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author AntonLeslie
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
/*
 * modCount should be incremented when anything in the list is being changed
 */
	
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
		
	}
	
	public void expandIfNeccessary() {
		if(array.length == rear) {
			expandCapacity();
		}
	}
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(T element) {
		expandIfNeccessary();
		for (int index = rear; index > 0; index--) {
			array[index] = array [index-1];
		}
		
		array[0] = element;
		rear ++;
		modCount ++;
	}

	@Override
	public void addToRear(T element) {
		expandIfNeccessary();
		array[rear] = element;
		rear++;
		modCount ++;
	}

	@Override
	public void add(T element) {
		
		addToRear(element);
		
	}

	@Override
	public void addAfter(T element, T target) {
		int targetIndex = indexOf(target);
		if(targetIndex < 0) {
				throw new NoSuchElementException();
		}
		expandIfNeccessary();
		for (int i = rear; i > targetIndex+1; i--) {
			array[i] = array[i-1];
		}
		array[targetIndex + 1] = element;
		rear++;
		modCount ++;
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > rear) {
			throw new IndexOutOfBoundsException();
		}
		expandIfNeccessary();
		for (int i = rear ; i > index ; i--) {
			array[i] = array[i-1];
		}
		array[index] = element;
		rear++;
		modCount ++;
		
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[0];
		for (int index = 0; index < rear - 1; index++) {
			array[index] = array [index+1];
		}
		rear --;
		modCount ++;
		array[rear]=null;
		return retVal;
	}

	@Override
	public T removeLast() {
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = array[rear-1];
		array[rear-1] = null;
		rear--;
		modCount ++;
		
		return retVal;
		
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		T retVal = array[index];
		rear--;
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
		modCount++;
		
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		
		return rear == 0;
	}

	@Override
	public int size() { 
		return rear;
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
			for (int i = 0; i < rear; i++) {
				str.append(array[i].toString());
				str.append(", ");
			}
			if (!isEmpty()) {
				str.delete(str.length()-2, str.length());
			}
			str.append("]");
			return str.toString();		
		}

	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean canRemove;
		
		
/**
*Initialize an Iterator in front of the first element
*/
		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount)
			{
				throw new ConcurrentModificationException();
			}
			
			return nextIndex < rear;
		}

		@Override
		public T next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
				}
			nextIndex++;
			canRemove = true;
			return array[nextIndex-1];
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount)
			{
				throw new ConcurrentModificationException();
			}
			
				if(!canRemove)
				{
					throw new IllegalStateException();
				}
				
				for(int i = nextIndex - 1 ; i < rear - 1; i++) {
					array[i] = array [i+1];
				}
				array[rear - 1]= null;
				rear --;
				modCount ++;
				iterModCount ++;
				canRemove = false;		
			}
			
			
		}
	}

