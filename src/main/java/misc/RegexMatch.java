package misc;

public class RegexMatch {
  static boolean matches(String s, String re) {
    if (s == null) {
      throw new IllegalArgumentException("input string is null");
    }

    if (s.isEmpty()) {
      return re.isEmpty() || (re.length() == 2 && re.charAt(1) == '*');
    }

    if (re.isEmpty()) {
      return false;
    }

    final char r1 = re.charAt(0);
    char r2 = 'a';
    if (re.length() > 1) {
      r2 = re.charAt(1);
    }

    boolean result = false;
    if (r2 == '*') {
      switch (r1) {
        case '.':
          for (int i = 0; i < s.length(); i++) {
            result = matches(s.substring(i), re.substring(2));
            if (result) {
              break;
            }
          }
          break;
        default:
          for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i - 1) == r1) {
              result = matches(s.substring(i), re.substring(2));
              if (result) {
                break;
              }
            }
          }
          break;
      }
    } else {
      switch (r1) {
        case '.':
          return matches(s.substring(1), re.substring(1));
        default:
          if (r1 == s.charAt(0)) {
            return matches(s.substring(1), re.substring(1));
          } else {
            return false;
          }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(RegexMatch.matches("", ".*"));
    System.out.println(RegexMatch.matches("", "."));

    // true
    System.out.println(RegexMatch.matches("a", "."));
    System.out.println(RegexMatch.matches("", ".*"));
    System.out.println(RegexMatch.matches("abc", ".*c"));
    System.out.println(RegexMatch.matches("abc", ".*bc"));
    System.out.println(RegexMatch.matches("abc", ".*abc"));
    System.out.println(RegexMatch.matches("abc", "..."));

    System.out.println(RegexMatch.matches("facebook", "facebo*k"));
    System.out.println(RegexMatch.matches("k", "i*k"));
    System.out.println(RegexMatch.matches("k", "a.*k"));
  }
}
