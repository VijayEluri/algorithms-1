package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Anagram {
  Map<Character, Integer> anagram;

  Anagram(String s) {
    anagram = new HashMap<Character, Integer>();

    for (final Character c : s.toCharArray()) {
      if (anagram.containsKey(c)) {
        anagram.put(c, 1 + anagram.get(c));
      } else {
        anagram.put(c, 1);
      }
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((anagram == null) ? 0 : anagram.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Anagram other = (Anagram) obj;
    if (anagram == null) {
      if (other.anagram != null) {
        return false;
      }
    } else if (!anagram.equals(other.anagram)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Anagram [anagram=" + anagram + "]";
  }
}

public class AnagramsList {
  public static void main(String[] args) {
    System.out.println(new Anagram("abc").equals(new Anagram("bac")));

    final Map<Anagram, List<String>> anagramMap = new HashMap<Anagram, List<String>>();
    for (final String str : new String[]{"cab", "cz", "abc", "bca", "zc"}) {
      final Anagram a = new Anagram(str);
      List<String> values = anagramMap.get(a);
      if (values == null) {
        anagramMap.put(a, values = new ArrayList<String>());
      }
      values.add(str);
    }

    System.out.println(anagramMap.values());
  }
}
