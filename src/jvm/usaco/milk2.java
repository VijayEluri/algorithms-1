package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * ID: rdsr.me1 PROG: milk2 LANG: JAVA
 */
public class milk2 {
    public static void main(
            String[] _) throws Exception {
        final MilkingTime[] times = new MilkingTime[5000];
        final BufferedReader br = new BufferedReader(
                new FileReader("resources/milk2.in"));
        int n = Integer.valueOf(br.readLine());
        MilkingTime t;
        int count = 0;
        while (n-- > 0) {
            t = MilkingTime.parse(br.readLine());
            times[count++] = t;
        }

        Arrays.sort(times, 0, count);
        t = times[0];
        int lct = t.duration();
        int lit = 0;
        for (int i = 1; i < count; i++) {
            if (t.isOverlapping(times[i])) {
                t = MilkingTime.getOverlappedTime(t, times[i]);
                if (t.duration() > lct) {
                    lct = t.duration();
                }
            } else {
                final MilkingTime s = times[i];
                final int d = MilkingTime.getDifference(t, s);
                if (d > lit) {
                    lit = d;
                }
                if (s.duration() > lct) {
                    lct = s.duration();
                }
                t = s;
            }
        }

        if (times[0].isOverlapping(times[count - 1])) {
            lit = 0;
        }

        final PrintWriter pw = new PrintWriter("resources/milk2.out");
        pw.println(lct + " " + lit);
        pw.close();
    }
}


class MilkingTime implements Comparable<MilkingTime> {
    int start;
    int finish;

    private MilkingTime(String _start, String _finish) {
        start = Integer.valueOf(_start);
        finish = Integer.valueOf(_finish);
    }

    public static int getDifference(
            MilkingTime t1, MilkingTime t2) {
        final MilkingTime earlier = earlier(t1, t2);
        final MilkingTime later = later(t1, t2);
        return later.start - earlier.finish;
    }

    private MilkingTime(int _start, int _finish) {
        start = _start;
        finish = _finish;
    }

    public static MilkingTime parse(String input) {
        final String[] times = input.split(" +");
        return new MilkingTime(times[0], times[1]);
    }

    public boolean isOverlapping(MilkingTime t) {
        final MilkingTime earlier = earlier(this, t);
        final MilkingTime later = later(this, t);

        if (later.start <= earlier.finish) {
            return true;
        }
        return false;
    }

    public int duration() {
        return finish - start;
    }

    public static MilkingTime getOverlappedTime(
            MilkingTime t1, MilkingTime t2) {
        final MilkingTime earlier = earlier(t1, t2);
        final MilkingTime later = later(t1, t2);

        if (earlier.finish >= later.finish) {
            return earlier;
        }
        return new MilkingTime(earlier.start, later.finish);
    }

    private static MilkingTime later(
            MilkingTime t1, MilkingTime t2) {
        if (t1.start >= t2.start) {
            return t1;
        }
        return t2;
    }

    private static MilkingTime earlier(
            MilkingTime t1, MilkingTime t2) {
        if (t1.start <= t2.start) {
            return t1;
        }
        return t2;
    }

    @Override
    public int compareTo(MilkingTime t) {
        if (start > t.start) {
            return 1;
        }
        if (start == t.start) {
            if (finish == t.finish) {
                return 0;
            }
            if (finish < t.finish) {
                return -1;
            }
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return start + " " + finish;
    }
}
