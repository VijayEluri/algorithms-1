package tc.srm184;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TeamBuilder {
  private String[] paths;
  private boolean[][] pathMatrix;
  private int n;

  public int[] specialLocations(String[] paths) {
    this.paths = paths;
    n = paths.length;
    pathMatrix = new boolean[n][n];

    for (int i = 0; i < n; i++)
      BFS(i);

    return new int[] { noOfincoming(), noOfoutgoing() };
  }

  private int noOfincoming() {
    int count = 0;
    for (int i = 0; i < n; i++)
      if (inComing(i) == n)
        count += 1;
    return count;
  }

  private int inComing(int node) {
    int count = 0;
    for (int j = 0; j < n; j++)
      if (pathMatrix[node][j])
        count += 1;
    return count;
  }

  private int noOfoutgoing() {
    int count = 0;
    for (int i = 0; i < n; i++)
      if (outGoing(i) == n)
        count += 1;
    return count;
  }

  private int outGoing(int node) {
    int count = 0;
    for (int i = 0; i < n; i++)
      if (pathMatrix[i][node])
        count += 1;
    return count;
  }

  private void BFS(int startNode) {
    final Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(startNode);
    pathMatrix[startNode][startNode] = true;

    while (!queue.isEmpty()) {
      final Integer node = queue.poll();
      final List<Integer> neighbours = neighbours(node);
      for (final Integer neighbour : neighbours)
        if (!pathMatrix[startNode][neighbour]) {
          queue.offer(neighbour);
          pathMatrix[startNode][neighbour] = true;
        }
    }
  }

  private List<Integer> neighbours(Integer node) {
    final List<Integer> neighbours = new ArrayList<Integer>();
    for (int i = 0; i < n; i++)
      if (paths[node].charAt(i) == '1')
        neighbours.add(i);
    return neighbours;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int[] p1) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    TeamBuilder obj;
    int[] answer;
    obj = new TeamBuilder();
    final long startTime = System.currentTimeMillis();
    answer = obj.specialLocations(p0);
    final long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.print("\t" + "{");
      for (int i = 0; p1.length > i; ++i) {
        if (i > 0) {
          System.out.print(",");
        }
        System.out.print(p1[i]);
      }
      System.out.println("}");
    }
    System.out.println("Your answer:");
    System.out.print("\t" + "{");
    for (int i = 0; answer.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(answer[i]);
    }
    System.out.println("}");
    if (hasAnswer) {
      if (answer.length != p1.length) {
        res = false;
      } else {
        for (int i = 0; answer.length > i; ++i) {
          if (answer[i] != p1[i]) {
            res = false;
          }
        }
      }
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

    // ----- test 0 -----
    p0 = new String[] { "010", "000", "110" };
    p1 = new int[] { 1, 1 };
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[] { "0010", "1000", "1100", "1000" };
    p1 = new int[] { 1, 3 };
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[] { "01000", "00100", "00010", "00001", "10000" };
    p1 = new int[] { 5, 5 };
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[] { "0110000", "1000100", "0000001", "0010000", "0110000", "1000010", "0001000" };
    p1 = new int[] { 1, 3 };
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
// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
