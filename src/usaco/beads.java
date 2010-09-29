package usaco;

/*
ID: rdsr.me1
PROG: beads
LANG: JAVA
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class beads {
	private static void countBeads(
			BufferedReader r, PrintWriter w) throws Exception {
		r.readLine(); //not required
		char[] beads = r.readLine().toCharArray();
		
		int max = 0, prevCount = 0, whites = 0;
		for (int i = 0, sz = beads.length; i < sz;) {
			int j = i, count = 0;
			char colour = beads[i]; 
			for (; (beads[j] == colour || beads[j] == 'w') && count < sz; 
				   j = (j + 1) % sz, count++)
				;

			if (prevCount + count > max)
				max = prevCount + count;
			prevCount = count + whites;

			if (j == 0) j = sz - 1;
			else j = j - 1;
			
			if (beads[j] == 'w') {
				int wt = 0;
				for (; j != i && beads[j] == 'w'; wt++) {
					j--;
				}
				whites = wt;
			}
			i += count;
		}
		
		w.println(max);
	}
	
	public static void main(String[] _) throws Exception {
		FileWriter w = new FileWriter("src/main/resources/beads.out");
		countBeads(new BufferedReader(new FileReader("src/main/resources/beads.in")),
				new PrintWriter(w));
		w.close();
	}
}
