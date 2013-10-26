package usaco;

/**
 * ID: rdsr.me1 PROG: subset LANG: JAVA
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class subset {
    int n;
    long[][] s;

    public subset(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws IOException {
        final Scanner s = new Scanner(new File("resources/subset.in"));
        final int N = s.nextInt();
        s.close();

        final subset ss = new subset(N);
        final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("resources/subset.out")));
        pw.println(ss.solve());
        pw.close();
    }

    private long solve() {
        int sum = n * (n + 1) / 2;
        if (sum % 2 != 0) {
            return 0;
        }
        sum /= 2;
        s = new long[sum + 1][n + 1];

        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= n; j++) {
                s[i][j] = -1;
            }
        }
        return solve(sum, 1) / 2;
    }

    private long solve(int sum, int i) {
        if (sum == 0) {
            return 1;
        }
        if (sum < i || i > n) {
            return 0;
        }
        if (s[sum][i] != -1) {
            return s[sum][i];
        }
        return s[sum][i] = solve(sum - i, i + 1) + solve(sum, i + 1);
    }
}
