package usaco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: hamming LANG: JAVA
 */
public class hamming {
  int n, b, d;
  boolean done;

  public hamming(int n, int b, int d) {
    this.n = n;
    this.b = b;
    this.d = d;
  }

  void search(int[] codes, int i) {
    if (i == n - 1) {
      done = true;
    }

    for (int c = codes[i] + 1; c < 256 && !done; c++) {
      if (satisfies(c, codes, i)) {
        codes[i + 1] = c;
        search(codes, i + 1);
      }
    }
  }

  private boolean satisfies(int c, int[] codes, int i) {
    for (int j = i; j >= 0; j--) {
      final int r = c ^ codes[j];
      if (ones(r) < d) {
        return false;
      }
    }
    return true;
  }

  private static int ones(int o) {
    return Integer.bitCount(o);
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/hamming.in");
    final int N = s.nextInt();
    final int B = s.nextInt();
    final int D = s.nextInt();
    s.close();

    final hamming h = new hamming(N, B, D);
    final int[] codes = new int[N];
    codes[0] = 0;
    h.search(codes, 0);

    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("build/hamming.out")));
    for (int i = 0; i < N; i += 10) {
      for (int j = 0; j < 10 && i + j < N; j++) {
        pw.print(codes[i + j]);
        if (j != 9 && i + j != N - 1) {
          pw.print(" ");
        }
      }
      pw.println();
    }
    pw.close();
  }
}
