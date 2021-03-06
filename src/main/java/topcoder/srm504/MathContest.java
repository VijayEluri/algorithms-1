package topcoder.srm504;

public class MathContest {
  char[] stack;
  int top, bottom;
  boolean reversed;
  boolean colorsChanged;
  Character Black = 'B';
  Character White = 'W';

  public int countBlack(final String ballSequence, final int repetitions) {
    stack = generateStack(ballSequence, repetitions);
    int count = 0;
    while (notEmpty(stack)) {
      final char ball = top(stack);
      if (ball == Black) {
        count += 1;
        changeColors(stack);
      } else {
        reverseStack(stack);
      }
    }
    return count;
  }

  private void reverseStack(final char[] seq) {
    if (reversed) {
      reversed = false;
    } else {
      reversed = true;
    }
  }

  private void changeColors(final char[] seq) {
    if (Black == 'B') {
      Black = 'W';
      White = 'B';
    } else {
      Black = 'B';
      White = 'W';
    }
  }

  private char top(final char[] seq) {
    if (reversed) {
      return seq[bottom--];
    } else {
      return seq[top++];
    }
  }

  private boolean notEmpty(final char[] seq) {
    return top <= bottom;
  }

  private char[] generateStack(final String ballSequence, final int repetitions) {
    final char[] a = ballSequence.toCharArray();
    final char[] stack = new char[ballSequence.length() * repetitions];
    int x = 0;
    for (int i = 0; i < repetitions; i++) {
      for (final char c : a) {
        stack[x++] = c;
      }
    }
    top = 0;
    bottom = stack.length - 1;
    return stack;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(final int testNum, final String p0, final int p1, final boolean hasAnswer,
      final int p2) {
    System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + p1);
    System.out.println("]");
    MathContest obj;
    int answer;
    obj = new MathContest();
    final long startTime = System.currentTimeMillis();
    answer = obj.countBlack(p0, p1);
    final long endTime = System.currentTimeMillis();
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

  public static void main(final String[] args) {
    boolean all_right;
    all_right = true;

    String p0;
    int p1;
    int p2;

    // ----- test 0 -----
    p0 = "BBWWB";
    p1 = 1;
    p2 = 2;
    all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = "BBB";
    p1 = 10;
    p2 = 1;
    all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = "BW";
    p1 = 10;
    p2 = 20;
    all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = "WWWWWWWBWWWWWWWWWWWWWW";
    p1 = 1;
    p2 = 2;
    all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
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
