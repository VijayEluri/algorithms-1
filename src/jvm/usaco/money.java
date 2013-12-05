package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import util.FastScanner;

/**
 * ID: rdsr.me1 PROG: money LANG: JAVA
 */
public class money {
    static long nWays(int[] v, int n) {
        //return solve(0, n, v, new long[v.length][n + 1]);
        return solve(v, n);
    }

    static long solve(int[] v, int n) {
        final long[][] dp = new long[v.length][n + 1];
        
        dp[0][v[0]] = 1;
        
        for (int i = 1; i < v.length; i++) {    
            for (int j = v[0]; j <= n; j++) {
                for (int k = 0; j - v[i] * k >= 0; k++) {
                    dp[i][j] += dp[i - 1][j - v[i] * k];
                }
            }
        }
        
        return dp[v.length - 1][n];
    }


    static long solve(int i, int n, int[] v, long[][] cache) { 
        if (i == v.length) {
            if (n == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (cache[i][n] != 0l) {
            return cache[i][n];
        }

        long nWays = 0l;
        for (int j = 0; n - v[i] * j >= 0; j++) {
            nWays += solve(i + 1, n - v[i] * j, v, cache);
        }
        return cache[i][n] = nWays;
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

        final long startTime = System.currentTimeMillis();

        w.println(new money().nWays(v, n));
        w.close();
        final long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
