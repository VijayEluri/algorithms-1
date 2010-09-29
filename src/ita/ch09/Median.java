package ita.ch09;

import java.util.Arrays;
import java.util.Random;

public class Median {
	int median(
	  int[] u, int p, int q, int[] v, int r, int s) {
		int m = median(v, p, q);
		int n = median(u, r, s);
		
		if (p == q)
			return m < n ? m : n;
		if (m == n)
			return m;
		if (m < n)
			return median(u, (p+q)/2, q, v, r, (r+s)/2);
		return median(u, p, (p+q)/2, v, (r+s)/2, s); 
		
	}

	private int median(int[] u, int p, int q) {
		return u[(p+q)/2];
	}
	
	private static int[] genarray(int n) {
		int[] v = new int[n];
		Random r = new Random(n);
		for(int i=0; i < n; i++) {
			v[i] = r.nextInt();
		}
		return v;
	}
	
	public static void main(String[] _) {
		int[] u = genarray(10000000);
		int[] v = genarray(10000000);
		
		Arrays.sort(u); Arrays.sort(v);
		
		System.out.println( 
		  new Median().median(
				  u, 0, 9999999, v, 0, 9999999));
	}
}
