package topcoder.misc.dp;

// Single Round Match 159 Round 1 - Division II, Level Three

public class ThePriceIsRight {
  int[] s; // s[i] longest ordered seq, having prices[i] as the last item
  int[] c; // total no. of ways of achieving s[i] 

  public int[] howManyReveals(int[] prices) {
    s = new int[prices.length];
    c = new int[prices.length];

    for (int i = 0; i < prices.length; i++) {
      s[i] = 1;
      c[i] = 1;
      for (int j = i - 1; j >= 0; j--) {
        if (prices[i] > prices[j]) {
          if (s[j] + 1 > s[i]) {
            s[i] = s[j] + 1;
            c[i] = c[j];
          } else if (s[i] == s[j] + 1) {
            c[i] += c[j];
          }
        }
      }
    }

    int longest = 0, count = 0;
    for (int i = 0; i < prices.length; i++) {
      if (longest < s[i]) {
        longest = s[i];
        count = c[i];
      } else if (longest == s[i]) {
        count += c[i];
      }
    }

    return new int[]{longest, count};
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
