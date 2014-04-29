package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Single Round Match 248 Round 1 - Division II, Level Three

public class BitStrings {
  Map<String, Integer> cache = new HashMap<String, Integer>();
  String[] list;

  public int maxStrings(String[] _list, int numZeroes, int numOnes) {
    list = _list;
    return solve(list.length - 1, numZeroes, numOnes);
  }

  int solve(int i, int _0s, int _1s) {
    if (i == -1 || _0s + _1s == 0) {
      return 0;
    }

    String key = i + "|" + _0s + "|" + _1s;
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    int reqd0s = count(list[i], '0');
    int reqd1s = count(list[i], '1');

    int count = 0;
    if (reqd0s <= _0s && reqd1s <= _1s) {
      count = Math.max(solve(i - 1, _0s, _1s), solve(i - 1, _0s - reqd0s, _1s - reqd1s) + 1);
    } else {
      count = solve(i - 1, _0s, _1s);
    }

    cache.put(key, count);
    return count;
  }

  int count(String string, char _c) {
    int count = 0;
    for (char c : string.toCharArray()) {
      if (c == _c) {
        count += 1;
      }
    }
    return count;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
