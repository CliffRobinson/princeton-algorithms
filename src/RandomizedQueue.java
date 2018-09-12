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
		if (item == null) {throw new IllegalArgumentException("Tried to enqueue null value");}
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
		int index = (n == 1) ? 0 : StdRandom.uniform(n-1);
		Item item = a[index];
		
		a[index] = a[n-1];
		a[n-1] = null;
		n--;
		
		if(n > 0 && n <= (a.length/4)) {
			downsize();
		}
		if (item == null) {System.out.println("Null item!");}
		return item;
	}
	

	
	public Item sample() {
		if (isEmpty()) {throw new NoSuchElementException("Tried to sample from empty queue.");}
		int index = (n == 1 || n == 2) ? 0 : StdRandom.uniform(n-2);
		return a[index];
	}
	
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private Item[] shuffled;
		private int count;
		private int i;
		private int total;
		
		public RandomizedQueueIterator () {
			
			shuffled = (Item[]) new Object[n];
			
			for (int j = 0; j < n; j++) {
				shuffled[j] = a[j];
			}
			
			StdRandom.shuffle(shuffled);
			this.count = 0;
			this.i = 0;
			this.total = n;
		}
		
		public void remove() { throw new UnsupportedOperationException("Lel whet?"); }
		
		public boolean hasNext() {
			return count < total;
		}
		
		public Item next() {
			if (!this.hasNext()) { throw new NoSuchElementException("Tried to iterate into nothing");}
			Item item = null;
			
			while (item == null) {
				if (shuffled[i] != null) {
					item = shuffled[i];
					count++;
				}
				i++;
			}
			return item;
			
		}
		
	}
}
