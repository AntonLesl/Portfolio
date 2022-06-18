import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked node-based implementation of IndexedUnsortedList.
 * A ListIterator with working add(), remove(), and set methods are implemented.
 * 
 * 
 * @author Anton Leslie
 * 
 * @param <T> type to store
 */

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private Node<T> tail; 
	private Node<T> head; 
	private int size; 
	private int modCount;
	
	public IUDoubleLinkedList() {
		 head = tail = null;
		 size = 0;
		 modCount = 0;
	}

	@Override
	public void addToFront(T element) { 
		Node<T> newNode = new Node<T>(element);
		if(isEmpty()) {
			tail = head = newNode;
		} else {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		add(element);
	}

	@Override
	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		if(isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
		modCount++;

	}

	@Override
	public void addAfter(T element, T target) {
		Node<T> targetNode = head;
		while (targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) {
			throw new NoSuchElementException();
		}
		Node<T> newNode = new Node<T>(element);
		if (targetNode == head && targetNode == tail) {
			newNode.setPrev(targetNode);
			targetNode.setNext(newNode);
			tail = newNode;
		} else if (targetNode == tail) {
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		} else {
			newNode.setPrev(targetNode);
			newNode.setNext(targetNode.getNext());
			targetNode.getNext().setPrev(newNode);
			targetNode.setNext(newNode);
		}
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		
		if (index < 0 || index > size) 
		{ throw new IndexOutOfBoundsException(); }
		
		Node<T> newNode = new Node<T>(element); 
		
		if (index == 0) {
			newNode.setNext(head); 
			
			if(head != null) 
			{ head.setPrev(newNode); }

			else {
				tail = newNode;
			}
			head = newNode;
		  }
		else {
			Node<T> targetNode = head;
			for (int i = 0;i < index-1; i++) {
				targetNode = targetNode.getNext();
			}
				newNode.setNext(targetNode.getNext());
				newNode.setPrev(targetNode);
				targetNode.setNext(newNode);
				if (index != size) {
					newNode.getNext().setPrev(newNode);
				}
				else {
					tail = newNode;
				}
				
			
		}
		size++;
		modCount++;
		
		 
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		head = head.getNext(); 
		if(head == null) {
			tail = null;
		}
		size--;
		modCount++;
		return retVal;
		
	}

	@Override
	public T removeLast() { 
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = tail.getElement();
		if(size > 1) {
			tail = tail.getPrev();
			tail.setNext(null);
		}else {
			tail = head = null;
		}
		modCount++;
		size--;
		return retVal;
		
	}

	@Override
	public T remove(T element) { 
		
		Node<T> targetNode = head;
		while(targetNode !=null && !targetNode.getElement().equals(element)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) {
			
			throw new NoSuchElementException();
		}
		if(targetNode != tail) {
		targetNode.getNext().setPrev(targetNode.getPrev());
		}
		else {
			tail = targetNode.getPrev();
		}
		if(targetNode != head) {
		targetNode.getPrev().setNext(targetNode.getNext());
		}
		else {
			head = targetNode.getNext();
		}
		size--;
		modCount++;
		return targetNode.getElement();
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		T retVal = null;
		if (index == indexOf(tail.getElement())) {
			retVal = this.removeLast();
		} else if (index == 0) { 
			retVal = head.getElement();
			this.removeFirst();
		} else {
			ListIterator<T> lit = listIterator(index);
			retVal = lit.next();
			lit.remove();
		}
		return retVal;
		
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListIterator<T> lit = listIterator(index);
		lit.next();
		lit.set(element);
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}	
		if(index == 0) {
			return head.getElement();
		}
		ListIterator<T> lit = listIterator(index);
		return lit.next();
	}

	@Override
