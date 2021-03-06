package topcoder.srm493;

public class AmoebaDivTwo {
  public int count(String[] table, int K) {
    int count = 0;
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length(); j++) {
        count += placeCount(i, j, K, table);
      }
    }
    return count;
  }

  private int placeCount(int i, int j, int k, String[] table) {
    boolean row = true, col = true;
    if (table.length - i < k) {
      col = false;
    }
    if (table[i].length() - j < k) {
      row = false;
    }

    for (int c = i; c < k + i && col; c++) {
      if (table[c].charAt(j) != 'A') {
        col = false;
      }
    }
    for (int r = j; r < k + j && row; r++) {
      if (table[i].charAt(r) != 'A') {
        row = false;
      }
    }

    int count = 0;
    if (row) {
      count += 1;
    }
    if (col) {
      count += 1;
    }

    if (k == 1) {
      return count / 2;
    }
    return count;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, int p1, boolean hasAnswer, int p2) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}" + "," + p1);
    System.out.println("]");
    AmoebaDivTwo obj;
    int answer;
    obj = new AmoebaDivTwo();
    long startTime = System.currentTimeMillis();
    answer = obj.count(p0, p1);
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

    String[] p0;
    int p1;
    int p2;

    // ----- test 0 -----
    p0 = new String[]{"MA"};
    p1 = 2;
    p2 = 0;
    all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"AAA", "AMA", "AAA"};
    p1 = 3;
    p2 = 4;
    all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"AA", "AA", "AA"};
    p1 = 2;
    p2 = 7;
    all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"MMM", "MMM", "MMM"};
    p1 = 1;
    p2 = 0;
    all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new String[]{"AAM", "MAM", "AAA"};
    p1 = 1;
    p2 = 6;
    all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 5 -----
    p0 = new String[]{"AAA", "AAM", "AAA"};
    p1 = 2;
    p2 = 9;
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
