

public class TheNumbersWithLuckyLastDigit {
  int[] nos;
  public int find(int n) {
  }

  private int[] build(int n) {
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, int p0, boolean hasAnswer, int p1) {
    System.out.print("Test " + testNum + ": [" + p0);
    System.out.println("]");
    TheNumbersWithLuckyLastDigit obj;
    int answer;
    obj = new TheNumbersWithLuckyLastDigit();
    long startTime = System.currentTimeMillis();
    answer = obj.find(p0);
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
    new TheNumbersWithLuckyLastDigit().find(1000000000);
    boolean all_right;
    all_right = true;

    int p0;
    int p1;

    // ----- test 0 -----
    p0 = 99;
    p1 = 4;
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = 11;
    p1 = 2;
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = 13;
    p1 = -1;
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = 1234567;
    p1 = 1;
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
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
