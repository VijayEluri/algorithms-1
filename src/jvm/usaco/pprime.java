//package usaco;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/*
ID: rdsr.me1 
LANG: JAVA 
PROG: pprime
 */
public class pprime {
    public static void main(String[] args) throws IOException {
        final Scanner s = new Scanner(new File("pprime.in"));
        final int a = s.nextInt();
        final int b = s.nextInt();
        s.close();

        final PrintWriter pr = new PrintWriter("pprime.out");
        final SortedSet<Integer> primes = buildPrimes((int)Math.sqrt(b) + 1);

        for (int i = 1; i < 10; i += 2) {
            final int n = i;
            if (n >= a && n <= b && primes.contains(n)) {
                pr.println(n);
            }
        }

        for (int i = 1; i < 10; i += 2) {
            final int n = 10 * i + i;
            if (n >= a && n <= b && primes.contains(n)) {
                pr.println(n);
            }
        }

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                final int n = pow(10, 2) * i + 10 * j + i;
                if (n >= a && n <= b && primes.contains(n)) {
                    pr.println(n);
                }
            }
        }

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                final int n = pow(10, 3) * i + pow(10, 2) * j + 10 * j + i;
                if (n >= a && n <= b && primes.contains(n)) {
                    pr.println(n);
                }
            }
        }

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    final int n = pow(10, 4) * i + pow(10, 3) * j + pow(10, 2) * k + 10 * j + i;
                    if (n >= a && n <= b && primes.contains(n)) {
                        pr.println(n);
                    }
                }
            }
        }

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    final int n = pow(10, 5) * i + pow(10, 4) * j + pow(10, 3) * k + pow(10, 2) * k + 10 * j + i;
                    if (n >= a && n <= b && primes.contains(n)) {
                        pr.println(n);
                    }
                }
            }
        }

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        final int n =
                                pow(10, 6) * i + pow(10, 5) * j + pow(10, 4) * k + pow(10, 3) * l + pow(10, 2) * k + 10
                                        * j + i;
                        if (n >= a && n <= b && primes.contains(n)) {
                            pr.println(n);
                        }
                    }
                }
            }
        }


        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        final int n =
                                pow(10, 7) * i + pow(10, 6) * j + pow(10, 5) * k + pow(10, 4) * l + pow(10, 3) * l
                                        + pow(10, 2) * k + 10 * j + i;
                        if (n >= a && n <= b && primes.contains(n)) {
                            pr.println(n);
                        }
                    }
                }
            }
        }
        pr.close();
    }

    private static SortedSet<Integer> buildPrimes(int upperBound) {
        final TreeSet<Integer> primes = new TreeSet<Integer>();
        for (int i = 2; i <= upperBound; i++) {
            if (isPrime(i, primes)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(int n, SortedSet<Integer> primes) {
        for (final int p : primes) {
            if (Math.sqrt(p) > n) {
                break;
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
