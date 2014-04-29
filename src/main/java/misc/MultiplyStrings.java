package misc;

public class MultiplyStrings {
  static String multiply(String a, String b) {
    if (a == null || a.isEmpty()) {
      throw new IllegalArgumentException("Invalid number: " + a);
    }
    if (b == null || b.isEmpty()) {
      throw new IllegalArgumentException("Invalid number: " + b);
    }

    final String s = a.length() < b.length() ? a : b;
    final String t = a.length() < b.length() ? b : a;

    long r = 0l;
    long pow10 = 1l;
    final int n1 = s.length();
    for (int i = 0; i < s.length(); i++) {
      r += pow10 * multiply(s.charAt(n1 - i - 1), t);
      pow10 *= 10;
    }

    return Long.toString(r);
  }

  private static long multiply(char c, String s) {
    final int n = c - '0';
    int carry = 0;
    long r = 0l;
    long pow10 = 1l;
    for (int i = s.length() - 1; i >= 0; i--) {
      final int mi = s.charAt(i) - '0';
      int ri = (mi * n + carry);
      if (ri > 9) {
        carry = ri / 10;
        ri = ri % 10;
      } else {
        carry = 0;
      }
      r += pow10 * ri;
      pow10 *= 10;
    }
    return r;
  }

  public static void main(String[] args) {
    System.out.println(MultiplyStrings.multiply("12", "2"));
  }
}
