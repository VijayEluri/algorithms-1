package usaco;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import util.Util;


/*
ID: rdsr.me1
LANG: JAVA
TASK: combo
*/
public class combo {
  int _N;

  public combo(int N) {
    _N = N;
  }

  public static void main(String[] args)
      throws IOException {
    final Scanner s = Util.scanner("src/main/resources/usaco/combo.in");
    final int N = s.nextInt();
    Combination fc = new Combination(s.nextInt(), s.nextInt(), s.nextInt());
    Combination mc = new Combination(s.nextInt(), s.nextInt(), s.nextInt());
    s.close();

    final combo c = new combo(N);
    final int solns = c.nSolns(fc, mc);

    final PrintWriter pw = Util.writer("build/combo.out");
    pw.println(solns);
    pw.close();
  }

  private int nSolns(Combination fc, Combination mc) {
    Set<Combination> validCombinations = validCombinations(fc);
    validCombinations.addAll(validCombinations(mc));
    return validCombinations.size();
  }

  private Set<Combination> validCombinations(Combination c) {
    Set<Combination> cs = new HashSet<Combination>();
    for (int i = -2; i < 3; i++) {
      for (int j = -2; j < 3; j++) {
        for (int k = -2; k < 3; k++) {
          cs.add(new Combination(circular(c._a, i), circular(c._b, j), circular(c._c, k)));
        }
      }
    }
    return cs;
  }

  private int circular(int v, int i) {
    int r = (v + i + _N) % _N;
    if (r == 0) {
      return _N;
    }
    return r;
  }
}

class Combination {
  int _a, _b, _c;

  public Combination(int a, int b, int c) {
    _a = a;
    _b = b;
    _c = c;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Combination that = (Combination) o;
    if (_a != that._a) {
      return false;
    }
    if (_b != that._b) {
      return false;
    }
    if (_c != that._c) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = _a;
    result = 31 * result + _b;
    result = 31 * result + _c;
    return result;
  }

  @Override
  public String toString() {
    return "(" + _a + " " + _b + " " + _c + ")";
  }
}

