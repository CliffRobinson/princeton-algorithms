import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

	private final double[] estimates;
	
	public PercolationStats( int n, int trials) {
		if  (n <= 0 || trials <= 0 ) {
			throw new IllegalArgumentException();
		}
		this.estimates = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			estimates[i] = trial(n);
			//System.out.println("Estimate: "+ estimates[i]);
		}
		
	}
		
	public double mean() {
		return StdStats.mean(this.estimates);
	}
	
	public double stddev() {
		return StdStats.stddev(this.estimates);
	}
	
	private double confidenceInterval() {
		double stdDev = this.stddev();
		double z = 1.960;
		double n = this.estimates.length;
		return z*(stdDev/Math.sqrt(n));
	}
	
	public double confidenceLo() {
		return this.mean() - this.confidenceInterval();
	}
	
	public double confidenceHi() {
		return this.mean() + this.confidenceInterval();
	}
	
	private double  trial(int n) {
		Percolation p = new Percolation(n);
				
		while (!p.percolates()) {
			int i = StdRandom.uniform(n)+1;
			int j = StdRandom.uniform(n)+1;
			if (!p.isOpen(i, j)) {
				p.open(i, j);
			}
		}
		//System.out.println("Open sites are "+ p.numberOfOpenSites());
		//System.out.println("n is "+ n);
		double output = ((double) p.numberOfOpenSites())/(n*n);
		//System.out.println("Output is: "+ output);
		return (output);
	}
	
	public static void main(String[] args) {
		System.out.println("Running a test");
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, T);
		
		System.out.printf("%% java-algs4 PercolationStats %d %d \n", n, T);
		System.out.printf("mean 					= %f\n", ps.mean());
		System.out.printf("stddev					= %f\n", ps.stddev());
		 System.out.print("95% confidence interval	= [ "+ps.confidenceLo()+", "+ps.confidenceHi()+" ]");
	}

}
