package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

// Single Round Match 254 Round 1 - Division II, Level Three

public class Piglets {
  Map<String, Integer> cache = new HashMap<String, Integer>();

  int choose(String trough) {
    if (trough.indexOf('-') == -1)
      return -1;
    
    if (trough.charAt(0) == '-')
      return 0;

    if (trough.charAt(trough.length() - 1) == '-')
      return trough.length() - 1;
    
    int si = -1, length = 0, maxLength = 0;;
    for (int i = 1; i < trough.length() - 1;) {
      if (trough.charAt(i) == '-') {
        int _si = i;
        int _length = 0;
        while(trough.charAt(i++) == '-')
          _length++;

        if (_length > maxLength)
          maxLength = _length;
        
        if (_length > 1) {
          si = _si;
          length = _length;
        }
      } else
        i++;
    }
    
    if (maxLength == 1)
      return trough.indexOf('-');
    
    return si + position(length);
  }

  private int position(int length) {
    return length - 2;
  }

  public static void main(String[] _) {
    System.out.println(new Piglets().choose("p-p-p"));
    System.out.println(new Piglets().choose("p---p---p--p"));
    System.out.println(new Piglets().choose("p----p"));
    System.out.println(new Piglets().choose("p-----p"));
    System.out.println(new Piglets().choose("p------p-p--p-p"));
    System.out.println(new Piglets().choose("p-p-p-p-p-p---p"));
    System.out.println(new Piglets().choose("p---pp----p--p"));
    System.out.println(new Piglets().choose("p--------"));
    System.out.println(new Piglets().choose("p------------p"));
    System.out.println(new Piglets().choose("p----pp-pppppp"));
  }
}
