package tc.misc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;


public class DrivingPlans {
  int[] sp(int s, int n, Map<Integer, Collection<Vertex>> adjl) {
    int[] d = new int[n];
    PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(n, new Comparator<Vertex>() {
      @Override
      public int compare(Vertex o1, Vertex o2) {
        return o1.d - o2.d;
      }
    });

    d[s] = 0;
    q.offer(new Vertex(s, 0));
    for (int i = 1; i < n; i++) {
      if (i != s) {
        d[i] = Integer.MAX_VALUE;
        q.offer(new Vertex(i, d[i]));
      }
    }

    while (!q.isEmpty()) {
      Vertex u = q.poll();
      for (Vertex v : adjl.get(u.i)) {
        int dist = v.d + d[u.i];
        if (dist < d[v.i]) {
          q.remove(new Vertex(v.i, d[v.i]));
          d[v.i] = dist;
          q.add(new Vertex(v.i, dist));
        }
      }
    }
    return d;
  }

  public int count(int N, int[] A, int[] B, int[] C) {
    Map<Integer, Collection<Vertex>> adjList = mkAdjList(A, B, C);
    int[] d = sp(1, N + 1, adjList);
    final int[] rd = sp(N, N + 1, adjList);

    for (int i = 0; i < A.length; i++) {
      if (C[i] == 0) {
        if (d[A[i]] + rd[B[i]] == rd[1] || rd[A[i]] + d[B[i]] == rd[1]) {
          return -1;
        }
      }
    }

    Integer[] vertices = new Integer[N + 1];
    for (int i = 0; i <= N; i++) {
      vertices[i] = i;
    }

    Arrays.sort(vertices, new Comparator<Integer>() {
      @Override
      public int compare(Integer u, Integer v) {
        return rd[u] - rd[v];
      }
    });
    int[] dp = new int[N + 1];
    dp[N] = 1;
    for (int i = 1; i <= N; i++) {
      int u = vertices[i];
      if (adjList.containsKey(u)) {
        Collection<Vertex> nghbrs = adjList.get(u);
        for (Vertex v : nghbrs) {
          if (v.d + rd[u] + d[v.i] == rd[1]) {
            dp[v.i] += dp[u] % 1000000009;
          }
        }
      }
    }

    return dp[1] % 1000000009;
  }

  private static Map<Integer, Collection<Vertex>> mkAdjList(int[] a, int[] b, int[] c) {
    Map<Integer, Collection<Vertex>> adjList = new HashMap<Integer, Collection<Vertex>>();
    for (int i = 0; i < a.length; i++) {
      addTo(adjList, a[i], b[i], c[i]);
      addTo(adjList, b[i], a[i], c[i]);
    }
    return adjList;
  }

  private static void addTo(Map<Integer, Collection<Vertex>> adjList, int u, int v, int w) {
    if (adjList.containsKey(u)) {
      adjList.get(u).add(new Vertex(v, w));
    } else {
      Collection<Vertex> list = new LinkedList<Vertex>();
      list.add(new Vertex(v, w));
      adjList.put(u, list);
    }
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, int p0, int[] p1, int[] p2, int[] p3, boolean hasAnswer,
      int p4) {
    System.out.print("Test " + testNum + ": [" + p0 + "," + "{");
    for (int i = 0; p1.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p1[i]);
    }
    System.out.print("}" + "," + "{");
    for (int i = 0; p2.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p2[i]);
    }
    System.out.print("}" + "," + "{");
    for (int i = 0; p3.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p3[i]);
    }
    System.out.print("}");
    System.out.println("]");
    DrivingPlans obj;
    int answer;
    obj = new DrivingPlans();
    final long startTime = System.currentTimeMillis();
    answer = obj.count(p0, p1, p2, p3);
    final long endTime = System.currentTimeMillis();
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

    int p0;
    int[] p1;
    int[] p2;
    int[] p3;
    int p4;

    // ----- test 0 -----
    p0 = 5;
    p1 = new int[]{3, 5, 2, 3};
    p2 = new int[]{1, 2, 4, 4};
    p3 = new int[]{45346, 90709, 33430, 82039};
    p4 = 1;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, p3, true, p4) && all_right;

    // ----- test 0 -----
    p0 = 2;
    p1 = new int[]{1};
    p2 = new int[]{2};
    p3 = new int[]{1};
    p4 = 1;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, p3, true, p4) && all_right;

    // ----- test 0 -----
    p0 = 4;
    p1 = new int[]{1, 1, 2, 3};
    p2 = new int[]{2, 3, 4, 4};
    p3 = new int[]{1, 1, 1, 1};
    p4 = 2;
    all_right = KawigiEdit_RunTest(0, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = 4;
    p1 = new int[]{1, 1, 2, 3};
    p2 = new int[]{2, 3, 4, 4};
    p3 = new int[]{1, 1, 1, 0};
    p4 = -1;
    all_right = KawigiEdit_RunTest(1, p0, p1, p2, p3, true, p4) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = 7;
    p1 = new int[]{1, 1, 2, 3, 4, 4, 5, 6};
    p2 = new int[]{2, 3, 4, 4, 5, 6, 7, 7};
    p3 = new int[]{1, 1, 2, 2, 3, 3, 4, 4};
    p4 = 4;
    all_right = KawigiEdit_RunTest(2, p0, p1, p2, p3, true, p4) && all_right;
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

class Vertex {
  int i;
  int d;

  public Vertex(int _i, int _d) {
    i = _i;
    d = _d;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + d;
    result = prime * result + i;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Vertex other = (Vertex) obj;
    if (d != other.d) {
      return false;
    }
    if (i != other.i) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "tc.misc.Vertex [i=" + i + ", d=" + d + "]";
  }
}
