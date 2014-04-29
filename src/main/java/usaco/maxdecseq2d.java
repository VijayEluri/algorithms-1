package usaco;

import java.io.IOException;
import util.FastScanner;


public class maxdecseq2d {
  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/maxdecseq2d.in");
    final int n = s.nextInt();
    final int m = s.nextInt();

    final int[] A = new int[n];
    final int[] B = new int[n];

    for (int i = 0; i < n; i++) {
      A[i] = s.nextInt();
    }
    for (int i = 0; i < m; i++) {
      B[i] = s.nextInt();
    }
    s.close();

    final int[][] len = new int[n][m];

    for (int i = 0; i < m; i++) {
      if (B[m - 1] == A[i]) {
        len[i][m - 1] = 1;
      }
    }

    for (int j = 0; j < m; j++) {
      if (A[n - 1] == B[j]) {
        len[n - 1][j] = 1;
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      for (int j = m - 2; j >= 0; j--) {
        if (A[i] == B[j]) {
          len[i][j] = 1 + len[i + 1][j + 1];
        } else {
          len[i][j] = Math.max(len[i + 1][j], len[i][j + 1]);
        }
      }
    }
    System.out.println(len[0][0]);
  }
}
