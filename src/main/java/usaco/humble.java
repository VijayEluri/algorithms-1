package usaco;
/**
 ID: rdsr.me1
 PROB: humble
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

/**
 For a given set of K prime numbers S = {p1, p2, ..., pK}, consider the set of all numbers whose prime factors are a subset of S. This set contains, for example, p1, p1p2, p1p1, and p1p2p3 (among others). This is the set of `humble numbers' for the input set S. Note: The number 1 is explicitly declared not to be a humble number.

 Your job is to find the Nth humble number for a given set S. Long integers (signed 32-bit) will be adequate for all solutions.
 */

public class humble {
  public static void main(String[] args) throws IOException {
    final Scanner in = new Scanner(new File("humble.in"));
    final int k = in.nextInt();
    final int n = in.nextInt();
    final List<Integer> s = new ArrayList<>(k);
    for (int i = 0; i < k; i++) {
      s.add(in.nextInt());
    }
    in.close();
    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
    final long r = solve(s, n);
    pw.println(r);
    pw.close();
  }

  private static long solve(List<Integer> primes, int n) {
    Collections.sort(primes);
    long[] humbles = new long[n + 1];
    humbles[0] = 1;
    int hl = 1;
    while (true) {
      long minHum = Long.MAX_VALUE;
      for (int prime : primes) {
        long kHum = bsearch(humbles, prime, hl);
        if (kHum < minHum) {
          minHum = kHum;
        }
      }
      humbles[hl] = minHum;
      hl += 1;
      if (hl == n + 1) {
        return humbles[hl - 1];
      }
    }
  }

  private static long bsearch(long[] humbles, int prime, int sz) {
    int i = 0;
    long l = humbles[sz - 1];
    int j = sz - 1;
    while (i < j - 1) {
      int m = (i + j) / 2;
      long v = prime * humbles[m];
      if (v > l) {
        j = m;
      } else {
        // v <= l
        i = m;
      }
    }
    long v = prime * humbles[i];
    if (v <= humbles[sz - 1]) {
      v = prime * humbles[j];
    }
    return v;
  }
}

