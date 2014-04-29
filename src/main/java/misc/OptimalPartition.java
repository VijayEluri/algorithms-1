package misc;

import java.util.ArrayList;
import java.util.Collection;


public class OptimalPartition {
  void partition(int[] a, int k) {
    final int n = a.length;
    final int[][] m = new int[n][k];

    final int[] p = new int[n];
    p[0] = a[0];
    for (int i = 1; i < n; i++) {
      p[i] = p[i - 1] + a[i];
    }

    final int[] s = new int[n];
    s[n - 1] = a[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      s[i] = s[i + 1] + a[i];
    }

    for (int i = 0; i < k; i++) {
      m[0][k] = a[0];
    }

    for (int i = 0; i < n; i++) {
      m[i][0] = p[i];
    }

    final Collection<Integer> indices = new ArrayList<>(n);
    for (int i = 1; i < n; i++) {
      for (int l = 1; l < k; l++) {
        int index = -1;
        m[i][l] = Integer.MAX_VALUE;
        for (int j = i - 1; j >= 1; j--) {
          final int v = Math.max(m[j][l], s[j + 1]);
          if (v < m[i][l]) {
            m[i][l] = v;
            index = j;
          }
        }
        indices.add(index);
      }
    }
  }
}
