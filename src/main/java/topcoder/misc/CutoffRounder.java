package topcoder.misc;

public class CutoffRounder {
  public int round(String num, String cutoff) {
    int decimal = -1;
    for (int i = 0; i < num.length(); i++) {
      if (num.charAt(i) == '.') {
        decimal = i;
      }
    }

    if (decimal == -1) {
      return Integer.valueOf(num);
    }

    String fraction = "0" + num.substring(decimal);
    if (Double.valueOf(fraction) >= Double.valueOf(cutoff)) {
      return Integer.valueOf("0" + num.substring(0, decimal)) + 1;
    }
    return Integer.valueOf("0" + num.substring(0, decimal));
  }
}
