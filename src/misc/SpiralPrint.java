package misc;

public class SpiralPrint {
  static final class Cursor {
    enum Direction {
      RIGHT, DOWN, LEFT, UP;
    }
    
    int i, j;
    int r,c;
    int level;
    int count;
    Direction direction;
    
    public Cursor(int _r, int _c) {
      direction = Direction.RIGHT;
      r = _r;
      c = _c;
      level = 0;
    }
    
    boolean hasNext() {
      return count < r * c;
    }
    
    Cursor next() {
      switch(direction) {
        case RIGHT:
          return stepRight();
        case DOWN:
          return stepDown();
        case LEFT:
          return stepLeft();
        case UP:
          return stepUp();
      }
      return null;
    }

    private Cursor stepUp() {
      // TODO Auto-generated method stub
      return null;
    }

    private Cursor stepLeft() {
      // TODO Auto-generated method stub
      return null;
    }

    private Cursor stepDown() {
      // TODO Auto-generated method stub
      return null;
    }

    private Cursor stepRight() {
      if (level + i < r)
        return new Cursor(
      return null;
    }
  }
  
  public void print(int[][] matrix) {
    int r = matrix.length;
    int c = matrix[0].length;
    
    Cursor cursor = new Cursor(r, c);
    while (cursor.hasNext()) {
      System.out.println(cursor.next());
    }
  }
}
