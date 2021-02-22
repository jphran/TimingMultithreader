package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A priority queue that supports access of the minimum element only.
 * 
 * @author Erin Parker, Matthew Barkey, and Ian Langston
 * @version February 9, 2021
 * 
 * @param <E> - the type of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	private E[] array;
	private Comparator<? super E> cmp;
	private int size = 0;
	
	/**
	 * Constructor for SimplePriorityQueueto to sort the elements in natural ordering.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		array = (E[]) new Object[16];
	}

	/**
	 * Constructor for SimplePriorityQueueto to sort the elements with a custom Comparator methods.
	 * 
	 * @param cmp - the custom Comparator method
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		array = (E[]) new Object[16];
		this.cmp = cmp;
	}

	/**
	 * Retrieves, but does not remove, the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMin() throws NoSuchElementException {
		// check if object has any elements first, then return the last element. Otherwise, throw exception.
		if (size == 0) 
			throw new NoSuchElementException("No such element exists.");
		else
			return array[size - 1];
	}

	/**
	 * Retrieves and removes the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMin() throws NoSuchElementException {
		E element = this.findMin(); // get the min value
		size--; // reduce size to ignore element
		return element;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(E item) {
		// if the array is empty, just put item in index 0 and return:
		if (this.isEmpty() == true) {
			array[0] = item;
			size++;
			return;
		}
		else {
			// double the size of the array if the array is full:
			if(size + 1 == array.length) {
				// create a new array twice the size:
	            E[] array2 = (E[]) new Object[array.length*2];
	            for(int i = 0; i < size; i++) {
	                array2[i] = array[i];
	            }
	            array = array2;
	        }
			
			if (binarySearch(item) != size) { // make sure item insert location requires the shift of other elements
				for (int i = size; i >= binarySearch(item); i--) {
					array[i + 1] = array[i]; // shift elements above the item index 
				}
			}

			array[binarySearch(item)] = item; // insert item into the desired index
			size++; // increase size to represent the modified array size
			return;
		}
	}

	/**
	 * Returns the index in this corresponding to item
	 * 
	 * @param item - the element to search
	 * @return the index in this that corresponds to item.
	 */
	private int binarySearch(E item) {
		// initialize variables:
		int mid = 0;
		int low = 0;
		int high = size - 1;
		// loop until we converge to an index:
		while (low <= high) {
			mid = (low + high) / 2; // set mid to the middle index
			// reassign the low or high index depending on which side of the mid index item is on:
			if (compare(array[mid], item) == 0) {
				return mid + 1;
			} else if (compare(array[mid], item) < 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		// once we have found the closest element in array, decide which side of the index item goes on:
		if (compare(array[mid], item) > 0) {
			return mid + 1;
		}
		else {
			return mid;
		}
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		// loop through all elements in coll and call insert() for each:
		for (E element : coll) {
			this.insert(element);
		}

	}

	/**
	 * Chooses which compare method to use: either the default comparator, or a custom comparator if specified.
	 * 
	 * @param elt1 - first element to be compared
	 * @param elt2 - second element to be compared
	 * 
	 * @return int value corresponding to which element is greater
	 */
	@SuppressWarnings("unchecked")
	private int compare(E elt1, E elt2) {
		if (cmp == null)
			return ((Comparable<? super E>) elt1).compareTo(elt2);
		return cmp.compare(elt1, elt2);
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@Override
	public void clear() {
		size = 0;
	}

}
