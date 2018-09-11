import java.util.Iterator;
import java.lang.UnsupportedOperationException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int n;
	private Item[] a;
	
	public RandomizedQueue() {
		this.n = 0;
		this.a = (Item[]) new Object[2];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		
	}
	
	public void enqueue(Item item) {
		
	}
	
	public Item dequeue() {
		
	}
	
	public Item sample() {
		
	}
	
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		public void remove() { throw new UnsupportedOperationException("Lel whet?"); }
		
		public boolean hasNext() {
			return false;
		}
		
		public Item next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("Tried to iterate into nothing");
			}
			
		}
		
	}
}
