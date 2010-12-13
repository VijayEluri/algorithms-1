package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Single Round Match 227 Round 1 - Division II, Level Three

public class TreePlanting {
  Map<String, Long> cache = new HashMap<String, Long>();
  
  long countArrangements(int total, int fancy) {
    return solve(total, fancy, false);
  }

  private long solve(int total, int fancy, boolean used) {
    if (fancy > total)
      return 0;
    
    if (fancy == total) {
      if (fancy <= 1)
        return fancy;
      else 
        return 0;
    }
    
    if (fancy == 0)
      return 1;
    
    String key = total + " " + fancy + " " + used;
    if (cache.containsKey(key))
      return cache.get(key);
    
    long ans = 0;
    if (used)
      ans = solve(total - 1, fancy, false);  
    else
      ans = 
        solve(total - 1, fancy - 1, true) +
        solve(total - 1, fancy, false);
    
    cache.put(key, ans);
    return ans;
  }
  
  public static void main(String[] _) {
    System.out.println(new TreePlanting().countArrangements(4, 2));
    System.out.println(new TreePlanting().countArrangements(3, 1));
    System.out.println(new TreePlanting().countArrangements(4, 3));
    System.out.println(new TreePlanting().countArrangements(10, 4));
    System.out.println(new TreePlanting().countArrangements(58, 21));
  }
}
