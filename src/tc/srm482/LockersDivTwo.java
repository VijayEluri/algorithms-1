package tc.srm482;

// 500 points, div-2

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class LockersDivTwo
{
	
	public int lastOpened(int N)
	{
		boolean[] arr = new boolean[N+1];
		Arrays.fill(arr, true);
		int steps = 2;
		int last = 1;
		for(int i=1; i<=N; i++){
			if(!arr[i])	continue;
			if(arr[i]) last = i;
			arr[i] = false;
			int local = 0;
			for(int j=i; j<=N; j++){
				if(arr[j])
					local++;
				if(local==steps){//System.out.println(".."+j);
					arr[j] = false;
					last = j;
					local=0;
				}
			}
			steps++;
			//System.out.println("==="+steps);
		}
		return last;
	}
	
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int p0, boolean hasAnswer, int p1) {
		System.out.print("Test " + testNum + ": [" + p0);
		System.out.println("]");
		LockersDivTwo obj;
		int answer;
		obj = new LockersDivTwo();
		long startTime = System.currentTimeMillis();
		answer = obj.lastOpened(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p1);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p1;
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
		
		int p0;
		int p1;
		
		// ----- test 0 -----
		p0 = 10000;
		p1 = 6;
		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = 42;
		p1 = 42;
		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = 314;
		p1 = 282;
		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
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
