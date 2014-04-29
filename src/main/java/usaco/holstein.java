package usaco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: holstein LANG: JAVA
 */
public class holstein {
  int nV, nG;
  int[] minV;
  int[][] feeds;

  int min;
  boolean[] minSelected;

  public holstein(int nV, int nG, int[] minV, int[][] feeds) {
    this.nV = nV;
    this.nG = nG;
    this.minV = minV;
    this.feeds = feeds;

    this.min = nG + 1;
    this.minSelected = new boolean[nG];
  }

  private void solve(int i, boolean[] selected) {
    if (i == nG) {
      final int nS = satisfies(selected);
      if (nS < min) {
        min = nS;
        assign(selected);
      }
    } else {
      selected[i] = true;
      solve(i + 1, selected);
      selected[i] = false;
      solve(i + 1, selected);
    }
  }

  private void assign(boolean[] selected) {
    for (int i = 0; i < nG; i++) {
      minSelected[i] = selected[i];
    }
  }

  private int satisfies(boolean[] selected) {
    int cnt = 0;
    final int[] sumV = new int[nV];

    for (int i = 0; i < nG; i++) {
      if (selected[i]) {
        cnt += 1;
        for (int j = 0; j < nV; j++) {
          sumV[j] += feeds[i][j];
        }
      }
    }

    for (int i = 0; i < nV; i++) {
      if (sumV[i] < minV[i]) {
        return nG + 1;
      }
    }
    return cnt;
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/holstein.in");
    final int nV = s.nextInt();
    final int[] minV = readVs(s, nV);

    final int nG = s.nextInt();
    final int[][] feeds = new int[nG][];
    for (int i = 0; i < nG; i++) {
      feeds[i] = readVs(s, nV);
    }
    s.close();

    final holstein h = new holstein(nV, nG, minV, feeds);
    h.solve(0, new boolean[nG]);

    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("build/holstein.out")));
    pw.print(h.min + " ");
    int printed = 0;
    for (int i = 0; i < nG; i++) {
      if (h.minSelected[i]) {
        pw.print(i + 1);
        printed += 1;
        if (printed == h.min) {
          pw.println();
        } else {
          pw.print(" ");
        }
      }
    }
    pw.close();
  }

  private static int[] readVs(FastScanner s, int nV)
      throws IOException {
    final int[] vs = new int[nV];
    for (int i = 0; i < nV; i++) {
      vs[i] = s.nextInt();
    }
    return vs;
  }
}
