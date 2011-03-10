package utilities;

public class StandardProcedures {
  public static int[] reverse(int[] input) {
    int sz = input.length;
    int[] output = new int[sz];
    
    for (int i = 0; i < sz; i++)
      output[i] = input[sz - i - 1];
    return output;
  }
  
  public static char[][] to2DCharArray(String[] input) {
      char[][] output = new char[input.length][input[0].length()];
      for (int i = 0; i < input.length; i++)
          output[i] = input[i].toCharArray();
      return output;
  }
}
