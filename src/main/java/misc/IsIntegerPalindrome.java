package misc;

import java.util.Arrays;


public class IsIntegerPalindrome {
  static boolean isPalindrome(int a) {
    if (a < 10) {
      return true;
    }
    int length = length(a);
    for (int i = 1; i < length; i+= 2) {
      if (a < 10) {
        return true;
      }
      int left = (int) (a / Math.pow(10, length - i));
      int right = a % 10;
      if (left != right) {
        return false;
      }

      a = (int) (a % Math.pow(10, length - i));
      a = a / 10;
    }
    return true;
  }

  static private int length(int a) {
    int cnt = 0;
    while (a > 0) {
      a = a / 10;
      cnt += 1;
    }
    return cnt;
  }

  public static void main(String[] args) {
    System.out.println(isPalindrome(34543));
  }
}
