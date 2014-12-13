package usaco;

import java.io.PrintWriter;
import java.util.Scanner;
import util.Util;

/*
ID: rdsr.me1
LANG: JAVA
TASK: skidesign
*/
public class skidesign {
  public static void main(String[] args) {
    Scanner s = Util.scanner("src/main/resources/usaco/skidesign.in");
    int N = s.nextInt();
    int[] elevations = new int[N];
    for (int i = 0; i < N; i++) {
      elevations[i] = s.nextInt();
    }

    int maxElevation = max(elevations);
    int minElevation = min(elevations);
    long minCost = Integer.MAX_VALUE;

    for (int b = maxElevation; b >= 17 + minElevation; b--) {
      long cost = 0l;
      for (int elevation : elevations) {
        cost += cost(elevation, b);
      }
      if (cost < minCost) {
        minCost = cost;
      }
    }

    final PrintWriter writer = Util.writer("build/skidesign.out");
    writer.println(minCost);
    writer.close();
  }

  private static long cost(int elevation, int b) {
    if (elevation <= b && elevation >= b - 17) {
      return 0;
    }
    if (elevation > b) {
      long v = elevation - b;
      return v * v;
    }
    if (elevation < b - 17) {
      long v = b - 17 - elevation;
      return v * v;
    }
    throw new RuntimeException("Cannot happen");
  }

  private static int min(int[] xs) {
    int m = Integer.MAX_VALUE;
    for (int x : xs) {
      if (x < m) {
        m = x;
      }
    }
    return m;
  }

  private static int max(int[] elevations) {
    int m = -1;
    for (int elevation : elevations) {
      if (elevation > m) {
        m = elevation;
      }
    }
    return m;
  }
}
