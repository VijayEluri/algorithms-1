package tc.srm504;

public class ComparerInator {
  public int makeProgram(final int[] A, final int[] B, final int[] wanted) {
    int i;
    for (i = 0; i < A.length; i++) {
      if (A[i] != wanted[i]) {
        break;
      }
    }
    if (i == A.length) {
      return 1;
    }

    for (i = 0; i < A.length; i++) {
      if (B[i] != wanted[i]) {
        break;
      }
    }
    if (i == A.length) {
      return 1;
    }

    for (i = 0; i < A.length; i++) {
      final int value = A[i] < B[i] ? A[i] : B[i];
      if (value != wanted[i]) {
        break;
      }
    }
    if (i == A.length) {
      return 7;
    }

    for (i = 0; i < A.length; i++) {
      final int value = A[i] < B[i] ? B[i] : A[i];
      if (value != wanted[i]) {
        break;
      }
    }
    if (i == A.length) {
      return 7;
    }

    return -1;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(final int testNum, final int[] p0, final int[] p1, final int[] p2,
      final boolean hasAnswer, final int p3) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p0[i]);
    }
    System.out.print("}" + "," + "{");
    for (int i = 0; p1.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p1[i]);
    }
    System.out.print("}" + "," + "{");
    for (int i = 0; p2.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p2[i]);
    }
    System.out.print("}");
    System.out.println("]");
    ComparerInator obj;
    int answer;
    obj = new ComparerInator();
    final long startTime = System.currentTimeMillis();
    answer = obj.makeProgram(p0, p1, p2);
    final long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.println("\t" + p3);
    }
    System.out.println("Your answer:");
    System.out.println("\t" + answer);
    if (hasAnswer) {
      res = answer == p3;
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

  public static void main(final String[] args) {
    boolean all_right;
    all_right = true;

    int[] p0;
    int[] p1;
    int[] p2;
    int p3;

    // ----- test 0 -----
    p0 = new int[]{1};
    p1 = new int[]{2};
    p2 = new int[]{2};
    p3 = 1;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new int[]{1, 3};
    p1 = new int[]{2, 1};
    p2 = new int[]{2, 3};
    p3 = 7;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new int[]{1, 3, 5};
    p1 = new int[]{2, 1, 7};
    p2 = new int[]{2, 3, 5};
    p3 = -1;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new int[]{1, 3, 5};
    p1 = new int[]{2, 1, 7};
    p2 = new int[]{1, 3, 5};
    p3 = 1;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    p1 = new int[]{5, 4, 7, 8, 3, 1, 1, 2, 3, 4, 6};
    p2 = new int[]{1, 2, 3, 4, 3, 1, 1, 2, 3, 4, 6};
    p3 = 7;
    all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 5 -----
    p0 = new int[]{1, 5, 6, 7, 8};
    p1 = new int[]{1, 5, 6, 7, 8};
    p2 = new int[]{1, 5, 6, 7, 8};
    p3 = 1;
    all_right = KawigiEdit_RunTest(5, p0, p1, p2, true, p3) && all_right;
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