public int indexOf(T element) {
		int index = -1;
		Node<T> current = head;
		int currentIndex = 0;
		while (index < 0 && current != null) {
			if(element.equals(current.getElement())){
				index = currentIndex;
			} else {
				current = current.getNext();
				currentIndex++;
			}
		}
		return index;
	}


	@Override
	public T first() {
		if (isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		
		return head.getElement();
	}

	@Override
	public T last() {
		if (isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		return indexOf(target) > -1;
	}

	@Override
	public boolean isEmpty() {
		
		return head == null;
	}

	@Override
	public int size() {
		return size;
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		Iterator<T> it = this.iterator();
		while(it.hasNext()) {
			str.append(it.next());
			str.append(", ");
		}
		if(!isEmpty()) {
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}
	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}

	/** ListIterator for IUDoubleLinkedList */
	private class DLLIterator implements ListIterator<T>{

		private Node<T> nextNode;
		private int nextIndex;
		private int iterModCount;
		private Node<T> lastReturnNode;

		/**
		 * Intitializes to the beginning of the list
		 */
		public DLLIterator() {
			this(0);
		}
		
		/**
		 * DLL listIterator constructor that takes in a starting index
		 * @param startingIndex
		 */
		public DLLIterator(int startingIndex) {
			if(startingIndex < 0 || startingIndex > size) {
				throw new IndexOutOfBoundsException();
			}
			nextNode = head;
			nextIndex = 0;
			iterModCount = modCount;
			for(; nextIndex < startingIndex; nextIndex++) {
				nextNode = nextNode.getNext();
			}
			nextIndex = startingIndex;
			iterModCount = modCount;
			lastReturnNode = null;
		}
	
		/** Iterator hasNext method */
		@Override
		public boolean hasNext() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextNode != null;
		}
		/** Iterator next method */
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal = nextNode.getElement();
			lastReturnNode = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			return retVal;
		}
		/** ListIterator hasPrevious method */
		@Override
		public boolean hasPrevious() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextNode != head;
		}
		/** ListIterator previous method */
		@Override
		public T previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if(nextNode != null) {
				nextNode = nextNode.getPrev();
			} else {
				nextNode = tail;
			}
			lastReturnNode = nextNode;
			nextIndex--;
			return nextNode.getElement();
		}
		/** ListIterator nextIndex method */
		@Override
		public int nextIndex() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}
		/** ListIterator previousIndex method */
		@Override
		public int previousIndex() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex-1;
		}
		/** ListIterator set method */
		@Override
		public void set(T element) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturnNode == null) {
				throw new IllegalStateException();
			}
			if (lastReturnNode == nextNode) {
				nextNode.setElement(element);
			} else if (!hasNext()) {
				tail.setElement(element);
			} else {
				nextNode.getPrev().setElement(element);
			}
			iterModCount++;
			modCount++;

			
		}
		/** ListIterator add method */
		@Override
		public void add(T element) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(nextNode);
			if (nextNode != null) {
				newNode.setPrev(nextNode.getPrev());
				nextNode.setPrev(newNode);
				newNode.setNext(nextNode);
			} else {
				newNode.setPrev(tail);
				tail = newNode;
			}
			if (newNode.getPrev() != null) {
				newNode.getPrev().setNext(newNode);
			} else {
				head = newNode;
			}
			size++;
			modCount++;
			iterModCount++;
			nextIndex++;
			lastReturnNode = null;
			
		}
		/** ListIterator remove method */
		@Override
		public void remove() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(lastReturnNode == null) {
				throw new IllegalStateException();
			}
			if(lastReturnNode != head) {
				lastReturnNode.getPrev().setNext(lastReturnNode.getNext());
			} else {
				head = lastReturnNode.getNext();
			}
			if(lastReturnNode != tail) {
				lastReturnNode.getNext().setPrev(lastReturnNode.getPrev());
			} else {
				tail = lastReturnNode.getPrev();
			}
			if(nextNode == lastReturnNode) {
				nextNode = nextNode.getNext();
			} else {
				nextIndex--;
			}
			size--;
			modCount++;
			iterModCount++;
			lastReturnNode = null;
			
		}
		
	}

}

