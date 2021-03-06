package topcoder.srm233;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


// too slow
public class SmartWordToy {
  Queue<String> q = new LinkedList<String>();
  Set<String> cache = new HashSet<String>();
  Map<String, Integer> distance = new HashMap<String, Integer>();

  public int minPresses(String start, String finish, String[] forbid) {
    cache.addAll(forbiddenWords(forbid));
    return bfs(start, finish);
  }

  private int bfs(String start, String finish) {
    distance.put(start, 0);
    cache.add(start);
    q.offer(start);

    while (!q.isEmpty()) {
      String node = q.poll();
      List<String> nghbrs = neighbors(node);

      for (String nghbr : nghbrs) {
        if (cache.contains(nghbr)) {
          continue;
        }
        if (nghbr.equals(finish)) {
          return distance.get(node) + 1;
        }
        distance.put(nghbr, distance.get(node) + 1);
        cache.add(nghbr);
        q.offer(nghbr);
      }
    }
    return -1;
  }

  private Set<String> forbiddenWords(String[] forbid) {
    Set<String> s = new HashSet<String>();
    for (String f : forbid) {
      String[] fl = f.split(" ");
      for (int i = 0; i < fl[0].length(); i++) {
        for (int j = 0; j < fl[1].length(); j++) {
          for (int k = 0; k < fl[2].length(); k++) {
            for (int l = 0; l < fl[3].length(); l++) {
              char[] w = new char[4];
              w[0] = fl[0].charAt(i);
              w[1] = fl[1].charAt(j);
              w[2] = fl[2].charAt(k);
              w[3] = fl[3].charAt(l);
              String word = new String(w);
              s.add(word);
            }
          }
        }
      }
    }
    return s;
  }

  private List<String> neighbors(String node) {
    List<String> nghbrs = new LinkedList<String>();
    for (int i = 0; i < 4; i++) {
      String left = node.substring(0, i);
      String right = node.substring(i + 1);
      nghbrs.add(left + next(node.charAt(i)) + right);
      nghbrs.add(left + prev(node.charAt(i)) + right);
    }
    return nghbrs;
  }

  private char prev(char c) {
    if (c == 'a') {
      return 'z';
    }
    return (char) (c - 1);
  }

  private char next(char c) {
    if (c == 'z') {
      return 'a';
    }
    return (char) (c + 1);
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String p0, String p1, String[] p2, boolean hasAnswer, int p3) {
    System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + "\"" + p1 + "\"" + "," + "{");
    for (int i = 0; p2.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p2[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    SmartWordToy obj;
    int answer;
    obj = new SmartWordToy();
    long startTime = System.currentTimeMillis();
    answer = obj.minPresses(p0, p1, p2);
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

    String p0;
    String p1;
    String[] p2;
    int p3;

    // ----- test 0 -----
    p0 = "aaaa";
    p1 = "zzzz";
    p2 = new String[]{"a a a z", "a a z a", "a z a a", "z a a a", "a z z z", "z a z z", "z z a z", "z z z a"};
    p3 = 8;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = "aaaa";
    p1 = "bbbb";
    p2 = new String[]{};
    p3 = 4;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = "aaaa";
    p1 = "mmnn";
    p2 = new String[]{};
    p3 = 50;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = "aaaa";
    p1 = "zzzz";
    p2 = new String[]{"bz a a a", "a bz a a", "a a bz a", "a a a bz"};
    p3 = -1;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = "aaaa";
    p1 = "zzzz";
    p2 =
        new String[]{"cdefghijklmnopqrstuvwxyz a a a", "a cdefghijklmnopqrstuvwxyz a a", "a a cdefghijklmnopqrstuvwxyz a", "a a a cdefghijklmnopqrstuvwxyz"};
    p3 = 6;
    all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 5 -----
    p0 = "aaaa";
    p1 = "bbbb";
    p2 = new String[]{"b b b b"};
    p3 = -1;
    all_right = KawigiEdit_RunTest(5, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 6 -----
    p0 = "zzzz";
    p1 = "aaaa";
    p2 =
        new String[]{"abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
            "abcdefghijkl abcdefghijkl abcdefghijkl abcdefg"
                + "hijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
            "abcdefghijkl abcdefghijkl abcdefghijkl a"
                + "bcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk", "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk"};
    p3 = -1;
    all_right = KawigiEdit_RunTest(6, p0, p1, p2, true, p3) && all_right;
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
