package misc;

import java.util.Arrays;

class Search {
  /*
   * You are given a circularly sorted array of n integers, 
   * i.e. the array was first sorted and then left or right-shifted 
   * any number of times. Search for a given integer k in the array in O(log n) time.
   *  
   */
  
  int search(int[] nos, int x) {
    int p = cBinarySearch(nos, 1, nos.length - 1, nos[0]);
    int i = Arrays.binarySearch(nos, 0, p + 1, x);
    if (i == -1)
      i = Arrays.binarySearch(nos, p + 1, nos.length, x);
    return i;
  }

  int cBinarySearch(int[] nos, int i, int j, int x) {
    if (i > j)
      return -1;
    
    int m = -1;
    while (j >= i) {
      m = (i + j)/2;
      if (nos[m] > x) i = m + 1;
      if (nos[m] < x) j = m - 1;
    }      
    return m;
  }
}