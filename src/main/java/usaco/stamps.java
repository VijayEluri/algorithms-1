package usaco;

/**
ID: rdsr.me1
PROB: stamps
LANG: JAVA
 */

import java.io.*;
import java.util.*;

/**
 Given a set of N stamp values (e.g., {1 cent, 3 cents}) and an upper limit K to the number of stamps that can fit on an envelope, calculate the largest unbroken list of postages from 1 cent to M cents that can be created.

 For example, consider stamps whose values are limited to 1 cent and 3 cents; you can use at most 5 stamps. It's easy to see how to assemble postage of 1 through 5 cents (just use that many 1 cent stamps), and successive values aren't much harder:

 6 = 3 + 3
 7 = 3 + 3 + 1
 8 = 3 + 3 + 1 + 1
 9 = 3 + 3 + 3
 10 = 3 + 3 + 3 + 1
 11 = 3 + 3 + 3 + 1 + 1
 12 = 3 + 3 + 3 + 3
 13 = 3 + 3 + 3 + 3 + 1.
 However, there is no way to make 14 cents of postage with 5 or fewer stamps of value 1 and 3 cents. Thus, for this set of two stamp values and a limit of K=5, the answer is M=13.

 The most difficult test case for this problem has a time limit of 3 seconds.

 PROGRAM NAME: stamps

 INPUT FORMAT

 Line 1:	Two integers K and N. K (1 <= K <= 200) is the total number of stamps that can be used. N (1 <= N <= 50) is the number of stamp values.
 Lines 2..end:	N integers, 15 per line, listing all of the N stamp values, each of which will be at most 10000.
 SAMPLE INPUT (file stamps.in)

 5 2
 1 3
 OUTPUT FORMAT

 Line 1:	One integer, the number of contiguous postage values starting at 1 cent that can be formed using no more than K stamps from the set.
 SAMPLE OUTPUT (file stamps.out)

 13

 */

public class stamps {
  // no. of stamps
  private int k;
  // total stamp denominations
  private int n;
  // stamp denominations
  private int[] denoms;

  public static void main(String[] args) throws IOException {
    final Scanner in = new Scanner(new File("stamps.in"));
    stamps s = new stamps();
    s.k = in.nextInt();
    s.n = in.nextInt();
    s.denoms = new int[s.n];
    for (int i = 0; i < s.n; i++) {
      s.denoms[i] = in.nextInt();
    }

    final PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter("stamps.out")));
    pw.println(s.solve());
    pw.close();
  }

  long solve() {
    int maxPostage = k * max(denoms);

    // s[i] = min no. of stamps required to construct postage of value i
    int[] s = new int[maxPostage + 1];
    s[0] = 0;
    for (int d : denoms) {
      // min num. of stamps required to construct postage is equal to the stamp denomination is 1
      s[d] = 1;
    }
    // algorithm
    // if s[i] holds min no. of stamps to compute postage 'i'
    // then to compute s[i] we can take the min of (s[i - d(j)] + 1, s[i]) for d(j) belongs to denoms
    // This work since s[i - d(j)] holds min. of of stamps to compute postage i - d(j) , and postage
    // i can only be constructed by adding one of the denominations to i - d(j). We pick that denomination
    // which uses the minimum no. of stamps

    // after computing no. of stamps for postage i, we verify if the number of
    // stamps used is greater than k, in which case we break the loop and return
    // i-1 .

    for (int i = 1; i <= maxPostage; i++) {
      // we don't want default of 0, since we use this value to compute min, see below
      s[i] = k + 1;
      for (int d : denoms) {
        if (i >= d) {
          s[i] = Math.min(s[i - d] + 1, s[i]);
        }
      }
      if (s[i] > k) {
        return i - 1;
      }
    }
    return maxPostage;
  }

  private int max(int[] denoms) {
    int max = -1;
    for (int x : denoms) {
      if (x > max) {
        max = x;
      }
    }
    return max;
  }
}

