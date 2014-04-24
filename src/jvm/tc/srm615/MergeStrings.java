package tc.srm615;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MergeStrings {
    private static final String NOT_POSSIBLE = "Z";
    Map<List<String>, String> cache;

    public String getmin(String S, String A, String B) {
        cache = new HashMap<List<String>, String>();
        final String r = find(S, A, B);
        if (r == NOT_POSSIBLE) {
            return "";
        } else {
            return r;
        }
    }

    private String find(String s, String a, String b) {
        final List<String> key = list(s, a, b);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        String r = NOT_POSSIBLE;
        if (s.length() != a.length() + b.length()) {
            r = NOT_POSSIBLE;
        } else {
            if (s.isEmpty()) {
                r = "";
            } else if (a.isEmpty() || b.isEmpty()) {
                final String c = a.isEmpty() ? b : a;
                if (matches(c, s)) {
                    r = c;
                } else {
                    r = NOT_POSSIBLE;
                }
            } else {
                final char ca = a.charAt(0);
                final char cb = b.charAt(0);
                final char cs = s.charAt(0);

                if (cs == '?') {
                    final String r1 = find(s.substring(1), a.substring(1), b);
                    final String r2 = find(s.substring(1), a, b.substring(1));
                    r = min(ca, r1, cb, r2);
                } else {
                    if (cs == ca) {
                        r = find(s.substring(1), a.substring(1), b);
                    }
                    String ar = NOT_POSSIBLE;
                    if (cs == cb) {
                        ar = find(s.substring(1), a, b.substring(1));
                    }
                    r = min(cs, r, cs, ar);
                }
            }
        }
        cache.put(key, r);
        return r;
    }

    private static String min(char c1, String r1, char c2, String r2) {
        if (r1 == NOT_POSSIBLE && r2 == NOT_POSSIBLE) {
            return NOT_POSSIBLE;
        }
        if (r1 == NOT_POSSIBLE) {
            return c2 + r2;
        }
        if (r2 == NOT_POSSIBLE) {
            return c1 + r1;
        }

        r1 = c1 + r1;
        r2 = c2 + r2;
        return r1.compareTo(r2) <= 0 ? r1 : r2;
    }

    private static boolean matches(String c, String s) {
        for (int i = 0; i < c.length(); i++) {
            if (s.charAt(i) != '?' && c.charAt(i) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static List<String> list(String s, String a, String b) {
        return Arrays.asList(s, a, b);
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String p0, String p1, String p2, boolean hasAnswer, String p3) {
        System.out
                .print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + "\"" + p1 + "\"" + "," + "\"" + p2 + "\"");
        System.out.println("]");
        MergeStrings obj;
        String answer;
        obj = new MergeStrings();
        final long startTime = System.currentTimeMillis();
        answer = obj.getmin(p0, p1, p2);
        final long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + "\"" + p3 + "\"");
        }
        System.out.println("Your answer:");
        System.out.println("\t" + "\"" + answer + "\"");
        if (hasAnswer) {
            res = answer.equals(p3);
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
        String p3;

        // ----- test 0 -----
        p0 = "??CC??";
        p1 = "ABC";
        p2 = "BCC";
        p3 = "ABCCBC";
        all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = "WHAT?";
        p1 = "THE";
        p2 = "WA";
        p3 = "";
        all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = "PARROT";
        p1 = "PARROT";
        p2 = "";
        p3 = "PARROT";
        all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = "???????????";
        p1 = "AZZAA";
        p2 = "AZAZZA";
        p3 = "AAZAZZAAZZA";
        all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = "????K??????????????D???K???K????????K?????K???????";
        p1 = "KKKKKDKKKDKKDDKDDDKDKK";
        p2 = "KDKDDKKKDDKDDKKKDKDKKDDDDDDD";
        p3 = "KDKDKDKKKDDKDDKKKDKDKKDKDDDKDDDKKDKKKDKKDDKDDDKDKK";
        all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = "????Q???J???B?X";
        p1 = "PIQJBQ";
        p2 = "CPVJVXBLX";
        p3 = "CPIPQJBVJQVXBLX";
        all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;

        if (all_right) {
            System.out.println("You're a stud (at least on the example cases)!");
        } else {
            System.out.println("Some of the test cases had errors.");
        }
    }
    // END KAWIGIEDIT TESTING
}
// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
