package topcoder.misc.dp;

// srm 296, div 2, level 3 

import java.util.HashMap;
import java.util.Map;


public class NewAlbum {
  Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
  int noOfSongsPerDisk;

  public int leastAmountOfCDs(int nSongs, int length, int cdCapacity) {
    noOfSongsPerDisk = (cdCapacity + 1) / (length + 1);
    return solve(nSongs);
  }

  private int solve(int nSongs) {
    if (nSongs <= noOfSongsPerDisk) {
      if (nSongs % 13 == 0) {
        return 2;
      } else {
        return 1;
      }
    }

    if (cache.containsKey(nSongs)) {
      return cache.get(nSongs);
    }

    int min = 5001;
    for (int songs = noOfSongsPerDisk; songs > 0; songs--) {
      if (songs % 13 == 0) {
        continue;
      }
      int noOfAblums = solve(nSongs - songs);
      if (noOfAblums + 1 < min) {
        min = noOfAblums + 1;
      }
    }

    cache.put(nSongs, min);
    return min;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
