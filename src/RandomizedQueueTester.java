import edu.princeton.cs.algs4.Stopwatch;
import java.util.Iterator;

public class RandomizedQueueTester {

	private static void test(Object actual, Object expected, int n) {
		System.out.printf("Test %d: ", n);
		if (expected.equals(actual)) {
			System.out.print("passed!");
		} else {
			System.out.println("FAILED.");
			System.out.println("Expected is "+ expected);
			System.out.println("Actual is "+ actual);
			
		}
		System.out.print("\n");
	}

	private static void testArray(Object[] expected, Object[] actual, int n) {
		System.out.printf("Test %d: ", n);
		boolean result = true;
		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(actual[i]))
				result = false;
		}

		if (result) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.");
		}
		System.out.print("\n");
	}

	public static void timeTrial(int n) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		Stopwatch loadClock = new Stopwatch();
		for (int i = 0; i < n; i++) {
			rq.enqueue(i);
		}
		double loadTime = loadClock.elapsedTime();
		
		Stopwatch iterateConstructorClock = new Stopwatch();
		Iterator<Integer> rqi = rq.iterator();
		double constructorTime = iterateConstructorClock.elapsedTime();
		
		Stopwatch iterateClock = new Stopwatch();
		while(rqi.hasNext()) {
			rqi.next();
		}
		double iterateTime = iterateClock.elapsedTime();
		System.out.printf("N=%d:	Load: %f	Construct:	%f	Iterate:	%f\n", n, loadTime, constructorTime, iterateTime);
		
	}
	
	public static void main(String[] args) {
		int n = 1;
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		//Test 1: isEmpty returns correctly for empty queue.
		Object expected = (boolean) true;
		Object actual = rq.isEmpty();
		test(actual, expected, n++);
		
		//Test 2: size returns correctly for empty queue.
		expected = 0;
		actual = rq.size();
		test(actual, expected, n++);
		
		rq.enqueue("one");
		
		//Test 3: isEmpty returns correctly for queue with one item.
		expected = false;
		actual = rq.isEmpty();
		test(actual, expected, n++);
		
		//Test 4: size returns correctly for queue with one item.
		expected = 1;
		actual = rq.size();
		test(actual, expected, n++);
		
		//Test 5: sample works correctly with one item.
		
		expected = "one";
		actual = rq.sample();
		test(actual, expected, n++);
		
		//Test 6: deque works correctly with one item.
		expected = "one";
		actual = rq.dequeue();
		test(actual, expected, n++);
		
		//Test 7: isEmpty returns correctly for queue post emptying.
		expected = true;
		actual = rq.isEmpty();
		test(actual, expected, n++);
		
		//Test 8: size returns correctly for queue post emptying.
		expected = 0;
		actual = rq.size();
		test(actual, expected, n++);
		
		//Test 9: enqueue "one" three times and see what happens.
		
		rq.enqueue("one");
		rq.enqueue("one");
		rq.enqueue("one");
		
		expected = "oneoneone";
		actual = rq.dequeue()+rq.dequeue()+rq.dequeue();
		test(actual, expected, n++);	
		
		//Test 10: Time trials.
		n++;
		System.out.println("Test 10:");
		//timeTrial(100);
		//timeTrial(1000);
		timeTrial(10000);
		timeTrial(100000);
		timeTrial(1000000);
		//timeTrial(10000000);
		System.out.println("");
		
		//Test 11: Simultaneous Iterators:
		rq.enqueue("one");
		rq.enqueue("two");
		rq.enqueue("three");
		rq.enqueue("four");
		rq.enqueue("five");
		
		Iterator<String> rqi1 = rq.iterator();
		Iterator<String> rqi2 = rq.iterator();
		System.out.printf("1: %s, %s, %s, %s, %s,\n", rqi1.next(), rqi1.next(), rqi1.next(), rqi1.next(), rqi1.next() );
		System.out.printf("2: %s, %s, %s, %s, %s, \n", rqi2.next(), rqi2.next(), rqi2.next(), rqi2.next(), rqi2.next() );
		
		//Test 12: Internal data structure:
		
		System.out.printf("\nRQ is empty: %b\n", rq.isEmpty());
		
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		rq.enqueue("six");
		rq.enqueue("seven");
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		rq.enqueue("eight");
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		rq.enqueue("nine");
		rq.enqueue("ten");
		System.out.printf("Dequed item is %s, n is %d\n", rq.dequeue(), rq.size());
		
		
	}

}
