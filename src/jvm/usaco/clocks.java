package usaco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
 * ID: rdsr.me1 PROG: clocks LANG: JAVA
 */

public class clocks {
    private static int[][] levers = {
            clocksGroup("ABDE"),
            clocksGroup("ABC"),
            clocksGroup("BCEF"),
            clocksGroup("ADG"),
            clocksGroup("BDEFH"),
            clocksGroup("CFI"),
            clocksGroup("DEGH"),
            clocksGroup("GHI"),
            clocksGroup("EFHI")
    };

    static String shortestMoves(int[] state) {
        String shortest = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 4; m++) {
                            for (int n = 0; n < 4; n++) {
                                for (int o = 0; o < 4; o++) {
                                    for (int p = 0; p < 4; p++) {
                                        for (int q = 0; q < 4; q++) {
                                            final int[] moves = new int[] {i, j, k, l, m, n, o, p, q};
                                            if (goal(next(state, moves))) {
                                                final String movesStr = movesAsStr(moves);
                                                if (shortest == null || shortest.compareTo(movesStr) > 0) {
                                                    shortest = movesStr;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return shortest;
    }

    private static boolean goal(int[] state) {
        for (int i = 0; i < 9; i++) {
            if (state[i] != 12) {
                return false;
            }
        }
        return true;
    }

    private static int[] next(int[] state, int[] leversPressed) {
        final int[] next = Arrays.copyOf(state, state.length);
        for (int lever = 0; lever < leversPressed.length; lever++) {
            final int[] clocksGroup = levers[lever];
            for (int pressed = 0; pressed < leversPressed[lever]; pressed++) {
                for (final int clock : clocksGroup) {
                    next[clock] = next[clock] % 12 + 3;
                }
            }
        }
        return next;
    }

    private static int[] clocksGroup(String s) {
        final int[] m = new int[s.length()];
        for (int i = 0; i < m.length; i++) {
            m[i] = s.charAt(i) - 'A';
        }
        return m;
    }

    private static String movesAsStr(int[] seq) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.length; i++) {
            for (int j = 0; j < seq[i]; j++) {
                sb.append(i + 1).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        final Scanner s = new Scanner(new File("resources/clocks.in"));
        final int[] state = new int[9];
        for (int i = 0; s.hasNext(); i++) {
            state[i] = s.nextInt();
        }
        s.close();

        final PrintWriter pr = new PrintWriter("resources/clocks.out");
        pr.println(shortestMoves(state));
        pr.close();
    }

}
