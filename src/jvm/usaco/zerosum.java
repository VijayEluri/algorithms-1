package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ID: rdsr.me1 PROG: zerosum LANG: JAVA
 */
public class zerosum {
    int n;
    SortedSet<String> sums;

    enum PrevOp {
        NONE,
        PLUS,
        MINUS,
        SPACE,
    }

    public zerosum(int n) {
        this.n = n;
        sums = new TreeSet<String>();
    }

    public static void main(String[] args) throws IOException {
        final Scanner s = new Scanner(new File("zerosum.in"));
        final int n = s.nextInt();
        s.close();

        final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("zerosum.out"))));
        final zerosum zs = new zerosum(n);
        zs.solve(1, 0, PrevOp.NONE, new StringBuilder());

        for (final String sum : zs.sums) {
            w.println(sum);
        }

        w.close();
    }

    private void solve(int i, int sum, PrevOp prevOp, StringBuilder sb) {
        if (i == n + 1) {
            if (sum == 0) {
                if (!sums.add(sb.toString())) {
                    System.out.println("DUPLICATE: " + sb);
                }
            }
        } else {
            if (i == 1) {
                solve(i + 1, i, PrevOp.PLUS, new StringBuilder(sb).append(i));
            } else {
                solve(i + 1, sum + i, PrevOp.PLUS, new StringBuilder(sb).append('+').append(i));
                solve(i + 1, sum - i, PrevOp.MINUS, new StringBuilder(sb).append('-').append(i));
         
                if (prevOp != PrevOp.SPACE) {
                    final int ii = 10 * (i - 1) + i;
                    final int newSum = sum + (prevOp == PrevOp.PLUS ? (1 - i) : (i - 1));

                    if (i == 2) {
                        solve(i + 1, newSum + ii, PrevOp.SPACE, new StringBuilder()
                                .append(i - 1)
                                .append(' ')
                                .append(i));
                    } else {
                        solve(i + 1, newSum + ii, PrevOp.SPACE, new StringBuilder(sb.substring(0, sb.length() - 2))
                                .append('+')
                                .append(i - 1)
                                .append(' ')
                                .append(i));
                        solve(i + 1, newSum - ii, PrevOp.SPACE, new StringBuilder(sb.substring(0, sb.length() - 2))
                                .append('-')
                                .append(i - 1)
                                .append(' ')
                                .append(i));
                    }
                }
            }
        }
    }
}
