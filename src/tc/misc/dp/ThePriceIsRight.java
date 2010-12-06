package tc.misc.dp;

import java.util.Arrays;

public class ThePriceIsRight {
  int[] s; // s[i] longest ordered seq, having prices[i] as the last item
  int[] c; // total no. of ways of achieving s[i] 

  int[] howManyReveals(int[] prices) {
    s = new int[prices.length];
    c = new int[prices.length];
    s[0] = 1;
    c[0] = 1;

    for (int i = 1; i < prices.length; i++) { 
      s[i] = 1; // default
      c[i] = 1;
      for (int j = i - 1; j >= 0; j--) 
        if (prices[i] > prices[j])
          if (s[i] < s[j] + 1) {
            s[i] = s[j] + 1;
            c[i] = 1;
          } else if (s[i] == s[j] + 1)  
            c[i] += 1;
    }

    int longest = 0, count = 0;
    for (int i = 0; i < prices.length; i++)
      if (longest < s[i]) {
        longest = s[i];
      } 

    for (int i = 0; i < prices.length; i++)
      if (longest == s[i])
        count += 1;

    return new int[]{ longest, count };
  }

  public static void main(String[] _) {
    System.out.println(
        Arrays.toString(
            new ThePriceIsRight().howManyReveals(
                new int[] {29,31,73,70,14,5,6,34,53,30,15,86})));
    System.out.println(
        Arrays.toString(
            new ThePriceIsRight().howManyReveals(
                new int[]{10,9,8,7,6,5,4,3,2,1})));       
    System.out.println(
        Arrays.toString(
            new ThePriceIsRight().howManyReveals(
                new int[]{1,2,3,4,5,6,7,8,9,10})));
    System.out.println(
        Arrays.toString(
            new ThePriceIsRight().howManyReveals(
                new int[] {10,20,11,12})));

  }
}
