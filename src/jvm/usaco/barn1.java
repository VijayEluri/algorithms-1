package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * ID: rdsr.me1 PROG: barn1 LANG: JAVA
 */

public class barn1 {
    public static void main(
            String[] _)
            throws Exception {

        BufferedReader br = null;
        PrintWriter pw = null;

        try {
            br = new BufferedReader(new FileReader("resources/barn1.in"));
            pw = new PrintWriter("resources/barn1.out");

            final String[] nos = br.readLine().split(" ");
            final int M = Integer.valueOf(nos[0]), S = Integer.valueOf(nos[1]), C = Integer.valueOf(nos[2]);

            String c;
            final boolean occupied[] = new boolean[S + 1];
            while ((c = br.readLine()) != null) {
                occupied[Integer.valueOf(c)] = true;
            }

            int lastOccupied = -1, firstOccupied = -1;
            final int[] separation = new int[C];
            Arrays.fill(separation, 0);

            int cnt = 0, prevOccupied = -1;
            for (int i = 1; i < occupied.length; i++) {
                if (occupied[i] == true) {
                    if (firstOccupied == -1) {
                        firstOccupied = i;
                        prevOccupied = i;
                    }

                    if (i - (prevOccupied + 1) > 0) {
                        separation[cnt++] = i - (prevOccupied + 1);
                    }
                    prevOccupied = i;
                    lastOccupied = prevOccupied;
                }
            }

            Arrays.sort(separation);
            final int fixedS = lastOccupied - firstOccupied + 1;
            pw.println(solve(M, fixedS, separation));

        } finally {
            if (br != null) {
                br.close();
            }
            if (pw != null) {
                pw.close();
            }
        }
    }

    static int solve(int M, int fixedS, int[] separation) {
        int stallsCovered = fixedS;
        int boardsUsed = 1;

        for (int i = separation.length - 1; i > 0; i--, boardsUsed++) {
            final int s = separation[i];
            if (boardsUsed == M) {
                break;
            }
            stallsCovered -= s;
        }
        return stallsCovered;
    }
}
