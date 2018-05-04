
/**
 * Represents a bag
 * @Owner: Allen Millington
 */

import java.util.Random;

public class Bag<T> {
	
	/*
	 * The bag class should only contain the following fields. A private integer
	 * named “size” A private final array of type generic named “items” A private
	 * static final integer named “CAPACITY” which is initialized to 20
	 */
	
	private int size;
	private T[] items;
	// At most each player has 16 pieces, and each piece can make up to 64 moves
	// (8X8 board)
	private static final int CAPACITY = 16 * 64;
	
	/*
	 * The default constructor should initialize size to 0 and initialize the items
	 * array to have a size of CAPACITY.
	 */
	
	/**
	 * Public Bag Constructor. Default capacity 16(pieces per player) * 64 possible
	 * moves max
	 */
	@SuppressWarnings("unchecked")
	public Bag() {
		this.size = 0;
		this.items = (T[]) new Object[CAPACITY];
	}
	
	/**
	 * Gets the number of items in the bag
	 * 
	 * @return the number of items in the bag
	 */
	public int getCurrentSize() {
		return (this.size);
	}
	
	/**
	 * Enlarge the bag if needed
	 * 
	 * @return true if bag resized
	 */
	public boolean ensureCapacity() {
		
		if (size == CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[items.length * 2];
			
			for (int i = 0; i < size; i++) {
				temp[i] = items[i];
			}
			
			items = temp;
		}
		return false;
	}
	
	/**
	 * Tells if bag is empty
	 * 
	 * @return true if bag is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Adds an item to the bag
	 * 
	 * @param item
	 *            Item to add to the bag
	 * @return true if successful add
	 */
	public boolean add(T item) {
		boolean full = ensureCapacity();
		if (!full) {
			items[size] = item;
			size++;
		}
		return !full;
	}
	
	/**
	 * Remove an item from the bag. Returns most recently added item from the bag.
	 * 
	 * @return most recent item
	 */
	
	public T remove() {
		
		T returnItem = null;
		
		if (!isEmpty()) {
			returnItem = items[size - 1];
			items[size - 1] = null;
			size--;
			
		}
		return returnItem;
	}
	
	/**
	 * Removes a particular item from the bag
	 * 
	 * @param item
	 *            the item you want to remove
	 * @return true if the item is found and removed
	 */
	public boolean remove(T item) {
		
		// boolean found = false;
		
		for (int i = 0; i < size; i++) {
			if (items[i].equals(item)) {
				items[i] = items[size - 1];
				items[size - 1] = null;
				size--;
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * remove an item from a particular spot in the array. Used for random remove
	 * method
	 * 
	 * @param a
	 *            the array position to remove
	 * @return true if item successfully removed.
	 */
	public boolean remove(int a) {
		if (a <= size) {
			
			items[a] = items[size - 1];
			items[size - 1] = null;
			size--;
			return true;
			
		}
		return false;
		
	}
	
	/**
	 * clears the bag
	 */
	public void clear() {
		
		while (!isEmpty()) {
			remove();
		}
	}
	
	/**
	 * Get the number of times the item appears in the bag.
	 * 
	 * @param item
	 *            item to search for
	 * @return the number of times the item appears in the bag.
	 */
	public int getFrequencyOf(T item) {
		int frequency = 0;
		for (int i = 0; i < size; i++) {
			// item[i].getClass().isInstance(item)
			if (items[i].getClass().isInstance(item))
				frequency++;
		}
		
		return frequency;
	}
	
	/**
	 * Tells if the item is within the bag.
	 * 
	 * @param item
	 *            the item you want to search for
	 * @return true if the item is found.
	 */
	public boolean contains(T item) {
		boolean found = false;
		
		for (int i = 0; !found && (i < size); i++)
			if (item.equals(this.items[i]))
				found = true;
			
		return found;
	}
	
	/**
	 * Outputs bag to array
	 * 
	 * @return an array of the items
	 */
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		
		T[] returnArray = (T[]) new Object[CAPACITY];
		
		for (int i = 0; i < size; i++) {
			returnArray[i] = items[i];
		}
		
		return returnArray;
	}
	
	/**
	 * Removes a random item from the bag.
	 * 
	 * @return random item
	 */
	public T getRandomItem() {
		if (size != 0) {
			
			Random rando = new Random();
			
			int randomMove = rando.nextInt(size);
			
			T output = items[randomMove];
			
			remove(randomMove);
			return output;
		}
		return null;
	}
	
	/**
	 * prints the contents of the current bag
	 */
	public void printBag() {
		int i = 0;
		for (i = 0; i < size; i++) {
			((PieceMove) items[i]).print();
		}
		
		System.out.println(i);
	}
	
}
