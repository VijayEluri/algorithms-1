package usaco;

import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;


/*
 * ID: rdsr.me1 PROG: numtri LANG: JAVA
 */
public class numtri {
  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/numtri.in");
    final int r = s.nextInt();
    final int[][] tri = new int[r][r];
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < i + 1; j++) {
        tri[i][j] = s.nextInt();
      }
    }
    s.close();

    final PrintWriter pw = new PrintWriter("build/numtri.out");
    pw.println(highestSum(tri));
    pw.close();
  }

  private static int highestSum(int[][] t) {
    for (int i = 1; i < t.length; i++) {
      for (int j = 0; j < t[i].length; j++) {
        t[i][j] += Math.max(get(t, i - 1, j - 1), get(t, i - 1, j));
      }
    }
    int max = 0;
    for (int j = 0; j < t[t.length - 1].length; j++) {
      if (t[t.length - 1][j] > max) {
        max = t[t.length - 1][j];
      }
    }
    return max;
  }

  private static int get(int[][] s, int i, int j) {
    if (j < 0 || j >= s[i].length) {
      return 0;
    }
    return s[i][j];
  }
}
