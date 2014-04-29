package tc.misc.dp;

import util.StandardProcedures;

// srm 278, div 2, level 3

public class IntegerSequence {
  public int maxSubsequence(int[] numbers) {
    int[] s = longestAscSeq(numbers);
    int[] t = longestAscSeq(StandardProcedures.reverse(numbers));

    int sz = numbers.length, max = 0;
    for (int i = 0; i < sz; i++) {
      if (s[i] + t[sz - i - 1] > max) {
        max = s[i] + t[sz - i - 1];
      }
    }

    return sz - max + 1;
  }

  int[] longestAscSeq(int[] numbers) {
    int sz = numbers.length;
    int[] s = new int[sz];

    for (int i = 0; i < sz; i++) {
      s[i] = 1;
      for (int j = i - 1; j >= 0; j--) {
        if (numbers[i] > numbers[j] && s[j] + 1 > s[i]) {
          s[i] = s[j] + 1;
        }
      }
    }
    return s;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
