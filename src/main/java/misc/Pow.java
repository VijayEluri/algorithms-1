package misc;

public class Pow {
  long pow(int base, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return base;
    }

    if (n % 2 == 0) {
      return pow(base, n / 2) * pow(base, n / 2);
    } else {
      return pow(base, n - 1) * base;
    }
  }

  public static void main(String[] _) {
    System.out.println(new Pow().pow(0, 3));
  }
}
