package tc.misc.dp;

import BitStrings;

public class BitStringsTest {

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, int p1, int p2, boolean hasAnswer, int p3) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}" + "," + p1 + "," + p2);
    System.out.println("]");
    BitStrings obj;
    int answer;
    obj = new BitStrings();
    long startTime = System.currentTimeMillis();
    answer = obj.maxStrings(p0, p1, p2);
    long endTime = System.currentTimeMillis();
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
  public static void main(String[] args) {
    boolean all_right;
    all_right = true;

    String[] p0;
    int p1;
    int p2;
    int p3;

    // ----- test 0 -----
    p0 = new String[]   {"10101000000010101011111111111111111110101010000", "11111111111000000000000010101011111111111", "100000000000000000000000000000000000000000000000", "11111111111111010101010000000000000101011111111111", "1010100100001111101001010001110010100000011101011", "1111111000000000000000010101001011111101001010101", "110100101010010101010101010010101011011111101000", "1111111111111111100000000000000000001010100101011", "110010101010010000000000000000000101010010111", "100000000000111111101010010001111111101010100101", "111101010100000000000000000001010111111110101111", "111111111111110000000010101010000000111101010011", "101010010000000111111111111111111110101001010111", "111111111111111000000000000000000000000101010010", "101000000111111111111111111111010100101010100101", "101010100000000000000000001111111111111111101001", "1111111111111111111111111111110000000000000000000", "0000000000000000000000001111111111111101010100101", "0100111111100000000001010101010000000001111110101", "0000111111111111111111111111000000010101010011111"};
    p1 = 123;
    p2 = 411;
    p3 = 7;


    all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"00","110","101"};
    p1 = 2;
    p2 = 4;
    p3 = 2;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"111","01","11","10","101"};
    p1 = 3;
    p2 = 9;
    p3 = 5;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","10101010101010101010101010101010101010101010101010","1010101010101010101010101010101010101010101010" +
        "1010","10101010101010101010101010101010101010101010101010"};
    p1 = 500;
    p2 = 500;
    p3 = 20;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
    // ------------------

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }
  // END KAWIGIEDIT TESTING
}
}
