package misc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


class Point {
  int i;
  int j;

  public Point(int i, int j) {
    this.i = i;
    this.j = j;
  }

  @Override
  public String toString() {
    return "Point [i=" + i + ", j=" + j + "]";
  }
}

class Board {
  int[][] cells;
  int remainingCells;

  public Board(int[][] cells) {
    this.cells = cells;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (this.cells[i][j] == 0) {
          this.remainingCells += 1;
        }
      }
    }
  }

  public void set(Point pos, int candidate) {
    cells[pos.i][pos.j] = candidate;
    remainingCells -= 1;
  }

  public void unset(Point pos) {
    cells[pos.i][pos.j] = 0;
    remainingCells += 1;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j] == 0) {
          sb.append("| .");
        } else {
          sb.append("| " + cells[i][j]);
        }
      }
      sb.append("|\n");
    }
    return sb.toString();
  }
}

public class Sudoku {
  boolean done = false;

  void solve(Board board) {
    if (goal(board)) {
      System.out.println(board);
      done = true;
    } else {
      final Object[] pc = posAndCandidates(board);
      final Point pos = (Point) pc[0];
      final Collection<Integer> candidates = (Collection<Integer>) pc[1];
      for (final int candidate : candidates) {
        board.set(pos, candidate);
        solve(board);
        board.unset(pos);
        if (done) {
          break;
        }
      }
    }
  }

  private static Collection<Integer> candidates(Point pos, Board board) {
    final Set<Integer> candidates = new HashSet<Integer>();
    for (int i = 1; i < 10; i++) {
      candidates.add(i);
    }

    for (int i = 0; i < 9; i++) {
      if (board.cells[i][pos.j] != 0) {
        candidates.remove(board.cells[i][pos.j]);
      }
    }

    for (int j = 0; j < 9; j++) {
      if (board.cells[pos.i][j] != 0) {
        candidates.remove(board.cells[pos.i][j]);
      }
    }

    final int i0 = pos.i - pos.i % 3;
    final int j0 = pos.j - pos.j % 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.cells[i0 + i][j0 + j] != 0) {
          candidates.remove(board.cells[i0 + i][j0 + j]);
        }
      }
    }
    return candidates;
  }

  private static Object[] posAndCandidates(Board board) {
    Collection<Integer> candidates = null;
    Point pos = null;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board.cells[i][j] == 0) {
          final Point p = new Point(i, j);
          final Collection<Integer> c = candidates(p, board);
          if (candidates == null || c.size() < candidates.size()) {
            candidates = c;
            pos = p;
          }
        }
      }
    }
    return new Object[]{pos, candidates};
  }

  private static boolean goal(Board board) {
    return board.remainingCells == 0;
  }

  public static void main(String[] args) {
    final int[][] cells = {{0, 0, 0, 0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 3, 5, 0, 0, 0}, {0, 0, 0, 6, 0, 0, 0, 7, 0},

        {7, 0, 0, 0, 0, 0, 3, 0, 0}, {0, 0, 0, 0, 4, 0, 8, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0},

        {0, 0, 0, 1, 2, 0, 0, 0, 0}, {0, 8, 0, 0, 0, 0, 0, 4, 0}, {0, 5, 0, 0, 0, 0, 6, 0, 0}};
    final Board board = new Board(cells);
    new Sudoku().solve(board);
  }
}
