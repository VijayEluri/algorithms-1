package misc;

public class CountPalindromes {
  long[][] s;

  long count(String str) {
    int sz = str.length();
    s = new long[sz][sz];

    for (int i = 0; i < sz; i++) {
      s[i][i] = 1;
    }

    for (int i = 1; i < sz - 1; i++) {
      int l = Math.min(i, sz - i - 1);
      for (int j = 1; j <= l; j++) {
        if (str.charAt(i - j) == str.charAt(i + j) && s[i - j + 1][i + j - 1] != 0) {
          s[i - j][i + j] = s[i - j + 1][i + j - 1] + 1;
        }
      }
    }

    long cnt = 0L;
    for (int i = 0; i < sz; i++) {
      for (int j = i; j < sz; j++) {
        cnt += s[i][j];
      }
    }

    return cnt;
  }
}
