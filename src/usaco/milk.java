package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
ID: rdsr.me1
PROG: milk
LANG: JAVA
*/

class Produce { 
	public final long price;
	public final long amount;
	
	Produce(int _price, int _amount) { 
		price = _price; 
		amount = _amount;
	}
	
	Produce(String[] data) throws Exception { 
		price = Integer.valueOf(data[0]); 
		amount = Integer.valueOf(data[1]);
	}
	
	public static final Comparator<Produce> cmp = new Comparator<Produce>() {
		public int compare(Produce f1, Produce f2) {
			if (f1.price < f2.price)
				return -1;
			if (f1.price == f2.price)
				return 0;
			return 1;
		}
	};
}

public class milk {
	public static long solve(
		List<Produce> produces, int amount) {

		Collections.sort(produces, Produce.cmp);

		long left = amount;
		long minPrice = 0;

		for (int i = 0; left > 0 && i < produces.size(); i++) {
			Produce p = produces.get(i);
			if (p.amount < left) {
				left = left - p.amount; 
				minPrice += p.price * p.amount;
			} else {
				minPrice += p.price * left;
				left = 0;
			}
		}
		
		return minPrice;
	}
	
	public static void main(
			String[] _)
		throws Exception {

		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			br = new BufferedReader(
					new FileReader("src/main/resources/milk.in"));
			pw = new PrintWriter("src/main/resources/milk.out");
				
			String[] nos = br.readLine().split(" ");
			int n = Integer.valueOf(nos[0]);
			int m = Integer.valueOf(nos[1]);
			
			List<Produce> produces = new ArrayList<Produce>(m);
			for (int i = 0; i < m; i++)
				produces.add(new Produce(br.readLine().split(" ")));
			
			pw.println(solve(produces, n));

		} finally {
			if (br != null) br.close();
			if (pw != null) pw.close();
		}
	}
}
