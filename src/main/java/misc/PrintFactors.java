package misc;

public class PrintFactors {
  static void print(int n, StringBuilder acc, int startingFactor) {
    for (int i = startingFactor; i <= Math.sqrt(n) + 1; i++) {
      if (n % i == 0) {
        final int f1 = i;
        final int f2 = n / i;
        if (f1 <= f2) {
          print(f2, acc.append(f1 + "*"), f1);
          acc.delete(acc.length() - 2, acc.length());
        }
      }
    }

    System.out.println(acc.toString() + n);
  }

  public static void main(String[] args) {
    PrintFactors.print(32, new StringBuilder(), 2);
  }
}
