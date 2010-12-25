package tc.misc.dp;

public class Books {
  public int sortMoves(String[] titles) {
    int l = titles.length;
    int [] s = new int[l];

    s[0] = 1;
    for (int i = 1; i < l; i++) {
      s[i] = 1;
      for (int j = i - 1; j >= 0; j--)
        if (titles[i].compareTo(titles[j]) >= 0 &&
            s[j] + 1 > s[i])
          s[i] = s[j] + 1;
    }

    int max = 0;  
    for (int i = 0; i < l; i++) {
      int _max = s[i];
      if (_max > max)
        max = _max;
    }

    return l - max;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
