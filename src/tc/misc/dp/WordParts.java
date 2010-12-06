package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

//Single Round Match 159 Round 1 - Division I, Level Two
//Single Round Match 159 Round 1 - Division II, Level Three

public class WordParts {
  Map<String, Integer> cache = new HashMap<String, Integer>();

  int partcount(String original, String compound) {
    if (compound.equals(""))
      return 0;

    if (original.equals(compound)     ||
        original.startsWith(compound) ||
        original.endsWith(compound))
      return 1;

    if (cache.containsKey(compound))
      return cache.get(compound);

    int parts = -1;
    for (int i = compound.length() - 1; i > 0; i--) {
      if (original.startsWith(compound.substring(0, i)) ||
          original.endsWith(compound.substring(0, i))) {
        int t = partcount(original, compound.substring(i));
        if (t != -1) {
          if (parts == -1)
            parts = t + 1;
          else
            if (t + 1 < parts)
              parts = t + 1;
        }
      }
    }

    cache.put(compound, parts);
    return parts;
  }

  public static void main(String[] _) {
    System.out.println(new WordParts().partcount("ANTIDISESTABLISHMENTARIANISM", "ANTIDISIANISMISM"));
    System.out.println(new WordParts().partcount("ANTIDISESTABLISHMENTARIANISM", "ESTABLISHMENT"));
    System.out.println(new WordParts().partcount("TOPCODERDOTCOM", "TOMTMODERDOTCOM"));
    System.out.println(new WordParts().partcount("HELLO", "HELLOHEHELLOLOHELLO"));
    System.out.println(new WordParts().partcount("DONTFORGETTHEEMPTYCASE", ""));
    System.out.println(new WordParts().partcount("BAAABA", "BAAABAAA"));
    System.out.println(new WordParts().partcount("ABBBAABABBBAABBABBABABBABAABBAABBBBBABBABABBABAABB", "BBBAABABBBAABBABBABABBABAABBAABBBBBABBABABBABAABAA"));
  }
}

