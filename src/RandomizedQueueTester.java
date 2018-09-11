
public class RandomizedQueueTester {

	private static void test(Object expected, Object actual, int n) {
		System.out.printf("Test %d: ", n);
		if (expected.equals(actual)) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.");
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

	public static void main(String[] args) {
		int n = 1;
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		//Test 1: isEmpty returns correctly for empty queue.
		Object expected = (boolean) true;
		Object actual = rq.isEmpty();
		test(actual, expected, n);
	}

}
