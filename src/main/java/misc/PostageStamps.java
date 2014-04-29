package misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PostageStamps {
  Set<Integer> d;

  int smallestPostage(int m, Integer[] denominations) {
    d = new HashSet<Integer>(Arrays.asList(denominations));

    for (int postage = 1; true; postage++) //hopefully the algo, may not run forever 
    {
      if (!canBePlaced(m, postage)) {
        return postage;
      }
    }
  }

  /*
   * m is the no. of stamps, the envelope can hold
   * postage is a value which can or cannot be held
   * 
   * DDP, this algo hasn't been memoized, so for 
   * larger input, it will take a very, very long 
   * time.
   */
  private boolean canBePlaced(int m, int postage) {
    if (m == 0)  // trivial case
    {
      return postage == 0;  // only true when postage itself is 0
    }

    if (d.contains(postage))
    //if postage is one of the denominations, it can obviously be placed
    {
      return true;
    }

    // obviously, now, the value of postage is not 
    // one of the denominations, and the value of 
    // m (the no. of slots on the envelope) is greater
    // than 0.

    // Note, that if the m slots can be filled with
    // the given postage value, the mth slot will 
    // contain one of the denominations. 
    // So we try with each denomination, d(i), at the mth
    // place and try to see whether we can fill the 
    // remaining m - 1 slots with a value of "postage - d(i)".

    for (int denomination : d) {
      if (postage > denomination && canBePlaced(m - 1, postage - denomination)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] _) {
    System.out.println(new PostageStamps().smallestPostage(3, new Integer[]{1, 2, 5}));
    System.out.println(new PostageStamps().smallestPostage(2, new Integer[]{1, 1}));
    System.out.println(new PostageStamps().smallestPostage(3, new Integer[]{3}));
  }
}


