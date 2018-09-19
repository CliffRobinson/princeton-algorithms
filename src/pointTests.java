
public class pointTests {

	public static void main(String[] args) {
		Point p0 = new Point(0,0); //This is the point we are comparing against. 
		
		
		int actual = 1;
		int expected = 2;
		int n = 1;
		
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
	}

}
