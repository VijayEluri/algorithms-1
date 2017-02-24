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

// This approach does not complete in allotted time.
public class stamps1 {

  private int k;
  private int n;
  private int maxPossiblePostage;
  private int[] denoms;
  private Map<String, Boolean> cache = new HashMap<>();

  public static void main(String[] args) throws IOException {
    final Scanner in = new Scanner(new File("stamps.in"));
    stamps1 s = new stamps1();
    s.k = in.nextInt();
    s.n = in.nextInt();
    s.denoms = new int[s.n];
    for (int i = 0; i < s.n; i++) {
      s.denoms[i] = in.nextInt();
    }
    s.maxPossiblePostage = s.k * 10000;

    final PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter("stamps.out")));
    pw.println(s.solve());
    pw.close();
  }

  long solve() {
    for (int p = 1; p <= maxPossiblePostage; p++) {
      if (!canMakePostage(p, 0, k)) {
        return p-1;
      }
    }
    return maxPossiblePostage;
  }
  //  50 * 200 == 10^4
  private boolean canMakePostage(int p, int dIdx, int nStamps) {
    String ck = p + ":" + dIdx + ":" + nStamps;
    if (cache.containsKey(ck)) {
      return cache.get(ck);
    }
    if (p == 0 && nStamps >= 0) {
      return true;
    }
    if (dIdx >= denoms.length || nStamps <= 0) {
      return false;
    }
    boolean r = canMakePostage(p - denoms[dIdx], dIdx, nStamps - 1)
        || canMakePostage(p, dIdx + 1, nStamps);
    cache.put(ck, r);
    return r;
  }
}

