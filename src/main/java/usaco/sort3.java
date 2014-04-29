package usaco;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: sort3 LANG: JAVA
 */
public class sort3 {
  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/sort3.in");
    final int n = s.nextInt();
    final int[] nos = new int[n];
    final int[] sorted = new int[n];
    for (int i = 0; i < n; i++) {
      sorted[i] = nos[i] = s.nextInt();
    }
    s.close();

    Arrays.sort(sorted);

    int swpCnt = 0;
    for (int i = 0; i < n; i++) {
      if (nos[i] != sorted[i]) {
        final int j = find(i, nos, sorted);
        swpCnt += 1;
        swap(i, j, nos);
      }
    }

    final PrintWriter pw = new PrintWriter("build/sort3.out");
    pw.println(swpCnt);
    pw.close();
  }

  private static void swap(int i, int j, int[] nos) {
    final int t = nos[i];
    nos[i] = nos[j];
    nos[j] = t;
  }

  private static int find(int i, int[] nos, int[] sorted) {
    for (int j = nos.length - 1; j > i; j--) {
      if (nos[j] == sorted[i] && nos[i] == sorted[j]) {
        return j;
      }
    }

    for (int j = nos.length - 1; j > i; j--) {
      if (nos[j] == sorted[i]) {
        return j;
      }
    }

    return -1;
  }
}
