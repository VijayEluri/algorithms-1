package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: money LANG: JAVA
 */
public class money {
  static long nWays(int[] v, int n) {
    final long[] nWays = new long[n + 1];

    nWays[0] = 1;
    for (int j = 0; j < v.length; j++) {
      for (int i = 1; i <= n; i++) {
        nWays[i] += i - v[j] >= 0 ? nWays[i - v[j]] : 0;
      }
    }

    return nWays[n];
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/money.in");
    final int nv = s.nextInt();
    final int n = s.nextInt();
    final int[] v = new int[nv];
    for (int i = 0; i < nv; i++) {
      v[i] = s.nextInt();
    }
    s.close();

    final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("build/money.out"))));
    final long startTime = System.currentTimeMillis();
    w.println(money.nWays(v, n));
    w.close();
    final long endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
  }
}
