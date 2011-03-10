package tc.srm494;

public class Painting {
  String[] picture;
  int N;
  int M;

  public int largestBrush(String[] _picture) {
    picture = _picture;
    N = picture.length;
    M = picture[0].length();
    int size = Math.min(N, M);

    for (int s = 2; s <= size; s++)
      if (!isBrushUsable(s))    // if Brush of size s is not usable?
        return s - 1;
    return size;
  }

  private boolean isBrushUsable(int s) {
    boolean[][] foundBlockCache = new boolean[N][M];
    
    for (int i = 0; i < N; i++)
      for (int j = 0; j < M; j++) {
        if (picture[i].charAt(j) == 'B') {
          if (foundBlockCache[i][j])
            continue;
          
          boolean found = false;
          for (int k = i; k >= 0 && k > i - s && !found; k--)
            for (int l = j; l >= 0 && l > j - s && !found; l--)
              found = foundBlock(k, l, i, j, s, foundBlockCache);
          if (!found)
            return false;
        }
      }
    return true;
  }

  private boolean foundBlock(int oi, int oj, int i, int j, int s, boolean[][] cache) {
    if (oi + s > N || oj + s > M)
      return false;
    
    for (int k = oi; k < oi + s; k++)
      for (int l = oj; l < oj + s; l++)
        if (picture[k].charAt(l) == 'W')
          return false;
    
    // fill cache
    for (int k = oi; k < oi + s; k++)
      for (int l = oj; l < oj + s; l++)
        cache[k][l] = true;
    
    return true;
  }


  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int p1) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    Painting obj;
    int answer;
    obj = new Painting();
    long startTime = System.currentTimeMillis();
    answer = obj.largestBrush(p0);
    long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.println("\t" + p1);
    }
    System.out.println("Your answer:");
    System.out.println("\t" + answer);
    if (hasAnswer) {
      res = answer == p1;
    }
    if (!res) {
      System.out.println("DOESN'T MATCH!!!!");
    } else if ((endTime - startTime) / 1000.0 >= 2) {
      System.out.println("FAIL the timeout");
      res = false;
    } else if (hasAnswer) {
      System.out.println("Match :-)");
    } else {
      System.out.println("OK, but is it right?");
    }
    System.out.println("");
    return res;
  }
  public static void main(String[] args) {
    boolean all_right;
    all_right = true;

    String[] p0;
    int p1;

    // ----- test 0 -----
    p0 = new String[]{"BBBB","BBBB","BBBB","BBBB"};
    p1 = 4;
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"BBBB","BWWB","BWWB","BBBB"};
    p1 = 1;
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"WBBBBB","BBBBBB","BBBBBB","BBBBBB"};
    p1 = 3;
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"BBBB","BBBB","WBBB","BBBB","BBBB","BBBB"};
    p1 = 2;
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new String[]{"WBBBBBWWWWWWWWW","WBBBBBBWWWWWWWW","WBBBBBBBBBBBWWW","WBBBBBBBBBBBWWW","BBBBBBBBBBBBBBB","BBBBBBBBBBBBBBB","BBBBBBBBBBBBBBB","BBBBBBBBWWBBBBB","BBBBBBBBWBBBBBB","WBBBBBBBWBBBBBW","BBBBBBBWWBBBBBW","BBBBBBBWWBBBBBW","BBBBBBWWWBBBBBW","BBBBBWWWWWWWWWW","BBBBBWWWWWWWWWW"};
    p1 = 5;
    all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
    // ------------------

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }
  // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
