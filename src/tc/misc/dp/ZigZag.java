package tc.misc.dp;

import java.util.Arrays;

public class ZigZag {
    int [] s;
    int [] z;

    public int longestZigZag(int[] a) {
        s = new int[a.length];
        z = new int[a.length];

        Arrays.fill(s, 1);
        z[0] = 1;

        for (int i = 1; i < a.length; i++)
            for (int j = 0; j < i; j++)
                if (s[j] + 1 > s[i] && isOppSign(a[i] - a[j], j)) {
                    s[i] = s[j] + 1;
                    z[i] = a[i] - a[j];
                }
        return max(s);
    }

    private boolean isOppSign(int v, int j) {
        if (j == 0)
            return true;

        return (v > 0 && z[j] < 0) ||
            (v < 0 && z[j] > 0);
    }

    private int max(int[] s) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < s.length; i++)
            if (s[i] > max)
                max = s[i];
        return max;
    }

    public static void main(String [] _) {
        System.out.println(new ZigZag().longestZigZag(new int[]{ 1, 7, 4, 9, 2, 5 }));
        System.out.println(new ZigZag().longestZigZag(new int[]{ 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }));
        System.out.println(new ZigZag().longestZigZag(new int[]{ 44 }));
        System.out.println(new ZigZag().longestZigZag(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
        System.out.println(new ZigZag().longestZigZag(new int[]{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
        System.out.println(new ZigZag().longestZigZag(new int[]{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                                                                 600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                                                                 67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                                                                 477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                                                                 249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }));
    }
}
