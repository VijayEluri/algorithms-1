package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * ID: rdsr.me1 PROG: nocows LANG: JAVA
 */
public class nocows {
  int[][] cache;
  private int M;

  public static void main(String[] args)
      throws IOException {
    final Scanner s = new Scanner(new File("src/main/resources/usaco/nocows.in"));
    final int n = s.nextInt();
    final int k = s.nextInt();
    s.close();

    final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("build/nocows.out"))));
    w.println(new nocows().nPedgrees(n, k));
    w.close();
  }

  private int nPedgrees(int n, int k) {
    M = 9901;
    cache = new int[n + 1][k + 1];
    cache[1][1] = 1;
    return solve(n, k);
  }

  private int solve(int n, int k) {
    if (n % 2 == 0) {
      return 0;
    }

    // max. height possible
    final int maxHt = (n + 1) / 2;
    if (maxHt < k) {
      return 0;
    }

    // min. height possible
    final int minHt = minHeight(n);
    if (minHt > k) {
      return 0;
    }

    if (cache[n][k] != 0) {
      return cache[n][k];
    }

    long s = 0;
    long sk_1 = 0;
    for (int nleft = 1; nleft <= n - 2; nleft += 2) {
      final long sl = solve(nleft, k - 1);
      if (sl != 0) {
        final int nRight = (n - 1) - nleft;
        for (int l = minHeight(nRight); l <= Math.min((nRight + 1) / 2, k - 2); l++) {
          final long sr = solve(nRight, l);
          s += sl * sr;
        }
        sk_1 += sl * solve(nRight, k - 1);
      }
    }
    return cache[n][k] = (int) ((2 * s + sk_1) % M);
  }

  private static int minHeight(int x) {
    final double r = Math.log10(x) / Math.log10(2);
    if ((r - (int) r) == 0) {
      return (int) r;
    } else {
      return (int) r + 1;
    }
  }
}
