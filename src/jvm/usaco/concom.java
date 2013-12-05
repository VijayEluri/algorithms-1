package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;

/**
 * ID: rdsr.me1 PROG: concom LANG: JAVA
 */
public class concom {
    public static void main(String[] args) throws IOException {
        final FastScanner fs = new FastScanner("concom.in");
        final int n = 100;
        final int m = fs.nextInt();
        final int[][] c = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            c[fs.nextInt()][fs.nextInt()] = fs.nextInt();
        }
        fs.close();

        while (true) {
            final int[][] d = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j == i) {
                        continue;
                    }

                    if (c[i][j] > 50) {
                        for (int k = 1; k <= n; k++) {
                            if (k == i || k == j) {
                                continue;
                            }
                            d[i][k] += c[j][k];
                        }
                    }
                }
            }

            boolean retry = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (c[i][j] + d[i][j] > 50 && c[i][j] <= 50) {
                        c[i][j] += 50;
                        retry = true;
                    }
                }
            }

            if (!retry) {
                break;
            }
        }

        final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("concom.out"))));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (c[i][j] > 50 && i != j) {
                    w.println(i + " " + j);
                }
            }
        }
        w.close();
    }
}
