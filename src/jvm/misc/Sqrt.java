package misc;

public class Sqrt {
  static final double EPSILON = 0.00001;
  double x;

  public double sqrt(double _x) {
    x = _x;
    return sqrt(0, x);
  }

  private double sqrt(double i, double j) {
    double m = 1;
    while (!satisfies(m)) {
      if (m * m > x)
        j = m; 
      else
        i = m;

      m = (i + j)/2;
    }
    return m;
  }

  private boolean satisfies(double m) {
    return Math.abs(m * m - x) <= EPSILON;
  }

  public static void main(String[] _) {
    System.out.println(new Sqrt().sqrt(16l));
  }
}
