package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Single Round Match 156 Round 1 - Division II, Level Three

public class WordParts {
  Map<String, Integer> cache = new HashMap<String, Integer>();

  public int partCount(String original, String compound) {
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
        int t = partCount(original, compound.substring(i));
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
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
