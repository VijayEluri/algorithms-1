package usaco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import util.FastScanner;


/**
 * ID: rdsr.me1 PROG: ttwo LANG: JAVA
 */
public class ttwo {
  char[][] grid;

  public ttwo(char[][] grid) {
    this.grid = grid;
  }

  private int minutesToMeet() {
    final Queue<State> q = new LinkedList<State>();
    final Map<State, Integer> cache = new HashMap<State, Integer>();

    final int[] f = find('F');
    final int[] c = find('C');

    final State start = new State(DIR.N, f, DIR.N, c);
    q.add(start);
    cache.put(start, 0);

    while (!q.isEmpty()) {
      final State s = q.poll();
      if (goal(s)) {
        return cache.get(s);
      }
      final State n = neighbors(s);
      if (!cache.containsKey(n)) {
        cache.put(n, cache.get(s) + 1);
        q.offer(n);
      }
    }
    return 0;
  }

  private State neighbors(State s) {
    int[] fpNext = next(s.fp, s.fd);
    DIR fdNext = s.fd;
    if (blocked(fpNext)) {
      fdNext = rotate(fdNext);
      fpNext = s.fp; // just rotate don't move forward
    }

    int[] cpNext = next(s.cp, s.cd);
    DIR cdNext = s.cd;
    if (blocked(cpNext)) {
      cdNext = rotate(cdNext);
      cpNext = s.cp; // see above
    }

    return new State(fdNext, fpNext, cdNext, cpNext);
  }

  private static DIR rotate(DIR fd) {
    if (fd == DIR.N) {
      return DIR.E;
    }
    if (fd == DIR.E) {
      return DIR.S;
    }
    if (fd == DIR.S) {
      return DIR.W;
    }
    if (fd == DIR.W) {
      return DIR.N;
    }
    return null;
  }

  private boolean blocked(int[] p) {
    return p[0] >= grid.length ||
        p[0] < 0 ||
        p[1] >= grid[0].length ||
        p[1] < 0 ||
        (grid[p[0]][p[1]] == '*');
  }

  private static int[] next(int[] p, DIR d) {
    if (d == DIR.N) {
      return new int[]{p[0] - 1, p[1]};
    }
    if (d == DIR.E) {
      return new int[]{p[0], p[1] + 1};
    }
    if (d == DIR.S) {
      return new int[]{p[0] + 1, p[1]};
    }
    if (d == DIR.W) {
      return new int[]{p[0], p[1] - 1};
    }
    return null;
  }

  private static boolean goal(State s) {
    return Arrays.equals(s.fp, s.cp);
  }

  private int[] find(char c) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j] == c) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

  public static void main(String[] args)
      throws IOException {
    final char[][] grid = new char[10][10];
    final FastScanner fs = new FastScanner("src/main/resources/usaco/ttwo.in");

    for (int i = 0; i < 10; i++) {
      final String s = fs.next();
      for (int j = 0; j < 10; j++) {
        grid[i][j] = s.charAt(j);
      }
    }
    fs.close();

    final PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File("build/ttwo.out"))));
    final long startTime = System.currentTimeMillis();
    w.println(new ttwo(grid).minutesToMeet());
    w.close();
    final long endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
  }

  static enum DIR {
    N, E, S, W;
  }

  static final class State {
    DIR fd;
    int[] fp;
    DIR cd;
    int[] cp;

    public State(DIR fd, int[] fp, DIR cd, int[] cp) {

      this.fd = fd;
      this.fp = fp;
      this.cd = cd;
      this.cp = cp;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((cd == null) ? 0 : cd.hashCode());
      result = prime * result + Arrays.hashCode(cp);
      result = prime * result + ((fd == null) ? 0 : fd.hashCode());
      result = prime * result + Arrays.hashCode(fp);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final State other = (State) obj;
      if (cd != other.cd) {
        return false;
      }
      if (!Arrays.equals(cp, other.cp)) {
        return false;
      }
      if (fd != other.fd) {
        return false;
      }
      if (!Arrays.equals(fp, other.fp)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "State [fd=" + fd + ", fp=" + Arrays.toString(fp) + ", cd=" + cd + ", cp=" + Arrays.toString(cp) + "]";
    }
  }
}
