package usaco;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
ID: rdsr.me1 
PROG: ariprog 
LANG: JAVA
 */
public class ariprog {
    public static void main(String[] args) throws IOException {
        final Scanner s = new Scanner(new File("resources/ariprog.in"));
        final int n = s.nextInt();
        final int m = s.nextInt();
        s.close();

        final PrintWriter pr = new PrintWriter("resources/ariprog.out");
        final int l = 2 * sqr(m);
        final boolean[] S = new boolean[l + 1];

        for (int p = 0; p <= m; p++) {
            for (int q = 0; q <= m; q++) {
                S[sqr(p) + sqr(q)] = true;
            }
        }

        boolean foundSeq = false;
        final List<int[]> seqs = new ArrayList<int[]>(10000);
        for (int a = 0; a < S.length; a++) {
            if (S[a]) {
                for (int d = 1; a + (n - 1) * d <= l; d++) {
                    if (allTermsInS(a, d, n, S)) {
                        foundSeq = true;
                        seqs.add(new int[] {a, d});
                    }
                }
            }
        }

        if (!foundSeq) {
            pr.println("NONE");
        } else {
            Collections.sort(seqs, new Comparator<int[]>() {
                @Override
                public int compare(int[] x, int[] y) {
                    final int diff = x[1] - y[1];
                    if (diff != 0) {
                        return diff;
                    }
                    return x[0] - y[0];
                }

            });
        }
        
        for (int[] x : seqs) {
            pr.println(x[0] + " " + x[1]);
        }
        
        pr.close();
    }

    private static boolean allTermsInS(int a, int d, int n, boolean[] s) {
        for (int j = 2; j <= n; j++) {
            if (!s[a + (j - 1) * d]) {
                return false;
            }
        }
        return true;
    }

    private static int sqr(int x) {
        return x * x;
    }
}
