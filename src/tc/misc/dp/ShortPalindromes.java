package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Topcoder SRM 165, DIV 2, level 3

public class ShortPalindromes {
  Map<String, String> s = new HashMap<String, String>();

  public String shortest(String base) {
    if (s.containsKey(base))
      return s.get(base);

    if (isPalindrome(base)) {
      s.put(base, base);
      return base;
    }

    if (base.length() == 2) {
      if (base.charAt(0) < base.charAt(1))
        s.put(base, base + base.substring(0, 1));
      else
        s.put(base, base.substring(1) + base);
      return s.get(base);
    }

    String x = base.substring(0, 1);
    String y = base.substring(base.length() - 1);

    if (base.charAt(0) == base.charAt(base.length() - 1)) {
      String p = shortest(base.substring(1, base.length() - 1));
      s.put(base, x + p + x);
      return s.get(base);
    }

    String p1 = x + shortest(base.substring(1)) + x;
    String p2 = y + shortest(base.substring(0, base.length() - 1)) + y;

    s.put(base, getPalindrome(p1, p2));
    return s.get(base);
  }

  private boolean isPalindrome(String base) {
    for (int i = 0, j = base.length() - 1; i < j; i++, j--)
      if (base.charAt(i) != base.charAt(j))
        return false;
    return true;
  }

  private String getPalindrome(String p1, String p2) {
    if (p1.length() < p2.length())
      return p1;
    if (p1.length() > p2.length())
      return p2;

    int r = p1.compareTo(p2);
    if (r < 0)
      return p1;
    else
      return p2;
  }
}
// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
