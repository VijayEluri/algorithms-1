package tc.misc.dp;

// Single Round Match 254 Round 1 - Division II, Level Three

public class Piglets {
  public int choose(String trough) {
    if (trough.indexOf('-') == -1) {
      return -1;
    }

    if (trough.charAt(0) == '-') {
      return 0;
    }

    if (trough.charAt(trough.length() - 1) == '-') {
      return trough.length() - 1;
    }

    int si = -1, length = 0, maxLength = 0;
    ;
    for (int i = 1; i < trough.length() - 1; ) {
      if (trough.charAt(i) == '-') {
        int _si = i;
        int _length = 0;
        while (trough.charAt(i++) == '-') {
          _length++;
        }

        if (_length > maxLength) {
          maxLength = _length;
        }

        if (_length > 1) {
          si = _si;
          length = _length;
        }
      } else {
        i++;
      }
    }

    if (maxLength == 1) {
      return trough.indexOf('-');
    }

    return si + position(length);
  }

  private int position(int length) {
    return length - 2;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
