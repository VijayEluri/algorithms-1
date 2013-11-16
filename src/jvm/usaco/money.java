package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class money {
    int[] v;

    long nWays(int[] v, int n) {
        this.v = v;
        return solve(0, n, new LinkedList<Integer>());
    }

    private long solve(int i, int n, List l) {
        if (i == v.length) {
            if (n == 0) {
                System.out.println(l);
                return 1;
            } else {
                return 0;
            }
        } else {
            long nWays = 0l;
            for (int j = 0; n - v[i] * j >= 0; j++) {
                for (int k = j; k > 0; k--) {
                    l.add(v[i]);
                }
                nWays += solve(i + 1, n - v[i] * j, l);

                for (int k = j; k > 0; k--) {
                    l.remove(l.size() - 1);
                }
            }
            return nWays;
        }
    }


    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("money.in");
        final int nv = s.nextInt();
        final int n = s.nextInt();
        final int[] v = new int[nv];
        for (int i = 0; i < nv; i++) {
            v[i] = s.nextInt();
        }
        s.close();

        final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("money.out"))));
        Arrays.sort(v);
        w.println(new money().nWays(v, n));
        w.close();
    }
}


class FastScanner implements Closeable {
    final BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        tokenizer = new StringTokenizer("");
    }

    public String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
