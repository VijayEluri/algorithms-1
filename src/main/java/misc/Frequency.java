package misc;

class Frequency {
  interface NextCall {
    int nextCall(int nos[], int n, int i, int m, int j);
  }

  int frequency(int[] nos, int n) {
    return bsearch(nos, n, 0, nos.length - 1, new NextCall() {
      public int nextCall(int[] nos, int n, int i, int m, int j) {
        return bsearch(nos, n, m + 1, j, this);
      }
    }) - bsearch(nos, n, 0, nos.length - 1, new NextCall() {
      public int nextCall(int[] nos, int n, int i, int m, int j) {
        return bsearch(nos, n, i, m - 1, this);
      }
    }) + 1;
  }

  private int bsearch(int[] nos, int n, int i, int j, NextCall nc) {
    if (i > j) {
      return -1;
    }

    int m = (i + j) / 2;

    if (nos[m] < n) {
      return bsearch(nos, n, m + 1, j, nc);
    } else if (nos[m] > n) {
      return bsearch(nos, n, i, m - 1, nc);
    }

    int corner = nc.nextCall(nos, n, i, m, j);
    return corner == -1 ? m : corner;
  }
}