package usaco;

import java.io.IOException;
import util.FastScanner;


public class numbergame {
  int n;
  int[] seq;
  int[][] score;
  int[][] sum;

  public numbergame(int[] seq, int n) {
    this.n = n;
    this.seq = seq;
    this.score = new int[n][n];
    this.sum = new int[n][n];

    sum[0][0] = seq[0];
    for (int i = 1; i < n; i++) {
      sum[i][i] = seq[i];
      sum[0][i] = seq[i] + sum[0][i - 1];
    }

    for (int i = 1; i < n; i++) {
      for (int j = i; j < n; j++) {
        sum[i][j] = sum[0][j] - sum[0][i - 1];
      }
    }
  }

  private int solve(int i, int j) {
    if (score[i][j] != 0) {
      return score[i][j];
    }
    if (i == j) {
      return score[i][j] = seq[i];
    }
    final int p2ls = solve(i + 1, j);
    final int p2rs = solve(i, j - 1);
    return score[i][j] = sum[i][j] - Math.min(p2ls, p2rs);
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/numbergame.in");
    final int n = s.nextInt();
    final int[] seq = new int[n];
    for (int i = 0; i < n; i++) {
      seq[i] = s.nextInt();
    }
    s.close();

    final numbergame ng = new numbergame(seq, n);
    System.out.println(ng.solve(0, n - 1));
  }
}
