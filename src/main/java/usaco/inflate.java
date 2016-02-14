/*
ID: rdsr.me1
TASK: inflate
LANG: JAVA
*/

package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class inflate {
  public static void main(String[] args) throws Exception {
    final Scanner s = new Scanner(new File("inflate.in"));
    final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

    try {
      int m = s.nextInt();
      int n = s.nextInt();

      int[] minutes = new int[n];
      int[] points = new int[n];

      for (int i = 0; i < n; i++) {
        points[i] = s.nextInt();
        minutes[i] = s.nextInt();
      }

      int[] mp = new int[m + 1];

      for (int i = 0; i < n; i++) {
        for (int j = 0; j <= m; j++) {
          if (minutes[i] <= j) {
            mp[j] = Math.max(mp[j], mp[j - minutes[i]] + points[i]);
          }
        }
      }

      pw.println(mp[m]);

    } finally {
      s.close();
      pw.close();
    }
  }
}
