package utility;

public class Lib {
  public static int[] reverse(int[] input) {
    int sz = input.length;
    int[] output = new int[sz];
    
    for (int i = 0; i < sz; i++)
      output[i] = input[sz - i - 1];
    return output;
  }
}
