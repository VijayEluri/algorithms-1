package tc.misc.dp;

// Single Round Match 351 Round 1 - Division II, Level Three
// here's another version of InsertSort using Dynamic programming.

public class InsertSort2 {
  public int calcMinimalCost(int[] theArray) {
    int sum = 0;
    for (int a : theArray)
      sum += a;
    
    int [] s = new int[theArray.length];
    s[0] = theArray[0];
    
    for (int i = 1; i < theArray.length; i++) {
      s[i] = theArray[i];
      for (int j = i - 1; j >= 0; j--) {
        if (theArray[i] >= theArray[j] &&
            s[j] + theArray[i] > s[i])
          s[i] = s[j] + theArray[i];
      }
    }
    
    int noCost = Integer.MIN_VALUE;
    for (int i = 0; i < s.length; i++)
      if (s[i] > noCost)
        noCost = s[i];
    
    return sum - noCost;
  }
  
  public static void main(String[] _) {
    System.out.println(
        new InsertSort2().calcMinimalCost(
            new int[]{7, 1, 2, 3}));

    System.out.println(
        new InsertSort2().calcMinimalCost(
            new int[]{7, 1, 2, 5}));

    System.out.println(
        new InsertSort2().calcMinimalCost(
            new int[]{1, 8, 8, 12, 99}));

    System.out.println(
        new InsertSort2().calcMinimalCost(
            new int[]{8, 2, 6, 5, 1, 4}));


    System.out.println(
        new InsertSort2().calcMinimalCost(
            new int[]{6, 4, 5, 3, 8, 2, 7, 2, 11, 2, 2}));
  }
}
