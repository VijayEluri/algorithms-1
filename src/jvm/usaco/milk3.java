//package usaco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/*
ID: rdsr.me1 
PROG: clocks 
LANG: JAVA
*/
public class milk3 {
    int[] capacity;

    Collection<Integer> amountInC(int A, int B, int C) {
        capacity = new int[] {A, B, C};
        //int[][][] cache = new int[A+1][B+1][C+1];
        
        final List<Integer> c = new LinkedList<Integer>();
        final int max = Math.max(A, Math.max(B, C));
        final int d = max;
        for (int i = 0; i <= d; i++) {
            c.addAll(search(new int[] {0, 0, C}, d));
        }
        
        final SortedSet<Integer> s = new TreeSet<Integer>(c);
        return s;
    }

    List<Integer> search(int[] s, int depth) {
        final List<Integer> r = new LinkedList<Integer>();
        if (s[0] == 0 && s[2] != 0) {
            r.add(s[2]);
        }
        if (depth == 0) {
            return r;
        } else {
            int[] t = pour(0, 1, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            t = pour(0, 2, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            t = pour(1, 0, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            t = pour(1, 2, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            t = pour(2, 0, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            t = pour(2, 1, s);
            if (!Arrays.equals(s, t)) {
                r.addAll(search(t, depth - 1));
            }
            return r;
        }
    }

    private int[] pour(int i, int j, int[] s) {
        final int[] t = Arrays.copyOf(s, s.length);
        if (t[j] == capacity[j]) {
            // target container is full
            return t;
        }
        if (t[i] == 0) {
            // source container is empty
            return t;
        }
        if (t[i] <= capacity[j] - t[j]) {
            t[j] = t[j] + t[i];
            t[i] = 0;
            return t;
        }
        if (t[i] > capacity[j] - t[j]) {
            t[i] = t[i] - capacity[j] + t[j];
            t[j] = capacity[j];
            return t;
        }
        return t;
    }

    public static void main(String[] _) throws FileNotFoundException {
        final Scanner s = new Scanner(new File("milk3.in"));
        final int a = s.nextInt();
        final int b = s.nextInt();
        final int c = s.nextInt();
        s.close();

        final PrintWriter pr = new PrintWriter("milk3.out");
        for (final int o : new milk3().amountInC(a, b, c)) {
            pr.print(o);
            pr.print(" ");
        }
        pr.println();
        pr.close();
    }
}
