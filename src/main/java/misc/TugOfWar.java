package misc;

import java.util.Set;


/**
 *
 Tug of War
 *
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the
 * difference of the sum of two subsets is as minimum as possible
 */

public class TugOfWar {
  int solve(int a[], int i, Set<Integer> subset, int s) {
    if (i < 0) {
      return Integer.MAX_VALUE;
    }

    if (subset.size() < a.length / 2 - 1) {
      return Integer.MAX_VALUE;
    }

    subset.add(i);
    int used = solve(a, i - 1, subset, s - a[i]) + a[i];
    subset.remove(i);
    int notUsed = solve(a, i - 1, subset, s);
    if (Math.abs(used - s) < Math.abs(notUsed - s)) {
      return used;
    } else {
      return notUsed;
    }
  }
}
