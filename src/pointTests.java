
public class pointTests {

	public static void main(String[] args) {
		Point p0 = new Point(0,0); //This is the point we are comparing against. 
		
		
		int actual = 1;
		int expected = 2;
		int n = 1;
		
		//Point Tests:
		
		//Test 1, x0 < x1 & y0 < y1. p0 is less than p1.
		expected = -1;
		Point p1 = new Point(1,1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		
		//Test 2, x0 = x1 & y0 < y1. p0 is less than p1.
		expected = -1;
		p1 = new Point (0, 1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		
		//Test 3, x0 > x1 & y0 < y1. p0 is less than p1.
		expected = -1;
		p1 = new Point (-1, 1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		
		//Test 4, x0 < x1 & y0 < y1. p0 is less than p1.
		expected = -1;
		p1 = new Point (1, 0);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		//Test 5, x0 = x1 & y0 = y1. p0 equals p1.
		expected = 0;
		p1 = new Point (0, 0);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		//Test 6, x0 > x1 & y0 = y1. p0 is more than p1.
		expected = 1;
		p1 = new Point (-1, 0);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		//Test 7, x0 < x1 & y0 > y1. p0 is more than p1.
		expected = 1;
		p1 = new Point (1, -1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		//Test 8, x0 = x1 & y0 > y1. p0 is more than p1. 
		expected = 1;
		p1 = new Point (0, -1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		//Test 9, x0 > x1 & y0 > y1. p0 is more than p1.
		expected = 1;
		p1 = new Point (-1, -1);
		actual = p0.compareTo(p1);
		Suite.test(expected, actual, n++);
		
		//SlopeTo Tests:		
		System.out.print("\nSlopeTo Tests: \n");
		Point a = new Point(1,5);
		Point b = new Point(2,2);
		Point c = new Point(4,4);
		Point d = new Point(5,1);
		
		//Test 10: p0.slopeTo(a), expect 5.
		double dExpected = 5.0;
		double dActual = p0.slopeTo(a);
		Suite.test(dExpected, dActual, n++);
		
		//Test 11: a.slopeTo(p0), expect 5.
		dExpected = 5.0;
		dActual = a.slopeTo(p0);
		Suite.test(dExpected, dActual, n++);
		
		//Test 12: p0.slopeTo(b), expect 1.
		dExpected = 1.0;
		dActual = p0.slopeTo(b);
		Suite.test(dExpected, dActual, n++);
		
		//Test 13: b.slopeTo(p0), expect 1.
		dExpected = 1.0;
		dActual = b.slopeTo(p0);
		Suite.test(dExpected, dActual, n++);
		
		//Test 14: p0.slopeTo(c), expect 1.
		dExpected = 1.0;
		dActual = p0.slopeTo(c);
		Suite.test(dExpected, dActual, n++);
		
		//Test 15: c.slopeTo(p0), expect 1.
		dExpected = 1.0;
		dActual = c.slopeTo(p0);
		Suite.test(dExpected, dActual, n++);

		//Test 16: p0.slopeTo(d), expect 0.2.
		dExpected = 0.2;
		dActual = p0.slopeTo(d);
		Suite.test(dExpected, dActual, n++);
		
		//Test 17: d.slopeTo(p0), expect 1.
		dExpected = 0.2;
		dActual = d.slopeTo(p0);
		Suite.test(dExpected, dActual, n++);

		//Test 18: a.slopeTo(c), expect -1/3
		dExpected = (double) -1.0/3.0;
		dActual = a.slopeTo(c);
		Suite.test(dExpected, dActual, n++);
		
		//Test 19: a.slopeTo(b), expect -3
		dExpected = (double) -3.0;
		dActual = a.slopeTo(b);
		Suite.test(dExpected, dActual, n++);
	}

}
