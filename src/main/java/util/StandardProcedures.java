package util;

import java.util.HashSet;
import java.util.Set;


public class StandardProcedures {
  public static int[] copyOf(int[] array, int length) {
    final int[] result = new int[length];
    for (int j = 0; j < length; j++) {
      result[j] = array[j];
    }
    return result;
  }

  public static int[] reverse(int[] input) {
    final int sz = input.length;
    final int[] output = new int[sz];

    for (int i = 0; i < sz; i++) {
      output[i] = input[sz - i - 1];
    }
    return output;
  }

  public static char[][] to2DCharArray(String[] input) {
    final char[][] output = new char[input.length][input[0].length()];
    for (int i = 0; i < input.length; i++) {
      output[i] = input[i].toCharArray();
    }
    return output;
  }

  public static Set<String> permutate(char[] input) {
    return permutate(input, 0);
  }

  private static Set<String> permutate(char[] input, int i) {
    final Set<String> p = new HashSet<String>();

    if (i == input.length) {
      p.add(new String(input));
      return p;
    }

    for (int j = i; j < input.length; j++) {
      p.addAll(permutate(input, i + 1));
      final char c = input[j];
      input[j] = input[0];
      input[0] = c;
      if (input[0] != input[j]) {
        p.addAll(permutate(input, i + 1));
      }
    }

    return p;
  }
}
