package misc;

import java.util.List;

public class MultiplyArrays {
  public long multiply(int[] a, int[] b) {
    return asLong(multiply(a, b, 0));
  }


  private long asLong(List<Integer> multiply) {
    return 0;
  }


  List<Integer> multiply(int[]a , int[] b, int i) {
    if (b.length == 1)
      return multiply(a, b[0]);
    
    List<Integer> partial1 = multiply(a, b, i + 1);
    List<Integer> partial2 = multiply(a, b[i]);
   
    partial2.add(0);
    return sum(partial1, partial2);
  }
  
  private List<Integer> sum(List<Integer> partial1, List<Integer> partial2) {
    return null;
  }

  public List<Integer> multiply(int a[], int x) {
    return null;
  }
}
