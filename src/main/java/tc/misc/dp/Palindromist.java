package tc.misc.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// srm 294, div 2, level 3

public class Palindromist {
  Set<String> words;
  Map<String, String> cache = new HashMap<String, String>();

  public String palindrome(String text, String[] _words) {
    words = new HashSet<String>(Arrays.asList(_words));
    String reverse = new StringBuilder(text).reverse().toString();
    String palindrome1 = text + reverse.substring(1);
    String palindrome2 = text + reverse;

    String phrase1 = makePhrase(palindrome1);
    String phrase2 = makePhrase(palindrome2);

    if (phrase1 == "") {
      return phrase2;
    }
    if (phrase2 == "") {
      return phrase1;
    }
    if (phrase1.compareTo(phrase2) < 0) {
      return phrase1;
    }
    return phrase2;
  }

  private String makePhrase(String palindrome) {
    if (palindrome.length() == 0) {
      return "";
    }

    if (cache.containsKey(palindrome)) {
      return cache.get(palindrome);
    }

    String ans = "";
    for (int i = 1; i < palindrome.length(); i++) {
      String word = palindrome.substring(0, i);
      if (words.contains(word)) {
        String s = makePhrase(palindrome.substring(i, palindrome.length()));
        if (s != "") {
          ans = word + " " + s;
          break;
        }
      }
    }

    if (ans == "" && words.contains(palindrome)) {
      ans = palindrome;
    }
    cache.put(palindrome, ans);
    return ans;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
