package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// SRM 227 Div 2 Level 3

public class TreePlanting {
  Map<String, Long> cache = new HashMap<String, Long>();

  public long countArrangements(int total, int fancy) {
    return solve(total, fancy, false);
  }

  private long solve(int total, int fancy, boolean used) {
    if (fancy > total) {
      return 0;
    }

    if (fancy == total) {
      if (fancy <= 1) {
        return fancy;
      } else {
        return 0;
      }
    }

    if (fancy == 0) {
      return 1;
    }

    String key = total + " " + fancy + " " + used;
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    long ans = 0;
    if (used) {
      ans = solve(total - 1, fancy, false);
    } else {
      ans = solve(total - 1, fancy - 1, true) + solve(total - 1, fancy, false);
    }

    cache.put(key, ans);
    return ans;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
