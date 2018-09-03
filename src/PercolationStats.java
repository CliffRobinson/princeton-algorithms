import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

	private final double[] estimates;
	private final double mean;
	private final double stdDev;
	private final double confidenceInterval;
	private final double confidenceLo;
	private final double confidenceHi;
	
	public PercolationStats( int n, int trials) {
		if  (n <= 0 || trials <= 0 ) {
			throw new IllegalArgumentException();
		}
		this.estimates = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			estimates[i] = trial(n);
			//System.out.println("Estimate: "+ estimates[i]);
		}
		
		this.mean = StdStats.mean(this.estimates);
		this.stdDev = StdStats.stddev(this.estimates);
		this.confidenceInterval = 1.960*(this.stdDev/Math.sqrt(trials));
		this.confidenceLo = this.mean - this.confidenceInterval;
		this.confidenceHi = this.mean + this.confidenceInterval;
	}
		
	public double mean() {
		return this.mean;
	}
	
	public double stddev() {
		return this.stdDev;
	}
		
	public double confidenceLo() {
		return this.confidenceLo;
	}
	
	public double confidenceHi() {
		return this.confidenceHi;
	}
	
	private double trial(int n) {
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
		//System.out.println("Running a test");
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, T);
		
		System.out.printf("%% java-algs4 PercolationStats %d %d \n", n, T);
		System.out.printf("mean 					= %f\n", ps.mean());
		System.out.printf("stddev					= %f\n", ps.stddev());
		 System.out.print("95% confidence interval	= [ "+ps.confidenceLo()+", "+ps.confidenceHi()+" ]");
	}

}
