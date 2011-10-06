package misc;

class Normalize {
  static String normalize(String exp) {
    if (isPrimitive(exp))
      return exp;

    else {
      String[] subexps = tokenize(exp);
      if (subexps.length == 1)
        return normalize(subexps[0]);
      else
        return "(" + normalize(subexps[0]) + "+" + normalize(subexps[1]) + ")";
    }
  }

  private static String[] tokenize(String exp) {
    String nexp = exp.substring(1, exp.length() - 1);

    int i = 0, brackets = 0;
    if (nexp.charAt(i) == '(') {
      while (i < nexp.length()) {
        if (nexp.charAt(i) == ')') {
          brackets--;
          if (brackets == 0)
            break;
        }

        if (nexp.charAt(i) == '(')
          brackets++;

        i++;
      }
    }
    i += 1; // get to the operator;

    if (i == nexp.length())     
      return new String[]{ nexp };

    return new String[] {
        nexp.substring(0, i),
        nexp.substring(i + 1, nexp.length())
    };
  }

  private static boolean isPrimitive(String exp) {
    return 
    exp.length() == 1 &&
    exp.charAt(0) >= 'a' &&
    exp.charAt(0) <= 'z';   
  }
}