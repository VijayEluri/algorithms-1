/*
ID: rdsr.me1
TASK: agrinet
LANG: JAVA
*/

package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class agrinet {
  static int computeFiberAmount(int n, int[][] g) {

    final Queue<Node> nodes = new PriorityQueue<Node>(n);

    for (int i = 1; i < n; i++) {
      nodes.add(new Node(i, g[0][i]));
    }

    boolean[] visited = new boolean[n];
    visited[0] = true;

    int minAmount = 0;
    int i = 1;
    while (i < n) {
      Node node = nodes.poll();
      minAmount += node.wt;

      visited[node.idx] = true;

      // add neighbors
      for (int j = 0; j < n; j++) {
        if (!visited[j]) {
          final Node nbr = new Node(j, g[node.idx][j]);
          for (Node k : nodes) {
            if (nbr.idx == k.idx) {
              if (nbr.wt < k.wt) {
                nodes.remove(k);
                nodes.add(nbr);
              }
              break;
            }
          }
        }
      }

      i += 1;
    }

    return minAmount;
  }

  public static void main(String[] _) throws Exception {
    final Scanner s = new Scanner(new File("agrinet.in"));
    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

    try {
      final int n = s.nextInt();
      final int[][] g = new int[n][n];

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          g[i][j] = s.nextInt();
        }
      }

      pw.println(computeFiberAmount(n, g));

    } finally {
      if (s != null) {
        s.close();
      }
      if (pw != null) {
        pw.close();
      }
    }
  }

  private static class Node implements  Comparable<Node> {
    int idx;
    int wt;

    public Node(int idx, int wt) {
      this.idx = idx;
      this.wt = wt;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Node node = (Node) o;

      return idx == node.idx;
    }

    @Override
    public int hashCode() {
      return idx;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Node{");
      sb.append("idx=").append(idx);
      sb.append(", wt=").append(wt);
      sb.append('}');
      return sb.toString();
    }

    @Override
    public int compareTo(Node o) {
      return wt - o.wt;
    }
  }
}
