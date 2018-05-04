/**
 * Node class (lol). Simple node for use in linked list priority queue.
 * 
 * @author Owner Allen Millington
 * @param <T>
 *            generic data type
 */
public class Node<T> {
	
	Node<T> next; // Next Node
	T data; // Data to store
	
	public Node(T data, Node<T> next) {
		
		this.data = data;
		this.next = next;
	}
	
}
