package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/*
ID: rdsr.me1
PROG: dualpal
LANG: JAVA
*/

public class dualpal {
	private static final char [] digits = "0123456789ABCDEFGHIJ".toCharArray();

	public static void main(
			String[] _)
		throws Exception {

		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			br = new BufferedReader(
					new FileReader("src/main/resources/dualpal.in"));
			pw = new PrintWriter("src/main/resources/dualpal.out");
			
			String[] nos = br.readLine().split(" ");
			int N = Integer.valueOf(nos[0]);
			int S = Integer.valueOf(nos[1]);
			
			for (int n = S + 1, count = 0; count < N; n++) {
				int palCount = 0;
				
				for (int base = 2;
					 base <= 10 && palCount < 2; 
					 base++) {
					if (isPalindrome(toBase(n, base)))
						palCount++;
				}
				
				if (palCount == 2) {
					count++;
					pw.println(n);
				}
			}
		} finally {
			if (br != null) br.close();
			if (pw != null) pw.close();
		}
	}

	private static boolean isPalindrome(char[] number) {
		for (int i = 0, j = number.length - 1;
		i < j;
		i++, j--) 
			if (number[i] != number[j])
				return false;

		return true;
	}

	private static char[] toBase(int number, int base) {
		/*
		 * converts a number to the specified base
		 * both the number and the base are in base 10
		 */

		if (number < base)
			return new char[]{digits[number]};

		int n = (int) (Math.log(number)/Math.log(base));
		char[] cnumber = new char[n + 1];

		int i = n;
		int e = number;
		while (e > 0) {
			int r = e % base;	
			e = e / base;
			cnumber[i--] = digits[r] ;
		}
		return cnumber;
	}
}
