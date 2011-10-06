import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pair {
  Integer fenceIndex;
  int level;

  public Pair(Integer fenceIndex, int level) {
    this.fenceIndex = fenceIndex;
    this.level = level;
  }
}

public class SteeplechaseTrack2 {
  private String[] fences;
  private String[] tracks;

  public int maxComplexity(String[] fences, String[] tracks, int N) {
    this.fences = fences;
    this.tracks = tracks;

    int maxComplexity = -1;
    for (final int fenceIndex : startingFenceIndices()) {
      final int n_1Complexity = maxComplexityFrom(fenceIndex, N - 1);
      if (n_1Complexity == -1)
        continue;
      final int complexity = distanceFromStartingLine(fences[fenceIndex]) + jump(fences[fenceIndex]) + n_1Complexity;
      if (complexity > maxComplexity)
        maxComplexity = complexity;
    }
    return maxComplexity;
  }

  private int maxComplexityFrom(Integer StartingFenceIndex, Integer n) {
    final Queue<Pair> queue = new LinkedList<Pair>();
    queue.add(new Pair(StartingFenceIndex, 1));

    int maxComplexity = -1;
    final int[] d = new int[fences.length];
    while (!queue.isEmpty()) {
      final Pair pair = queue.poll();
      if (distanceToEndLine(fences[pair.fenceIndex]) != 0
            && d[pair.fenceIndex] + distanceToEndLine(fences[pair.fenceIndex]) > maxComplexity)
          maxComplexity = d[pair.fenceIndex] + distanceToEndLine(fences[pair.fenceIndex]);

      if (pair.level > n)
        return maxComplexity;

      for (final Integer neighbor : neighbors(pair.fenceIndex)) {
        final int c = w(pair.fenceIndex, neighbor);
        if (d[pair.fenceIndex] + c + jump(fences[neighbor]) > d[neighbor]) {
          d[neighbor] = d[pair.fenceIndex] + c + jump(fences[neighbor]);
          queue.add(new Pair(neighbor, pair.level + 1));
        }
      }
    }

    return maxComplexity;
  }

  private int jump(String fence) {
    return Character.digit(fence.charAt(0), 10);
  }

  private int w(Integer fenceIndex, Integer neighbor) {
    return tracks[fenceIndex].charAt(neighbor) - '0';
  }

  private List<Integer> neighbors(Integer fi) {
    final List<Integer> neighbors = new ArrayList<Integer>();

    for (int i = 0; i < fences.length; i++)
      if (tracks[fi].charAt(i) != '0')
        neighbors.add(i);

    return neighbors;
  }

  private int distanceFromStartingLine(String fence) {
    return Character.digit(fence.charAt(1), 10);
  }

  private int distanceToEndLine(String fence) {
    return Character.digit(fence.charAt(2), 10);
  }

  private List<Integer> startingFenceIndices() {
    final List<Integer> startingFenceIndices = new ArrayList<Integer>();

    for (int i = 0; i < fences.length; i++)
      if (distanceFromStartingLine(fences[i]) != 0)
        startingFenceIndices.add(i);

    return startingFenceIndices;
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, String[] p1, int p2, boolean hasAnswer, int p3) {
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
    System.out.print("}" + "," + p2);
    System.out.println("]");
    SteeplechaseTrack2 obj;
    int answer;
    obj = new SteeplechaseTrack2();
    final long startTime = System.currentTimeMillis();
    answer = obj.maxComplexity(p0, p1, p2);
    final long endTime = System.currentTimeMillis();
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
    String[] p1;
    int p2;
    int p3;

    // ----- test 0 -----
    p0 = new String[] { "310", "300", "301" };
    p1 = new String[] { "010", "001", "000" };
    p2 = 4;
    p3 = 13;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[] { "923" };
    p1 = new String[] { "1" };
    p2 = 100;
    p3 = 1004;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[] { "111", "222", "333" };
    p1 = new String[] { "743", "985", "380" };
    p2 = 1;
    p3 = 9;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[] { "101", "202", "303" };
    p1 = new String[] { "659", "431", "770" };
    p2 = 5;
    p3 = -1;
    all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new String[] { "693", "982", "236" };
    p1 = new String[] { "603", "986", "780" };
    p2 = 10;
    p3 = 172;
    all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
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
