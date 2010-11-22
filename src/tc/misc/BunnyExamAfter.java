package tc.misc;

// Topcoder SRM - 487 Div 2, Level 1
// really bad algorithm, what was I thinking!

public class BunnyExamAfter {
    int maxPoints[][];
    int BLACK = 2;
    int GRAY  = 1;
    int WHITE = 0;

    public int getMaximum(String black, String gray, String white) {
        maxPoints = new int[black.length()][2];
        int i;
        for (i = 0; i < black.length(); i++) {
            int sum = 1;
            if (gray.charAt(i) == white.charAt(i))
                sum = 2;

            int acc = getMaxPoints(i-1);

            if (black.charAt(i) == white.charAt(i))
                maxPoints[i][WHITE] = acc;
            else
                // if white is correct
                maxPoints[i][WHITE] = sum + acc;

            if (black.charAt(i) == gray.charAt(i))
                maxPoints[i][GRAY] = acc;
            else
                // if gray is correct
                maxPoints[i][GRAY] = sum + acc;
        }

        return max(maxPoints[i-1][WHITE], maxPoints[i-1][GRAY]);
    }

    private int getMaxPoints(int i) {
        if (i < 0)
            return 0;
        else
            return max(maxPoints[i][GRAY], maxPoints[i][WHITE]);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String p0, String p1, String p2, boolean hasAnswer, int p3) {
        System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + "\"" + p1 + "\"" + "," + "\"" + p2 + "\"");
        System.out.println("]");
        BunnyExamAfter obj;
        int answer;
        obj = new BunnyExamAfter();
        long startTime = System.currentTimeMillis();
        answer = obj.getMaximum(p0, p1, p2);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + p3);
        }
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        if (hasAnswer) {
            res = answer == p3;
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

        String p0;
        String p1;
        String p2;
        int p3;

        // ----- test 0 -----
        p0 = "ABC";
        p1 = "ABC";
        p2 = "ABC";
        p3 = 0;
        all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = "A";
        p1 = "B";
        p2 = "B";
        p3 = 2;
        all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = "ABC";
        p1 = "PQR";
        p2 = "XYZ";
        p3 = 3;
        all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = "AAAAA";
        p1 = "AABBB";
        p2 = "BBBAA";
        p3 = 6;
        all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = "TOPCODER";
        p1 = "TOPBUNNY";
        p2 = "THEHONEY";
        p3 = 9;
        all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
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
