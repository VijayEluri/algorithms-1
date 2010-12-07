package tc.misc.dp;

public class BadSubString {
  long[][] s;
  
  static final int A = 0;
  static final int B = 1;
  static final int C = 2;
  
  long howMany(int length) {
    s = new long[3][length + 1];
     
    s[A][0] = 1; s[A][1] = 1;
    s[B][0] = 1; s[B][1] = 1;
    s[C][0] = 1; s[C][1] = 1;
    
    for (int j = 2; j < length + 1; j++) {
      s[A][j] = s[A][j-1] + s[C][j-1];
      s[B][j] = s[A][j-1] + s[B][j-1] + s[C][j-1];
      s[C][j] = s[A][j-1] + s[B][j-1] + s[C][j-1];
    }
  
    return s[A][length] + s[B][length] + s[C][length];
  }
  
  public static void main(String[] _) {
    System.out.println(new BadSubString().howMany(1));
    System.out.println(new BadSubString().howMany(3));
    System.out.println(new BadSubString().howMany(29));
  }
}
