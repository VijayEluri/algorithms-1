package topcoder.misc.dp;

// Single Round Match 209 Round 1 - Division II, Level Three

public class LoadBalancing {
  int[] chunkSizes;
  int[][] s;
  int n;

  public int minTime(int[] chunkSizes) {
    if (chunkSizes.length == 0) {
      return 0;
    }

    this.chunkSizes = chunkSizes;
    n = chunkSizes.length;

    int totalTime = 0;
    for (int i = 0; i < chunkSizes.length; i++) {
      chunkSizes[i] /= 1024;
      totalTime += chunkSizes[i];
    }

    s = new int[n][totalTime / 2 + 1];
    int t = solve(chunkSizes.length - 1, totalTime / 2);
    return Math.max(t, totalTime - t) * 1024;
  }

  private int solve(int i, int S) {
    if (i == -1 || S == 0) {
      return 0;
    }

    if (s[i][S] != 0) {
      return s[i][S];
    }

    int diffIfChosen = 0, v1 = 0, v2 = 0;
    if (chunkSizes[i] > S) {
      diffIfChosen = Math.abs(chunkSizes[i] - S);
      v1 = chunkSizes[i];
    } else {
      v1 = solve(i - 1, S - chunkSizes[i]) + chunkSizes[i];
      diffIfChosen = Math.abs(v1 - S);
    }

    v2 = solve(i - 1, S);
    int diffIfNotChosen = Math.abs(v2 - S);

    if (diffIfChosen < diffIfNotChosen) {
      s[i][S] = v1;
    } else {
      s[i][S] = v2;
    }

    return s[i][S];
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
