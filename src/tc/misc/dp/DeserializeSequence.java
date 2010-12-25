package tc.misc.dp;

public class DeserializeSequence {
  public int howMany(String str) {
    return solve(0, str.substring(0));
  }
  
  /*
   * solve(m, s) returns the total number of nondecreasing 
   * sequences forming the string s, where the smallest 
   * element in each sequence is >= m
   */
  private int solve(int m, String str) {
    if (str.length() == 0)
      return 1;

    str = str.replaceFirst("^0+", "");

    int count = 0;
    for (int i = 0; i < 7 && i < str.length(); i++) {
      int n = Integer.valueOf(str.substring(0, i+1));
      if (n == 0 || n < m || n > 1000000)
        continue;
      count += solve(n, str.substring(i+1));
    }
    return count;
  }

 
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
