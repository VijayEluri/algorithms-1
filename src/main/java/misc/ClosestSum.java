package misc;

public class ClosestSum {
  /* given an array of numbers and a value S, find a sum s 
   * (by summing a subset of the numbers) which is nearest
   *  to S.
   */
  int[][] s;
  int[] nos;

  public int closestSum(int[] _nos, int S) {
    s = new int[_nos.length][S + 1];
    nos = _nos;

    return solve(_nos.length - 1, S);
  }

  private int solve(int i, int S) {
    if (i == -1 || S == 0) {
      return 0;
    }

    if (s[i][S] != 0) {
      return s[i][S];
    }

    int diffIfChosen = 0, v1 = 0, v2 = 0;
    if (nos[i] > S) {
      diffIfChosen = Math.abs(nos[i] - S);
      v1 = nos[i];
    } else {
      v1 = solve(i - 1, S - nos[i]) + nos[i];
      diffIfChosen = Math.abs(v1 - S);
    }

    v2 = solve(i - 1, S);
    int diffIfNotChosen = Math.abs(v2 - S);

    if (diffIfChosen < diffIfNotChosen) {
      s[i][S] = v1;
    } else {
      s[i][S] = v2;
    }

    return s[i][S];
  }

  public static void main(String[] _) {
    System.out.println(new ClosestSum().closestSum(new int[]{6, 2, 3}, 7));
  }
}
