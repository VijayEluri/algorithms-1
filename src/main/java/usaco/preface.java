package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


/**
 * ID: rdsr.me1 PROG: preface LANG: JAVA
 */
public class preface {
  String[] dp;
  PrintWriter pw;
  private HashMap<Character, Integer> count;

  char[] numerals = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

  void numeralCount(PrintWriter pw, int n) {
    this.pw = pw;
    this.count = new HashMap<Character, Integer>();
    this.dp = new String[Math.max(n, 10) + 1];
    dp[0] = "";
    dp[1] = "I";
    dp[2] = "II";
    dp[3] = "III";
    dp[4] = "IV";
    dp[5] = "V";
    dp[6] = "VI";
    dp[7] = "VII";
    dp[8] = "VIII";
    dp[9] = "IX";

    for (int i = 1; i <= n; i++) {
      final String roman = solve(i);
      // System.out.println(roman);
      for (final char c : roman.toCharArray()) {
        if (count.containsKey(c)) {
          count.put(c, count.get(c) + 1);
        } else {
          count.put(c, 1);
        }
      }
    }

    for (final char c : numerals) {
      printCount(c);
    }
  }

  String solve(int number) {
    if (dp[number] != null) {
      return dp[number];
    }

    if (number >= 1000) {
      final int hd = number / 1000;
      dp[number] = repeat('M', hd) + solve(number - hd * 1000);
    } else if (number >= 100) {
      final int hd = number / 100;
      int recurseOn = number - hd * 100;
      if (hd < 4) {
        dp[number] = repeat('C', hd);
      } else if (hd == 4) {
        dp[number] = "CD";
      } else if (hd >= 5 && hd < 9) {
        dp[number] = "D";
        recurseOn = number - 5 * 100;
      } else { // if (number == 9) {
        dp[number] = "CM";
      }
      dp[number] += solve(recurseOn);
    } else if (number >= 10) {
      final int hd = number / 10;
      int recurseOn = number - hd * 10;
      if (hd < 4) {
        dp[number] = repeat('X', hd);
      } else if (hd == 4) {
        dp[number] = "XL";
      } else if (hd >= 5 && hd < 9) {
        dp[number] = "L";
        recurseOn = number - 5 * 10;
      } else { // if (number == 9) {
        dp[number] = "XC";
      }
      dp[number] += solve(recurseOn);
    }
    return dp[number];
  }

  private void printCount(char c) {
    if (count.containsKey(c)) {
      pw.println(c + " " + count.get(c));
    }
  }

  private static String repeat(char c, int n) {
    final StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      sb.append(c);
    }
    return sb.toString();
  }

  public static void main(String[] args)
      throws IOException {
    final Scanner s = new Scanner(new File("src/main/resources/usaco/preface.in"));
    final int N = s.nextInt();
    s.close();

    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("build/preface.out")));
    final preface p = new preface();
    p.numeralCount(pw, N);
    pw.close();
  }
}
