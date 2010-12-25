package tc.misc.dp;

//  Single Round Match 178 Round 1 - Division II, Level Three

public class BadSubstring {
  long[][] s;

  static final int A = 0;
  static final int B = 1;
  static final int C = 2;

  public long howMany(int length) {
    if (length == 0)
      return 1;
    
    s = new long[3][length + 1];
        
    s[A][0] = 1; s[A][1] = 1;
    s[B][0] = 1; s[B][1] = 1;
    s[C][0] = 1; s[C][1] = 1;

    for (int j = 2; j <= length; j++) {
      s[A][j] = s[A][j-1] + s[B][j-1] + s[C][j-1];
      s[B][j] = s[B][j-1] + s[C][j-1];
      s[C][j] = s[A][j-1] + s[B][j-1] + s[C][j-1];
    }

    return s[A][length] + s[B][length] + s[C][length];
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
