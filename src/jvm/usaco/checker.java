package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
ID: rdsr.me1
PROG: checker 
LANG: JAVA
*/
public class checker {
    int[][] first3;
    int filled;
    int n;
    int nSolns;

    int[][] SE_NW;
    int[][] SW_NE;

    boolean[] colOcc; // row occupied
    boolean[] SE_NW_OCC; // se-nw diagonal occupied
    boolean[] SW_NE_OCC; // sw-ne diagonal occupied

    boolean[][] soln;

    int allSolutions(int n) {
        this.first3 = new int[3][6];
        this.filled = 0;
        this.n = n;

        SE_NW = fill();
        //print(SE_NW);

        //System.out.println("-===-");

        SW_NE = rotate90ClockWise(SE_NW);
        //print(SW_NE);

        colOcc = new boolean[n];
        SE_NW_OCC = new boolean[2 * n - 1];
        SW_NE_OCC = new boolean[2 * n - 1];
        soln = new boolean[n][n];

        solve(0);
        return nSolns;
    }

    private void print(int[][] m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.format("  %3d  ", m[i][j]);
            }
            System.out.println();
        }
    }

    private int[][] rotate90ClockWise(int[][] m1) {
        final int[][] m2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m2[j][n - i - 1] = m1[i][j];
            }
        }
        return m2;
    }

    private int[][] fill() {
        final int[][] m = new int[n][n];
        int id = 0;
        for (int col = 0; col < n; col++) {
            for (int i = 0; i + col < n; i++) {
                m[i][i + col] = id;
            }
            id += 1;
        }

        for (int row = 1; row < n; row++) {
            for (int j = 0; row + j < n; j++) {
                m[row + j][j] = id;
            }
            id += 1;
        }
        return m;
    }

    private void solve(int row) {
        if (row == n) {
            if (filled < 3) {
                first3[filled] = soln();
                filled += 1;
            }
            nSolns += 1;
        } else {
            for (int col = 0; col < n; col++) {
                if (canBePlaced(row, col)) {
                    place(row, col);
                    solve(row + 1);
                    unplace(row, col);
                }
            }
        }
    }

    private boolean canBePlaced(int row, int col) {
        return !colOcc[col] &&
                !SE_NW_OCC[SE_NW[row][col]] &&
                !SW_NE_OCC[SW_NE[row][col]];
    }

    private void place(int row, int col) {
        soln[row][col] = true;
        colOcc[col] = true;
        SE_NW_OCC[SE_NW[row][col]] = true;
        SW_NE_OCC[SW_NE[row][col]] = true;
    }

    private void unplace(int row, int col) {
        soln[row][col] = false;
        colOcc[col] = false;
        SE_NW_OCC[SE_NW[row][col]] = false;
        SW_NE_OCC[SW_NE[row][col]] = false;
    }

    private int[] soln() {
        int k = 0;
        final int[] cols = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (soln[i][j]) {
                    cols[k] = j + 1;
                    k += 1;
                    break;
                }
            }
        }
        return cols;
    }

    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();        
     
        final FastScanner s = new FastScanner("checker.in");
        final int N = s.nextInt();
        s.close();

        final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
        final checker c = new checker();
        final int solns = c.allSolutions(N);
        for (final int[] pos : c.first3) {
            for (int i = 0; i < N; i++) {
                pw.print(pos[i]);
                if (i < N - 1) {
                    pw.print(" ");
                }
            }
            pw.println();
        }
        pw.println(solns);
        pw.close();
        
        final long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
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
