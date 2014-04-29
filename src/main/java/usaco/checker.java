package usaco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: checker LANG: JAVA
 */
public class checker {
  int filled;
  int n;
  int nSolns;

  int colo;
  int rdia;
  int ldia;

  int[] soln;

  PrintWriter pw;

  int allSolutions(int n, PrintWriter pw) {
    this.n = n;
    this.pw = pw;

    soln = new int[n];

    solve(0);
    return nSolns;
  }

  private void solve(int row) {
    if (row == n) {
      if (filled < 3) {
        for (int i = 0; i < n; i++) {
          pw.print(soln[i] + 1);
          if (i != n - 1) {
            pw.print(" ");
          }
        }
        pw.println();
        filled += 1;
      }
      nSolns += 1;
    } else {
      for (int col = 0; col < n; col++) {
        final int c = 1 << col;
        final int l = 1 << 13 - row + col;
        final int r = 1 << row + col;

        if ((colo & c) == 0 && (ldia & l) == 0 && (rdia & r) == 0) {
          colo |= c;
          ldia |= l;
          rdia |= r;

          soln[row] = col;
          solve(row + 1);

          colo ^= c;
          ldia ^= l;
          rdia ^= r;
        }
      }
    }
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/checker.in");
    final int N = s.nextInt();
    s.close();

    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("build/checker.out")));
    final checker c = new checker();
    final int solns = c.allSolutions(N, pw);
    pw.println(solns);
    pw.close();
  }
}
