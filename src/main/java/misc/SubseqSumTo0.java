package misc;

/**
 * Given an array write a function to print all continuous subsequences in the array which sum of 0.
 * e.g: Input: Array = [-1, -3, 4, 5, 4] output: -1, -3, 4
 *
 */

public class SubseqSumTo0 {
  public static void print(int[] nos) {
    final int[][] sum = new int[nos.length][nos.length];
    for (int i = 0; i < nos.length; i++) {
      sum[i][i] = nos[i];
    }

    for (int i = 0; i < nos.length; i++) {
      for (int j = i + 1; j < nos.length; j++) {
        sum[i][j] = sum[i][j - 1] + nos[j];
      }
    }

    for (int i = 0; i < nos.length; i++) {
      for (int j = i; j < nos.length; j++) {
        if (sum[i][j] == 0) {
          for (int k = i; k <= j; k++) {
            System.out.print(nos[k] + " ");
          }
          System.out.println();
        }
      }
    }
  }

  public static void main(String[] args) {
    print(new int[]{-1, -3, 4, 5, 4, -13, 13});
  }
}
