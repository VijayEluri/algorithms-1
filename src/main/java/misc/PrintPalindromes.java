package misc;

import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class PrintPalindromes {
  static void printPalindromes(String[] inputArray) {
    final int n = inputArray.length;
    final int m = inputArray[0].length();

    final SortedSet<String> palindromes = new TreeSet<String>();

    for (int i = 0; i < n; i++) {
      allPalindromes(inputArray[i], palindromes);
    }

    for (int j = 0; j < m; j++) {
      allPalindromes(column(inputArray, j), palindromes);
    }

    for (final String dlr : diagonalsLeftToRight(inputArray)) {
      allPalindromes(dlr, palindromes);
    }

    for (final String drl : diagonalsRightToLeft(inputArray)) {
      allPalindromes(drl, palindromes);
    }

    for (String p : palindromes) {
      System.out.println(p);
    }
  }

  static void allPalindromes(String s, Set<String> palindromes) {

  }

  private static Collection<String> diagonalsRightToLeft(String[] inputArray) {
    return null;
  }

  private static Collection<String> diagonalsLeftToRight(String[] inputArray) {
    return null;
  }

  private static String column(String[] inputArray, int j) {
    // TODO Auto-generated method stub
    return null;
  }
}
