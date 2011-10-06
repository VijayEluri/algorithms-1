package misc;

public class Match {
  boolean matches(String s, String re) {
    if (s == null)
      throw new NullPointerException("input string is null");
    
    if (s.length() == 0)
      if (re.length() == 0 || (re.length() == 1 && re.charAt(0) == '*'))
        return true;
      else
        return false;
    
    if (re.charAt(0) == '?' || s.charAt(0) == re.charAt(0))
      return matches(s.substring(1), re.substring(1));
    
    boolean matched = false;
    if (re.charAt(0) == '*') {
      for (int i = 0; i < s.length() && !matched; i++) {
        matched = matches(s.substring(i), re.substring(1));
      }
    }
    return matched; 
  }
}
