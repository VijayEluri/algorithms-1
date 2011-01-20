package tc.srm484;

public class AfraidOfEven {
  public int[] restoreProgression(int[] seq) {
    if (isAP(seq))
      return seq;

    for(int a = seq[0]; a <= Integer.MAX_VALUE/2; a *= 2)
      for (int b = seq[1]; b <= Integer.MAX_VALUE/2; b *= 2) {
        int[] ap = makeAP(a, b-a, seq.length);
        if (isPossible(ap, seq)) 
          return ap;
      }
    return null;
  }

  boolean isPossible(int[] ap, int[] seq) {
    for (int i = 0; i < ap.length; i++)
      if (ap[i] % seq[i] != 0)
        return false;
    return true;
  }

  int[] makeAP(int a, int d, int n) {
    int[] ap = new int[n];

    for (int i = 0; i < n; i++)
      ap[i] = a + i * d;
    return ap;
  }

  boolean isAP(int[] seq) {
    for (int i = 1; i < seq.length - 1; i++)
      if (seq[i] - seq[i-1] != seq[i+1] - seq[i])
        return false;
    return true;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, int[] p0, boolean hasAnswer, int[] p1) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p0[i]);
    }
    System.out.print("}");
    System.out.println("]");
    AfraidOfEven obj;
    int[] answer;
    obj = new AfraidOfEven();
    long startTime = System.currentTimeMillis();
    answer = obj.restoreProgression(p0);
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
        System.out.print(p1[i]);
      }
      System.out.println("}");
    }
    System.out.println("Your answer:");
    System.out.print("\t" + "{");
    for (int i = 0; answer.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(answer[i]);
    }
    System.out.println("}");
    if (hasAnswer) {
      if (answer.length != p1.length) {
        res = false;
      } else {
        for (int i = 0; answer.length > i; ++i) {
          if (answer[i] != p1[i]) {
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

    int[] p0;
    int[] p1;

    // ----- test 0 -----
    p0 = new int[]{1,1,3,1,5};
    p1 = new int[]{1,2,3,4,5};
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new int[]{9,7,5,3,1};
    p1 = new int[]{9,7,5,3,1};
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new int[]{999,999,999,999};
    p1 = new int[]{999,999,999,999};
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new int[]{7,47,5,113,73,179,53};
    p1 = new int[]{14,47,80,113,146,179,212};
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new int[]{749,999,125,1};
    p1 = new int[]{1498,999,500,1};
    all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
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
