
public class Percolation {

	private boolean[][] grid;
	private int openSites = 0;
	
	public boolean getCell(int row, int col) {
		return this.grid[row][col];
	}
	
	public void setCell(int row, int col, boolean value) {
		if (value) {
			this.openSites++;
		} else if (this.openSites > 0) {
			this.openSites--;
		}
		this.grid[row][col] = value;
	}
	
	public int numberOfOpenSites() {
		return this.openSites;
	}

	public Percolation (int n) {
		this.grid = new boolean[n][n];
	}
	
	public void open (int row, int col) {
		this.setCell(row, col, true);
	}
	
	public boolean isOpen(int row, int col) {
		return this.getCell(row, col);
	}
	
	public boolean isFull(int row, int col) {
		return !this.isOpen(row, col);
	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation(5);
		
		p.open(2, 3);
		p.open(1, 4);
		
		for (int i = 0; i < p.grid.length ; i++) {
			for (int j = 0; j <p.grid[i].length; j++) {
				System.out.print(p.isOpen(i, j)+" ");
			}
			System.out.println("");
		}
		
		System.out.println(p.isFull(1, 4));
		System.out.println(p.numberOfOpenSites());
		
		p.setCell(2, 3, false);
		p.setCell(1, 4, false);
		System.out.println(p.numberOfOpenSites());
		
		p.setCell(0, 0, false);
		p.setCell(1, 0, false);
		
		System.out.println(p.numberOfOpenSites());
		
	}

}
