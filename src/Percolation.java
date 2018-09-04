import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[][] grid;
	private int openSites = 0;
	private int n;
	private int siteCount;
	private boolean hasPercolated = false;

	private WeightedQuickUnionUF uf;

	//	private char block = '\u2588';
	//	private String blackSquare = this.block+""+this.block;
	//	private String whiteSquare = "  ";

	public Percolation (int input) {

		if (input <= 0) {
			throw new IllegalArgumentException();
		} else {
			int n = input+1;
			this.n = n;
			this.siteCount = ((input*input)+1);		//this int equals the value of the final (virtual) site. The first virtual site is 0. 
			this.grid = new boolean[n][n];
			this.uf = new WeightedQuickUnionUF(siteCount+1);

			for (int i = 1; i < n; i++) {
				this.uf.union(0, i);
				this.uf.union(siteCount, siteCount-i);
			} // join the virtual sites to the top and bottom row.

		}
	}	

	public void open (int row, int col) {
		this.setCell(row, col, true);
	}

	public boolean isOpen(int row, int col) {
		return this.getCell(row, col);
	}

	public boolean isFull(int row, int col) {
		if (this.hasPercolated) {
			return this.isOpen(row, col) && this.uf.connected(0, this.translate(row, col));
		} else {
			return this.isOpen(row, col) && this.uf.connected(0, this.translate(row, col)); //is there a way I can change the behaviour of isfull once i know the system has percolated?
		}
		
	}

	public int numberOfOpenSites() {
		return this.openSites;
	}

	public boolean percolates() {
		if (this.hasPercolated) {
			return true;
		} // thus hasPercolated == false 
		
		boolean outcome;
		if (this.grid.length == 2) {
			outcome =  this.grid[1][1];
		} else {
			outcome =  this.uf.connected(0, this.siteCount);
		}

		if (outcome == false) return outcome;
		
		this.hasPercolated = true;
		return true;
		
	}
	
	private boolean cellInBoundary(int row, int col) {
		return (row > 0 && row < this.n && col > 0 && col < this.n);
	}

	//	private void showGrid() {
	//
	//		String boundary = "**";
	//
	//		for (int i = 0; i < (this.n-1)*2; i++) {
	//			boundary = boundary + "*";
	//		}
	//
	//		System.out.println(boundary);
	//
	//		for (int i = 1; i < this.grid.length ; i++) {
	//			System.out.print("*");
	//			for (int j = 1; j <this.grid[i].length; j++) {
	//				if(this.isOpen(i, j) == false) {
	//					System.out.print(this.blackSquare);
	//				} else {
	//					System.out.print(this.whiteSquare);
	//				}
	//			}
	//			System.out.print("*");
	//			System.out.println("");
	//		}
	//		System.out.println(boundary);
	//	}

	private boolean getCell(int row, int col) {
		if ( this.cellInBoundary(row, col) ) {
			return this.grid[row][col];
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setCell(int row, int col, boolean value) {
		if (this.cellInBoundary(row, col)) {
			if (this.grid[row][col] != value) {
				this.grid[row][col] = value;
				if (value) {
					this.openSites++;
					this.joinNeighbours(row, col);
				} else if (this.openSites > 0) {
					this.openSites--;
				}
			}
		} else {
			throw new IllegalArgumentException("Row "+row+" and column "+col+" are out of boundary "+this.n);
		}
	}

	private void joinNeighbours(int row, int col) {
		joinPair(row, col, row-1, col);
		joinPair(row, col, row+1, col);
		joinPair(row, col, row, col-1);
		joinPair(row, col, row, col+1);

	}

	private void joinPair(int row, int col, int i, int j) {
		if (this.cellInBoundary(i,j) && this.grid[i][j]) {
			//System.out.printf("Joining %d-%d to %d-%d\n", row, col, i, j);
			this.uf.union(this.translate(row, col), this.translate(i, j));
		}
	}

	private int translate(int row, int col) {
		int output = ((row-1) * (this.n-1))+col;
		//System.out.printf("Row %d and col %d gives translation %d\n", row, col, output);
		return output;
	}

	public static void main(String[] args) {
		//testing functions shifted out to PercolationTester.

	}


}
