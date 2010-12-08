package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Single Round Match 197 Round 1 - Division II, Level Three
// very simple recurrence, but very complex base cases :( 

public class QuickSums2 {
  int n;
  int[][] s;
  int[] numbers;
  Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
  Map<String, Integer> cache = new HashMap<String, Integer>();

  int minSums(String _numbers, int sum) {
    n = _numbers.length();
    numbers = new int[n];

    for (int i = 0; i < n; i++)
      numbers[i] = Character.digit(_numbers.charAt(i), 10);

    for (int i = 0, j = n; i < n; i++, j--)
      mapping.put(j, i);

    return solve(sum, n);
  }

  int solve(int sum, int j) {
    if (sum < 0 || j < 0)
      return -1;

    if (j == 0) {
      if (sum == 0)
        return 0;
      else
      return -1;
    }
    
    if (j == 1) {
      if (numbers[mapping.get(j)] == sum)
        return 0;
      else 
        return -1;
    }
    
    if (j == 2) {
     if (sum == 10 * numbers[mapping.get(j)] + numbers[mapping.get(j - 1)])
       return 0;
    }

    if (cache.containsKey(sum + " " + j))
      return cache.get(sum + " " + j);
  
    int minAdditions = -1;
    
    int m = numbers[mapping.get(j)];
    int x = solve(sum - m, j - 1);
    if (x != -1) {
      if (m == 0) minAdditions = x;
      else minAdditions = x + 1;
    }
    
    int n = 10 * numbers[mapping.get(j)] + numbers[mapping.get(j - 1)];
    int y = solve(sum - n, j - 2);
    if (y != -1) {
      if (minAdditions == -1)
        if (n == 0) minAdditions = y;
        else minAdditions = y + 1;
      else
        if (n == 0) minAdditions = Math.min(minAdditions, y);
        else minAdditions = Math.min(minAdditions, y) + 1;
    }
      
    cache.put(sum + " " + j, minAdditions);
    return minAdditions;
  }


  public static void main(String[] _) {
    System.out.println(new QuickSums2().minSums("99999", 45));
    System.out.println(new QuickSums2().minSums("1110", 3));
    System.out.println(new QuickSums2().minSums("0123456789", 45));
    System.out.println(new QuickSums2().minSums("99999", 100));
    System.out.println(new QuickSums2().minSums("382834", 100));
    System.out.println(new QuickSums2().minSums("9230560001", 71));  
    System.out.println(new QuickSums2().minSums("0000001", 1));

  }
}
