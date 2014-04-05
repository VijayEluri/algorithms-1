package misc;

public class Match {
    static boolean matches(String s, String re) {
        if (s == null) {
            throw new NullPointerException("input string is null");
        }

        if (re.equals("*")) {
            return true;
        }

        if (s.length() == 0) {
            if (re.length() == 0) {
                return true;
            } else {
                return false;
            }
        }

        if (re.charAt(0) == '?' || s.charAt(0) == re.charAt(0)) {
            return matches(s.substring(1), re.substring(1));
        }

        boolean matched = false;
        if (re.charAt(0) == '*') {
            for (int i = 0; i < s.length() && !matched; i++) {
                matched = matches(s.substring(i), re.substring(1));
            }
        }
        return matched;
    }

    public static void main(String[] args) {
        //false 
        System.out.println(Match.matches("", "?*"));
        System.out.println(Match.matches("", "?"));
        System.out.println(Match.matches("", "*?"));
        
        //true
        System.out.println(Match.matches("a", "?"));
        System.out.println(Match.matches("", "*"));
        System.out.println(Match.matches("abc", "*c"));
        System.out.println(Match.matches("abc", "*bc"));
        System.out.println(Match.matches("abc", "*abc"));
        System.out.println(Match.matches("abc", "???"));
        System.out.println(Match.matches("abc", "*"));
    }
}
