package tc.misc.dp;

public class BikeRidingTest {
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, int[] p1, int p2, int p3, boolean hasAnswer, int p4) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}" + "," + "{");
    for (int i = 0; p1.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p1[i]);
    }
    System.out.print("}" + "," + p2 + "," + p3);
    System.out.println("]");
    BikeRiding obj;
    int answer;
    obj = new BikeRiding();
    long startTime = System.currentTimeMillis();
    answer = obj.countRoutes(p0, p1, p2, p3);
    long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.println("\t" + p4);
    }
    System.out.println("Your answer:");
    System.out.println("\t" + answer);
    if (hasAnswer) {
      res = answer == p4;
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
    int[] p1;
    int p2;
    int p3;
    int p4;

    // ----- test 0 -----
    p0 = new String[]   {"0000000010", "1000100010", "0000000010", "1100000010", "1010100000", "0110000000", "0000100101", "1100110100", "0000000000", "1110000000"};
    p1 = new int[]{0,3,8};
    p2 = 4;
    p3 = 100;
    p4 = -1;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"01000","00100","00010","01001","00000"};
    p1 = new int[]{0};
    p2 = 4;
    p3 = 10;
    p4 = -1;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"000111000000000","000111000000000","000111000000000","000000111000000","000000111000000","000000111000000","000000000111000","000000000111000","000000000111000","000000000000111","000000000000111","000000000000111","000000000000001","000000000000001","000000000000000"};
    p1 = new int[]{0,1,2};
    p2 = 14;
    p3 = 1000;
    p4 = 243;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"010","100","001"};
    p1 = new int[]{0};
    p2 = 2;
    p3 = 10;
    p4 = 0;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"00101","00100","00010", "11000", "00000"};
    p1 = new int[]{3};
    p2 = 2;
    p3 = 100;
    p4 = -1;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, p3, true, p4) && all_right;

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }
}
