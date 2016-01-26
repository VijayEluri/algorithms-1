package cc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class Seagrp {
    int i = 0;
  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner(System.in);
    final int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      if (solvable(graph(s))) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }

  private static boolean solvable(boolean[][] graph) {
    final boolean[] visited = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!visited[i]) {
        final int count = dfs(graph, i, visited);
        if (count % 2 != 0) {
          return false;
        }
      }
    }
    return true;
  }

    private static int dfs(boolean[][] graph, int start, boolean[] visited,) {
    visited[start] = true;
    int cnt = 1;
    for (final int n : neighbors(graph, start)) {
      if (!visited[n]) {
        cnt += dfs(graph, n, visited);
      }
    }
    finish[start] = i++;
    return cnt;
  }

  private static Collection<Integer> neighbors(boolean[][] graph, int start) {
    final Collection<Integer> r = new ArrayList<Integer>();
    for (int i = 0; i < graph.length; i++) {
      if (graph[start][i]) {
        r.add(i);
      }
    }
    return r;
  }

  private static boolean[][] graph(FastScanner s)
      throws IOException {
    final int n = s.nextInt();
    final int m = s.nextInt();

    final boolean[][] matrix = new boolean[n][n];
    for (int i = 0; i < m; i++) {
      final int a = s.nextInt() - 1;
      final int b = s.nextInt() - 1;

      matrix[a][b] = true;
      matrix[b][a] = true;
    }
    return matrix;
  }
}
