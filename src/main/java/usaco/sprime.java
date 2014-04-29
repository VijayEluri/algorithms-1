package usaco;

/*
 * ID: rdsr.me1 LANG: JAVA PROG: sprime
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class sprime {
  public static void main(String[] args)
      throws IOException {
    final Scanner s = new Scanner(new File("src/main/resoures/usaco/sprime.in"));
    final int N = s.nextInt();
    s.close();

    final List<Integer> primes = buildPrimes((int) Math.sqrt(pow(10, N) - 1) + 1);
    final PrintWriter pr = new PrintWriter("build/sprime.out");
    for (final int[] digits : crossProduct(N, 0)) {
      if (isSprime(digits, primes)) {
        pr.println(toNumber(digits, digits.length));
      }
    }
    pr.close();
  }

  private static boolean isSprime(int[] digits, List<Integer> primes) {
    for (int len = digits.length; len > 0; len--) {
      final int n = toNumber(digits, len);
      if (!isPrime(n, primes)) {
        return false;
      }
    }
    return true;
  }

  private static int toNumber(int[] digits, int len) {
    int n = 0;
    for (int i = 0; i < len; i++) {
      n += digits[i] * pow(10, len - i - 1);
    }
    return n;
  }

  private static Collection<int[]> crossProduct(int dimension, int index) {
    int[] es;
    if (index == 0) {
      es = new int[]{2, 3, 5, 7};
    } else {
      es = new int[]{1, 3, 7, 9};
    }
    if (index == dimension - 1) {
      final Collection<int[]> r = new ArrayList<int[]>(es.length);
      for (final int e : es) {
        r.add(new int[]{e});
      }
      return r;
    } else {
      final Collection<int[]> __r = crossProduct(dimension, index + 1);
      final Collection<int[]> r = new ArrayList<int[]>(es.length * __r.size());

      for (final int e : es) {
        for (final int[] pp : __r) {
          final int[] p = new int[pp.length + 1];
          p[0] = e;
          for (int i = 0; i < pp.length; i++) {
            p[i + 1] = pp[i];
          }
          r.add(p);
        }
      }
      return r;
    }
  }

  private static List<Integer> buildPrimes(int upperBound) {
    final List<Integer> primes = new LinkedList<Integer>();
    for (int i = 2; i <= upperBound; i++) {
      if (isPrime(i, primes)) {
        primes.add(i);
      }
    }
    return primes;
  }

  private static boolean isPrime(int n, List<Integer> primes) {
    if (n == 1) {
      return false;
    }
    for (final int p : primes) {
      if (p * p > n) {
        break;
      }
      if (p == n) {
        return true;
      }
      if (n % p == 0) {
        return false;
      }
    }
    return true;
  }

  private static int pow(int a, int b) {
    return (int) Math.pow(a, b);
  }
}
