package tc.misc;

//2003 TCO Semifinal Round 4 - Division I, Level One

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AvoidRoads {
    long[][] s;
    Map<String, String> badPaths = new HashMap<String, String>();

    public static void main(String[] _) {
        System.out.println(new AvoidRoads().numWays(1, 1, new String[]{}));
        System.out.println(new AvoidRoads().numWays(95, 16, new String[]{}));
        System.out.println(new AvoidRoads().numWays(2, 2, new String[]{"1222", "1121"}));
        System.out.println(new AvoidRoads().numWays(6, 6, new String[]{"0001", "6656"}));
        System.out.println(new AvoidRoads().numWays(9, 100, new String[]{"0203", "1213", "2223", "3233", "4243", "5253", "6263", "7273", "8283", "9293"}));
        System.out.println(new AvoidRoads().numWays(10, 100, new String[]{"0203", "1213", "2223", "3233", "4243", "5253", "6263", "7273", "8283", "9293"}));
        System.out.println(new AvoidRoads().numWays(24, 24, new String[]{"22162215", "123023", "20161916", "24232422", "18101910", "20191919", "152153", "192193", "18151915", "14101510", "177176", "231221", "23202319", "012011", "17151714", "178168", "22152115", "512412", "8384", "13231223", "13211421", "1819", "114124", "2313", "524523", "9394", "16211521", "223323", "175185", "17211722", "6171", "17141713", "6261", "19191918", "12171216", "171170", "314315", "21162115", "9989", "13141313", "18121912", "015014", "11241123", "216206", "234235", "197196", "13241424", "212202", "131121", "21102111"}));
    }

    public long numWays(int width, int height, String[] bad) {
        s = new long[height + 1][width + 1];
        s[0][0] = 0;

        for(int i = 0; i < height + 1; i++)
            Arrays.fill(s[i], -1);

        for(int i = 0; i < bad.length; i++) {
            int m = bad[i].length()/2;
            badPaths.put(bad[i].substring(0, m), bad[i].substring(m));
            badPaths.put(bad[i].substring(m), bad[i].substring(0, m));
        }

        return solve(height, width, height + width);
    }

    private long solve(int i, int j, int blocks) {
        if (i < 0 || j < 0)
            return 0;

        if(i == 0 && j == 0)
            return 1;

        if (s[i][j] >= 0)
            return s[i][j];

        long left = 0;
        if (!blocked(i, j, i, j - 1))
            left  =  solve(i, j - 1, blocks - 1);
        long down = 0;
        if (!blocked(i, j, i - 1, j))
            down = solve(i - 1, j, blocks - 1);

        return s[i][j] = left + down;
    }

    private boolean blocked(int i1, int j1, int i2, int j2) {
        String p = j1 + "" + i1;        // since m[i][j] == p(j, i); --> j = x-coord, i = y-coord
        String q = j2 + "" + i2;

        String u = badPaths.get(p);
        String v = badPaths.get(q);

        if (q.equals(u) || p.equals(v))
            return true;
        return false;
    }
}