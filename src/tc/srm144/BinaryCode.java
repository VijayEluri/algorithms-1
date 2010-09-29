package src.tc.srm144;

// 500 points div-2

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class BinaryCode {
	public String[] decode(String message) {
		char[] Q = message.toCharArray();
		char[] P = new char[message.length()];
		P[0] = '0';
		String p0 = decode(Q, P);
		P[0] = '1';
		String p1 = decode(Q, P);
		return new String[]{p0, p1};
	}
	
	private String decode(char[] Q, char[] P) {
		if (Q.length == 1) 
			if (Q[0] == '3' || Q[0] == '2')
				return "NONE";
			else if (Q[0] == '0' && P[0] == '1')
				return "NONE";
			else if (Q[0] == '1' && P[0] == '0')
				return "NONE";
			else 
				return String.valueOf(P);
			
		P[1] = diff(Q[0], P[0]);
		if (illegal(P[1]))
			return "NONE";
		
		int i = 2;
		for (; i < Q.length; i++) {
			P[i] = diff(Q[i-1], P[i-1], P[i-2]);
			if (illegal(P[i]))
				return "NONE";
		}
		
		if (P[i-1] == diff(Q[i-1], P[i-2]))
			return String.valueOf(P);
		return "NONE";
	}	

	private boolean illegal(char c) {
		return c != '1' && c != '0';
	}

	private char diff(char c, char d, char e) {
		return diff(diff(c, d), e);
	}

	private char diff(char c, char d) {
		return Character.forDigit(c - d, 10);
	}

	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, String p0, boolean hasAnswer, String[] p1) {
		System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"");
		System.out.println("]");
		BinaryCode obj;
		String[] answer;
		obj = new BinaryCode();
		long startTime = System.currentTimeMillis();
		answer = obj.decode(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.print("\t" + "{");
			for (int i = 0; p1.length > i; ++i) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print("\"" + p1[i] + "\"");
			}
			System.out.println("}");
		}
		System.out.println("Your answer:");
		System.out.print("\t" + "{");
		for (int i = 0; answer.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print("\"" + answer[i] + "\"");
		}
		System.out.println("}");
		if (hasAnswer) {
			if (answer.length != p1.length) {
				res = false;
			} else {
				for (int i = 0; answer.length > i; ++i) {
					if (!answer[i].equals(p1[i])) {
						res = false;
					}
				}
			}
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		String p0;
		String[] p1;
		
		// ----- test 0 -----
		p0 = "123210122";
		p1 = new String[]{"011100011","NONE"};
		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = "11";
		p1 = new String[]{"01","10"};
		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = "22111";
		p1 = new String[]{"NONE","11001"};
		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = "123210120";
		p1 = new String[]{"NONE","NONE"};
		all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = "3";
		p1 = new String[]{"NONE","NONE"};
		all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = "12221112222221112221111111112221111";
		p1 = new String[]{"01101001101101001101001001001101001","10110010110110010110010010010110010"};
		all_right = KawigiEdit_RunTest(5, p0, true, p1) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	// END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
