package topcoder.misc.dp;

// Single Round Match 351 Round 1 - Division II, Level Three

public class InsertSort {
  public int calcMinimalCost(int[] theArray) {
    int sum = 0;
    for (int a : theArray) {
      sum += a;
    }

    int[] s = new int[theArray.length];
    s[0] = theArray[0];

    for (int i = 1; i < theArray.length; i++) {
      s[i] = theArray[i];
      for (int j = i - 1; j >= 0; j--) {
        if (theArray[i] >= theArray[j] && s[j] + theArray[i] > s[i]) {
          s[i] = s[j] + theArray[i];
        }
      }
    }

    int noCost = Integer.MIN_VALUE;
    for (int i = 0; i < s.length; i++) {
      if (s[i] > noCost) {
        noCost = s[i];
      }
    }

    return sum - noCost;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
