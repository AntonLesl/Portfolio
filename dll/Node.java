/**
 * Node for Linked Lists
 *
 * @author Java Foundations, mvail, AntonLeslie
 * @version 4.0
 */
public class Node<T> {
	private Node<T> next;
	private T element;
	private Node<T> Prev;
	
	/**
  	 * Creates an empty node.
  	 */
	public Node(T element) {
		this.element = element;
		next = null;
		setPrev(null);
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(T element, Node<T> nextNode) {
		this.element = element;
		next = nextNode;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<T> getNext() {
		return next;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(Node<T> node) {
		next = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public T getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(T element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null);
	}

	/**
 	 * Returns the node that is behind this one.
  	 *
  	 * @return the node that behind the current one
  	 */
	public Node<T> getPrev() {
		return Prev;
	}

	/**
 	 * Sets the node that is behind this one.
 	 *
 	 * @param node
 	 *            the node to be set to behind the current one
 	 */
	public void setPrev(Node<T> Prev) {
		this.Prev = Prev;
	}
	
}
