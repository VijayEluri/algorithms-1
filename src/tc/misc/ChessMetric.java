package tc.misc;

import java.util.HashMap;
import java.util.Map;

// TopCoder Collegiate Challenge Round 4 - Division I, Level One

class Distance {
    final int x;
    final int y;
    final int count;

    public Distance(int _x, int _y, int _c) {
        x = _x;
        y = _y;
        count = _c;
    }

    @Override
        public boolean equals(Object o) {
        if (!(o instanceof Distance))
            return false;

        Distance d = (Distance) o;
        return x == d.x &&
            y == d.y &&
            count == d.count;
    }

    @Override
        public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + count;
        return result;
    }
}

public class ChessMetric {
    Map<Distance, Long> s = new HashMap<Distance, Long>();
    int size;

    public static void main(String[] _) {
        System.out.println(new ChessMetric().howMany(3, new int[]{0,0}, new int[]{1,0}, 1));
        System.out.println(new ChessMetric().howMany(3, new int[]{0,0}, new int[]{0,0}, 2));
        System.out.println(new ChessMetric().howMany(3, new int[]{0,0}, new int[]{2,2}, 1));
        System.out.println(new ChessMetric().howMany(100, new int[]{0,0}, new int[]{0,99}, 50));
        System.out.println(new ChessMetric().howMany(100, new int[]{0,0}, new int[]{50, 50}, 35));
    }

    public long howMany(int size, int[] start, int[] end, int numMoves) {
        this.size = size;
        s.put(new Distance(end[0], end[1], 0), 1L);
        return solve(start[0], start[1], numMoves);
    }

    private long solve(int i, int j, int numMoves) {
        if (i < 0 || i > size -1)
            return 0;
        if (j < 0 || j > size - 1)
            return 0;

        Distance d = new Distance(i, j, numMoves);
        if (s.get(d) != null)
            return s.get(d);

        if(numMoves == 0)
            return 0;

        long r =
            //king
            solve(i - 1, j - 1, numMoves - 1) +
            solve(i - 1, j,     numMoves - 1) +
            solve(i - 1, j + 1, numMoves - 1) +
            solve(i,     j - 1, numMoves - 1) +
            solve(i,     j + 1, numMoves - 1) +
            solve(i + 1, j - 1, numMoves - 1) +
            solve(i + 1, j    , numMoves - 1) +
            solve(i + 1, j + 1, numMoves - 1) +
            //knight
            solve(i - 2, j - 1, numMoves - 1) +
            solve(i - 2, j + 1, numMoves - 1) +
            solve(i - 1, j - 2, numMoves - 1) +
            solve(i - 1, j + 2, numMoves - 1) +
            solve(i + 1, j - 2, numMoves - 1) +
            solve(i + 1, j + 2, numMoves - 1) +
            solve(i + 2, j - 1, numMoves - 1) +
            solve(i + 2, j + 1, numMoves - 1);

        s.put(new Distance(i, j, numMoves), r);
        return r;
    }
}
