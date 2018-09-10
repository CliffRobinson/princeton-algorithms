import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int n; // # of items.
	private Node first;
	private Node last;

	private class Node {
		private Item item;
		private Node prev;
		private Node next;
	}

	public Deque() {
		this.n = 0;
		this.first = null;
		this.last = null;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void addNode(Item item, Node prev, Node next) {
		Node newNode = new Node();
		newNode.item = item;
		newNode.prev = prev;
		newNode.next = next;
		this.n++;
		if (prev == null) {				//Adding a new first item
			this.first = newNode;
		} else {						//Adding a new not-first item
			prev.next = newNode;					
		}
		if (next == null) {				//Adding a new last item. 
			this.last = newNode;
		} else {						//Adding a new not-last item
			next.prev = newNode;
		}
	}

	public void addFirst(Item item) {
		addNode(item, null, this.first);
	}

	public void addLast(Item item) {
		addNode(item, this.last, null);
	}

	public Item removeNode(Node node) {
		if (this.isEmpty()) {throw new NoSuchElementException();}
		
		if (node.prev == null) { 		// Removing the first item
			this.first = node.next;
		} else {						// Removing NOT the first item
			node.prev.next = node.next;
		} 
		
		if (node.next == null) { 		// Removing the last item 
			this.last = node.prev;
		} else { 						// Removing NOT the last item
			node.next.prev = node.prev;
		}
		
		this.n--;
		return node.item;

	}

	public Item removeFirst() {
		return removeNode(this.first);
	}

	public Item removeLast() {
		return removeNode(this.last);
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		public Item next() {
			return removeFirst();
		}

		public boolean hasNext() {
			return !isEmpty();
		}
	}

	private static void test(Object expected, Object actual, int n) {
		System.out.printf("Test %d: ", n);
		if (expected.equals(actual)) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		Deque<String> d = new Deque<String>();
		// Test 1: isEmpty returns correctly for new deque.
		int n = 1;
		Object expected = (boolean) true;
		Object actual = (boolean) d.isEmpty();
		test(expected, actual, n++);

		// Test 2: size returns correctly for new deque.
		expected = (int) 0;
		actual = (int) d.size();
		test(expected, actual, n++);

		// Test 3: isEmpty returns correctly with one item added.
		d.addFirst("One");
		expected = (boolean) false;
		actual = d.isEmpty();
		test(expected, actual, n++);

		// Test 4: size returns correctly with one item added.
		expected = (int) 1;
		actual = d.size();
		test(expected, actual, n++);

		// Test 5: removeFirst returns "One".
		expected = (String) "One";
		actual = (String) d.removeFirst();
		test(expected, actual, n++);
		
		//Test 6: addFirst One, Two, Three, expect One, Two, Three, from removeLast.
		expected = (String) "OneTwoThree";
		d.addFirst("One");
		d.addFirst("Two");
		d.addFirst("Three");
		
		actual = (String) d.removeLast()+d.removeLast()+d.removeLast();
		test(expected, actual, n++);

		//Test 7: addLast One, Two, Three, expect One, Two, Three, from removeFirst.
		expected = (String) "OneTwoThree";
		d.addLast("One");
		d.addLast("Two");
		d.addLast("Three");
		
		actual = (String) d.removeFirst()+d.removeFirst()+d.removeFirst();
		test(expected, actual, n++);
		

	}

}
