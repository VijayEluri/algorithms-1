package tc.misc.dp;

// srm 281, div 2, level 3

public class BinarySearchable {
  public int howMany(int[] sequence) {
    int cnt = 0;

    for (int i = 0; i < sequence.length; i++) {
      if (isLessThan(sequence, i) && isGreaterThan(sequence, i)) {
        cnt += 1;
      }
    }
    return cnt;
  }

  boolean isLessThan(int[] sequence, int i) {
    int n = sequence[i];
    for (int j = i - 1; j >= 0; j--) {
      if (sequence[j] > n) {
        return false;
      }
    }
    return true;
  }

  boolean isGreaterThan(int[] sequence, int i) {
    int n = sequence[i];
    for (int j = i + 1; j < sequence.length; j++) {
      if (sequence[j] < n) {
        return false;
      }
    }
    return true;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
