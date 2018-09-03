import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class PercolationTester {

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
			//System.out.printf( "file %d: %s \n", i,  filesInDir[i] );
			File sample = new File("./src/testData/"+filesInDir[i]);
			test(sample);
		}
	}

	private static void test(File sample) {
		try {
			Scanner scn = new Scanner(sample);

			System.out.print("Name: " + sample.getName());


			int n = scn.nextInt();

			Percolation p = new Percolation(n);

			while(scn.hasNextInt()) {
				int i = scn.nextInt();
				int j = scn.nextInt();
				p.open(i, j);
			}
			scn.close();


			if (p.percolates()) {
				System.out.print(" - Percolates.\n");
			} else {
				System.out.print(" - Does not percolate.\n");
			}

			//System.out.println("");
			//p.showGrid();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
