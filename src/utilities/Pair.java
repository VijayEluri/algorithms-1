package utilities;

public class Pair {
  public int x, y;

  public Pair(int _x, int _y) {
    x = _x; y = _y;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Pair) {
      Pair p = (Pair) o;
      return x == p.x && y == p.y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    return result;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
