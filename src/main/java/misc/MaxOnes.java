package misc;

public class MaxOnes {
  static int find(int[][] a, int n) {
    int max = -1;
    int maxRowIdx = 0;
    for (int i = 0; i < n; i++) {
      int first0Idx = findOne(a[i]);
      if (first0Idx > max) {
        maxRowIdx = i;
        max = first0Idx;
      }
    }
    return maxRowIdx;
  }

  // Returns index of 1st 0
  private static int findOne(int[] a) {
    int l = 0;
    int r = a.length - 1;
    if (a.length == 0) {
      return -1;
    }
    if (a[0] == 0) {
      return 0;
    }
    if (a[a.length - 1] == 1) {
      return a.length;
    }
    while (l < r) {
      int m = (l + r) / 2;
      if (a[m] == 1) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    if (a[l] == 1) {
      return l + 1;
    }
    return l;
  }

  public static void main(String[] args) {
    int[][] a = new int[][]{
        {0}
    };
    System.out.println(find(a, a.length));
  }
}
