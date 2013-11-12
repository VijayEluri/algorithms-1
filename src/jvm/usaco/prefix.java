package usaco;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
ID: rdsr.me1 
PROG: prefix 
LANG: JAVA
*/
public class prefix {
    Set<String> primitves;
    int[] cache;

    public prefix(Set<String> primitives) {
        this.primitves = primitives;
    }

    private int longestPrefix(String seq) {
        cache = new int[seq.length() + 1];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
        return longestPrefix(seq.toCharArray(), seq.length());
    }

    private int longestPrefix(char[] seq, int len) {
        if (len <= 0) {
            return 0;
        }
        if (cache[len] != -1) {
            return cache[len];
        }
        for (int j = 1; j <= Math.min(10, len); j++) {
            final String p = new String(seq, len - j, j);
            if (primitves.contains(p)) {
                final int lp = longestPrefix(seq, len - p.length());
                if (lp == len - p.length()) {
                    return cache[len] = len;
                }
            }
        }
        return cache[len] = longestPrefix(seq, len - 1);
    }

    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("prefix.in");
        String str = null;
        final Set<String> primitives = new HashSet<String>();
        while (!(str = s.next()).equals(".")) {
            primitives.add(str);
        }

        final StringBuilder seq = new StringBuilder();
        while ((str = s.next()) != null) {
            seq.append(str);
        }
        s.close();

        final PrintWriter w = new PrintWriter("prefix.out");
        w.println(new prefix(primitives).longestPrefix(seq.toString()));
        w.close();

    }
}


class FastScanner implements Closeable {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        tokenizer = new StringTokenizer("");
    }

    public String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            final String line = reader.readLine();
            if (line == null) {
                return null;
            } else {
                tokenizer = new StringTokenizer(line);
            }
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
