package tc.misc.dp;

public class UnsealTheSafe {
  public long countPasswords(int N) {
    long s[][] = new long[N+1][10]; // s[i][j] = all passwords of length i with j as the last digit.

    for (int i = 0; i < 10; i++)
      s[1][i] = 1;

    for (int i = 2; i <= N; i++) {
      s[i][0] = s[i-1][7];
      s[i][1] = s[i-1][2] + s[i-1][4];
      s[i][2] = s[i-1][1] + s[i-1][3] + s[i-1][5];
      s[i][3] = s[i-1][2] + s[i-1][6];
      s[i][4] = s[i-1][1] + s[i-1][5] + s[i-1][7];
      s[i][5] = s[i-1][2] + s[i-1][4] + s[i-1][6] + s[i-1][8];
      s[i][6] = s[i-1][3] + s[i-1][5] + s[i-1][9];
      s[i][7] = s[i-1][4] + s[i-1][8] + s[i-1][0];
      s[i][8] = s[i-1][7] + s[i-1][5] + s[i-1][9];
      s[i][9] = s[i-1][6] + s[i-1][8];
    }

    long ans = 0;
    for (int i = 0; i <= 9; i++)
      ans += s[N][i];
    return ans;
  }
}