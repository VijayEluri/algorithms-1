package topcoder.srm509;

public class PalindromizationDiv2 {
  public static void main(String[] args) {
    boolean all_right;
    all_right = true;

    int p0;
    int p1;

    // ----- test 0 -----
    p0 = 25;
    p1 = 3;
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = 12321;
    p1 = 0;
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = 40;
    p1 = 4;
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = 2011;
    p1 = 9;
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = 0;
    p1 = 0;
    all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
    // ------------------

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }

  // END KAWIGIEDIT TESTING

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, int p0, boolean hasAnswer, int p1) {
    System.out.print("Test " + testNum + ": [" + p0);
    System.out.println("]");
    PalindromizationDiv2 obj;
    int answer;
    obj = new PalindromizationDiv2();
    final long startTime = System.currentTimeMillis();
    answer = obj.getMinimumCost(p0);
    final long endTime = System.currentTimeMillis();
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

  public int getMinimumCost(int x) {
    if (x < 10) {
      return 0;
    }

    int min = Integer.MAX_VALUE;
    if (x < 100) {
      for (int i = 0; i < 10; i++) {
        final int p = i * 10 + i;
        if (Math.abs(p - x) < min) {
          min = Math.abs(p - x);
        }
      }
      return min;
    } else if (x < 1000) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          final int p = j * 100 + i * 10 + j;
          if (Math.abs(p - x) < min) {
            min = Math.abs(p - x);
          }
        }
      }
      return min;
    } else if (x < 10000) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          final int p = j * 1000 + i * 100 + i * 10 + j;
          if (Math.abs(p - x) < min) {
            min = Math.abs(p - x);
          }
        }
      }
      return min;
    } else if (x < 100000) {
      for (int i = 0; i < 10; i++) {
        for (int k = 0; k < 10; k++) {
          for (int j = 0; j < 10; j++) {
            final int p = j * 10000 + i * 1000 + k * 100 + i * 10 + j;
            if (Math.abs(p - x) < min) {
              min = Math.abs(p - x);
            }
          }
        }
      }
      return min;
    }
    return 1;
  }
}
// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
