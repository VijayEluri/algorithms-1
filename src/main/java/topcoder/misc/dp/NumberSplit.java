package topcoder.misc.dp;

// SRM 242 Div 2 Level 3

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NumberSplit {
  Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

  public int longestSequence(int start) {
    if (start < 10) {
      return 1;
    }

    if (cache.containsKey(start)) {
      return cache.get(start);
    }

    List<Integer> successors = getSuccessors(start);

    int longest = 1;
    for (Integer successor : successors.subList(1, successors.size())) {
      int sequenceLength = longestSequence(successor);
      if (sequenceLength + 1 > longest) {
        longest = sequenceLength + 1;
      }
    }

    cache.put(start, longest);
    return longest;
  }

  List<Integer> getSuccessors(int n) {
    List<Integer> ans = new ArrayList<Integer>();
    ans.add(n);

    if (n >= 10) {
      for (int div = 10; div <= n; div *= 10) {
        int i = n % div;
        for (Integer j : getSuccessors(n / div)) {
          ans.add(j * i);
        }
      }
    }

    return ans;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
