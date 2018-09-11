import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdRandom;

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
		return n;
	}
	
	public void enqueue(Item item) {
		if (n == a.length) {upsize();}
		a[n] = item;
		n++;
	}
	
	private void upsize() {
		resize((a.length*2));
	}
		
	private void downsize() {
		resize((a.length/2));
	}
	
	private void resize(int newsize) {
		Item[] newA = (Item[]) new Object[newsize];
		int newI = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				newA[newI] = a[i];
				newI++;
			}
		}
		a = newA;
	}
	
	public Item dequeue() {
		if (isEmpty()) {throw new NoSuchElementException("Tried to deque from empty queue.");}
		Item item = null;
		int index = 0;
		
		while (item == null) {
			index = StdRandom.uniform(a.length);
			item = a[index];
		}
		a[index] = null;
		n--;
		
		if(n > 0 && n <= (a.length/4)) {
			downsize();
		}
		return item;
	}
	

	
	public Item sample() {
		Item item = null;
		int index = 0;
		
		while (item == null) {
			index = StdRandom.uniform(a.length);
			item = a[index];
		}
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private Item[] shuffled;
		private int n;
		
		public RandomizedQueueIterator () {
			shuffled = a;
			StdRandom.shuffle(shuffled);
			this.n = 0;
		}
		
		public void remove() { throw new UnsupportedOperationException("Lel whet?"); }
		
		public boolean hasNext() {
			return n < shuffled.length;
		}
		
		public Item next() {
			if (!this.hasNext()) { throw new NoSuchElementException("Tried to iterate into nothing");}
			Item item = null;
			
			while (item == null) {
				if (shuffled[n] != null) {
					item = shuffled[n];
				}
				n++;
			}
			return item;
			
		}
		
	}
}
