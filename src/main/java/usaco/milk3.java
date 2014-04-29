package usaco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/*
 * ID: rdsr.me1 PROG: milk3 LANG: JAVA
 */
public class milk3 {
  int[] capacity;
  boolean[][][] visited;

  List<Integer> amountInC(int a, int b, int c) {
    capacity = new int[]{a, b, c};
    visited = new boolean[a + 1][b + 1][c + 1];
    final int[] s = new int[]{0, 0, c};
    setVisited(s);
    final List<Integer> r = search(s);
    Collections.sort(r);
    return r;
  }

  private void setVisited(int[] s) {
    visited[s[0]][s[1]][s[2]] = true;
  }

  private boolean visited(int[] s) {
    return visited[s[0]][s[1]][s[2]];
  }

  private List<Integer> search(int[] s) {
    final List<Integer> r = new LinkedList<Integer>();
    if (s[0] == 0) {
      r.add(s[2]);
    }
    final List<Integer> ci = list(0, 1, 0, 2, 1, 0, 1, 2, 2, 0, 2, 1);
    for (int i = 0; i < ci.size() - 1; i += 2) {
      final int[] t = pour(ci.get(i), ci.get(i + 1), s);
      if (!visited(t)) {
        setVisited(t);
        r.addAll(search(t));
      }
    }
    return r;
  }

  private static List<Integer> list(Integer... is) {
    return Arrays.asList(is);
  }

  private int[] pour(int i, int j, int[] s) {
    final int[] t = Arrays.copyOf(s, s.length);
    if (t[j] == capacity[j]) {
      // target container is full
      return t;
    }
    if (t[i] == 0) {
      // source container is empty
      return t;
    }
    if (t[i] <= capacity[j] - t[j]) {
      t[j] = t[j] + t[i];
      t[i] = 0;
      return t;
    }
    if (t[i] > capacity[j] - t[j]) {
      t[i] = t[i] - capacity[j] + t[j];
      t[j] = capacity[j];
      return t;
    }
    return t;
  }

  public static void main(String[] _)
      throws FileNotFoundException {
    final Scanner s = new Scanner(new File("src/main/resources/usaco/milk3.in"));
    final int a = s.nextInt();
    final int b = s.nextInt();
    final int c = s.nextInt();
    s.close();

    final PrintWriter pr = new PrintWriter("build/milk3.out");
    final List<Integer> r = new milk3().amountInC(a, b, c);
    for (int i = 0; i < r.size(); i++) {
      pr.print(r.get(i));
      if (i < r.size() - 1) {
        pr.print(" ");
      }
    }
    pr.println();
    pr.close();
  }
}
