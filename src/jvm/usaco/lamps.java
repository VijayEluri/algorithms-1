package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lamps {
    int[] state;
    int[] on;
    int[] off;
    PrintWriter w;
    boolean printed;
    private int n;

    void allPossibleStates(PrintWriter w, int c, int n, int[] on, int[] off) {
        this.state = new int[on.length];
        this.on = on;
        this.off = off;
        this.n = n;

        allOn();

        if (c % 2 != 0) {
            alloff();
            if (condMet()) {
                print();
            }
            _3k_plus_1Off();
            if (condMet()) {
                print();
            }
            oddOff();
            if (condMet()) {
                print();
            }
            evenOff();
            if (condMet()) {
                print();
            }
        }

        if (c % 2 == 0) {
            _3k_plus_1On();
            if (condMet()) {
                print();
            }
            oddOn();
            if (condMet()) {
                print();
            }
            evenOn();
            if (condMet()) {
                print();
            }
        }

        if (c == 0 || c % 2 == 0) {
            if (condMet()) {
                print();
            }
        }

        if (!printed) {
            w.println("IMPOSSIBLE");
        }
    }

    private void evenOn() {
        for (int i = 1; i < state.length; i++) {
            state[i] |= 1 << 1;
            state[i] |= 1 << 3;
            state[i] |= 1 << 5;
            state[i] |= 1 << 7;
        }
    }

    private void oddOn() {
        for (int i = 1; i < state.length; i++) {
            state[i] |= 1 << 0;
            state[i] |= 1 << 2;
            state[i] |= 1 << 4;
            state[i] |= 1 << 6;
        }
    }

    private void _3k_plus_1On() {
        for (int i = 0; i < state.length; i++) {
            state[i] |= 1;
            state[i] |= 8;
            state[i] |= 64;
        }
    }

    private void evenOff() {
        oddOn();
    }

    private void _3k_plus_1Off() {
        for (int i = 0; i < state.length; i++) {
            state[i] &= ~1;
            state[i] &= ~8;
            state[i] &= ~64;
        }
    }

    private void oddOff() {
        evenOn();
    }

    private void alloff() {
        for (int i = 0; i < state.length; i++) {
            state[i] = 0;
        }
    }

    private boolean condMet() {
        // mask
        for (int i = n % Integer.SIZE; i < Integer.SIZE; i++) {
            state[state.length - 1] &= ~(1 << i);
        }

        if (Arrays.equals(and(), on) && Arrays.equals(xor(), off)) {
            return true;
        } else {
            return false;
        }
    }

    private int[] xor() {
        final int[] r = new int[state.length];
        for (int i = 0; i < state.length; i++) {
            r[i] = state[i] ^ off[i];
        }
        return r;
    }

    private int[] and() {
        final int[] r = new int[state.length];
        for (int i = 0; i < state.length; i++) {
            r[i] = state[i] & on[i];
        }
        return r;
    }

    private void allOn() {
        for (int i = 0; i < state.length; i++) {
            state[i] = Integer.MAX_VALUE;
        }
    }

    private void print() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < 8 && i * Integer.SIZE + j < n; j++) {
                if ((state[i] & (1 << j)) != 0) {
                    w.print("1");
                } else {
                    w.print("1");
                }
            }
        }
    }

    private static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(Integer.toBinaryString(a[i]));
        }
    }

    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("lamps.in");
        final int N = s.nextInt();
        final int C = s.nextInt();

        final int sz = N / Integer.SIZE + (N % Integer.SIZE != 0 ? 1 : 0);
        final int[] on = new int[sz];
        final int[] off = new int[sz];

        int l = 0;
        while ((l = s.nextInt()) != -1) {
            setbit(on, l - 1);
        }
        while ((l = s.nextInt()) != -1) {
            setbit(off, l - 1);
        }
        s.close();

        final lamps ls = new lamps();
        final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        ls.allPossibleStates(w, N, C, on, off);
        w.close();

    }

    private static void setbit(int[] a, int i) {
        a[a.length - 1 - i / Integer.SIZE] = 1 << (i % Integer.SIZE);
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
