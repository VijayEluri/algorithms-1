package usaco;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import util.FastScanner;


/*
 * ID: rdsr.me1 PROG: prefix LANG: JAVA
 */
public class prefix {
  Set<String> primitives;
  int[] cache;

  public prefix(Set<String> primitives) {
    this.primitives = primitives;
  }

  private int longestPrefix(String seq) {
    final int n = seq.length();
    final boolean[] s = new boolean[n];

    int i = 0;
    int lpl = 0;
    for (final String p : primitives) {
      if (p.length() > lpl) {
        lpl = p.length();
      }

      if (seq.startsWith(p)) {
        s[p.length() - 1] = true;
        if (p.length() - 1 > i) {
          i = p.length() - 1;
        }
      }
    }

    while (i < n) {
      boolean found = false;
      int incr = 0;
      for (final String p : primitives) {
        for (int j = Math.max(i - lpl, 0); j <= i && j + p.length() <= n; j++) {
          if (p.equals(seq.substring(j, j + p.length()))) {
            if (j == 0 || (j > 0 && s[j - 1])) {
              found = true;
              s[j + p.length() - 1] = true;
              if (p.length() > incr) {
                incr = p.length();
              }
            }
          }
        }
      }
      if (!found) {
        break;
      }
      i += incr;
    }

    for (int j = n - 1; j >= 0; j--) {
      if (s[j]) {
        return j + 1;
      }
    }
    return 0;
  }

  public static void main(String[] args)
      throws IOException {
    final FastScanner s = new FastScanner("src/main/resources/usaco/prefix.in");
    String str = null;
    final Set<String> primitives = new HashSet<String>();
    while (!(str = s.next()).equals(".")) {
      primitives.add(str);
    }

    final StringBuilder seq = new StringBuilder();
    while ((str = s.next()) != null) {
      seq.append(str);
    }
    s.close();

    final PrintWriter w = new PrintWriter("build/prefix.out");
    w.println(new prefix(primitives).longestPrefix(seq.toString()));
    w.close();
  }
}
