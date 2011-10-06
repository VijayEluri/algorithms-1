public class Islands {
    int n, m;
    public int beachLength(String[] kingdom) {
        n = kingdom.length;
        m = kingdom[0].length();

        int[][] count = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (kingdom[i].charAt(j) == '#') {
                    if(a(kingdom, i, j)) count[i][j] += 1;
                    if(b(kingdom, i, j)) count[i][j] += 1;
                    if(c(kingdom, i, j)) count[i][j] += 1;
                    if(d(kingdom, i, j)) count[i][j] += 1;
                    if(e(kingdom, i, j)) count[i][j] += 1;
                    if(f(kingdom, i, j)) count[i][j] += 1;
                }
        int length = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                length += count[i][j];
        return length;
    }

    private boolean f(String[] kingdom, int i, int j) {
        if (j == 0) return false;
        if (kingdom[i].charAt(j-1) == '.') return true;
        return false;
    }

    private boolean e(String[] kingdom, int i, int j) {
        if (i == n - 1) return false;
        if (j == 0 && i%2 == 0) return false;
        if (kingdom[i+1].charAt(j-1) == '.') return true;
        return false;
    }

    private boolean d(String[] kingdom, int i, int j) {
        if (i == n - 1) return false;
        if (j == m - 1 && i%2 != 0) return false;
        if (kingdom[i+1].charAt(j) == '.') return true;
        return false;
    }

    private boolean c(String[] kingdom, int i, int j) {
        if (j == m - 1) return false;
        if (kingdom[i].charAt(j+1) == '.') return true;
        return false;
    }

    private boolean b(String[] kingdom, int i, int j) {
        if (i == 0) return false;
        if (j == m - 1 && i%2 != 0) return false;
        if (kingdom[i-1].charAt(j) == '.') return true;
        return false;
    }

    private boolean a(String[] kingdom, int i, int j) {
        if (i == 0) return false;
        if (j == 0 && i%2 == 0) return false;
        if (kingdom[i-1].charAt(j) == '.') return true;
        return false;
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int p1) {
        System.out.print("Test " + testNum + ": [" + "{");
        for (int i = 0; p0.length > i; ++i) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print("\"" + p0[i] + "\"");
        }
        System.out.print("}");
        System.out.println("]");
        Islands obj;
        int answer;
        obj = new Islands();
        long startTime = System.currentTimeMillis();
        answer = obj.beachLength(p0);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + p1);
        }
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        if (hasAnswer) {
            res = answer == p1;
        }
        if (!res) {
            System.out.println("DOESN'T MATCH!!!!");
        } else if ((endTime - startTime) / 1000.0 >= 2) {
            System.out.println("FAIL the timeout");
            res = false;
        } else if (hasAnswer) {
            System.out.println("Match :-)");
        } else {
            System.out.println("OK, but is it right?");
        }
        System.out.println("");
        return res;
    }
    public static void main(String[] args) {
        boolean all_right;
        all_right = true;

        String[] p0;
        int p1;

        // ----- test 0 -----
        p0 = new String[]{".#...#.."};
        p1 = 4;
        all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = new String[]{"..#.##",".##.#.","#.#..."};
        p1 = 19;
        all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = new String[]{"#...#.....","##..#...#."};
        p1 = 15;
        all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = new String[]{"....#.",".#....","..#..#","####.."};
        p1 = 24;
        all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
        // ------------------

        if (all_right) {
            System.out.println("You're a stud (at least on the example cases)!");
        } else {
            System.out.println("Some of the test cases had errors.");
        }
    }
    // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
