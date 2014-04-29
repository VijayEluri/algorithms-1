package usaco;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: castle LANG: JAVA
 */
public class castle {
  int m, n;
  int[][] component; // component[i][j] ; node(i, j)
  int[] size; // size[component]
  int[][] nodes;

  boolean[][] visited;
  int[][] walls;

  void solve(int n, int m, int[][] nodes, PrintWriter pw) {
    this.n = n;
    this.m = m;
    this.nodes = nodes;
    this.component = new int[n][m];
    this.size = new int[n * m];
    this.visited = new boolean[n][m];
    this.walls = new int[n][m];

    int cc = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!visited[i][j]) {
          search(i, j, cc);
          cc += 1;
        }
      }
    }

    pw.println(cc);
    pw.println(max(size));

    int wi = 0, wj = 0;
    String dir = null;
    int maxRoomSize = 0;
    for (int j = m - 1; j >= 0; j--) {
      for (int i = 0; i < n; i++) {
        final int w = walls[i][j];

        if ((w & 1) != 0 && j - 1 >= 0 && component[i][j] != component[i][j - 1]) {
          final int newRoomSize = size[component[i][j]] + size[component[i][j - 1]];
          if (newRoomSize >= maxRoomSize) {
            maxRoomSize = newRoomSize;
            wi = i;
            wj = j - 1;
            dir = "E";
          }
        }

        if ((w & 4) != 0 && j + 1 < m && component[i][j] != component[i][j + 1]) {
          final int newRoomSize = size[component[i][j]] + size[component[i][j + 1]];
          if (newRoomSize >= maxRoomSize) {
            maxRoomSize = newRoomSize;
            wi = i;
            wj = j;
            dir = "E";
          }
        }

        if ((w & 2) != 0 && i - 1 >= 0 && component[i][j] != component[i - 1][j]) {
          final int newRoomSize = size[component[i][j]] + size[component[i - 1][j]];
          if (newRoomSize >= maxRoomSize) {
            maxRoomSize = newRoomSize;
            wi = i;
            wj = j;
            dir = "N";
          }
        }

        if ((w & 8) != 0 && i + 1 < n && component[i][j] != component[i + 1][j]) {
          final int newRoomSize = size[component[i][j]] + size[component[i + 1][j]];
          if (newRoomSize >= maxRoomSize) {
            maxRoomSize = newRoomSize;
            wi = i + 1;
            wj = j;
            dir = "N";
          }
        }
      }
    }

    pw.println(maxRoomSize);
    pw.println((wi + 1) + " " + (wj + 1) + " " + dir);
  }

  private void search(int i, int j, int cc) {
    visited[i][j] = true;
    component[i][j] = cc;
    size[cc] += 1;

    for (final int[] neighbor : neighbors(i, j)) {
      final int ni = neighbor[0];
      final int nj = neighbor[1];
      if (!visited[ni][nj]) {
        search(ni, nj, cc);
      }
    }
  }

  private Collection<int[]> neighbors(int i, int j) {
    final int v = nodes[i][j];
    final Collection<int[]> r = new ArrayList<int[]>(4);
    if ((v & 1) == 0 && j - 1 >= 0) {
      r.add(new int[]{i, j - 1});
    }
    if ((v & 2) == 0 && i - 1 >= 0) {
      r.add(new int[]{i - 1, j});
    }
    if ((v & 4) == 0 && j + 1 < m) {
      r.add(new int[]{i, j + 1});
    }
    if ((v & 8) == 0 && i + 1 < n) {
      r.add(new int[]{i + 1, j});
    }
    walls[i][j] = v;
    return r;
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/castle.in");
    final int m = s.nextInt();
    final int n = s.nextInt();

    final int[][] nodes = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        nodes[i][j] = s.nextInt();
      }
    }
    s.close();

    final PrintWriter pw = new PrintWriter("build/castle.out");
    new castle().solve(n, m, nodes, pw);
    pw.close();
  }

  private static int max(int[] a) {
    int max = -1;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }
    return max;
  }
}
