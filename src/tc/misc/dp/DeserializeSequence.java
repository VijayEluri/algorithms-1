package tc.misc.dp;

// Single Round Match 230 Round 1 - Division II, Level Three

public class DeserializeSequence {
  public int howMany(String str) {
    int count = 0;
    
    for (int i = 0; i < 7 && i < str.length(); i++) {
      int n = Integer.valueOf(str.substring(0, i+1));
      if (n == 0)
        continue;
      count += solve(n, str.substring(i+1));
    }
    return count;
  }
  /*
   * solve(m, s) returns the total number of nondecreasing 
   * sequences forming the string s, where the smallest 
   * element in each sequence is >= m
   */
  
  private int solve(int m, String str) {
    if (str.length() == 0)
       return 1;

    int count = 0;
    for (int i = 0; i < 7 && i < str.length(); i++) {
      int n = Integer.valueOf(str.substring(0, i+1));
      if (n == 0 || n < m || n > 1000000)
        continue;
      count += solve(n, str.substring(i+1));
    }
    return count;
  }
  
  public static void main(String[] _) {
  System.out.println(new DeserializeSequence().howMany("10010010010010010010010010010010010010010010010010"));
  System.out.println(new DeserializeSequence().howMany("1234"));
  System.out.println(new DeserializeSequence().howMany("00001"));
  System.out.println(new DeserializeSequence().howMany("1000000000000"));
  System.out.println(new DeserializeSequence().howMany("9876543210"));
  System.out.println(new DeserializeSequence().howMany("11111111111111111111111111111111111111111111111111"));
  System.out.println(new DeserializeSequence().howMany("11111111111111111111111119999999999999999999999999"));
  System.out.println(new DeserializeSequence().howMany("0105177821993492100320022188860042353"));
  }
}
