package usaco;

/*
 * ID: rdsr.me1 PROG: friday LANG: JAVA
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class friday {
    int[] monthToDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int[] frequencies = new int[7];

    public int[] solve(int n) {
        int day = 2;
        for (int year = 1900; year < 1900 + n; year++) {
            day = updateFrequencies(year, day);
        }
        return frequencies;
    }

    private int updateFrequencies(int year, int day) {
        if (isLeapYear(year)) {
            monthToDays[1] = 29;
        } else {
            monthToDays[1] = 28;
        }

        for (int month = 0; month < 12; month++) {
            frequencies[(day + 12) % 7] += 1;
            day = (day + monthToDays[month]) % 7;
        }

        return day;
    }

    private boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        return year % 4 == 0;
    }

    public static void main(
            String[] _)
            throws IOException {
        final PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("resources/friday.out")));

        final BufferedReader br = new BufferedReader(
                new FileReader("resources/friday.in"));

        String line;
        while ((line = br.readLine()) != null) {
            String output = "";
            for (final int frequency : new friday().solve(Integer.valueOf(line))) {
                output += frequency + " ";
            }
            pw.println(output.substring(0, output.length() - 1));
        }

        pw.close();
        br.close();
    }
}
