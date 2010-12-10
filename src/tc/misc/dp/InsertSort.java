package tc.misc.dp;

// Single Round Match 351 Round 1 - Division II, Level Three
// Can be done without any use of Dynamic programming

public class InsertSort {
  public int calcMinimalCost(int[] theArray) {
    int sum = 0;
    for (int a : theArray)
      sum += a;

    int minCost = Integer.MAX_VALUE;
    for (int i = 0; i < theArray.length; i++) {
      int lowest = theArray[i];
      int noCost = theArray[i];
      for (int j = i + 1; j < theArray.length; j++) {
        if (theArray[j] >= lowest) {
          lowest = theArray[j];
          noCost += theArray[j];
        }
      }
      if (minCost > sum - noCost)
        minCost = sum - noCost;
    }
    return minCost;
  }

  public static void main(String[] _) {
    System.out.println(
        new InsertSort().calcMinimalCost(
            new int[]{7, 1, 2, 3}));

    System.out.println(
        new InsertSort().calcMinimalCost(
            new int[]{7, 1, 2, 5}));

    System.out.println(
        new InsertSort().calcMinimalCost(
            new int[]{1, 8, 8, 12, 99}));

    System.out.println(
        new InsertSort().calcMinimalCost(
            new int[]{8, 2, 6, 5, 1, 4}));


    System.out.println(
        new InsertSort().calcMinimalCost(
            new int[]{6, 4, 5, 3, 8, 2, 7, 2, 11, 2, 2}));
  }
}
