import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		
		int k = 3;
		
		try {
			k = Integer.parseInt(args[0]);
		} catch (IndexOutOfBoundsException e){
			StdOut.print("No args.");
		}
		
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
			String butts = StdIn.readString();
			rq.enqueue(butts);
		}
		

		
		for (int i = 0; i < k; i++) {
			StdOut.println(rq.dequeue());
		}
		
		

	}

}
