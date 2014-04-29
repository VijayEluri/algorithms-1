package misc;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Given a number N, write a program that returns all possible combinations of numbers that add up
 * to N, as lists. (Exclude the N+0=N)
 *
 * For example, if N=4 return {{1,1,1,1},{1,1,2},{2,2},{1,3}}
 *
 * @author rdsr
 *
 */

public class AddUpToN {
  static Collection<Object> addUpToN(int n, int i) {
    final Collection<Object> r = new ArrayList<Object>();
    if (n < 0) {
      return r;
    }
    if (n == 0) {
      r.add(new ArrayList<Object>());
      return r;
    }

    for (int j = i; j <= n; j++) {
      final Collection<Object> _r = addUpToN(n - j, j);
      for (final Object e : _r) {
        ((Collection<Object>) e).add(j);
      }
      r.addAll(_r);
    }

    return r;
  }

  public static void main(String[] args) {
    System.out.println(addUpToN(4, 1));
  }
}
