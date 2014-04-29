package usaco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


/**
 * ID: rdsr.me1 PROG: runround LANG: JAVA
 */
public class runround {
  public static void main(String[] args)
      throws FileNotFoundException {
    final Scanner s = new Scanner(new File("src/main/resources/usaco/runround.in"));
    final int N = s.nextInt();
    s.close();

    final PrintWriter w = new PrintWriter(new File("build/runround.out"));
    w.println(nextRunAround(N));
    w.close();
  }

  private static int nextRunAround(int n) {
    for (int i = n + 1; ; i++) {
      final int[] digits = digits(i);
      if (runAround(digits)) {
        return i;
      }
    }
  }

  private static boolean runAround(int[] digits) {
    final boolean[] found = new boolean[10];
    int i = 0;
    for (int cnt = 0; cnt < digits.length; ) {
      final int d = digits[i];
      final int j = (i + d) % digits.length;
      if (found[digits[j]]) {
        return false;
      } else {
        found[digits[j]] = true;
        i = j;
        cnt += 1;
      }
    }
    return true;
  }

  private static int[] digits(int n) {
    final int[] digits = new int[9];
    int i = 0;
    while (n != 0) {
      digits[8 - i] = n % 10;
      i += 1;
      n /= 10;
    }
    return Arrays.copyOfRange(digits, 9 - i, 9);
  }
}
