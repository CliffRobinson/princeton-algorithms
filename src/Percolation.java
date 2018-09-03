import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Scanner;

public class Percolation {

	private boolean[][] grid;
	private int openSites = 0;
	private int n;
	private int siteCount;

	private WeightedQuickUnionUF uf;

	public char block = '\u2588';
	public String blackSquare = this.block+""+this.block;
	public String whiteSquare = "  ";

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
	public boolean getCell(int row, int col) {
		if ( this.cellInBoundary(row, col) ) {
			return this.grid[row][col];
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void setCell(int row, int col, boolean value) {
		if (this.cellInBoundary(row, col)) {
			this.grid[row][col] = value;
			if (value) {
				this.openSites++;
				this.joinNeighbours(row, col);
			} else if (this.openSites > 0) {
				this.openSites--;
			}

		} else {
			System.out.printf("Row %d and column %d are out of boundary %d", row, col, this.n);
			throw new IllegalArgumentException();
		}
	}


	public void joinNeighbours(int row, int col) {
		joinPair(row, col, row-1, col);
		joinPair(row, col, row+1, col);
		joinPair(row, col, row, col-1);
		joinPair(row, col, row, col+1);
		 
	}

	public void joinPair(int row, int col, int i, int j) {
		if (this.cellInBoundary(i,j) && this.grid[i][j]) {
			//System.out.printf("Joining %d-%d to %d-%d\n", row, col, i, j);
			this.uf.union(this.translate(row, col), this.translate(i, j));
		}
	}
	
	public int translate(int row, int col) {
		int output = ((row-1) * (this.n-1))+col;
		//System.out.printf("Row %d and col %d gives translation %d\n", row, col, output);
		return output;
	}
	
	public int numberOfOpenSites() {
		return this.openSites;
	}

	public boolean cellInBoundary(int row, int col) {
		return (row > 0 && row < this.n && col > 0 && col < this.n);
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

	public boolean percolates() {
		return this.uf.connected(0, this.siteCount);
	}

	public void showGrid() {
		for (int i = 1; i < this.grid.length ; i++) {
			for (int j = 1; j <this.grid[i].length; j++) {
				if(this.isFull(i, j)) {
					System.out.print(this.blackSquare);
				} else {
					System.out.print(this.whiteSquare);
				}
			}
			System.out.println("");
		}

	}

	public static void main(String[] args) {
		File aDirectory = new File("./src/testData");
	    
	    FilenameFilter fileNameFilter = new FilenameFilter() {
	    	@Override
            public boolean accept(File dir, String name) {
               if(name.lastIndexOf('.')>0) {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  // get extension
                  String str = name.substring(lastIndex);
                  // match path name extension
                  if(str.equals(".txt")) {
                     return true;
                  }
               }
               return false;
            }
         };	    
	    // get a listing of all files in the directory
	    String[] filesInDir = aDirectory.list(fileNameFilter);

	    // have everything i need, just print it now
	    for ( int i=0; i<filesInDir.length; i++ ) {
	      System.out.println( "file: " + filesInDir[i] );
	    }
		System.out.println("");
	    File sample = new File("./src/testData/"+filesInDir[0]);
	    
	    try {
			Scanner scn = new Scanner(sample);
			
		    scn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	}

}
