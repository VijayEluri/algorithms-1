package tc.misc.dp;

public class BitStrings {
  int maxStrings(
      String[] list, int numZeroes, int numOnes) {
    int[][] s = new int[list.length][3];
    s[0] = make(numZeroes, numOnes, list[0]);
    for (int i = 1; i < list.length; i++) {
      s[i] = make(numZeroes, numOnes, list[i]);
      for (int j = i - 1; j >= 0; j--) { 
        if (s[j][0] != 0 &&
            s[j][1] + s[i][1] <= numZeroes &&
            s[j][2] + s[i][2] <= numOnes &&
            s[j][0] + 1 > s[i][0])
          s[i] = new int[]{s[j][0] + 1, s[j][1] + s[i][1],  s[j][2] + s[i][2]};
      }
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < list.length; i++)
      if (s[i][0] > max)
        max = s[i][0];

    return max;
  }

  private int[] make(
      int numZeroes, int numOnes, String string) {
    int _0s = count(string, '0'); 
    int _1s = count(string, '1');
    
    if (_0s <= numZeroes && _1s <= numOnes)
      return new int[]{1, _0s, _1s};
    else
      return new int[]{0, 0, 0};
  }

  private int count(String string, char _c) {
    int count = 0;
    for (char c : string.toCharArray())
      if (c == _c)
        count += 1;
    return count;
  }

  public static void main(String[] _) {
    System.out.println(
        new BitStrings().maxStrings(
            new String[]{"00", "110", "101"}, 2, 4));

    System.out.println(
        new BitStrings().maxStrings(
            new String[]{"111", "01", "11", "10", "101"}, 3, 9));

    System.out.println(
        new BitStrings().maxStrings(
            new String[]{
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
                "10101010101010101010101010101010101010101010101010",
            "10101010101010101010101010101010101010101010101010"},
            500,
            500));
  }
}
