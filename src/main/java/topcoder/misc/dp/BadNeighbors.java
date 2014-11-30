package topcoder.misc.dp;

//2004 TCCC Online Round 4 - Division I, Level One

public class BadNeighbors {
  int[][] s;
  boolean[] includesFirst;

  public static void main(String[] _) {
    System.out.println(new BadNeighbors().maxDonations(new int[]{10, 3, 2, 5, 7, 8}));
    System.out.println(new BadNeighbors().maxDonations(new int[]{11, 15}));
    System.out.println(new BadNeighbors().maxDonations(new int[]{7, 7, 7, 7, 7, 7, 7}));
    System.out.println(new BadNeighbors().maxDonations(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}));
    System.out.println(new BadNeighbors().maxDonations(
        new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}));
  }

  public int maxDonations(int[] donations) {
    int n = donations.length;
    s = new int[n][2];
    includesFirst = new boolean[2];

    s[0][0] = 0;
    s[0][1] = donations[0];
    includesFirst[0] = false;
    includesFirst[1] = true;

    int d = 0;
    boolean includes = false;
    for (int i = 1; i < n; i++) {
      if (s[i - 1][0] > s[i - 1][1]) {
        d = s[i - 1][0];
        includes = includesFirst[0];
      } else {
        d = s[i - 1][1];
        includes = includesFirst[1];
      }

      s[i][1] = s[i - 1][0] + donations[i];
      includesFirst[1] = includesFirst[0];

      s[i][0] = d;
      includesFirst[0] = includes;
    }

    if (includesFirst[1]) {
      s[n - 1][1] -= s[0][1];
    }

    return max(s[n - 1][0], s[n - 1][1]);
  }

  private int max(int a, int b) {
    if (a > b) {
      return a;
    }
    return b;
  }
}
