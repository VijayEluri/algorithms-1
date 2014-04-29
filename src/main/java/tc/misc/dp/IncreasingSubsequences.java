package tc.misc.dp;

// SRM 348 Div 2 Level 3

public class IncreasingSubsequences {
  public long count(int[] a) {
    long[] s = new long[a.length];
    boolean[] used = new boolean[a.length];

    s[0] = 1;
    for (int i = 1; i < a.length; i++) {
      int max = Integer.MIN_VALUE;
      for (int j = i - 1; j >= 0; j--) {
        if (a[i] > a[j]) {
          if (a[j] > max) {
            s[i] += s[j];
            max = a[j];
          }
          used[j] = true;
        }
      }

      if (s[i] == 0) {
        s[i] = 1;
      }
    }

    long count = 0;
    for (int i = 0; i < a.length; i++) {
      if (!used[i]) {
        count += s[i];
      }
    }
    return count;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
