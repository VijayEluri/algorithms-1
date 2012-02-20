package misc;

import java.util.Arrays;
import java.util.List;

public class SortWithAnagrams {
  public static boolean anagrams(String a, String b) {
    char[] ac = a.toCharArray();
    char[] bc = a.toCharArray();
    
    Arrays.sort(ac);
    Arrays.sort(bc);
    
    return ac.equals(bc);
  }
  
  class EquivString {
    List<String> anagrams;

    public EquivString(List<String> anagrams) {
      this.anagrams = anagrams;
    }
  }
  
  public static void main(String[] args) {
    String[] input = {"Ratan", "helo", "what", "idiot", "atanR", "nooo", "naart", "olhe"};
   
  }
}
