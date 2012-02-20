package misc;

public class SubsetSum {
  int [] nos;
  int[][] cache;
  
  boolean contains(int[] _nos, int sum) {
    nos = _nos;
    cache = new int[nos.length][sum + 1];
    return solve(0, sum);
  } 
  
  private boolean solve(int i, int sum) {
    if (i == nos.length && sum == 0)
      return true;
    
    if (i == nos.length)
      return false;
  
    if (sum < 0) return false;
        
    if (cache[i][sum] == 1)
      return true;
    if (cache[i][sum] == -1)
      return false;
    
    boolean found = solve(i + 1, sum - nos[i]) || solve(i + 1, sum);
    if (found)
      cache[i][sum] = 1;
    else
      cache[i][sum] = -1;
            
    return found;
  }

  public static void main(String[] args) {
    System.out.println(new SubsetSum().contains(new int[] {6,8,41,36,58,69}, 1));
  }
}
