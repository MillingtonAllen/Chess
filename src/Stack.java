/**
 * @author Owner Allen Millington
 * @param <T>
 *            Object
 */
public class Stack<T> {
	
	Node<T> firstNode; // the first node
	int numberOfNodes = 0; // number of Nodes
	
	/**
	 * adds an item to the stack
	 * 
	 * @param data
	 *            Object
	 */
	public void push(T data) {
		
		Node<T> newNode = new Node<T>(data, firstNode);
		firstNode = newNode;
		
	}
	
	/**
	 * Returns an array of the stack
	 * 
	 * @return an object array of generic type
	 */
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] output = (T[]) new Object[numberOfNodes];
		
		Node<T> currentNode = firstNode;
		
		for (int i = 0; i < numberOfNodes; i++) {
			output[i] = currentNode.data;
			currentNode = currentNode.next;
		}
		
		return output;
		
	}
	
	/**
	 * Returns top of stack
	 * 
	 * @return top object of stack
	 */
	public T pop() {
		
		T output = null;
		if (firstNode != null)
			output = firstNode.data;
		
		return output;
	}
	
	/**
	 * If the first entry.data == null, get rid of the entry
	 * Used in case the piece was empty. Makes it easy to pull entry to undo move if
	 * invalid.
	 */
	public void trimNull() {
		
		if (firstNode != null) {
			if (firstNode.data == null)
				firstNode = firstNode.next;
		}
	}
	
}
