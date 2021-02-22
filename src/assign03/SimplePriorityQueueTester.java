package assign03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class contains tests for SimplePriorityQueue.
 * 
 * @author Matthew Barkey and Ian Langston
 * @version February 9, 2021
 */
public class SimplePriorityQueueTester<E> extends SimplePriorityQueue<E> {
	private SimplePriorityQueue<Integer> intArray = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<Integer> intArray2 = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<Integer> intArray3 = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<Integer> intArray4 = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<Double> doubleArray = new SimplePriorityQueue<Double>();
	private SimplePriorityQueue<Double> doubleArray1 = new SimplePriorityQueue<Double>();
	private SimplePriorityQueue<Character> charArray1 = new SimplePriorityQueue<Character>();
	private SimplePriorityQueue<Character> charArray2 = new SimplePriorityQueue<Character>();
	private SimplePriorityQueue<String> stringArray1 = new SimplePriorityQueue<String>();
	private SimplePriorityQueue<String> stringArray2 = new SimplePriorityQueue<String>();

	@BeforeEach
	void setUp() throws Exception {

	}

	// Tests for integer arrays:
	@Test
	public void testInsertInt() {
		intArray3.insert(3);
		intArray4.insert(3);
		assertTrue(intArray3.deleteMin().equals(intArray4.deleteMin()));
	}

	@Test
	public void testDeleteInt() {
		intArray.insert(69);
		intArray.insert(3);
		intArray.insert(3);
		intArray.insert(1000);
		assertEquals(intArray.size(), 4);
		intArray.deleteMin();
		assertEquals(intArray.size(), 3);
	}

	@Test
	public void testInsertMultipleInts() {
		intArray.clear();
		intArray2.clear();
		intArray.insert(4);
		intArray.insert(3);
		intArray.insert(3);
		intArray.insert(2);
		intArray.insert(1);
		intArray.insert(4);
		intArray2.insert(1);
		intArray2.insert(2);
		intArray2.insert(4);
		intArray2.insert(4);
		intArray2.insert(3);
		intArray2.insert(3);

		for (int i = 0; i < 5; i++) {
			assertTrue(intArray.deleteMin().equals(intArray2.deleteMin()));
		}

	}

	@Test
	public void testInsertNegativeInts() {
		intArray.insert(4);
		intArray.insert(3);
		intArray.insert(-1);
		intArray.insert(2);
		intArray.insert(1);
		intArray.insert(-2);
		intArray2.insert(1);
		intArray2.insert(2);
		intArray2.insert(4);
		intArray2.insert(-2);
		intArray2.insert(3);
		intArray2.insert(-1);

		for (int i = 0; i < 5; i++) {
			assertTrue(intArray.deleteMin().equals(intArray2.deleteMin()));
		}

	}

	@Test
	public void testInsertZeroAndNegative() {
		intArray.insert(4);
		intArray.insert(3);
		intArray.insert(0);
		intArray.insert(2);
		intArray.insert(1);
		intArray.insert(-2);
		intArray2.insert(1);
		intArray2.insert(2);
		intArray2.insert(4);
		intArray2.insert(-2);
		intArray2.insert(3);
		intArray2.insert(0);

		for (int i = 0; i < 5; i++) {
			assertTrue(intArray.deleteMin().equals(intArray2.deleteMin()));
		}

	}

	@Test
	public void testFindMinInt() {
		intArray3.insert(2);
		intArray3.insert(5);
		intArray4.insert(2);
		intArray4.insert(20);
		assertTrue(intArray3.findMin().equals(intArray4.findMin()));
	}

	@Test
	public void testClearInts() {
		intArray3.insert(2);
		intArray4.insert(2);
		intArray3.clear();
		intArray4.clear();
		assertTrue(intArray3.size() == (intArray4.size()));
	}

	@Test
	public void testInsertAllStrings() {
		var stringArray = new SimplePriorityQueue<String>(new sortStringByLastLetter());
		var stringArray1 = new SimplePriorityQueue<String>(new sortStringByLastLetter());
		var stringArray2 = new ArrayList<String>();
		stringArray1.insert("sssa");
		stringArray1.insert("sssb");
		stringArray.insert("sssa");
		stringArray.insert("sssb");
		stringArray2.add("sssd");
		stringArray2.add("sssz");
		stringArray2.add("sssc");
		stringArray.insertAll(stringArray2);
		stringArray1.insertAll(stringArray2);
		for (int i = 0; i < stringArray.size(); i++) {
			assertTrue(stringArray.deleteMin().equals(stringArray1.deleteMin()));
		}
	}

	@Test
	public void testArrayDoubling() {
		// add 18 elements:
		intArray.insert(3);
		intArray.insert(2);
		intArray.insert(3);
		intArray.insert(4);
		intArray.insert(1);
		intArray.insert(4);
		intArray.insert(3);
		intArray.insert(2);
		intArray.insert(3);
		intArray.insert(4);
		intArray.insert(1);
		intArray.insert(4);
		intArray.insert(3);
		intArray.insert(2);
		intArray.insert(3);
		intArray.insert(4);
		intArray.insert(1);
		intArray.insert(4);
		assertTrue(intArray.size() == 18);
	}

