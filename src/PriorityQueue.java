
/**
 * Priority Queue using an array implementation
 * 
 * @author Owner Allen Millington
 * @param <T>
 *            Comparables
 */
import java.util.Arrays;

public class PriorityQueue<T extends Comparable<? super T>> {
	
	private int size;
	private T[] pQ;
	private int capacity = 6;
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		size = 0;
		pQ = (T[]) new Comparable[capacity];
	}
	
	/**
	 * Adds an object to the priority queue
	 * 
	 * @param newEntry
	 *            item to add
	 * @return true if added
	 */
	public boolean enqueue(T newEntry) {
		
		int index;
		
		ensureCapacity();
		
		// Find the index where the item belongs first (as a loop)
		
		index = findIndex(newEntry);
		
		// Ok, so now I have the index that I want to insert it into.
		
		// After that segment, in a separate loop, make space for the object
		
		for (int i = size; i > index; i--) {
			pQ[i] = pQ[i - 1];
		}
		
		// Finally, insert object
		pQ[index] = newEntry;
		
		size++;
		return true;
		
	}
	
	/**
	 * prints the entire queue array
	 */
	public void print() {
		// Prints the array
		
		for (int i = 0; i <= size; i++) {
			System.out.println(pQ[i]);
		}
		
	}
	
	/**
	 * Finds the index of an entry
	 * 
	 * @param anEntry
	 *            the entry to search for
	 * @return index of item
	 */
	private int findIndex(T anEntry) {
		
		int index = 0;
		
		if (size != 0) {
			for (int i = 0; i < size; i++)
				if (anEntry.compareTo(pQ[i]) > 0)
					index = i + 1;
		}
		return index;
	}
	
	private void ensureCapacity() {
		
		if (size + 1 == capacity) {
			capacity *= 2;
			pQ = Arrays.copyOf(pQ, capacity);
		}
	}
	
	public T dequeue() {
		
		T returnObject = null;
		
		if (size != 0) {
			returnObject = pQ[size - 1];
			pQ[size - 1] = null;
			size--;
		}
		
		return returnObject;
		
	}
	
	/**
	 * Gets the front of the queue (does not remove)
	 * 
	 * @return Front item
	 */
	public T getFront() {
		
		T returnObject = null;
		
		if (size != 0) {
			returnObject = pQ[size - 1];
		}
		
		return returnObject;
	}
	
	/**
	 * Checks if array is empty
	 * 
	 * @return true if array is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Clears the queue
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		pQ = (T[]) new Object[capacity];
		
	}
}
