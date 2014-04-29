package misc;

import java.util.Iterator;
import util.Pair;


enum Direction {
  N, E, S, W;
}

class SpiralIterator implements Iterator<Pair> {
  private final int r, c;
  int ri, ci;
  int cnt;

  Direction d; // current direction
  int level; // spiral level;

  public SpiralIterator(int r, int c) {
    this.r = r;
    this.c = c;

    d = Direction.E;
    level = 1;
  }

  @Override
  public boolean hasNext() {
    return cnt < r * c;
  }

  @Override
  public Pair next() {
    final Pair p = new Pair(ri, ci);
    switch (d) {
      case E:
        if (ci == c - level) {
          ri += 1;
          d = changeDirection(d);
        } else {
          ci += 1;
        }
        break;

      case S:
        if (ri == r - level) {
          ci -= 1;
          d = changeDirection(d);
        } else {
          ri += 1;
        }
        break;

      case W:
        if (ci == level - 1) {
          ri -= 1;
          d = changeDirection(d);
        } else {
          ci -= 1;
        }
        break;

      case N:
        if (ri == level) {
          ci += 1;
          level += 1;
          d = changeDirection(d);
        } else {
          ri -= 1;
        }
        break;
    }

    cnt += 1;
    return p;
  }

  private static Direction changeDirection(Direction d) {
    switch (d) {
      case E:
        return Direction.S;
      case S:
        return Direction.W;
      case W:
        return Direction.N;
      case N:
        return Direction.E;
      default:
        throw new IllegalStateException();
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

public class FillMatrix {
  static int[][] fill(int r, int c) {
    final int[][] m = new int[r][c];
    int i = 1;
    final Iterator<Pair> iter = new SpiralIterator(r, c);
    while (iter.hasNext()) {
      final Pair p = iter.next();
      m[p.x][p.y] = i;
      i += 1;
    }
    return m;
  }

  public static void main(String[] args) {
    final int r = 3, c = 5;
    final int[][] m = FillMatrix.fill(r, c);
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print(m[i][j] + " ");
      }
      System.out.println();
    }
  }
}
