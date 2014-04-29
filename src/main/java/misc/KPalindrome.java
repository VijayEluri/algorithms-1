package misc;

/**
 * A k-palindrome is a string which transforms into a palindrome on removing at most k characters.
 *
 * Given a string S, and an interger K, print "YES" if S is a k-palindrome; otherwise print "NO".
 * Constraints: S has at most 20,000 characters. 0<=k<=30
 *
 * Sample Test Case#1: Input - abxa 1 Output - YES Sample Test Case#2: Input - abdxa 1 Output - No
 *
 *
 */
public class KPalindrome {
  static boolean isKPalindrome(String s, int k) {
    if (k < 0) {
      return false;
    }
    if (s.length() <= 1) {
      return true;
    }

    if (s.charAt(0) == s.charAt(s.length() - 1)) {
      return isKPalindrome(s.substring(1, s.length() - 1), k);
    } else {
      return isKPalindrome(s.substring(0, s.length() - 1), k - 1) || isKPalindrome(s.substring(1, s.length()), k - 1);
    }
  }

  public static void main(String[] args) {
    System.out.println(isKPalindrome("abxa", 1));
    System.out.println(isKPalindrome("abdxa", 10));
  }
}
