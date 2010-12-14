package tc.misc.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberSplit {
  Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
  
  int longestSequence(int start) {
    if (start < 10)
      return 1;
     
    if (cache.containsKey(start))
      return cache.get(start);
    
    List<Integer> successors = getSuccessors2(start);
    
    int longest = 1;
    for (Integer successor : successors.subList(1, successors.size())) {
      int sequenceLength = longestSequence(successor);
      if (sequenceLength + 1 > longest)
        longest = sequenceLength + 1;
    }
    
    cache.put(start, longest);
    return longest;
  }

  List<Integer> getSuccessors2(int n) {
    List<Integer> ans = new ArrayList<Integer>();
    ans.add(n);

    if (n >= 10) {
      for (int div = 10; div < n; div *= 10) {
        int i = n % div;
        for (Integer j : getSuccessors2(n / div))
          ans.add(j * i);
      }
    }
    
    return ans;
  }
  
  public static void main(String[] _) {
    System.out.println(new NumberSplit().longestSequence(97));
    System.out.println(new NumberSplit().longestSequence(234));
    System.out.println(new NumberSplit().longestSequence(876));
    System.out.println(new NumberSplit().longestSequence(99999));
    System.out.println(new NumberSplit().longestSequence(94389));
    System.out.println(new NumberSplit().longestSequence(100000));
  }
}
