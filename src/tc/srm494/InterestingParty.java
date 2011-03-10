package tc.srm494;

import java.util.HashSet;
import java.util.Set;

public class InterestingParty {
  public int bestInvitation(String[] first, String[] second) {
    Set<String> s = new HashSet<String>();

    for (int i = 0; i < first.length; i++) {
      s.add(first[i]);
      s.add(second[i]);
    }

    int max = 0;
    for (String topic : s) {
      int count = 0;
      for (int i = 0; i < first.length; i++)
        if (first[i].equals(topic) || second[i].equals(topic))
          count++;
      if (count > max)
        max = count;
    }

    return max;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, String[] p1, boolean hasAnswer, int p2) {
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
      System.out.print("\"" + p1[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    InterestingParty obj;
    int answer;
    obj = new InterestingParty();
    long startTime = System.currentTimeMillis();
    answer = obj.bestInvitation(p0, p1);
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
    String[] p1;
    int p2;

    // ----- test 0 -----
    p0 = new String[]{"fishing","gardening","swimming","fishing"};
    p1 = new String[]{"hunting","fishing","fishing","biting"};
    p2 = 4;
    all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"variety","diversity","loquacity","courtesy"};
    p1 = new String[]{"talking","speaking","discussion","meeting"};
    p2 = 1;
    all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"snakes","programming","cobra","monty"};
    p1 = new String[]{"python","python","anaconda","python"};
    p2 = 3;
    all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"t","o","p","c","o","d","e","r","s","i","n","g","l","e","r","o","u","n","d","m","a","t","c","h","f","o","u","r","n","i"};
    p1 = new String[]{"n","e","f","o","u","r","j","a","n","u","a","r","y","t","w","e","n","t","y","t","w","o","s","a","t","u","r","d","a","y"};
    p2 = 6;
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
