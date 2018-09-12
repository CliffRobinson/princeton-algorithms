import java.util.Iterator;
import java.util.NoSuchElementException;

import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

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
		if (item == null) {throw new IllegalArgumentException("Attempted to add null to deque");}
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

	private Item removeNode(Node node) {
		if (this.isEmpty()) {throw new NoSuchElementException("Tried to remove from empty deque");}
		
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
		
		private Node current = first;
		
		public Item next() {
			if (!this.hasNext()) {throw new NoSuchElementException("Tried to iterate past bounds of deque");};
			Item currentItem = current.item;
			current = current.next;
			return currentItem;
		}

		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("lul wut?");
		}
	}

	public static void main(String[] args) {
		

	}

}
