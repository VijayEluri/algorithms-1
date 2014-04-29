package tc.srm152;

// 1000 points

public class ProblemWriting {
  public String myCheckData(String dotForm) {
    if (dotForm.length() < 1 || dotForm.length() > 25) {
      return "dotForm must contain between 1 and 25 characters, inclusive.";
    }

    char[] df = dotForm.toCharArray();
    if (!Character.isDigit(df[0])) {
      return "dotForm is not in dot notation, check character 0.";
    }

    int[] r = parsedf(df, 1);
    if (r[0] == 0) {
      return "";
    } else {
      return "dotForm is not in dot notation, check character " + r[1] + ".";
    }
  }

  private int[] parsedf(char[] df, int index) {
    // this method assumes the first part is already parsed as
    // <dotNotation>, so this just parses the rest of the string.

    int i = index;
    // parse dots
    while (i < df.length && df[i] == '.') {
      i += 1;
    }
    if (i >= df.length) {
      return new int[]{-1, df.length};
    }
    // parse operator
    if (!isOperator(df[i])) {
      return new int[]{1, i};
    }
    i += 1;
    // parse dots again
    while (i < df.length && df[i] == '.') {
      i += 1;
    }
    if (i >= df.length) {
      return new int[]{-1, df.length};
    }
    // parse number
    if (!Character.isDigit(df[i])) {
      return new int[]{1, i};
    }

    if (i == df.length - 1)
    // we are done
    {
      return new int[]{0, 0};
    } else {
      return parsedf(df, i + 1);
    }
  }

  private boolean isOperator(char c) {
    return c == '+' ||
        c == '-' ||
        c == '*' ||
        c == '/';
  }

  private static boolean KawigiEdit_RunTest(int testNum, String p0, boolean hasAnswer, String p1) {
    System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"");
    System.out.println("]");
    ProblemWriting obj;
    String answer;
    obj = new ProblemWriting();
    long startTime = System.currentTimeMillis();
    answer = obj.myCheckData(p0);
    long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.println("\t" + "\"" + p1 + "\"");
    }
    System.out.println("Your answer:");
    System.out.println("\t" + "\"" + answer + "\"");
    if (hasAnswer) {
      res = answer.equals(p1);
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
    String p1;

    // ----- test 0 -----
    p0 = "9.....*8..+.3./.";
    p1 = "dotForm is not in dot notation, check character 16.";
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = "9..+.5...*....3";
    p1 = "";
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = "5.3+4";
    p1 = "dotForm is not in dot notation, check character 2.";
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = "9*9*9*9*9*9*9*9*9*9*9*9*9*9";
    p1 = "dotForm must contain between 1 and 25 characters, inclusive.";
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = "3.........../...........4";
    p1 = "";
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
//Powered by KawigiEdit 2.1.4 (beta) modif	ied by pivanof!
