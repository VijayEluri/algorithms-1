package usaco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.TreeSet;

import util.FastScanner;

/*
 * ID: rdsr.me1 PROG: lamps LANG: JAVA
 */
public class lamps {
    int[] end;
    int n;

    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("lamps.in");
        final int N = s.nextInt();
        final int C = s.nextInt();
        final int[] end = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            end[i] = 2; // NA
        }
        int l = 0;
        while ((l = s.nextInt()) != -1) {
            end[l] = 1; // ON
        }
        while ((l = s.nextInt()) != -1) {
            end[l] = 0; // OFF
        }
        s.close();

        final lamps ls = new lamps();
        final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        for (final String state : ls.list(N, C, end)) {
            w.println(state);
        }
        w.close();
    }

    private SortedSet<String> list(int n, int c, int[] end) {
        this.n = n;
        this.end = end;

        final SortedSet<String> printed = new TreeSet<String>();

        for (int i = 0; i < 16; i++) {
            if (Integer.bitCount(i) <= c) {
                final int[] state = new int[n + 1];
                toggle(state, 1, 1); // All on;
                if ((i & 1) != 0) {
                    toggle(state, 1, 1);
                }
                if ((i & 2) != 0) {
                    toggle(state, 1, 2);
                }
                if ((i & 8) != 0) {
                    toggle(state, 1, 3);
                }
                if ((i & 4) != 0) {
                    toggle(state, 2, 2);
                }
                if (matches(state)) {
                    printed.add(asString(state));
                }
            }
        }

        if (printed.size() == 0) {
            printed.add("IMPOSSIBLE");
        }
        return printed;
    }

    private static String asString(int[] state) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < state.length; i++) {
            sb.append(state[i]);
        }
        return sb.toString();
    }

    private static void toggle(int[] state, int i, int incr) {
        for (; i < state.length; i += incr) {
            state[i] = (state[i] + 1) % 2;
        }
    }

    private boolean matches(int[] state) {
        for (int i = 0; i < n + 1; i++) {
            if (end[i] != 2 && end[i] != state[i]) {
                return false;
            }
        }
        return true;
    }
}
