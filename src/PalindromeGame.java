import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PalindromeGame {
  public int getMaximum(String[] front, int[] back) {
    Map<String, Queue<Integer>> wordsWieght = new HashMap<String, Queue<Integer>>();
    Set<String> words = new HashSet<String>(Arrays.asList(front));

    for (int i = 0; i < front.length; i++) {
      String reverse = reverse(front[i]);
      if (words.contains(reverse)) {
        if(!wordsWieght.containsKey(reverse))
          wordsWieght.put(reverse, new PriorityQueue<Integer>(1, Collections.reverseOrder()));
        Queue<Integer> q = wordsWieght.get(reverse);
        q.offer(back[i]);
      }
    }

    int weight = 0;
    String largetUnpairedPalindromeWt = null;
    for (int i = 0; i < front.length; i++) 
      if (!wordsWieght.containsKey(front[i]) && front[i].equals(reverse(front[i])) && back[i] > weight) {
        weight = back[i];
        largetUnpairedPalindromeWt = front[i];
      }

    int maximum = weight;
    Set<String> pairs = new HashSet<String>();
    for (String w : wordsWieght.keySet())
      if (!pairs.contains(reverse(w)))
        pairs.add(w);

    for (String w : pairs) {
      Queue<Integer> q1 = wordsWieght.get(w);
      Queue<Integer> q2 = wordsWieght.get(reverse(w));
      int sz = q1.size();
      if (w.equals(reverse(w))) {
        int last = -1;
        int head = q1.peek();
        while(!q1.isEmpty()) {
          last = q1.poll();
          maximum += last;
        }
        if (sz%2 != 0 && head > weight) {
          maximum += head - weight;
          weight = head;
        } else if (head < weight)
          maximum -= last;
      } else {
        while (!q1.isEmpty() && !q2.isEmpty())
          maximum += q1.poll() + q2.poll();
      }
    }

    return maximum;
  }

  private String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, int[] p1, boolean hasAnswer, int p2) {
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
    System.out.print("}");
    System.out.println("]");
    PalindromeGame obj;
    int answer;
    obj = new PalindromeGame();
    long startTime = System.currentTimeMillis();
    answer = obj.getMaximum(p0, p1);
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
    int[] p1;
    int p2;

    // ----- test 0 -----
    p0 = new String[]{"a","a","b"};
    p1 = new int[]{7,5,3};
    p2 = 15;
    all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"rabbit"};
    p1 = new int[]{1000000};
    p2 = 0;
    all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"abc","abc","def","cba","fed"};
    p1 = new int[]{24,7,63,222,190};
    p2 = 499;
    all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
    
    // ----- test 3 -----
    p0 = new String[]{"xyx", "xyx", "xyx", "zzz", "zzz", "zzz"};
    p1 = new int[]{5, 7, 2, 1, 6, 4};
    p2 = 24;
    all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
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
