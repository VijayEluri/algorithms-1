package usaco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.FastScanner;

/**
 * ID: rdsr.me1 PROG: frac1 LANG: JAVA
 */
public class frac1 {
    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("resources/frac1.in");
        final int N = s.nextInt();
        s.close();

        final Set<Float> occured = new HashSet<Float>();

        final List<int[]> n = new ArrayList<int[]>(sz(N));

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                final float v = j / (float) i;
                if (occured.add(v)) {
                    n.add(new int[] {j, i});
                }
            }
        }
        n.add(new int[] {0, 1});
        n.add(new int[] {1, 1});
        Collections.sort(n, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                final float r = ((float) a[0] / a[1]) - ((float) b[0] / b[1]);
                if (r < 0) {
                    return -1;
                } else if (r == 0.0) {
                    return 0;
                } else {
                    return 1;
                }

            }
        });

        final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        for (final int[] e : n) {
            pw.println(e[0] + "/" + e[1]);
        }
        pw.close();
    }

    private static int sz(int n) {
        return n * (n + 1) * (2 * n + 1) / 6 + 1;
    }
}
