package misc;

import java.util.Arrays;
import java.util.Set;


/**
 * Created by rratti on 19/3/15.
 */
public class SumToS {
  static void findSubset(int[] a, int idx, boolean[] select, int s) {
    if (idx <= a.length - 1) {
      if (s == 0) {
        for (int i = 0; i < a.length; i++) {
          if (select[i]) System.out.print(a[i] + ", ");
        }
        System.out.println();
      }
      select[idx] = true;
      findSubset(a, idx + 1, select, s - a[idx]);
      select[idx] = false;
      findSubset(a, idx + 1, select, s);
    } else if (idx >= a.length) {
      if (s == 0) {
        for (int i = 0; i < a.length; i++) {
          if (select[i]) System.out.print(a[i] + ", ");
        }
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    int[] a = new int[] {2, 3, 5};
    findSubset(a, 0, new boolean[a.length], 2);
  }
}