	@Test
	public void testThrow() {
		intArray4.insert(2);
		intArray4.clear();
		assertThrows(NoSuchElementException.class, () -> {
			intArray4.findMin();
		});
	}

	// Tests for double arrays:
	@Test
	public void testInsertDouble() {
		doubleArray.insert(2.2);
		doubleArray1.insert(2.2);
		assertTrue(doubleArray.deleteMin().equals(doubleArray1.deleteMin()));
	}

	@Test
	public void testInsertMultiplePosAndNegDouble() {
		doubleArray.insert(2.2);
		doubleArray.insert(0.001);
		doubleArray.insert(-3.0);
		doubleArray.insert(1000.0);
		doubleArray.insert(0.0);
		doubleArray1.insert(2.2);
		doubleArray1.insert(0.001);
		doubleArray1.insert(-3.0);
		doubleArray1.insert(1000.0);
		doubleArray1.insert(0.0);
		for (int i = 0; i < 4; i++) {
			assertTrue(doubleArray.deleteMin().equals(doubleArray1.deleteMin()));
		}
	}

	@Test
	public void testInsertAllDoubles() {
		var doubleArray = new SimplePriorityQueue<Double>(new doubleComparator());
		var doubleArray1 = new ArrayList<Double>();
		doubleArray.insert(1.1);
		doubleArray.insert(0.2);
		doubleArray1.add(-99.9);
		doubleArray1.add(0.69);
		doubleArray.insertAll(doubleArray1);
		assertTrue(doubleArray.size() == 4);
	}

	@Test
	public void testClearDoubles() {
		doubleArray.insert(5.9);
		doubleArray1.insert(6.9);
		doubleArray1.clear();
		doubleArray.clear();
		assertTrue(intArray3.size() == (intArray4.size()));
	}

	// Tests for char arrays:
	@Test
	public void testInsertCharacter() {
		charArray1.insert('a');
		assertTrue(charArray1.deleteMin().equals('a'));
	}

	@Test
	public void findMinChar() {
		charArray1.insert('z');
		charArray1.insert('b');
		charArray1.insert('a');
		charArray1.insert('g');
		assertTrue(charArray1.findMin().equals('a'));
	}

	@Test
	public void deleteMinChar() {
		charArray1.insert('z');
		charArray1.insert('b');
		charArray1.insert('a');
		charArray1.insert('g');
		assertEquals(charArray1.size(), 4);
		assertTrue(charArray1.deleteMin().equals('a'));
		assertEquals(charArray1.size(), 3);
	}

	@Test
	public void testCharOrder() {
		charArray1.insert('a');
		charArray1.insert('b');
		charArray1.insert('c');
		charArray1.insert('d');
		charArray2.insert('b');
		charArray2.insert('c');
		charArray2.insert('d');
		charArray2.insert('a');
		for (int i = charArray1.size(); i > 0; i--)
			assertTrue(charArray1.deleteMin().equals(charArray2.deleteMin()));
	}

	// Tests for string arrays:
	@Test
	public void testInsertString() {
		stringArray1.insert("HELLO!");
		assertTrue(stringArray1.deleteMin().equals("HELLO!"));
	}
	
	@Test
	public void testFindMinString() {
		stringArray1.insert("HELLO!");
		stringArray1.insert("Ahhhhhhhhhh");
		assertTrue(stringArray1.findMin().equals("Ahhhhhhhhhh"));
	}
	
	@Test
	public void testDeleteMinString() {
		stringArray1.insert("HELLO!");
		stringArray1.insert("Ahhhhhhhhhh");
		assertEquals(stringArray1.size(), 2);
		assertTrue(stringArray1.deleteMin().equals("Ahhhhhhhhhh"));
		assertEquals(stringArray1.size(), 1);
	}

	@Test
	public void testStringOrder() {
		// create the same string array by calling the same inserts in a different
		// order:
		stringArray1.insert("HELLO!");
		stringArray1.insert("I do not");
		stringArray1.insert("know");
		stringArray1.insert("anything?");
		stringArray2.insert("anything?");
		stringArray2.insert("HELLO!");
		stringArray2.insert("know");
		stringArray2.insert("I do not");
		for (int i = charArray1.size(); i > 0; i--)
			assertTrue(charArray1.deleteMin().equals(charArray2.deleteMin()));
	}

	// custom Comparator methods we used in testing:
	private class sortStringByLastLetter implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			// sorts strings alphabetically according to the last character in the string
			int sort = o1.charAt(o1.length() - 1) - o2.charAt(o2.length() - 1);
			return sort;
		}

	}

	private class doubleComparator implements Comparator<Double> {
		@Override
		public int compare(Double o1, Double o2) {
			int sort = (int) (o1 - o2);
			return sort;
		}

	}
}
