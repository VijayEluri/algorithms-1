  package tc.misc.dp;

// srm 285, div 2, level 3

public class OperationsArrangement {
  public String arrange(String sequence) {
    StringBuilder sb = new StringBuilder();
    int[] s = new int[sequence.length()];

    if (sequence.indexOf("0") != -1) {
      for (char c: sequence.toCharArray())
        sb.append(c).append('*');
      return sb.substring(0, sb.length() - 1);
    }

    sb.append(sequence.charAt(0));
    s[0] = Character.digit(sequence.charAt(0), 10);

    for (int i = 1; i < sequence.length(); i++) {
      int valueUsingProduct = -1;
      int d = Character.digit(sequence.charAt(i), 10);

      int index = sb.lastIndexOf("+");
      if (index == -1)
        valueUsingProduct = s[i-1] * d;
      else {
        index  = index/2;
        valueUsingProduct = s[index] + (s[i-1] - s[index]) * d;
      }

      if (valueUsingProduct <= s[i-1] + d) {
        s[i] = valueUsingProduct;
        sb.append('*').append(d);
      } else {
        s[i] = s[i-1] + d;
        sb.append('+').append(d);
      }
    }

    return sb.toString();
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
