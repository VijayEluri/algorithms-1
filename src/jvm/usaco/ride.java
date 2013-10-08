package usaco;

/*
 * ID: rdsr.me1 PROG: ride LANG: JAVA
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ride {
    private static int sum(String a) {
        int sum = 1;
        for (final char c : a.toCharArray()) {
            sum *= (c - 'A' + 1);
        }
        return sum;
    }

    public static void main(String[] _) throws Exception {
        PrintWriter o = null;
        BufferedReader f = null;

        try {
            f = new BufferedReader(new FileReader("resources/ride.in"));
            o = new PrintWriter(new BufferedWriter(
                    new FileWriter("resources/ride.out")));

            while (true) {
                final String comet = f.readLine();
                if (comet == null) {
                    break;
                }
                final String group = f.readLine();

                if ((sum(group) % 47) == (sum(comet) % 47)) {
                    o.println("GO");
                } else {
                    o.println("STAY");
                }
            }
        } finally {
            o.close();
            f.close();
        }
    }
}
