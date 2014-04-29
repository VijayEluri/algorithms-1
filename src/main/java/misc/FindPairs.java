package misc;

import java.util.ArrayList;
import java.util.List;


public class FindPairs {
  /*
   * Given two sorted positive integer arrays A(n) and B(n) (W.L.O.G, let's 
   * say they are decreasingly sorted), we define a set S = {(a,b) | a \in A 
   * and b \in B}. Obviously there are n^2 elements in S. The value of such 
   * a pair is defined as Val(a,b) = a + b. Now we want to get the n pairs 
   * from S with largest values. The tricky part is that we need an O(n) 
   * algorithm
   */

  static final class Pair {
    final int first;
    final int second;

    public Pair(int _first, int _second) {
      first = _first;
      second = _second;
    }

    @Override
    public String toString() {
      return "(" + first + " " + second + ")";
    }
  }

  List<Pair> find(int[] A, int[] B) {
    List<Pair> pairs = new ArrayList<Pair>();
    boolean done = false;

    for (int i = 0; i < A.length - 1 && !done; i++) {
      for (int j = 0; j < B.length && !done; j++) {
        if (A[i] + B[j] > A[i + 1] + B[0]) {
          pairs.add(new Pair(A[i], B[j]));
          if (pairs.size() == A.length) {
            done = true;
          }
        } else {
          break;
        }
      }
    }
    return pairs;
  }

  public static void main(String[] _) {
    System.out.println(new FindPairs().find(new int[]{10, 5, 4, 1, 1, 1}, new int[]{9, 8, 7, 2, 1, 0}));
  }
}
