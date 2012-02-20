package misc;

public class JumpGame {
  int N;
  int[] s;
  
  int minJump(int[] a) {
    N = a.length;
    s = new int[N];
    
    return solve(a, 0);
  }

  private int solve(int[] a, int i) {
    if (i >= N)
      return -1;
    
    int jump = a[i];
    if (jump >= N - i - 1)
      return 1;
    
    if (s[i] != 0)
      return s[i];
    
    int min = -1;
    for (; jump >= 1; jump--) {
      int value = solve(a, i + jump) + 1;
      if (value != -1 && (value < min || min == -1))
        min = value;
    }
    s[i] = min;
      
    return min;
  }
  
  public static void main(String[] args) {
    System.out.println(new JumpGame().minJump(new int[] {2,3,1,1,4}));
  }
}

















