package tc.misc.dp;

import java.util.Arrays;

//2004 TCCC Online Round 4 - Division I, Level One

// cleaner, and much elegant version of BadNeighbors
public class BadNeighbors2 {
  int [][] s;

  int helper(int[] input) {
    return 
      max(maxDonations(Arrays.copyOfRange(input, 0, input.length - 1)),
          maxDonations(Arrays.copyOfRange(input, 1, input.length)));
  }

  public static void main(String[] _) {
    System.out.println(new BadNeighbors2().helper(new int[]{ 10, 3, 2, 5, 7, 8 }));
    System.out.println(new BadNeighbors2().helper(new int[]{ 11, 15}));
    System.out.println(new BadNeighbors2().helper(new int[]{ 7, 7, 7, 7, 7, 7, 7 }));
    System.out.println(new BadNeighbors2().helper(new int[]{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }));
    System.out.println(new BadNeighbors2().helper(new int[]{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
        6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
        52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }));
  }

  public int maxDonations(int[] donations) {
    int n = donations.length;
    s = new int[n][2];

    s[0][0] = 0;
    s[0][1] = donations[0];

    int d = 0;
    for(int i = 1; i < n; i++) {
      if (s[i - 1][0] > s[i - 1][1])
        d = s[i - 1][0];
      else
        d = s[i - 1][1];

      s[i][1] = s[i - 1][0] + donations[i];
      s[i][0] = d;
    }

    return max(s[n - 1][0], s[n - 1][1]);
  }

  public int max(int a, int b) {
    if (a > b)
      return a;
    return b;
  }
}
