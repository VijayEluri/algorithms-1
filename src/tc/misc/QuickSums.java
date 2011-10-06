package tc.misc;

import java.util.HashMap;
import java.util.Map;

public class QuickSums {
  int n;
  int[][] s;
  int[] numbers;
  Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
  Map<String, Integer> cache = new HashMap<String, Integer>();

  public int minSums(String _numbers, int sum) {
    n = _numbers.length();
    numbers = new int[n];

    for (int i = 0; i < n; i++)
      numbers[i] = Character.digit(_numbers.charAt(i), 10);

    for (int i = 0, j = n; i < n; i++, j--)
      mapping.put(j, i);

    return solve(sum, n);
  }

  int solve(int sum, int j) {
    if (sum < 0 || j < 0)
      return -1;

    if (j == 0) {
      if (sum == 0)
        return 0;
      else
      return -1;
    }
    
    if (j == 1) {
      if (numbers[mapping.get(j)] == sum)
        return 0;
      else 
        return -1;
    }
    
    if (j == 2) {
     if (sum == 10 * numbers[mapping.get(j)] + numbers[mapping.get(j - 1)])
       return 0;
    }

    if (cache.containsKey(sum + " " + j))
      return cache.get(sum + " " + j);
  
    int minAdditions = -1;
    
    int m = numbers[mapping.get(j)];
    int x = solve(sum - m, j - 1);
    if (x != -1) {
      if (m == 0) minAdditions = x;
      else minAdditions = x + 1;
    }
    
    int n = 10 * numbers[mapping.get(j)] + numbers[mapping.get(j - 1)];
    int y = solve(sum - n, j - 2);
    if (y != -1) {
      if (minAdditions == -1)
        if (n == 0) minAdditions = y;
        else minAdditions = y + 1;
      else
        if (n == 0) minAdditions = Math.min(minAdditions, y);
        else minAdditions = Math.min(minAdditions, y) + 1;
    }
      
    cache.put(sum + " " + j, minAdditions);
    return minAdditions;
  }
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, String p0, int p1, boolean hasAnswer, int p2) {
		System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + p1);
		System.out.println("]");
		QuickSums obj;
		int answer;
		obj = new QuickSums();
		long startTime = System.currentTimeMillis();
		answer = obj.minSums(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p2);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p2;
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
		int p1;
		int p2;
		
		// ----- test 0 -----
		p0 = "99999";
		p1 = 45;
		p2 = 4;
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = "1110";
		p1 = 3;
		p2 = 3;
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = "0123456789";
		p1 = 45;
		p2 = 8;
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = "99999";
		p1 = 100;
		p2 = -1;
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = "382834";
		p1 = 100;
		p2 = 2;
		all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = "9230560001";
		p1 = 71;
		p2 = 4;
		all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
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
