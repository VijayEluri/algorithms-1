package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/*
ID: rdsr.me1
PROG: palsquare
LANG: JAVA
*/

public class palsquare {
	private static final char [] digits = "0123456789ABCDEFGHIJ".toCharArray();
	
	public static void main(
			String[] _)
		throws Exception {
		
		BufferedReader br = new BufferedReader(
								new FileReader("src/main/resources/palsquare.in"));
		int base = Integer.valueOf(br.readLine());
		br.close();
		
		PrintWriter pw = new PrintWriter("src/main/resources/palsquare.out");
		for (int n = 1; n <= 300; n++) {
			char [] n2b = toBase(n * n, base); 
			if (isPalindrome(n2b)) {
				pw.print(toBase(n, base));
				pw.print(" ");
				pw.println(n2b);
			}
		}
		pw.close();
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
